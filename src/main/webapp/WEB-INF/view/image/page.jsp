<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Title</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/common/js/ajaxTool.js"></script>
	<script src="/common/js/bin21Tool.js"></script>
	<script src="/common/js/webTool.js"></script>
	<script src="/common/js/imageTool.js"></script>
</head>
<style>
	section {
		border: 1px solid black;
		margin: 5px;
		padding: 10px;
	}
</style>
<body>
	<section>
		<h3>PDF 다운로드</h3>
		<button type="button" onclick="ImageMgr.downloadPDF();">PDF 다운로드</button>
	</section>
	<section>
		<h3>이미지 멀티 업로드</h3>
		<div>단 프론트쪽 좀 더 살펴봐야 함</div>
		<input type="file" id="btnImageUpload" accept=".gif, .jpg, .png" multiple>
		<button type="button" id="btnImageSave">등록하기</button>
	</section>
	<section>
		<h3>이미지 업로드</h3>
		<div>하나도 안됨</div>
		<input type="file" id="btnImageUpload2" accept=".gif, .jpg, .png" multiple>
		<button type="button" id="btnImageSave2">등록하기</button>
	</section>
</body>
</html>

<script>
	let me = window.ImageMgr = {
		startup: function() {
			me.setVariables();
			me.setEvents();
			me.initialize();
		},
		setVariables: function() {
			/** 이미지 멀티 업로드 */
			me.imageFileList = [];
			me.btnImageUpload  = $('#btnImageUpload');
			/** 이미지 멀티 업로드 */


		},
		setEvents: function() {
			/** 이미지 멀티 업로드 */
			me.btnImageUpload.change(me.imageUpload);
			$('#btnImageSave').click(me.imageSave);
			/** 이미지 멀티 업로드 */


		},
		initialize: function() {

		},
		/** PDF 다운로드 */
		downloadPDF: function() {
			WebTool.submit({
				action: '/image/downloadPDF.file' ,
				target: '_self',
				params: {
					fileName: 'guide.pdf'
				}
			});
		},
		/** PDF 다운로드 */

		/** 이미지 멀티 업로드 */
		// 사진 추가시
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
				url     : '/image/uploadMultiple.file',
				data    : ImageTool.imageObject(data),
				type    : 'file',
				success : (result) => alert(result.message)
			});
		},
		/** 이미지 멀티 업로드 */
	}
	$(() => ImageMgr.startup());
</script>