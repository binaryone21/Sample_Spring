<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<Link rel="stylesheet" href="/css/memo.css">
</head>
<body>
	<section class="memo" id="section1">
		<h3>Scroll</h3>
		<button id="go99" onclick="ScrollMgr.go99()">Section99로 이동</button>
	</section>

	<section class="memo" id="section99" style="margin-top: 500px;">
		<h3>Section 99</h3>
	</section>
</body>
</html>

<script>
	let me = window.ScrollMgr = {
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
		go99: function() {
			$('html, body').animate({scrollTop:$('#section99').offset().top});
			// $(window).scrollTop($('#section99').offset().top);
		},
	}
	$(() => ScrollMgr.startup());
</script>