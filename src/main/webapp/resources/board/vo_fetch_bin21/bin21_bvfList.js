


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
        bin21.fetch("/board/vo_fetch_bin21/list/fetch", 'searchForm', writeBoard)
    }

    // 검색조건을 통한 Board 입력
    function writeBoard(map) {
        // Table header 영역
        let header = ['tp_pk', 'tp_name', 'tp_age', 'tp_job', '']
        bin21.writeTable(header, 'boardTable', map.list)
        
        // PageNavi 영역
        let navis = ['pageForward', 'pageNumbers', 'pageBackward']
        bin21.writeNavi(navis, map.search)
    }
