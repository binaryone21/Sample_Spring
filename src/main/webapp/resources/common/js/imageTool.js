window.ImageTool = {
    imageMap : {
        maxSize : 1024 * 1024 * 10,
        imageTypes : ['jpg','jpeg','png','bmp']
    },
    /**
     * 사진 충족 요건 확인
     *
     * @param   file
     * @returns {boolean}
     */
    validation : (file) => {
        let name = file.name;
        let nameArr = name.split('.');
        let type = nameArr[nameArr.length - 1];
        if($.inArray(type.toLowerCase(), ImageTool.imageMap.imageTypes) === -1) {
            alert('이미지 파일만 등록 가능합니다.')
            return false;
        }

        let size = file.size;
        if(size > ImageTool.imageMap.maxSize) {
            alert('첨부 가능한 사진의 최대 용량은 10MB입니다.')
            return false;
        }
        return true
    },
    /**
     *
     * @param params.name   - imageName
     * @param params.list   - imageList
     */
    imageObject : (params) => {
        let data = new FormData();
        for(let i=0; i<params.list.length; i++) {
            data.append(params.name + '_' + i, params.list[i]);
        }
        return data;
    }
}