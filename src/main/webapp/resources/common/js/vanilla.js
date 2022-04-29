/* serializeArray */
function form2json(form_id) {
    let form = document.getElementById(form_id)
    let data = new FormData(form)
    let json = {}

    for(let [key, value] of data) {
        json[key] = value
    }

    return JSON.stringify(json)
}