<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Board VO List</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/search/search_1.css">
    <link rel="stylesheet" href="/layout/table/table_1.css">
    <link rel="stylesheet" href="/board/vo/css/paging.css">
    <script src="/board/vo/js/board.js" defer></script>
</head>
<body style="margin: 40px; height:calc(100% - 80px);">

    <%-- Search 1 --%>
    <article class="search1_com_sc">
        <form id="searchForm">
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>시작 일시</label>
                <input type="date" name="startDate" value="${TP_Search.startDate}">
                <input type="time" name="startTime" value="${TP_Search.startTime}">
            </div>
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>종료 일시</label>
                <input type="date" name="endDate" value="${TP_Search.endDate}">
                <input type="time" name="endTime" value="${TP_Search.endTime}">
            </div>
            <div class="search1_com_sc_type" style="margin-right:10px">
                <label>검색 내용</label>
                <select name="searchType" value="${TP_Search.searchType}">
                    <option value="NAME">이름</option>
                    <option value="AGE">나이</option>
                </select>
                <input type="text" style="width:250px" value="${TP_Search.searchText}">
            </div>
            <button type="button" id="searchBtn">검색</button>
            <input  type="hidden" id="naviNo"    name="naviNo"    value="${TP_Search.naviNo}">
            <input  type="hidden" id="pageTotal" name="pageTotal" value="${TP_Search.pageTotal}">
            <input  type="hidden" id="pageNo"    name="pageNo"    value="${TP_Search.pageNo}">
            <input  type="hidden" id="pageStart" name="pageStart" value="${TP_Search.pageStart}">
            <input  type="hidden" id="pageEnd"   name="pageEnd"   value="${TP_Search.pageEnd}">
            <input  type="hidden" id="pageTarget">
        </form>
    </article>

    <%-- Table 1 --%>
    <article class="table1_com_tb" style="height:300px;">
        <div>
            <span>총 게시물</span>
            <span>54</span>
            <%--<span>${TP_Search.totalNo}</span>--%>
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
                <tbody>
                    <c:forEach var="TP_One" items="${TP_List}">
                        <tr>
                            <td>${TP_One.TP_PK}</td>
                            <td>${TP_One.TP_NAME}</td>
                            <td>${TP_One.TP_AGE}</td>
                            <td>${TP_One.TP_JOB}</td>
                            <td>내용</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </article>

    <article class="brd_pg">
        <div id="pageFoward"> < </div>
        <c:forEach var="num" begin="1" end="5" step="1">
            <div class="brd_pg_navi">${num}</div>
        </c:forEach>
        <div id="pageBackward"> > </div>
    </article>
</body>
</html>
