let ajaxTool = window.AjaxTool = {
    doAjaxUrl : (url) => ajaxTool.doAjax(url, {}, 'json'),
    doAjaxJson : (url, data) => ajaxTool.doAjax(url, data, 'json'),
    doAjax : (url, data, dataType) => {
        return $.ajax({
            url : url,
            data : data,
            dataType : dataType,
            type : 'post',
            global : false,
            cache : false,
            async : false,
            success : (result) => result,
            error : (jqXHR, textStatus, errorThrown) => console.log('ajax error => ', jqXHR)
        })
    },
    doAjaxFile : (url, data) => {
        return $.ajax({
            url     : url,
            data    : data,
            enctype : 'multipart/form-data',
            type    : 'post',
            processData : false,
            contentType : false,
            success : (result) => result,
            error : (jqXHR, textStatus, errorThrown) => console.log('ajax eroor => ', jqXHR)
        })
    }
}