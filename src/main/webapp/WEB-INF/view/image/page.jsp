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
	#section4 .previewContainer {
		display: flex;
		flex-wrap: wrap;
	}
	#section4 .previewContainer .preview {
		display: block;
		width: 100px;
		height: 100px;
		border: 1px solid #555;
		box-sizing: border-box;
		margin: 10px;
		object-fit: cover;
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
		<h4>이미지 미리보기, 업로드</h4>
		<input type="file" id="btnImageUpload2" onchange="ImageMgr.changeImage2();" accept="image/jpeg, image/png, image/gif, image/webp" />
		<img id="imgImageUpload2" src="" alt="" />
		<button type="button" id="btnImageSave2">등록하기</button>
	</section>
	<section id="section4">
		<h3>이미지 미리보기, 업로드 리스트</h3>
		<input type="file" class="btnUpload" onchange="ImageMgr.changeImage4(this);" accept="image/jpeg, image/png, image/gif, image/webp" />
		<div class="previewContainer"></div>
		<button type="button" class="btnSave">등록하기</button>
	</section>
	<section>
		<h3>이미지 업로드</h3>
		<div>하나도 안됨</div>
		<input type="file" id="btnImageUpload_" accept=".gif, .jpg, .png" multiple>
		<button type="button" id="btnImageSave_">등록하기</button>
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

		/** 이미지 미리보기, 업로드 */
		changeImage2: function(event) {
			let $this = $('#btnImageUpload2');
			let $img = $('#imgImageUpload2');

			me.getImageInfoDefer(event.target.files[0], function(result) {
				if(result.success) {
					let success = true;
					let message = [];
					me.isValidImageTypeDefer(result.data.type)
					.done(function(result) {

					})
					.fail(function(result) {
						success = false;
						message = result.message;
					});
					if(success) {
						me.isValidImageSizeDefer(result.data.width, result.data.height, 500, 500)
						.done(function(result) {
							$img.attr('src', URL.createObjectURL(event.target.files[0]));
						})
						.fail(function(result) {
							success = false;
							message = result.message;
							message.push('가로 세로 1000 x 1000px 의 이미지가 가장 알맞습니다.');
						});
					}
					if(success == false) {
						alert(message.join('\m'));
						$this.val('');
						$img.attr('src', '');
						return;
					}
				} else {
					alert(result.message);
					$this.val('');
					$img.attr('src', '');
				}
			})
		},
		getImageInfoDefer: function(file, callBackFunc) {
			let image = new Image();
			image.src = URL.createObjectURL(file);
			image.onload = function() {
				let result = { // 이걸 걍 리턴하면 안됨?
					data: {
						width: this.width,
						height: this.height,
						naturalHeight: this.naturalHeight,
						naturalWidth: this.naturalWidth,
						type: file.type,
						size: file.size
					},
					success: true,
					message: 'success'
				};
				if(typeof callBackFunc === "function") {
					callBackFunc( result );
				}
			};
			image.onerror = function() {
				let result = {
					success: false,
					message: 'Error loading as image!'
				};
				if(typeof callBackFunc === "function") {
					callBackFunc( result );
				}
			}
		},
		isValidImageTypeDefer: function(type) {
			let defer = $.Deferred();
			if($.inArray(type, ['image/png', 'image/jpeg', 'image/gif', 'image/webp']) >= 0) {
				defer.resolve({
					message: 'success',
					success: true
				});
			} else {
				let message = [];
				message.push('jpg, gif, webp 나 png 파일만 업로드할 수 있습니다.');
				message.push('파일의 확장자를 확인하여 주세요.');

				defer.reject({
					message: message,
					success: false,
				});
			}
			return defer.promise();
		},
		isValidImageSizeDefer: function(width, height, limitWidth, limitHeight) {
			let defer = $.Deferred();
			if(width >= limitWidth && height >= limitHeight) {
				defer.resolve({
					message: 'success',
					success: true
				})
			} else {
				let message = [];
				message.push('이미지 크기가 작아 업로드 할 수 없습니다.');
				message.push('가로 세로 ' + limitWidth + ' x ' + limitHeight + 'px 이상의 이미지 파일을 첨부해주세요.');
				defer.reject({
					message: message,
					success: false
				});
			}
			return defer.promise();
		},
		/** 이미지 미리보기, 업로드 */

		/** section4 */
		changeImage4: function(e) {
			let $previewContainer = $('#section4 .previewContainer');
			let files = e.files;
			Array.from(files).forEach((v, i) => {
				let imgUrl = URL.createObjectURL(v);
				let imgTag = document.createElement('img');
				imgTag.classList.add('preview');
				imgTag.setAttribute('src', imgUrl);
				$previewContainer.append(imgTag);
			});
		},
	}

	$(() => ImageMgr.startup());
</script>