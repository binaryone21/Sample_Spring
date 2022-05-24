


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
    searchBtn.addEventListener('click', () => searchList(1))

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
        searchList(1)
        searchText.focus()
    }

    // 검색내용 엔터누를시 조회
    function searchEnter() {
        if(window.event.keyCode == 13) searchList(1)
    }

    // 검색조건을 통한 List 조회
    function searchList(pageNo) {
        document.getElementById('pageNo').value = pageNo
        fetch("/board/vo_fetch/list/fetch", {
            method: "POST",
            body: form2json('searchForm'),
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

    // 검색조건을 통한 List 작성
    function writeBoard(map) {
        writeList(map.list)
        writeNavi(map.search)
    }

    // 검색 조건을 통한 Table 입력
    function writeList(list) {
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
            table.rows[i].addEventListener('click', () => searchView(view['tp_pk']))
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
    function writeNavi(tp_pk) {
        // alert(tp_pk+' view 조회')
    }

    // 검색조건을 통한 View 조회
    function searchView(tp_pk) {
        document.getElementById('pageNo').value = tp_pk
        fetch("/board/vo_fetch_bin21/view/fetch", {
            method: "POST",
            body: tp_pk,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json, text/html",
                credentials: "same-origin",
                mode: "cors"
            }
        })
            .then((res) => res.json())
            .then((view) => writeView(view))
            .catch((err) => console.log("Petch Error >> ", err))
    }

    // 검색조건을 통한 View 작성
    function writeView(view) {
        for (let idx in view) {
            let target = document.querySelector('table#boardView *[name="' + idx + '"]')
            if (target != undefined)
                target.value = view[idx]
        }

        let tp_name = document.querySelector('table#boardView *[name="tp_name"]')
        tp_name.setAttribute('readonly', 'readonly')
        tp_name.setAttribute('class', 'table1_com_tv_readonly')

        document.getElementById('check').value = "O"
    }

    // View 신규
    function clearView() {
        let targets = document.querySelectorAll('table#boardView *[name]')
        for (target of targets) {
            target.value = ''
        }

        let tp_name = document.querySelector('table#boardView *[name="tp_name"]')
        tp_name.removeAttribute('readonly')
        tp_name.removeAttribute('class')

        document.getElementById('check').value = "N"
    }

    // View 저장
    function saveView() {
        let check = document.getElementById('check').value
        if(check == 'N') {
            fetch("/board/vo_fetch_bin21/insert/fetch", {
                method: "POST",
                body: form2json("viewForm"),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json, text/html",
                    credentials: "same-origin",
                    mode: "cors"
                }
            })
                .then((res) => res.json())
                .then((result) => checkBoard(result))
                .catch((err) => console.log("Petch Error >> ", err))
        } else {
            fetch("/board/vo_fetch_bin21/update/fetch", {
                method: "POST",
                body: form2json("viewForm"),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json, text/html",
                    credentials: "same-origin",
                    mode: "cors"
                }
            })
                .then((res) => res.json())
                .then((result) => checkBoard(result))
                .catch((err) => console.log("Petch Error >> ", err))
         }
    }

    // View 삭제
    function deleteView() {
        let check = document.getElementById('check').value
        let tp_pk = document.querySelector('form#viewForm *[name="tp_pk"]').value
        if(check == 'O') {
            fetch("/board/vo_fetch_bin21/delete/fetch", {
                method: "POST",
                body: tp_pk,
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json, text/html",
                    credentials: "same-origin",
                    mode: "cors"
                }
            })
                .then((res) => res.json())
                .then((result) => checkBoard(result))
                .catch((err) => console.log("Petch Error >> ", err))
        }

        let targets = document.querySelectorAll('table#boardView *[name]')
        for (target of targets) {
            target.value = ''
        }

        let tp_name = document.querySelector('table#boardView *[name="tp_name"]')
        tp_name.removeAttribute('readonly')
        tp_name.removeAttribute('class')
    }

    // 성공 여부
    function checkBoard(check) {
        let tp_name = document.querySelector('table#boardView *[name="tp_name"]')
        tp_name.setAttribute('readonly', 'readonly')
        tp_name.setAttribute('class', 'table1_com_tv_readonly')
        setTimeout(searchList(1), 1000)
    }















    /** */

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
