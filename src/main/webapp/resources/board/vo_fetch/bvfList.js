


/*** Global Variable **********************************************************/


    let naviNo = document.getElementById('naviNo').value            // 하단 네비게이션의 수
    let pageTotal = document.getElementById('pageTotal').value      // 총 페이지의 수
    let pageNo = document.getElementById('pageNo').value            // 현재 페이지의 번호
    let pageStart = document.getElementById('pageStart').value      // 하단 네비게이션 시작 페이지 번호
    let pageEnd = document.getElementById('pageEnd').value          // 하단 네비게이션 끝 페이지 번호
    let pageTarget = document.getElementById('pageTarget')          // 조회하려는 페이지 번호
    let searchBtn = document.getElementById('searchBtn')            // 검색 버튼


/*** Event ********************************************************************/


    // 페이지 로딩시
    document.addEventListener('DOMContentLoaded', startBoard)

    // 검색 버튼 클릭시
    searchBtn.addEventListener('click', searchBoard)


/*** Function *****************************************************************/


    function startBoard() {
        searchBoard(1)
        setNavi()
    }

    // 네비게이션 설정
    function setNavi() {

        // 네비에기션 설정
        let listNavis = document.querySelectorAll('.brd_pg_navi')
        for(let i=0; i<naviNo; i++) {
            if(Number(pageStart) + i <= pageEnd) {
                listNavis[i].addEventListener('click', () => {
                    searchBoard(Number(pageStart) + i)
                })
                // 현재 페이지
                if(Number(pageStart) + i == pageNo) {
                    listNavis[i].setAttribute('class', 'brd_pg_naviAct')
                }
            } else {
                listNavis[i].style.backgroundColor = '#EEEEEE'
            }
        }

        // < 네비에기션 설정
        if(pageStart != 1) {
            let pageForward = document.getElementById('pageFoward')
            pageForward.addEventListener('click', () => {
                searchBoard(pageStart - naviNo)
            })
        }

        // > 네비게이션 설정
        if(pageEnd != pageTotal) {
            let pageBackward = document.getElementById('pageBackward')
            pageBackward.addEventListener('click', () => {
                searchBoard(pageStart + naviNo)
            })
        }
    }

    // 검색조건을 통한 Board 조회
    function searchBoard(num) {
        if (typeof num != 'number') {
            num = 1
        }
        pageTarget.setAttribute("name", "pageTarget")
        pageTarget.value = num

        let json = form2json('searchForm')
        fetch("/board/vo_fetch/list/fetch", {
            method: "POST",
            body: json,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json, text/html",
                credentials: "same-origin",
                mode: "cors"
            }
        })
            .then((res) => res.json())
            .then((list) => writeBoard(list))
            .catch((err) => console.log("Petch Error >> ", err))
    }

    // 검색조건을 통한 Board 입력
    function writeBoard(list) {
        let boardTable = document.getElementById('boardTable')
        let html = ''
        for(let view of list) {
            html += "<tr>";
            html += "<td>" + view.tp_pk + "</td>";
            html += "<td>" + view.tp_name + "</td>";
            html += "<td>" + view.tp_age + "</td>";
            html += "<td>" + view.tp_job + "</td>";
            html += "<td>" + "내용" + "</td>";
            html += "</tr>";
        }
        boardTable.innerHTML = html
    }