<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/search/search_1.css">
    <script src="/layout/search/search_1.js" defer></script>
</head>
<body style="margin: 40px; height:calc(100% - 80px)">

    <h1>Search 1</h1>
    <article class="search1_com_sc">
        <form id="searchForm">
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>시작 일시</label>
                <input type="date" class="dateTimes" name="startDate" value="2022-01-01">  <%--value="${TP_Search.startDate}"--%>
                <input type="time" class="dateTimes" name="startTime" value="00:00">       <%--value="${TP_Search.startTime}"--%>
            </div>
            <div class="search1_com_sc_date" style="margin-right:30px">
                <label>종료 일시</label>
                <input type="date" class="dateTimes" name="endDate" value="2022-12-31">    <%--value="${TP_Search.endDate}"--%>
                <input type="time" class="dateTimes" name="endTime" value="23:59">         <%--value="${TP_Search.endTime}"--%>
            </div>
            <div class="search1_com_sc_type" style="margin-right:10px">
                <label>검색 내용</label>
                <select name="searchType" value="">                     <%--value="${TP_Search.searchType}"--%>
                    <option value="NAME">이름</option>
                    <option value="AGE">나이</option>
                </select>
                <input type="text" style="width:250px" value="">                <%--value="${TP_Search.searchText}"--%>
            </div>
            <button type="button" id="searchBtn">검색</button>
            <input  type="hidden" id="naviNo"    name="naviNo"    value=""> <%--value="${TP_Search.naviNo}"--%>
            <input  type="hidden" id="pageTotal" name="pageTotal" value=""> <%--value="${TP_Search.pageTotal}--%>
            <input  type="hidden" id="pageNo"    name="pageNo"    value=""> <%--value="${TP_Search.pageNo}--%>
            <input  type="hidden" id="pageStart" name="pageStart" value=""> <%--value="${TP_Search.pageStart}--%>
            <input  type="hidden" id="pageEnd"   name="pageEnd"   value=""> <%--value="${TP_Search.pageEnd}--%>
            <input  type="hidden" id="pageTarget">
        </form>
    </article>






</body>
</html>
