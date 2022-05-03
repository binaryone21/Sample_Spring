/*************************
 * Board Vo Default List *
 *************************/

/*** Global Variable **********************************************************/


    let searchBtn = document.getElementById('searchBtn')            // 검색 버튼


/*** Event ********************************************************************/


    // 페이지 로딩시
    document.addEventListener('DOMContentLoaded', setNavi)

    // 검색 버튼 클릭시
    searchBtn.addEventListener('click', () => { searchBoard(1) })


/*** Function *****************************************************************/


    // 네비게이션 설정
    function setNavi() {
        // 네비에기션 설정
        let listNavis = document.querySelectorAll('.brd_pg_navi')
        let pageNavi = document.getElementById('pageNavi')
        let pageStart = document.getElementById('pageStart')
        let pageEnd = document.getElementById('pageEnd')
        let pageTotal = document.getElementById('pageTotal')

        for(let i=0; i<pageNavi; i++) {
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
                searchBoard(pageStart - pageNavi)
            })
        }

        // > 네비게이션 설정
        if(pageTotal > pageEnd) {
            let pageBackward = document.getElementById('pageBackward')
            pageBackward.addEventListener('click', () => {
                searchBoard(pageStart + pageNavi)
            })
        }
    }

    // 검색조건을 통한 userDmnd 조회
    function searchBoard(pageNo) {
        document.getElementById('pageNo').value = pageNo
        let searchForm = document.getElementById('searchForm')
        searchForm.setAttribute('action', '/board/vo_default/list')
        searchForm.setAttribute('method', 'POST')
        searchForm.submit()
    }