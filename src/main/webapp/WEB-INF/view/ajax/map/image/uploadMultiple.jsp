<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ImageUploadPage</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/common/js/ajaxTool.js"></script>
    <script src="/common/js/imageTool.js"></script>
    <%--<script src="/ajax/map/image/multipleUpload.js" defer></script>--%>
</head>

<script>
    (() => {
        let me = window.AMImage = {
            startup : function() {
                me.setVariables();
                me.setEvents();
                me.initialize();
            },

            setVariables : function() {
                me.imageFileList = [];
                me.btnImageUpload  = $('#btnImageUpload');
            },

            setEvents : function() {
                me.btnImageUpload.change(me.imageUpload);
                $('#btnImageSave').click(me.imageSave);
            },

            initialize : function() {
            },

            /** 사진 추가시 */
            imageUpload : function() {
                let file = this.files[0];
                if(!ImageTool.validation(file)) return;
                me.imageFileList.push(file);
                me.btnImageUpload.val('');
            },
            imageSave : function() {
                let data = {
                    name : 'uploadImage',
                    list : me.imageFileList
                }
                AjaxTool.post({
                    url     : '/ajax/map/image/uploadMultiple.ajax',
                    data    : ImageTool.imageObject(data),
                    type    : 'file',
                    fn      : (result) => alert(result.message)
                });
            }
        }
    })();
    $(function() {
        AMImage.startup();
    });
</script>

<body>
    <input type="file" id="btnImageUpload" accept=".gif, .jpg, .png" multiple>
    <button type="button" id="btnImageSave">등록하기</button>
</body>
</html>
