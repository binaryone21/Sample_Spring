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
		<h3>템플릿 다운로드</h3>
		<button type="button" onclick="ExcelTool.downloadTemplate();">다운로드</button>
	</section>
	<section>
		<h3>템플릿 다운로드, 데이터와 함께</h3>
		<button type="button" onclick="ExcelTool.downloadTemplateWithData();">다운로드</button>
	</section>
	<section>
		<h3>미트박스 엑셀 다운로드</h3>
		<button type="button" onclick="ExcelTool.downloadExcel();">엑셀 다운로드</button>
	</section>
</body>
</html>

<script>
	let me = window.ExcelTool = {
		startup: function() {
			me.setVariables();
			me.setEvents();
			me.initialize();
		},
		setVariables: function() {

		},
		setEvents: function() {

		},
		initialize: function() {

		},
		downloadExcel: function() {
			WebTool.submit({
				action: '/excel/download.file' ,
				params: {
					fileName: 'Sample.xls'
				}
			});
		},
		downloadTemplate: function() {
			WebTool.submit({
				action: '/excel/downloadTemplate.file' ,
				params: {
					fileName: 'Template.xls'
				}
			});
		},
		downloadTemplateWithData: function() {
			WebTool.submit({
				action: '/excel/downloadTemplateWithData.file' ,
				params: {
					fileName: 'Template.xls'
				}
			});
		}
	}
	$(() => ExcelTool.startup());
</script>