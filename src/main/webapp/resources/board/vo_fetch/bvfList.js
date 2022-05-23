


/*** Global Variable **********************************************************/


    let pagePer = 20
    let pageNavi = 5
    let searchText = document.getElementById('searchText')          // 검색 내용
    let searchBtn = document.getElementById('searchBtn')            // 검색 버튼
    let newBoardBtn = document.getElementById('newBoardBtn')        // 신규 버튼
    let savBoardBtn = document.getElementById('savBoardBtn')        // 저장 버튼
    let delBoardBtn = document.getElementById('delBoardBtn')        // 삭제 버튼




/*** Event ********************************************************************/


    // 페이지 로딩시
    document.addEventListener('DOMContentLoaded', startBoard)

    // 검색 엔터시
    searchText.addEventListener('keyup', searchEnter)

    // 검색 버튼 클릭시
    searchBtn.addEventListener('click', () => searchBoardList(1))

    // 신규 버튼 클릭시
    newBoardBtn.addEventListener('click', clearView)

    // 수정 버튼 클릭시
    savBoardBtn.addEventListener('click', saveView)

    // 삭제 버튼 클릭시
    delBoardBtn.addEventListener('click', deleteView)


/*** Function *****************************************************************/


    // 화면 초기 설정
    function startBoard() {
        document.getElementById('pagePer').value = pagePer
        document.getElementById('pageNavi').value = pageNavi
        searchBoardList(1)
        searchText.focus()
    }

    // 검색내용 엔터누를시 조회
    function searchEnter() {
        if(window.event.keyCode == 13) searchBoardList(1)
    }


    /** 여기까지 */

    // 검색조건을 통한 Board 조회
    function searchBoardList(pageNo) {
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
        writeBoardList(map.list)
        writeBoardNavi(map.search)
    }

    // 검색 조건을 통한 Table 입력
    function writeBoardList(list) {
        let table = document.getElementById('boardList')
        while (table.rows[1]) {
            table.deleteRow(0)
        }
        let tr = table.querySelector('tr')
        tr.style.display = 'none'
        for (let i in list) {
            let view = list[i]
            table.insertRow(i)
            table.rows[i].innerHTML = tr.innerHTML
            table.rows[i].addEventListener('click', () => searchBoardView(view['tp_pk']))
            for (let idx in view) {
                let td = table.rows[i].querySelector('*[name="' + idx + '"]')
                if (td != undefined) {
                    td.setAttribute('value', view[idx])
                    td.innerHTML = view[idx]
                }
            }
        }
    }

    // 검색 조건을 통한 view 조회
    function searchBoardView(tp_pk) {
        alert(tp_pk+' view 조회')
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
