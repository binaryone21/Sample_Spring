<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Board VO Default List</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/search/search01.css">
    <link rel="stylesheet" href="/layout/table/table01.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/common/js/ajaxTool.js"></script>
    <script src="/common/js/bin21Tool.js"></script>
    <script src="/layout/search/search01.js" defer></script>
    <script src="/layout/table/table01.js"></script>
</head>

<script>
    (()=>{
        let me = window.AMBoard = {
            startUp : () => {
                me.setVariables();
                me.setEvents();
                me.initialize();
            },
            setVariables : () => {
                me.pageMap = PageTool.pageMap;
            },
            setEvents : () => {
                document.addEventListener('keyup', () => {
                    if(window.event.keyCode == 13) me.search();
                });
                $('#searchBtn').click(me.search);
            },
            initialize : () => {
                PageTool.makePage({
                    page    : $('#tableArticle01 .page')[0],
                    fn      : (e) => me.getList(e.target.getAttribute('value')),
                    pageMap : me.pageMap
                })
                SearchTool.setDateTime({
                    search  : $('#searchArticle01')[0]
                })
                me.search();
            },
            search : () => {
                me.getCount();
                me.getList(1);
            },
            getCount : () => {
                AjaxTool.post({
                    url     : "/ajax/map/board/listCount.ajax",
                    data    : { 'searchMap' : AjaxTool.serializeObject(($('#searchArticle01 form')[0])) },
                    dataType : 'json',
                    fn      : (result) => {
                        $('#tableArticle01 .total')[0].innerText = result.total;
                        me.pageMap.total = result.total;
                    }
                })
            },
            getList : (pageNo) => {
                if(pageNo == '0') return;
                PageTool.compute(me.pageMap, pageNo);
                PageTool.setPage({
                    page    : $('#tableArticle01 .page')[0],
                    pageMap : me.pageMap
                });
                AjaxTool.post({
                    url      : "/ajax/map/board/list.ajax",
                    data     : {
                        'searchMap' : AjaxTool.serializeObject($('#searchArticle01 form')[0]),
                        'pageMap'   : me.pageMap
                    },
                    dataType : 'json',
                    fn       : (result) => me.setList(result.boardList)
                })
            },
            setList : (boardList) => {
                Bin21Tool.setList({
                    table   : $('#tableArticle01 table')[0],
                    list    : boardList,
                    fn      : (result) => me.getView(result), // me.searchView
                    key     : 'personSeq'
                })
            },
            getView : (personSeq) => {
                console.log('클릭')
                return
                AjaxTool.post({
                    url         : "/ajax/map/board/view.ajax",
                    data        : {'personSeq' : personSeq},
                    dataType    : 'json',
                    fn          : (result) => {console.log(result)} // me.writeView
                })
            },
            writeView : (result) => {
                Bin21Tool.writeView({
                    table   : $('#asd'),
                    view    : result
                })
                Bin21Tool.editable({
                    table   : '',
                    td      : '',
                    editYn  : '',
                })
            },

        }
    })();
    $(function() {
        AMBoard.startUp();
    });
</script>
<body style="margin: 40px; height:calc(100% - 80px);">

    <%-- Search 1 --%>
    <article id="searchArticle01" class="com_search_01">
        <form id="searchForm01">
            <div class="date" style="margin-right:30px">
                <label>시작 일시</label>
                <input type="date" name="startDate" value="2022-04-01<%--${search.startDate}--%>">
                <input type="time" name="startTime" value="00:00<%--${search.startTime}--%>">
            </div>
            <div class="date" style="margin-right:30px">
                <label>종료 일시</label>
                <input type="date" name="endDate" value="2022-12-30<%--${search.endDate}--%>">
                <input type="time" name="endTime" value="23:59<%--${search.endTime}--%>">
            </div>
            <div class="type" style="margin-right:10px">
                <label>검색 내용</label>
                <select name="searchType">
                    <option value="NAME">이름</option>
                    <option value="AGE">나이</option>
                </select>
                <input type="text" name="searchText" style="width:250px">
            </div>
            <button type="button" id="searchBtn">검색</button>
        </form>
    </article>

    <article id="tableArticle01" class="com_table_list_01" style="height:300px;">
        <div>
            <span>총 게시물 </span>
            <span class="total"></span>
        </div>
        <div class="header">
            <div style="width:70px">No</div>
            <div style="width:130px">이름</div>
            <div style="width:130px">나이</div>
            <div style="width:130px">직업</div>
            <div style="width:calc(100% - 460px)">내용</div>
        </div>
        <div class="body">
            <table>
                <tr>
                    <td style="width:70px"   name="personSeq"></td>
                    <td style="width:130px"  name="name"></td>
                    <td style="width:130px"  name="age"></td>
                    <td style="width:130px"  name="job"></td>
                    <td style="width:calc(100% - 460px)"></td>
                </tr>
            </table>
        </div>
        <div class="page"></div>
    </article>
</body>
</html>
