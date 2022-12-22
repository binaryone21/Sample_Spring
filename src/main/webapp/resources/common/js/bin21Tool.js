window.Bin21Tool = {
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
}