<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	maximum-scale=1, minimum-scale=1, user-scalable=no '/>
<title>One Suite Room | Welcome</title>
<link rel="icon" type="image/x-icon"
	href='<c:url value="_img/OSR_Favicon.png" />' />
<link rel="shortcut icon" type="image/x-icon"
	href='<c:url value="_img/OSR_Favicon.png"/>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="css/normalize.css"></c:url>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="css/foundation.css"></c:url>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="css/app.css"></c:url>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="css/modal.css"></c:url>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="css/modal_2-3.css"></c:url>' />





</head>


<body>
	<div id="top-bar" class="row collapse ">

		<div id="temp-logo" class=" large-3 medium-3 small-12 columns"
			data-equalizer-watch="" style="height: 112px;">
			<a href="${pageContext.request.contextPath}/hotel/rooms"><img
				src='<c:url value="_img/OSR_logo3.png"> </c:url>' alt="OSR_logo"
				width="300" height="112"></a>
		</div>

		<!-- Right Side Top Bar  for large screen -->
		<!-- #BeginLibraryItem "/Library/TopNav.lbi" -->
		<nav class="large-7 small-9 medium-7  columns" data-equalizer-watch>
			<ul class="inline-list">
				<li><a
					href="${pageContext.servletContext.contextPath}/user/account">my
						account</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/hotel/rooms">buy</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/hotel/sell">sell</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/pageNotFound">map</a></li>
			</ul>
		</nav>
		<div id="cart" class="large-2 small-2 medium-2  columns"
			data-equalizer-watch>
			<a href="#" onClick="return false" data-reveal-id="modal3cart"> <img
				src='<c:url value="_img/OSR_viewcart.png"></c:url>' alt="cart" /></a>
			<div id="accountName">name</div>
		</div>
		<!-- #EndLibraryItem -->
	</div>
	<!-- End Header -->


	<!-- main content -->

	<div class="row">
		<div class="small-12 large-12 columns" id="temp-1Content"
			style="text-align: center">
			<div
				style="text-align: center; width: 60%; height: 75%; margin-left: 20%">
				<img src='<c:url value="_img/gears.gif"></c:url>' width="464"
					height "351"  lt="" />
			</div>
			<h1
				style="text-align: center; color: #3A2C1D; margin-top: -4%; font-weight: bold;">Page
				under Construction.</h1>
		</div>
	</div>

	<!--footer-->

	<footer class="row">
		<div id="footer" class="small-12 medium-12 large-12 columns">

			<div class="row">
				<div id="footer-img" class="text-right small-7 columns">
					<a href="#"><img src="_img/OSR_howitworks.png"
						alt="How it Works!" /> </a>

				</div>

				<div id="footer-media" class="small-5 columns">
					<a href="#"><img
						src='<c:url value="_img/facebooklink.png"></c:url>' alt="facebook" /></a>
					<a href="#"><img
						src='<c:url value="_img/twitterlinks.png">
					</c:url>'
						alt="twitter" /></a>
				</div>
			</div>
			<!-- #BeginLibraryItem "/Library/footer.lbi" -->
			<!-- #BeginLibraryItem "/Library/footer.lbi" -->

			<div class="row">

				<div class="small-12 medium-12 large-12 columns footerNav">
					<ul class="inline-list">
						<div class="small-6 medium-4 large-2 columns">
							<li><a
								href="${pageContext.servletContext.contextPath}/aboutUs">about
									us</a></li>
						</div>
						<div class="small-6 medium-4 large-2 columns">
							<li><a href="${pageContext.servletContext.contextPath}/blog">blog</a></li>
						</div>
						<div class="small-6 medium-4 large-2 columns">
							<li><a
								href="${pageContext.servletContext.contextPath}/press">press</a></li>
						</div>
						<div class="small-6 medium-4 large-2 columns">
							<li><a
								href="${pageContext.servletContext.contextPath}/legal">legal</a></li>
						</div>
						<div class="small-6 medium-4 large-2 columns">
							<li><a
								href="${pageContext.servletContext.contextPath}/investors">investors</a></li>
						</div>
						<div class="small-6 medium-4 large-2 columns">
							<li><a
								href="${pageContext.servletContext.contextPath}/contact">contact</a></li>
						</div>
					</ul>

				</div>
			</div>

			<!-- #EndLibraryItem -->
			<!-- #EndLibraryItem -->
		</div>
	</footer>

	<script src='<c:url value="js/vendor/jquery.js"></c:url>'></script>
	<script src='<c:url value="js/foundation/foundation.js"></c:url>'></script>
	<script
		src='<c:url value="js/foundation/foundation.equalizer.js"></c:url>'></script>
	<script
		src='<c:url value="js/foundation/foundation.reveal.js"></c:url>'></script>
	<script src='<c:url value="js/foundation/foundation.tab.js"></c:url>'></script>
	<script
		src='<c:url value="js/foundation/foundation.accordion.js"></c:url>'></script>
	<script
		src='<c:url value="js/foundation/foundation.dropdown.js"></c:url>'></script>
	<script>
		$(document).foundation();
	</script>



</body>
</html>
