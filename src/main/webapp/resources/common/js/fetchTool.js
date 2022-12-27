window.FetchTool = {
    post : async (params) => {
        await fetch(params.url, {
            method: 'post',
            body: JSON.stringify(params.data),
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json; charset=UTF-8",
                credentials: "same-origin",
                mode: "cors"
            }
        })
            .then((res) => res.json())
            .then(async (result) => await params.fn(result))
            .catch((err) => console.log('ERROR >> ', err))
    },


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