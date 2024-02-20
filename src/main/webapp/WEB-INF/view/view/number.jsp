<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<Link rel="stylesheet" href="/css/memo.css">
</head>
<body>
	<section class="memo">
		<h3>증가하는 숫자</h3>
		<span id="count1">1234</span>
	</section>
</body>
</html>

<script>
	let me = window.NumberMgr = {
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
			me.count1($('#count1'), 100000);
		},
		count1 : (target, number) => {
			target.animate({
				counter: number
			}, {
				duration: 2500,
				easing: 'swing',
				step: function(now) {   // 무조건 function() { }
					$(this).text(Math.ceil(now).toLocaleString());
				},
			});
		}
	}
	$(() => NumberMgr.startup());
</script>