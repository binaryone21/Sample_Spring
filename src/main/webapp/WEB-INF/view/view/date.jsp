<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<Link rel="stylesheet" href="/css/memo.css">
</head>
<body>
	<section class="memo" id="section1">
		<h3>D-day</h3>
		<div class="count-time-area">
			<b class="day">--</b> -
			<b class="hour">--</b> :
			<b class="minute">--</b> :
			<b class="second">--</b>
		</div>
	</section>
</body>
</html>

<script>
	let me = window.DateMgr = {
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
			me.countDownTimer($('#section1 .count-time-area'), '01/18/2024 11:59 PM');
		},
		countDownTimer: function (element, date) {
			let DATE = new Date(date);
			let SEC = 1000;
			let MIN = SEC * 60;
			let HOUR = MIN * 60;
			let DAY = HOUR * 24;

			let timer = setInterval(() => {
				let now = new Date();
				let dDay = DATE - now;
				if(dDay < 0) {
					clearInterval(timer);
					element.find('b').html('--');
					return;
				}

				let day = ('0' + Math.floor(dDay / DAY)).slice(-2);
				let hour = ('0' + Math.floor((dDay % day) / HOUR)).slice(-2);
				let min = ('0' + Math.floor((dDay % HOUR) / MIN)).slice(-2);
				let sec = ('0' + Math.floor((dDay % MIN) / SEC)).slice(-2);

				element.find('.day').html(day);
				element.find('.hour').html(hour);
				element.find('.minute').html(min);
				element.find('.second').html(sec);
			}, 1000);
		},
	}
	$(() => DateMgr.startup());
</script>