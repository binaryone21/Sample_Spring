/* serializeArray */
function form2json(form_id) {
    let form = document.getElementById(form_id)
    if(form == null) {
        console.log('ERRER >>', form_id, 'is not form or not found')
        return
    }

    let data = new FormData(form)
    let json = {}
    for(let [key, value] of data) {
        json[key] = value
    }

    return JSON.stringify(json)
}