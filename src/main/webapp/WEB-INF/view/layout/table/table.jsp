<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Table</title>
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/table/table_1.css">
</head>
<body style="margin: 40px; height:calc(100% - 80px)">

    <h1>Table 1</h1>
    <article class="table1_com_tb" style="height:300px; width:100%">
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
                <c:forEach var="num" begin="1" end="20" step="1">
                    <tr>
                        <td>${num}</td>
                        <td>유재석</td>
                        <td>20</td>
                        <td>배우</td>
                        <td>내용</td>
                    </tr>
                </c:forEach>
                <%--
                <c:forEach var="TP_One" items="${TP_List}">
                    <tr>
                        <td>${TP_One.TP_PK}</td>
                        <td>${TP_One.TP_NAME}</td>
                        <td>${TP_One.TP_AGE}</td>
                        <td>${TP_One.TP_JOB}</td>
                        <td>내용</td>
                    </tr>
                </c:forEach>
                --%>
                </tbody>
            </table>
        </div>
    </article>
</body>
</html>
