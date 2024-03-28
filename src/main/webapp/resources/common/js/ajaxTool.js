window.AjaxTool = {
    /**
     *
     * @param params.url - '/...'
     * @param params.data - {}(default), $('#...').serialize()
     * @param params.type - 'json'(default), 'html', 'file'
     * @param params.fn - function() { }
     * @returns {*}
     */
    post : (params) =>
        (params.type === 'file')
            ? $.ajax({
                url         : params.url,
                data        : params.data,
                enctype     : 'multipart/form-data',
                type        : 'post',
                contentType : false,
                processData : false,
                async       : (params.async !== false) ? true : false,
                success     : (result) => ((params.success) ? params.success(result) : console.log(result)),
                error       : (jqXHR, textStatus, errorThrown) => console.log(jqXHR)})
            : $.ajax({
                url         : params.url,
                data        : JSON.stringify((params.data) ? params.data : {}),
                dataType    : (params.type) ? params.type : 'json',
                type        : 'post',
                contentType : 'application/json; charset=UTF-8',
                global      : false,
                cache       : false,
                async       : (params.async !== false) ? true : false,
                success     : (result) => ((params.success) ? params.success(result) : console.log(result)),
                error       : (jqXHR, textStatus, errorThrown) => console.log(jqXHR)}),

    validation : (params) => {
        let result = '';
        if(!params.url)
            result += 'FetchTool Error >> params.url is not setting\n'
        if(!params.data)
            result += 'FetchTool Error >> params.data is not setting\n'
        if(!params.fn && typeof params.fn != 'function')
            result += 'FetchTool Error >> params.fn is not function or not setting\n'
        params.result = result;
    },
}