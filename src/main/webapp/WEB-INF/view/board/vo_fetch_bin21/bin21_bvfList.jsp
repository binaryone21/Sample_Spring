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
        <form id="searchForm">
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
                <input type="text" name="searchText" style="width:250px">
            </div>
            <button type="button" id="searchBtn">검색</button>
            <input  type="hidden" id="pagePer"   name="pagePer"   value="${search.pagePer}">
            <input  type="hidden" id="pageNavi"  name="pageNavi"  value="${search.pageNavi}">
            <input  type="hidden" id="pageNo"    name="pageNo"    value="${search.pageNo}">
        </form>
    </article>

    <%-- Table 1 --%>
    <article class="table1_com_tb" style="height:300px;">
        <div>
            <span>총 게시물 </span>
            <span id="totalNo"></span>
        </div>
        <div class="table1_com_tb_header">
            <div style="width:70px">No</div>
            <div style="width:130px">이름</div>
            <div style="width:130px">나이</div>
            <div style="width:130px">직업</div>
            <div style="width:calc(100% - 460px)">내용</div>
        </div>

        <div class="table1_com_tb_body">
            <table>
                <colgroup>
                    <col style="width:70px">
                    <col style="width:130px">
                    <col style="width:130px">
                    <col style="width:130px">
                    <col style="width:calc(100% - 460px)">
                </colgroup>
                <tbody id="boardTable">
                    <%-- 테이블 영역입니다. --%>
                </tbody>
            </table>
        </div>
    </article>

    <article class="paging1_com_pg">
        <div id="pageForward"> < </div>
        <c:forEach var="num" begin="1" end="5" step="1">
            <div class="pageNumbers"></div>
        </c:forEach>
        <div id="pageBackward"> > </div>
    </article>
</body>
</html>
