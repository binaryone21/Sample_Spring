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
    writeTable: function(header, table_id, list) {
        let table = document.getElementById(table_id)

        let html = "";
        for(let view of list) {
            html += "<tr>";
            for(let i=0; i<header.length; i++) {
                html += "<td>" + view[header[i]] + "</td>";
            }
            html += "</tr>";
        }

        table.innerHTML = html
    },
    writeNavi: function (navis, search) {
        let Forward = document.getElementById(navis[0])
        let Numbers = document.getElementsByClassName(navis[1])
        let Backward = document.getElementById(navis[2])

        // 페이지 네비게이션 설정
        for(let i=0; i<search.pageNavi; i++) {
            let num = Number(search.pageStart) + i
            if(num <= search.pageTotal) {
                Numbers[i].innerHTML = num
                Numbers[i].addEventListener('click', () => searchBoard(num))
                Numbers[i].setAttribute('class', 'pageNumbers')
                // 현재 페이지
                if(num == search.pageNo) {
                    Numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_act')
                }
            } else {
                Numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_none')
            }
        }

        // < 설정
        if(search.pageStart != 1) {
            Forward.addEventListener('click', () =>
                searchBoard(Number(search.pageStart) - Number(search.pageNavi)))
        }

        // > 설정
        if(search.pageEnd != search.pageTotal) {
            if(Number(search.pageEnd) + Number(search.pageNavi) <= search.pageTotal) {
                Backward.addEventListener('click', () =>
                    searchBoard(Number(search.pageStart) + Number(search.pageNavi)))
            } else {
                Backward.addEventListener('click', () =>
                    searchBoard(Number(search.pageTotal)) )
            }
        }
    },
}