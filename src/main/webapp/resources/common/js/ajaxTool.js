window.AjaxTool = {
    /**
     *
     * @param params.url - '/...'
     * @param params.data - $('#...').serialize()
     * @param params.dataType - 'json' or 'html'
     * @param params.fn - function() { }
     * @returns {*}
     */
    post : (params) =>
        $.ajax({
            url         : params.url,
            data        : JSON.stringify(params.data),
            dataType    : params.dataType,
            contentType : 'application/json; charset=UTF-8',
            type        : 'post',
            global      : false,
            cache       : false,
            async       : false,
            success     : (result) => params.fn(result),
            error       : (jqXHR, textStatus, errorThrown) => console.log(jqXHR)
        }),
/*
        $.post( params.url, params.data, (result, textStatus) =>
                (textStatus == 'success')
                    ? params.fn(result)
                    : console.log('ajax error')
            , params.dataType)
                .fail((err) => console.log('ajax error >> ', err)),
*/
    /**
     *
     * @param form
     * @returns {{}}
     */
    serializeObject : (form) => {
        if(form == null) {
            console.log('ERROR [AjaxTool serializeObject] >> is not form or not found');
            return;
        }

        let data = new FormData(form);
        let json = {};
        for(let [key, value] of data) {
            json[key] = value
        }

        return json;
    }
}