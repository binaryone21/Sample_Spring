let bin21 = {

    /* bin21.fetch('GET', 'sample/fetch', 'sampleForm', writeSample) */
    fetch: (url, data, func) => {
        if (check()) {
            doFetch(url, pro(data), func)
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
            let dt = document.getElementById(data)
            if(!dt) {
                result += 'ERROR [bin21Fetch] >>', data, 'is not form or not found\n';
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

        function pro(json) {
            return {
                method: "POST",
                body: json,
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

    /** 사용 설명<br>
     * @table_id - [ table ]의 id 값
     * @list - [ table ]에 들어갈 값을 가진 객체 목록
     * @fn - tr에 걸린 OnClick 이벤트의 함수
     * @key - 함수 실행시, 함수에 전달할 tr의 값
     * writeList('shTable', shList, shFn, 'shKey')<br>
     * [ shTable ] 내부에 [ shList ] 의 각 객체들을 tr 별로 담게 된다. 각 tr은 클릭시 [ shFn(shKey) ]를 실행시키게 된다.
     * */
    writeList: function (table_id, list, fn, key) {
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
                let td = table.rows[i].querySelector('*[name="' + idx + '"]')
                if (td != undefined) {
                    td.setAttribute('value', view[idx])
                    td.innerHTML = view[idx]
                }
            }
        }
    },

    /** 구현중
     *
     */
    writeNavi: function (page_id, navis, search, fn) {
        let forward = document.getElementById(navis[0])
        let numbers = document.getElementsByClassName(navis[1])
        let backward = document.getElementById(navis[2])

        // 페이지 네비게이션 설정
        for (let i = 0; i < search.pageNavi; i++) {
            let num = Number(search.pageStart) + i
            if (num <= search.pageTotal) {
                numbers[i].innerHTML = num
                numbers[i].addEventListener('click', () => fn(num))
                numbers[i].setAttribute('class', 'pageNumbers')
                // 현재 페이지
                if (num == search.pageNo) {
                    numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_act')
                }
            } else {
                numbers[i].setAttribute('class', 'pageNumbers paging1_com_pg_none')
            }
        }

        // < 설정
        if (search.pageStart != 1) {
            forward.addEventListener('click', () =>
                fn(Number(search.pageStart) - Number(search.pageNavi)))
        }

        // > 설정
        if (search.pageEnd != search.pageTotal) {
            if (Number(search.pageEnd) + Number(search.pageNavi) <= search.pageTotal) {
                backward.addEventListener('click', () =>
                    fn(Number(search.pageStart) + Number(search.pageNavi)))
            } else {
                backward.addEventListener('click', () =>
                    fn(Number(search.pageTotal)))
            }
        }
    },

    /** 사용설명<br>
     * @table_id - [ table ]의 id 값
     * @view - [ table ] 에 들어갈 값을 가진 객체
     * writeView('shTable', shView)<br>
     * [ shTable ] 내부에서 객체의 변수명과 동일한 name 을 갖고 있는 요소([ name="변수명" ])의 [ value ] 및 [ text ] 를 맵핑 시켜준다.
     */
    writeView: function (table_id, view) {
        for (let idx in view) {
            let target = document.querySelector('table#' + table_id + ' *[name="' + idx + '"]')
            if (target != undefined)
                target.value = view[idx]
        }
    },

    /** 사용설명<br>
     * @prom_id - [ form ]의 id 값
     * clearView('shForm')<br>
     * [ shForm ] 내부에서 [ name="?" ] 를 갖고 있는 요소의 [ value ] 및 [ text ] 를 전부 제거한다.
     */
    clearView: function (table_id) {
        let targets = document.querySelectorAll('table#' + table_id + ' *[name]')
        for (target of targets) {
            target.value = ''
        }
    },

    editable: function (table_id, name, yn) {
        let target = document.querySelector('table#' + table_id + ' *[name="' + name + '"]')
        if (yn) {
            target.removeAttribute('readonly')
            target.removeAttribute('class')
        } else {
            target.setAttribute('readonly', 'readonly')
            target.setAttribute('class', 'table1_com_tv_readonly')
        }
    }
}