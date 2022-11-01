let multipleUpload = window.main = {
    startup : function() {
        multipleUpload.setVariables();
        multipleUpload.setEvents();
        multipleUpload.initialize();
    },

    setVariables : function() {
        multipleUpload.IMAGE_MAX_SIZE = 1024 * 1024 * 10;
        multipleUpload.IMAGE_TYPES = ['jpg','jpeg','png','bmp']
        multipleUpload.imageFileList = [];
        multipleUpload.imageIdx = 0;

        multipleUpload.btnImageUpload  = $('#btnImageUpload');
        multipleUpload.btnImageSave    = $('#btnImageSave');
    },

    setEvents : function() {
        multipleUpload.btnImageUpload.change(multipleUpload.uploadImage);
        multipleUpload.btnImageSave.click(multipleUpload.saveImage);
    },

    initialize : function() {

    },

    /** 사진 추가시 */
    uploadImage : function() {
        console.log(this);
        let file = this.files[0];
        multipleUpload.imageFileList.push(file);
        multipleUpload.btnImageUpload.val('');
    },

    /** 사진 충족 요건 확인 */
    imageValidation : function(file) {
        let name = file.name;
        let nameArr = name.split('.');
        let type = nameArr[nameArr.length - 1];
        if($.inArray(type.toLowerCase(), multipleUpload.IMAGE_TYPES) === -1) {
            alert('이미지 파일만 등록 가능합니다.')
            return false;
        }

        let size = file.size();
        if(size > multipleUpload.IMAGE_MAX_SIZE) {
            alert('첨부 가능한 사진의 최대 용량은 10MB입니다.')
            return false;
        }

        // if(uploa)
    },

    saveImage : function() {
        let url = '/image/ajax_map/multipleUploadAjax';
        let data = new FormData();
        for(let i=0; i<multipleUpload.imageFileList.length; i++) {
            data.append('uploadImage_' + i, multipleUpload.imageFileList[i]);
        }
        let result = AjaxTool.doAjaxFile(url, data);
    }
}
multipleUpload.startup()

// 첨부 사진 클릭 시 미리보기 팝업
/*
$(document).on('click', '#swiperContainer img', function() {
    let imageSrc = $(this).attr('src');
    multipleUpload.imageIdx = $('#swiperSlide img').index(this);

    $('#popImage').bPopup({
        onOpen : function() {
            $('#popImage img').attr('src', imageSrc);
        },
        position : ['auto', 'auto']
    });
});
*/

// 파일 충족 요건 확인
// if(multipleUpload.imageValidation(file)) return;
// multipleUpload.setUploadImage(file);
// multipleUpload.setImageSlide;
// multipleUpload.btnImageUpload.val('');