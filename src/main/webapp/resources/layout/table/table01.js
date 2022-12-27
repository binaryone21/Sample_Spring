window.PageTool = {
    pageMap : {
        pagePer : 10,
        pageNavi : 10,
        pageSub : 4,    // 4 = [<<, <, ... , >, >>], 2 = [<, ... , >]
        total : 1,
        pageNo : 1
    },
    /**
     *
     * @param pageMap   - pageMap {}
     * @param pageNo    - int
     */
    compute : (pageMap, pageNo) => {
        pageMap.pageNo      = pageNo;
        pageMap.startNo     = (pageNo-1) * pageMap.pagePer;
        pageMap.pageTotal   = Math.ceil(pageMap.total/pageMap.pagePer);
        pageMap.pageStart   = (Math.floor((pageMap.pageNo-1) / pageMap.pageNavi) * pageMap.pageNavi) + 1;
        pageMap.pageEnd     = Math.min(pageMap.pageStart + pageMap.pageNavi - 1, pageMap.pageTotal);
    },
    /**
     * <<, <, ... , >, >>
     * @param params.page       - div
     * @param params.fn         - callBack
     * @param params.pageMap    - pageMap {}
     */
    makePage : (params) => {
        for(let i=0; i<params.pageMap.pageNavi + params.pageMap.pageSub; i++) {
            let div = document.createElement('div');
            div.addEventListener('click', params.fn);
            params.page.appendChild(div);
        }
    },
    /**
     *
     * @param params.page       - div
     * @param params.pageMap    - pageMap {}
     */
    setPage : (params) => {
        let pagingList = params.page.querySelectorAll('div');
        let iiStart    = (params.pageMap.pageSub == 4) ? 0 : 1;
        let iiEnd      = params.pageMap.pageNavi + ((params.pageMap.pageSub == 4) ? 4 : 3);
        for(let ii=iiStart; ii<iiEnd; ii++) {
            let div = pagingList[ii];
            switch(ii) {
                case 0 :
                    div.innerText = '<<';
                    if(params.pageMap.pageNo != '1') {
                        div.setAttribute('value', '1');
                        div.className = ''
                    } else {
                        div.setAttribute("value", "0");
                        div.className = 'off';
                    }
                    break;
                case 1 :
                    div.innerText = '<';
                    if(params.pageMap.pageNo != '1') {
                        div.setAttribute('value', Math.max(params.pageMap.pageStart-5, 1));
                        div.className = ''
                    } else {
                        div.setAttribute("value", "0");
                        div.className = 'off';
                    }
                    break;
                case iiEnd - 2 :
                    div.innerText = '>';
                    if(params.pageMap.pageNo != params.pageMap.pageTotal) {
                        div.setAttribute('value', Math.min(params.pageMap.pageStart+5, params.pageMap.pageTotal));
                        div.className = ''
                    } else {
                        div.setAttribute("value", "0");
                        div.className = 'off';
                    }
                    break;
                case iiEnd - 1 :
                    div.innerText = '>>';
                    if(params.pageMap.pageNo != params.pageMap.pageTotal) {
                        div.setAttribute('value', params.pageMap.pageTotal);
                        div.className = ''
                    } else {
                        div.setAttribute("value", "0");
                        div.className = 'off';
                    }
                    break;
                default :
                    let pageNum = params.pageMap.pageStart + ii - 2;
                    if(pageNum <= params.pageMap.pageTotal) {
                        div.innerText = pageNum;
                        div.setAttribute('value', pageNum);
                        div.className = (pageNum == params.pageMap.pageNo) ? 'on' : '';
                    } else {
                        div.innerText = '';
                        div.setAttribute('value', '0');
                        div.className = 'off';
                    }
                    break;
            }
        }
    }
}