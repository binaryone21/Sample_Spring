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
    <script src="/common/js/ajaxTool.js"></script>
    <script src="/common/js/fetchTool.js"></script>
    <script src="/common/js/bin21Tool.js"></script>
    <script src="/layout/search/search01.js"></script>
    <script src="/layout/table/table01.js"></script>
</head>

<%--
    async, await 어떻게 깔끔하게 처리할 수 있을까
    분기가 나오는 순간부터 fetch 끝까지 존재해야 한다.
    <form onsubmit="return false">
--%>
<script defer>
    (()=>{
        let me = window.FMBoard = {
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
                document.getElementById('btnBoardSearch').addEventListener('click', me.search);
                document.getElementById('btnBoardNew').addEventListener('click', me.viewNew);
                document.getElementById('btnBoardSave').addEventListener('click', me.viewSave);
                document.getElementById('btnBoardDelete').addEventListener('click', me.viewDelete);
            },
            initialize : () => {
                PageTool.makePage({
                    page    : document.querySelector('#tableArticle01 .page'),
                    fn      : (e) => me.getList(e.target.getAttribute('value')),
                    pageMap : me.pageMap
                })
                SearchTool.setDateTime({
                    search  : document.getElementById('searchArticle01')
                })
                me.search();
            },
            search : async () => {
                await me.getCount();
                await me.getList(1);
            },
            getCount : async () => {
                await FetchTool.post({
                    url     : "/fetch/map/board/selectBoardCount.fetch",
                    data    : { 'searchMap' : Bin21Tool.serializeObject(document.querySelector('#searchArticle01 form'))},
                    fn      : (result) => {
                        document.querySelector('#tableArticle01 .total').innerText = result.total;
                        me.pageMap.total = result.total;
                    }
                })
            },
            getList : (pageNo) => {
                if(pageNo == '0') return;
                PageTool.compute(me.pageMap, pageNo);
                PageTool.setPage({
                    page    : document.querySelector('#tableArticle01 .page'),
                    pageMap : me.pageMap
                });
                FetchTool.post({
                    url      : "/fetch/map/board/arraysBoard.fetch",
                    data     : {
                        'searchMap' : Bin21Tool.serializeObject(document.querySelector('#searchArticle01 form')),
                        'pageMap'   : me.pageMap
                    },
                    fn       : (result) => me.setList(result.boardList)
                })
            },
            setList : (boardList) => {
                Bin21Tool.setList({
                    table   : document.querySelector('#tableArticle01 table'),
                    list    : boardList,
                    key     : 'personSeq',
                    fn      : (result) => me.getView(result)
                })
            },
            getView : (personSeq) => {
                FetchTool.post({
                    url         : "/fetch/map/board/selectBoard.fetch",
                    data        : {'boardMap' : {'personSeq' : personSeq}},
                    fn          : (result) => me.setView(result.boardView)
                })
            },
            setView : (result) => {
                Bin21Tool.setView({
                    table   : document.querySelector('#tableArticle02 table'),
                    view    : result
                })
                Bin21Tool.editable({
                    table   : document.querySelector('#tableArticle02 table'),
                    name    : 'name',
                    editYn  : false,
                })
                document.querySelector('#tableArticle02 #check').value = 'O'
            },
            viewNew: () => {
                Bin21Tool.viewClear(document.querySelector('#tableArticle02 table'));
                Bin21Tool.editable({
                    table   : document.querySelector('#tableArticle02 table'),
                    name    : 'name',
                    editYn  : true
                });
                document.querySelector('#tableArticle02 #check').value = 'N';
            },
            viewSave : async () => {
                let check = document.querySelector('#tableArticle02 #check').value
                if(check == 'O') {
                    await FetchTool.post({
                        url         : "/fetch/map/board/updateBoard.fetch",
                        data        : {'boardMap' : Bin21Tool.serializeObject(document.querySelector('#tableArticle02 form'))},
                        fn          : (result) => alert(result.message)
                    });
                } else {
                    await FetchTool.post({
                        url         : "/fetch/map/board/insertBoard.fetch",
                        data        : {'boardMap' : Bin21Tool.serializeObject(document.querySelector('#tableArticle02 form'))},
                        fn          : (result) => alert(result.message)
                    });
                    await Bin21Tool.editable({
                        table   : document.querySelector('#tableArticle02 table'),
                        name    : 'name',
                        editYn  : false
                    });
                }
                await me.getList(me.pageMap.pageNo);
            },
            viewDelete : async () => {
                let check = document.querySelector('#tableArticle02 #check').value
                if(check == 'O') {
                    await FetchTool.post({
                        url         : "/fetch/map/board/deleteBoard.fetch",
                        data        : {'boardMap' : Bin21Tool.serializeObject(document.querySelector('#tableArticle02 form'))},
                        fn          : (result) => alert(result.message) // me.writeView
                    });
                }
                await Bin21Tool.viewClear(document.querySelector('#tableArticle02 table'));
                await me.getList(me.pageMap.pageNo);
            },


        }
    })();
    document.addEventListener('DOMContentLoaded', () => FMBoard.startUp());
</script>
<body style="margin: 40px; height:calc(100% - 80px);">

    <%-- Search 1 --%>
    <article id="searchArticle01" class="com_search_01">
        <form id="searchForm01" >
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
