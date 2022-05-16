let bin21 = {

    /* bin21.fetch('GET', 'sample/fetch', 'sampleForm', writeSample) */
    fetch : (url, form_id, func) => {
        if(check()) {
            doFetch(url, pro(form_id), func)
        }
        function check() {
            let result = '';

            /*
            method check
            if(method != 'POST' && method != 'GET') {
                result += 'ERROR [bin21Fetch] >> method is not POST, GET\n'
            }
            */

            /* url check*/
            if(!url) {
                result += 'ERROR [bin21Fetch] >> url is not setting\n'
            }

            /* form check*/
            let form = document.getElementById(form_id)
            if(!form) {
                result += 'ERROR [bin21Fetch] >>', form_id, 'is not form or not found\n';
            }

            /* function check */
            if(!(!func && typeof func != 'function')) {
                result += 'ERROR [bin21Fetch] >>', func, 'is not function or not found\n';
            }

            /* result */
            if(result) {
                return true
            } else {
                console.log(result)
                return false
            }
        }
        function pro(form_id) {
            return {
                method: "POST",
                body: form2json(form_id),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json; charset=UTF-8",
                    credentials: "same-origin",
                    mode: "cors"
                }
            }
        }
        function doFetch(url, pro, func) {
            fetch(url, pro)
                .then((res) => res.json())
                .then((data) => func(data))
                .catch((err) => console.log('ERROR [bin21Fetch] >>', err))
        }
    },
    writeList : function(table_id, list, fn, key) {
        let table = document.getElementById(table_id)
        while(table.rows[1]) {
            table.deleteRow(0)
        }
        let tr = table.querySelector('tr')
        tr.style.display = 'none'
        for(let i in list) {
            let view = list[i]
            table.insertRow(i)
            table.rows[i].innerHTML = tr.innerHTML
            if(fn != null) {
                table.rows[i].addEventListener('click', () => fn(view[key]))
            }
            for(let idx in view) {
                let td = table.rows[i].querySelector('*[name="' + idx +'"]')
                if(td != undefined) {
                    td.setAttribute('value', view[idx])
                    td.innerHTML=view[idx]
                }
            }
        }
    },
    writeNavi: function (page_id, navis, search, fn) {
        let forward = document.getElementById(page_id).getElementById(navis[0])
        let numbers = document.getElementById(page_id).getElementsByClassName(navis[1])
        let backward = document.getElementById(page_id).getElementById(navis[2])

        // 페이지 네비게이션 설정
        for(let i=0; i<search.pageNavi; i++) {
            let num = Number(search.pageStart) + i
            if(num <= search.pageTotal) {
                numbers[i].innerHTML = num
                numbers[i].addEventListener('click', () => fn(num))
                numbers[i].setAttribute('class', 'pageNumbers')
                // 현재 페이지
                if(num == search.pageNo) {
                    numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_act')
                }
            } else {
                numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_none')
            }
        }

        // < 설정
        if(search.pageStart != 1) {
            forward.addEventListener('click', () =>
                fn(Number(search.pageStart) - Number(search.pageNavi)))
        }

        // > 설정
        if(search.pageEnd != search.pageTotal) {
            if(Number(search.pageEnd) + Number(search.pageNavi) <= search.pageTotal) {
                backward.addEventListener('click', () =>
                    fn(Number(search.pageStart) + Number(search.pageNavi)))
            } else {
                backward.addEventListener('click', () =>
                    fn(Number(search.pageTotal)) )
            }
        }
    },
}