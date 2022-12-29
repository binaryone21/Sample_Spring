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
    <script src="/layout/search/search01.js"></script>
    <script src="/layout/table/table01.js"></script>
</head>

<%--
    <form onsubmit="return false">
--%>

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
                $('#btnBoardSearch').click(me.search);
                $('#btnBoardNew').click(me.viewNew);
                $('#btnBoardSave').click(me.viewSave);
                $('#btnBoardDelete').click(me.viewDelete);
            },
            initialize : () => {
                PageTool.makePage({
                    page    : $('#tableArticle01 .page')[0],
                    pageMap : me.pageMap,
                    fn      : (e) => me.getList(e.target.getAttribute('value'))
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
                    url     : "/ajax/map/board/selectBoardCount.ajax",
                    data    : { 'searchMap' : Bin21Tool.serializeObject($('#searchArticle01 form')[0]) },
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
                    url     : "/ajax/map/board/arraysBoard.ajax",
                    data    : {
                        'searchMap' : Bin21Tool.serializeObject($('#searchArticle01 form')[0]),
                        'pageMap'   : me.pageMap
                    },
                    fn      : (result) => me.setList(result.boardList)
                })
            },
            setList : (boardList) => {
                Bin21Tool.setList({
                    table   : $('#tableArticle01 table')[0],
                    list    : boardList,
                    key     : 'personSeq',
                    fn      : (result) => me.getView(result)
                })
            },
            getView : (personSeq) => {
                AjaxTool.post({
                    url     : "/ajax/map/board/selectBoard.ajax",
                    data    : {'boardMap' : {'personSeq' : personSeq}},
                    fn      : (result) => me.setView(result.boardView)
                })
            },
            setView : (result) => {
                Bin21Tool.setView({
                    table   : $('#tableArticle02 table')[0],
                    view    : result
                })
                Bin21Tool.editable({
                    table   : $('#tableArticle02 table')[0],
                    name    : 'name',
                    editYn  : false,
                })
                $('#tableArticle02 #check')[0].value = 'O'
            },
            viewNew : () => {
                Bin21Tool.viewClear($('#tableArticle02 table')[0]);
                Bin21Tool.editable({
                    table   : $('#tableArticle02 table')[0],
                    name    : 'name',
                    editYn  : true
                });
                $('#tableArticle02 #check')[0].value = 'N';
            },
            viewSave : () => {
                let check = $('#tableArticle02 #check')[0].value
                if(check == 'O') {
                    AjaxTool.post({
                        url     : "/ajax/map/board/updateBoard.ajax",
                        data    : {'boardMap' : Bin21Tool.serializeObject($('#tableArticle02 form')[0])},
                        fn      : (result) => alert(result.message)
                    });
                } else {
                    AjaxTool.post({
                        url     : "/ajax/map/board/insertBoard.ajax",
                        data    : {'boardMap' : Bin21Tool.serializeObject($('#tableArticle02 form')[0])},
                        fn      : (result) => alert(result.message)
                    });
                    Bin21Tool.editable({
                        table   : $('#tableArticle02 table')[0],
                        name    : 'name',
                        editYn  : false
                    });
                }
                me.getList(me.pageMap.pageNo);
            },
            viewDelete : () => {
                let check = $('#tableArticle02 #check')[0].value
                if(check == 'O') {
                    AjaxTool.post({
                        url     : "/ajax/map/board/deleteBoard.ajax",
                        data    : {'boardMap' : Bin21Tool.serializeObject($('#tableArticle02 form')[0])},
                        fn      : (result) => alert(result.message) // me.writeView
                    });
                }
                Bin21Tool.viewClear($('#tableArticle02 table')[0]);
                me.getList(me.pageMap.pageNo);
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
        <form id="searchForm01" onsubmit="return false">
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
            <button type="button" id="btnBoardSearch">검색</button>
        </form>
    </article>

    <%-- List 1 --%>
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

    <%-- View 1 --%>
    <article id="tableArticle02" class="com_table_view_01">
        <form id="viewForm">
            <table id="boardView">
                <colgroup>
                    <col style="width:100px">
                    <col style="width:calc(25% - 100px)">
                    <col style="width:100px">
                    <col style="width:calc(25% - 100px)">
                    <col style="width:100px">
                    <col style="width:calc(25% - 100px)">
                    <col style="width:100px">
                    <col style="width:calc(25% - 100px)">
                </colgroup>
                <tbody>
                    <tr>
                        <th>No</th>
                        <td><input type="text" name="personSeq" class="readonly" readonly></td>
                        <th>이름</th>
                        <td><input type="text" name="name" class="readonly" readonly></td>
                        <th>나이</th>
                        <td><input type="text" name="age" placeholder="-"></td>
                        <th>직업</th>
                        <td><input type="text" name="job" placeholder="-"></td>
                    </tr>
                    <tr>
                        <th>등록일시</th>
                        <td><input type="text" name="regDate" class="readonly" readonly></td>
                        <th>등록자</th>
                        <td><input type="text" name="regID" class="readonly" readonly></td>
                        <th>수정일시</th>
                        <td><input type="text" name="modDate" class="readonly" readonly></td>
                        <th>수정자</th>
                        <td><input type="text" name="modID" class="readonly" readonly></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" id="check" value="O">
            <div class="buttons">
                <button type="button" class="insert" id="btnBoardNew">신규</button>
                <button type="button" class="update" id="btnBoardSave">저장</button>
                <button type="button" class="delete" id="btnBoardDelete">삭제</button>
            </div>
        </form>
    </article>
</body>
</html>
