<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Board VO List</title>
    <style>
        * {
            box-sizing: border-box;
        }
    </style>
    <link rel="stylesheet" href="/board/vo/css/search.css">
    <link rel="stylesheet" href="/board/vo/css/table.css">
    <link rel="stylesheet" href="/board/vo/css/paging.css">
    <script src="/board/vo/js/board.js" defer></script>
</head>
<body>

    <%-- 검색 조건 --%>
    <article class="brd_sc">
        <form id="searchForm">
            <div class="brd_sc_date" style="margin-right:30px">
                <label>시작 일시</label>
                <input type="date" name="startDate" value="${TP_Search.startDate}">
                <input type="time" name="startTime" value="${TP_Search.startTime}">
            </div>
            <div class="brd_sc_date" style="margin-right:30px">
                <label>종료 일시</label>
                <input type="date" name="endDate" value="${TP_Search.endDate}">
                <input type="time" name="endTime" value="${TP_Search.endTime}">
            </div>
            <div class="brd_sc_type" style="margin-right:10px">
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

    <%-- 테이블 --%>
    <article class="brd_tb" style="height:300px;">
        <div>
            <span>총 게시물</span>
            <span>${TP_Search.totalNo}</span>
        </div>
        <div class="brd_tb_header">
            <div style="width:70px">No</div>
            <div style="width:130px">이름</div>
            <div style="width:130px">나이</div>
            <div style="width:130px">직업</div>
            <div style="width:calc(100% - 460px)">내용</div>
        </div>

        <div class="brd_tb_body">
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
