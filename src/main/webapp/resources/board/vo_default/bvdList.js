


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
document.addEventListener('DOMContentLoaded', setNavi)

// 검색 버튼 클릭시
searchBtn.addEventListener('click', searchBoard)


/*** Function *****************************************************************/


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

// 검색조건을 통한 userDmnd 조회
function searchBoard(num) {
    if(typeof num != 'number') { num = 1 }
    pageTarget.setAttribute("name", "pageTarget")
    pageTarget.value = num
    let searchForm = document.getElementById('searchForm')
    searchForm.setAttribute('action', '/board/vo_default/list')
    searchForm.setAttribute('method', 'POST')
    searchForm.submit()
}