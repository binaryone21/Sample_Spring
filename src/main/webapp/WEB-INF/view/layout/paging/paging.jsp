<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Paging</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/paging/paging_1.css">
    <script src="/layout/paging/paging_1.js" defer></script>
</head>
<body style="margin: 40px; height:calc(100% - 80px)">
    <c:set var="pageStart" value="1"/>
    <c:set var="pageEnd" value="5"/>
    <c:set var="pageTotal" value="3"/>

    <h1>Paging 1</h1>
    <article class="paging1_com_pg">
        <div id="pageFoward"> < </div>
        <c:forEach var="num" begin="${pageStart}" end="${pageEnd}" step="1">
            <c:if test="${num le pageTotal}">
                <div value="${num}">${num}</div>
            </c:if>
            <c:if test="${num gt pageTotal}">
                <div class="paging1_com_pg_none"></div>
            </c:if>
        </c:forEach>
        <div id="pageBackward"> > </div>
    </article>


</body>
</html>
