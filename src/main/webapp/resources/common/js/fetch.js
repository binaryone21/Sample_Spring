let bin21 = {

    /* bin21.fetch('GET', 'sample/fetch', 'sampleForm', writeSample) */
    fetch : (method, url, form_id, func) => {
        method = (method == null) ? 'POST' : 'GET'

        if(check()) {
            doFetch(url, init())
        }
        check = function() {
            let result = '';

            /* method check */
            if(method != 'POST' && method != 'GET') {
                result += 'ERROR [bin21Fetch] >> method is not POST, GET\n'
            }

            /* url check*/
            if(url == null) {
                result += 'ERROR [bin21Fetch] >> url is not setting\n'
            }

            /* form check*/
            let form = document.getElementById(form_id)
            if(form != null) {
                result += 'ERRER [bin21Fetch] >>', form_id, 'is not form or not found\n';
            }

            /* function check */
            if(func == null || typeof func != 'function') {
                result += 'ERRER [bin21Fetch] >>', func, 'is not function or not found\n';
            }

            /* result */
            if(result == '') {
                return true
            } else {
                console.log(result)
                return false
            }
        },
        init = function() {
            return {
                method: method,
                body: form2json(form_id),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json; charset=UTF-8",
                    credentials: "same-origin",
                    mode: "cors"
                }
            }
        },
        doFetch = function(url, init) {
            fetch(url, init)
                .then((res) => res.json())
                .then((data) => func(data))
                .catch((err) => console.log('ERRER [bin21Fetch] >>', err))
        }
    }
}