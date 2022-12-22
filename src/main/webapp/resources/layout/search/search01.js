window.SearchTool = {
    /**
     *
     * @param params.search - searchArticle
     */
    setDateTime : (params) => {
        let dateTimes = params.search.querySelectorAll('div[class="date"] input');
        for(let dateTime of dateTimes) {
            dateTime.addEventListener("click", function(e) {
                this.showPicker();
            })
        }
    }
}
