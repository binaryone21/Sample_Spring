<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImageUploadPage</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/common/js/ajax.js" defer></script>
    <script src="/image/js/uploadImage.js" defer></script>
</head>
<body>
    <input type="file" id="btnImageUpload" accept=".gif, .jpg, .png" multiple>
    <button type="button" id="btnImageSave">등록하기</button>
</body>
</html>
