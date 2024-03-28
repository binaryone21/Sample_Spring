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
	<section id="section1">
		<h3>응모하기</h3>
		<button type="button" onclick="ApplyMgr.applyEvent(5);">응모</button>
	</section>
</body>
</html>

<script>
	let me = window.ApplyMgr = {
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
		applyEvent: function(applyEventSeq) {
			AjaxTool.post({
				url : '/co/event/applyEvent.json',
				data : { applyEventSeq : applyEventSeq },
				async : false,
				success : function(result) {
					console.log(result);
				}
			})
		}
	}

	$(() => ApplyMgr.startup());
</script>