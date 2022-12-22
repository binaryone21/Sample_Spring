<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="totalNo" value="${result.totalNo}"/>

<script>
    (function () {
        let me = window.AMBoardList = {
            startUp : () => {
                me.setVariables();
                me.setEvents();
                me.initialize();
            },
            setVariables : () => {

            },
            setEvents : () => {
                me.boardPagingList.each((idx, div) => {
                    div.onClick = (() => { console.log('클릭') })
                })
            },
            initialize : () => {
                me.setPagingValue(me.searchMap);
            },
            setPagingValue : () => {
                me.pageMap.pageTotal = Math.ceil(me.pageMap.total/me.pageMap.pagePer);
                me.pageMap.pageStart = (((me.pageMap.pageNo-1) / me.pageMap.pageNavi) * me.pageMap.pageNavi) + 1;
                me.pageMap.pageEnd = me.pageMap.pageStart + me.pageMap.pageNavi - 1;

                console.log(me.pageMap);

                return;

                let pagingList = $('#boardPaging > div');
                pagingList.each((idx, div) => {
                    switch(idx) {
                        case 0 :
                            div.value = 1;
                            div.innerText = '<<';
                            break;
                        case 1 :
                            div.value = Math.max(pageMap.pageStart-5, 1);
                            div.innerText = '<<';
                            break;
                        case me.boardPagingList.length-2 :
                            div.value = Math.min(pageMap.pageStart+5, pageMap.pageTotal);
                            div.innerText = '>';
                            break;
                        case me.boardPagingList.length-1 :
                            div.value = pageMap.pageTotal;
                            div.innerText = '>>';
                            break;
                        default :
                            div.value = pageMap.pageStart + idx-2;
                            div.innerText = pageMap.pageStart + idx-2;
                            break;
                    }
                });
                console.log(me.boardPagingList)
            }
        }
    })();
    $(function() {
        AMBoardList.startUp();
    });
</script>
<%-- Table 1 --%>
<article class="table1_com_tl" style="height:300px;">
    <div>
        <span>총 게시물 </span>
        <span>${totalNo}</span>
    </div>
    <div class="table1_com_tl_header">
        <div style="width:70px">No</div>
        <div style="width:130px">이름</div>
        <div style="width:130px">나이</div>
        <div style="width:130px">직업</div>
        <div style="width:calc(100% - 460px)">내용</div>
    </div>

    <div class="table1_com_tl_body">
        <table>
            <colgroup>
                <col style="width:70px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:calc(100% - 460px)">
            </colgroup>
            <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td>${board.personSeq}</td>
                    <td>${board.name}</td>
                    <td>${board.age}</td>
                    <td>${board.job}</td>
                    <td>내용</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</article>

<article id="boardPaging" class="paging1_com_pg">
    <div><<</div>
    <div><</div>
    <c:forEach begin="1" end="${searchMap.pageNavi}">
        <div></div>
    </c:forEach>
    <div>></div>
    <div>>></div>
<%--    <div value="1"> << </div>
    <div value="${searchMap.pageStart-5}"> < </div>
    <c:forEach begin="1" end="${searchMap.pageNavi}">
        <div></div>
    </c:forEach>
    <div>
        <c:forEach
    </div>
    <c:forEach var="num" begin="${search.pageStart}" end="${search.pageEnd}" step="1">
        <c:if test="${num le search.pageTotal}">
            <c:if test="${num eq search.pageNo}">
                <div class="paging1_com_pg_act" value="${num}">${num}</div>
            </c:if>
            <c:if test="${num ne search.pageNo}">
                <div value="${num}">${num}</div>
            </c:if>
        </c:if>
        <c:if test="${num gt search.pageTotal}">
            <div class="paging1_com_pg_none"></div>
        </c:if>
    </c:forEach>
    <div value="${search.pageStart+5}"> > </div>
    <div value="${search.pageTotal}"> >> </div>--%>
</article>