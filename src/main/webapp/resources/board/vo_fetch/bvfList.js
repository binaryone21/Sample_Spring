


/*** Global Variable **********************************************************/


    let searchBtn = document.getElementById('searchBtn')            // 검색 버튼
    let pagePer = 20
    let pageNavi = 5


/*** Event ********************************************************************/


    // 페이지 로딩시
    document.addEventListener('DOMContentLoaded', startBoard)

    // 검색 버튼 클릭시
    searchBtn.addEventListener('click', () => searchBoard(1))


/*** Function *****************************************************************/


    // 화면 초기 설정
    function startBoard() {
        setBoard()
        searchBoard(1)
    }


    // 화면 페이징 정보 설정
    function setBoard() {
        document.getElementById('pagePer').value = pagePer
        document.getElementById('pageNavi').value = pageNavi
    }
    
    // 검색조건을 통한 Board 조회
    function searchBoard(pageNo) {
        document.getElementById('pageNo').value = pageNo

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
            .then((map) => writeBoard(map))
            .catch((err) => console.log("Petch Error >> ", err))
    }

    // 검색조건을 통한 Board 입력
    function writeBoard(map) {
        writeBoardTable(map.list)
        writeBoardNavi(map.search)
    }

    // 검색 조건을 통한 Table 입력
    function writeBoardTable(list) {
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

    // 네비게이션 설정
    function writeBoardNavi(search) {
        let pageNumbers = document.getElementsByClassName('pageNumbers')
        let pageForward = document.getElementById('pageForward')
        let pageBackward = document.getElementById('pageBackward')

        // 페이지 네비게이션 설정
        for(let i=0; i<search.pageNavi; i++) {
            let num = Number(search.pageStart) + i
            if(num <= search.pageTotal) {
                console.log(pageNumbers[i])
                pageNumbers[i].innerHTML = num
                pageNumbers[i].addEventListener('click', () => searchBoard(num))
                pageNumbers[i].setAttribute('class', 'pageNumbers')
                // 현재 페이지
                if(num == search.pageNo) {
                    pageNumbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_act')
                }
            } else {
                pageNumbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_none')
            }
        }

        // < 설정
        if(search.pageStart != 1) {
            pageForward.addEventListener('click', () =>
                searchBoard(Number(search.pageStart) - Number(search.pageNavi)))
        }

        // > 설정
        if(search.pageEnd != search.pageTotal) {
            if(Number(search.pageEnd) + Number(search.pageNavi) <= search.pageTotal) {
                pageBackward.addEventListener('click', () =>
                    searchBoard(Number(search.pageStart) + Number(search.pageNavi)))
            } else {
                pageBackward.addEventListener('click', () =>
                    searchBoard(Number(search.pageTotal)) )
            }
        }
    }

/*

        for(let i=0; i<search.pageNavi; i++) {
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
    }*/
