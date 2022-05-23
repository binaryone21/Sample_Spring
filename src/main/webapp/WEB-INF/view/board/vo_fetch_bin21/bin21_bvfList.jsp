<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Board VO Fetch List</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/search/search_1.css">
    <link rel="stylesheet" href="/layout/table/table_1.css">
    <link rel="stylesheet" href="/layout/paging/paging_1.css">
    <script src="/common/js/vanilla.js"></script>
    <script src="/common/js/bin21.js"></script>
    <script src="/layout/search/search_1.js" defer></script>
    <script src="/board/vo_fetch_bin21/bin21_bvfList.js" defer></script>
</head>
<body style="margin: 40px; height:calc(100% - 80px);">

    <%-- Search 1 --%>
    <article class="search1_com_sc">
        <form id="searchForm" onsubmit="return false">
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>시작 일시</label>
                <input type="date" name="startDate" value="${search.startDate}">
                <input type="time" name="startTime" value="${search.startTime}">
            </div>
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>종료 일시</label>
                <input type="date" name="endDate" value="${search.endDate}">
                <input type="time" name="endTime" value="${search.endTime}">
            </div>
            <div class="search1_com_sc_type" style="margin-right:10px">
                <label>검색 내용</label>
                <select name="searchType">
                    <option value="NAME">이름</option>
                    <option value="AGE">나이</option>
                </select>
                <input type="text" id="searchText" name="searchText" style="width:122px">
            </div>
            <button type="button" id="searchBtn">검색</button>
            <input  type="hidden" id="pagePer"   name="pagePer"   value="${search.pagePer}">
            <input  type="hidden" id="pageNavi"  name="pageNavi"  value="${search.pageNavi}">
            <input  type="hidden" id="pageNo"    name="pageNo"    value="${search.pageNo}">
        </form>
    </article> <%-- Search 1 --%>

    <%-- Table 1 --%>
    <article class="table1_com_tl" style="height: calc(50% - 100px);">
        <div>
            <span>총 게시물 </span>
            <span id="totalNo"></span>
        </div>
        <div class="table1_com_tl_header">
            <div style="width:70px">No</div>
            <div style="width:130px">이름</div>
            <div style="width:130px">나이</div>
            <div style="width:130px">직업</div>
            <div style="width:calc(100% - 460px)">내용</div>
        </div>
        <div class="table1_com_tl_body">
            <table id="boardList">
                <tr>
                    <td style="width:70px"  name="tp_pk"></td>
                    <td style="width:130px" name="tp_age"></td>
                    <td style="width:130px" name="tp_name"></td>
                    <td style="width:130px" name="tp_job"></td>
                    <td style="width:calc(100% - 460px)"></td>
                </tr>
            </table>
        </div>
    </article> <%-- Table 1 --%>

    <%-- Paging 1 --%>
    <article class="paging1_com_pg" id="boardPage">
        <div id="pageForward"> < </div>
        <c:forEach var="num" begin="1" end="5" step="1">
            <div class="pageNumbers"></div>
        </c:forEach>
        <div id="pageBackward"> > </div>
    </article> <%-- Paging 1 --%>

    <%-- View 1 --%>
    <article class="table1_com_tv">
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
                        <td><input type="text" name="tp_pk" class="table1_com_tv_readonly" readonly></td>
                        <th>이름</th>
                        <td><input type="text" name="tp_name" class="table1_com_tv_readonly" readonly></td>
                        <th>나이</th>
                        <td><input type="text" name="tp_age"></td>
                        <th>직업</th>
                        <td><input type="text" name="tp_job"></td>
                    </tr>
                    <tr>
                        <th>등록일시</th>
                        <td><input type="text" name="tp_reg_dt" class="table1_com_tv_readonly" readonly></td>
                        <th>등록자</th>
                        <td><input type="text" name="tp_reg_id" class="table1_com_tv_readonly" readonly></td>
                        <th>수정일시</th>
                        <td><input type="text" name="tp_mod_dt" class="table1_com_tv_readonly" readonly></td>
                        <th>수정자</th>
                        <td><input type="text" name="tp_mod_id" class="table1_com_tv_readonly" readonly></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" id="check" value="O">
        </form>
        <div class="table1_com_btns">
            <button type="button" class="com_btn_insert" id="newBoardBtn">신규</button>
            <button type="button" class="com_btn_update" id="savBoardBtn">저장</button>
            <button type="button" class="com_btn_delete" id="delBoardBtn">삭제</button>
        </div>
    </article> <%-- View 1 --%>
</body>
</html>
