window.Bin21Tool = {
    /**
     *
     * @param form
     * @returns {{}}
     */
    serializeObject : (form) => {
        if(form == null) {
            console.log('ERROR [Bin21Tool serializeObject] >> is not form or not found');
            return;
        }

        let data = new FormData(form);
        let json = {};
        for(let [key, value] of data) {
            json[key] = value
        }

        return json;
    },
    /**
     *
     * @param params.table  - table
     * @param params.list   - boardList
     * @param params.fn     - callBack
     * @param params.key    - fn(key)
     */
    setList : (params) => {
        while(params.table.rows[1]) {
            params.table.deleteRow(0);
        }
        let originTr = params.table.querySelector('tr');
        originTr.style.display = 'none';
        for(let [idx, view] of Object.entries(params.list)) {
            params.table.insertRow(idx);
            let tr = params.table.rows[idx];
            tr.innerHTML = originTr.innerHTML;
            if(params.fn != null) {
                tr.addEventListener('click', () => params.fn(view[params.key]))
            }
            for(let [key, value] of Object.entries(view)) {
                let td = tr.querySelector('*[name="'+ key +'"]');
                if(td != undefined) {
                    td.setAttribute('value', value);
                    td.innerHTML = value;
                }
            }
        }
    },
    /*
     * @param params.target - div
     * @param params.list   - list
     *
    setList : (params) => {
        let target = target;

        while(target.children[1]) {
            target.children[1].remove();
        }

        let HTML = target.innerHTML;
        target.children[0].style.display = 'none';

        for(let item of list) {
            list.append($(HTML)[0]);
            let lastChild = target.lastChild;
            $(lastChild).find('.data-img').attr('src', item.image);
            $(lastChild).find('.data-name').text(item.name);
        }
    }
     */
    setView : (params) => {
        for(let [key, value] of Object.entries(params.view)) {
            let target = params.table.querySelector('*[name="'+ key +'"]');
            if(target) {
                target.value = value;
            }
        }
    },
    viewClear : (table) => {
        let targets = table.querySelectorAll('*[name]');
        for(target of targets) {
            target.value = '';
        }
    },
    editable : (params) => {
        let target = params.table.querySelector('*[name="'+ params.name +'"]');
        if(params.editYn) {
            target.removeAttribute('readonly');
            target.removeAttribute('class');
        } else {
            target.setAttribute('readonly', 'readonly');
            target.setAttribute('class', 'readonly');
        }
    }
}