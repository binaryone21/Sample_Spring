


/*** Global Variable **********************************************************/


    let pagePer = 20    // 페이지당 보여주고 싶은 게시물 수 (default 20)
    let pageNavi = 5    // 하단 네비게이션 수 (default 5)
    let searchText = document.getElementById('searchText')      // 검색 내용
    let searchBtn = document.getElementById('searchBtn')        // 검색 버튼
    let newBoardBtn = document.getElementById('newBoardBtn')    // 신규 버튼
    let savBoardBtn = document.getElementById('savBoardBtn')    // 저장 버튼
    let delBoardBtn = document.getElementById('delBoardBtn')    // 삭제 버튼


/*** Event ********************************************************************/


    // 페이지 로딩시
    document.addEventListener('DOMContentLoaded', startBoard)

    // 검색 내용 엔터시
    document.addEventListener('keyup', searchEnter)

    // 검색 버튼 클릭시
    searchBtn.addEventListener('click', () => searchList(1))

    // 신규 버튼 클릭시
    newBoardBtn.addEventListener('click', clearView)

    // 저장 버튼 클릭시
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


    // 검색 내용 엔터시 조회
    function searchEnter() {
        if(window.event.keyCode == 13) searchList(1)
    }
    
    // 검색조건을 통한 List 조회
    function searchList(pageNo) {
        document.getElementById('pageNo').value = pageNo
        bin21.fetch("/board/vo_fetch_bin21/list/fetch", form2json('searchForm'), writeList)
    }

    // 검색조건을 통한 List 작성
    function writeList(map) {
        bin21.writeList('boardList', map.list, searchBoardView, 'tp_pk')

        document.getElementById('totalNo').innerText = map.search.totalNo
        // PageNavi 영역
        let navis = ['pageForward', 'pageNumbers', 'pageBackward']
        bin21.writeNavi('boardPage', navis, map.search, searchList)
    }

    // 검색조건을 통한 View 조회
    function searchBoardView(tp_pk) {
        bin21.fetch("/board/vo_fetch_bin21/view/fetch", tp_pk, writeView)
    }

    // 검색조건을 통한 View 작성
    function writeView(view) {
        bin21.writeView('boardView', view)
        bin21.editable('boardView', 'tp_name', false)
        document.getElementById('check').value = "O"
    }

    // View 신규
    function clearView() {
        bin21.clearView('boardView')
        bin21.editable('boardView', 'tp_name', true)
        document.getElementById('check').value = "N"
    }

    // View 저장
    function saveView() {
        console.log(form2json("viewForm"))
        let check = document.getElementById('check').value
        switch(check) {
            case 'N' : bin21.fetch("/board/vo_fetch_bin21/insert/fetch", form2json("viewForm"), checkBoard); break;
            case 'O' : bin21.fetch("/board/vo_fetch_bin21/update/fetch", form2json("viewForm"), checkBoard); break;
        }
    }

    // View 삭제
    function deleteView() {
        let check = document.getElementById('check').value
        let tp_pk = document.querySelector('form#viewForm *[name="tp_pk"]').value
        if(check == 'O')
            bin21.fetch("/board/vo_fetch_bin21/delete/fetch", tp_pk, checkBoard)
        bin21.clearView('boardView')
    }

    // 성공 여부
    function checkBoard(check) {
        bin21.editable('boardView', 'tp_name', false)
        setTimeout(searchList(1), 1000)
    }
