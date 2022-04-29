let dateTimes = document.getElementsByClassName('dateTimes')

for(let dateTime of dateTimes) {
    dateTime.addEventListener("click", function(evt) {
        this.showPicker();
    })
}