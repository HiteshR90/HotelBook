<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	maximum-scale=1, minimum-scale=1, user-scalable=no '/>
<title>One Suite Room | Cart</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>" />
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/normalize.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/foundation.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/slick.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal2.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal3.css"/>">

<script src="<c:url value="/js/vendor/modernizr.js"/>"></script>


</head>
<body>


	<!-- Header  ˁ˚ᴥ˚ˀ  Nav  -->
	<div id="top-bar" class="row collapse ">

		<div id="temp-logo" class=" large-3 medium-3 small-12 columns"
			data-equalizer-watch="" style="height: 112px;">
			<a href="#"><img src="<c:url value="/img/OSR_logo3.png"/>"
				alt="OSR_logo" width="300" height="112"></a>
		</div>

		<!-- Right Side Top Bar  for large screen -->
		<!-- #BeginLibraryItem "/Library/TopNav.lbi" -->

		<nav class="large-7 small-9 medium-7  columns" data-equalizer-watch>
		<ul class="inline-list">
			<li class="accountMenu"><a href="#" data-dropdown="hover1"
				data-options="is_hover:true">Hi, ${userName}</a>
				<ul id="hover1" class="f-dropdown" data-dropdown-content
					style="z-index: 500">
					<li><a
						href="${pageContext.servletContext.contextPath}/user/account">My
							Account</a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/user/logout">Log
							out</a></li>
				</ul></li>

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
			<a href="#" class="cartDetail" onclick="cartDetail(false);"> <img
				src="<c:url value="/img/OSR_viewcart.png"/>" alt="cart" /></a>
			<div class="cartCount">-</div>
		</div>

		<!-- #EndLibraryItem -->
	</div>
	<!-- End Header -->



	<!-- main content -->

	<div class="row" id="temp-1">
		<div class="small-12 large-12 columns" id="temp-1Content">

			<div class="row right-content-sell" style="margin-bottom: 4%">
				<div class="large-12 medium-12 small-12 columns">
					<h2
						style="margin: 0; font-family: 'motion picture'; font-size: 55px; color: #3A2C1D; text-align: center;">Complete
						Your Reservation</h2>
				</div>
			</div>

			<div class="row">
				<div class="large-6 medium-6 small-6 columns transbox"
					id="SelRmsContain">
					<h4>Selected Rooms</h4>

					<div class="selectedRooms">
						<c:forEach items="${roomData}" var="room">
							<div class="row collapse selectRes">
								<div class="large-9 medium-9 small-9 columns selectRes-info">${room.hotelName}</div>
								<div class="large-3 medium-3 small-3 columns selectRes-price">$${room.totalCost}</div>
							</div>
						</c:forEach>

					</div>

					<div class="totalContain">
						<div class="row">
							<div class="large-12 medium-12 small-12 columns"
								style="color: #C11A22">+ Fees</div>
						</div>
						<div class="row">
							<div class="large-12 medium-12 small-12 columns">$$</div>
						</div>
						<div class="row">
							<div class="large-12 medium-12 small-12 columns">${cartAmount}</div>
						</div>
						<div class="row">
							<div class="large-12 medium-12 small-12 columns">$$$$</div>
						</div>
					</div>



				</div>


				<div class="large-6 medium-6 small-6 columns" id="resPayment">

					<dl class="tabs radius" data-tab>
						<dd class="active">
							<a href="#panel2-1"><img
								src="<c:url value="/img/paypal_logo.jpg"/>" alt="paypal"
								style="border-radius: 10px; border: 1px solid #3A2C1D" /></a>
						</dd>
						<dd>
							<a href="#panel2-2"><img src="<c:url value="/img/CC.jpg"/>"
								"
								alt="credit card"
								style="border-radius: 10px; border: 1px solid #3A2C1D" /></a>
						</dd>
					</dl>
					<div class="tabs-content">
						<div class="content active" id="panel2-1">
							<div id="ppLogin">
								<p>
									To pay with paypal click <a
										href="<c:url value="/payment/paypal"/>">Here.</a>
								</p>
							</div>
						</div>
						<div class="content card-div" id="panel2-2"></div>
					</div>




				</div>

			</div>





			<div class="row right-content-sell" id="respayinfo">
				<div class="12-large 12-medium 12-small columns">
					<h4>How it Works! +</h4>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Phasellus ultricies ipsum tellus, in volutpat risus sagittis et.
						Aenean tincidunt non turpis vel facilisis. In vel facilisis enim,</p>


				</div>
			</div>


		</div>

		<!-- End Right Content -->

	</div>


	<!--footer-->

	<footer>
	<div class="row">
		<div class=" small-8 medium-8 large-8 columns" id="footer-img1">
			<a href="#"><img
				src='<c:url value="/img/OSR_howitworks.png"></c:url>'
				alt="How it Works!" id="footer-img1" /> </a>
		</div>

		<div id="footer-media" class="small-4 medium-4 large-4 columns">
			<a href="${pageContext.servletContext.contextPath}/pageNotFound"><img
				src='<c:url value="/img/facebooklink.png"></c:url>' alt="facebook" /></a>
			<a href="${pageContext.servletContext.contextPath}/pageNotFound"><img
				src='<c:url value="/img/twitterlinks.png"></c:url>' alt="twitter" /></a>
		</div>

	</div>
	<!-- #BeginLibraryItem "/Library/footer.lbi" --> <!-- #BeginLibraryItem "/Library/footer.lbi" -->

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
					<li><a href="${pageContext.servletContext.contextPath}/press">press</a></li>
				</div>
				<div class="small-6 medium-4 large-2 columns">
					<li><a href="${pageContext.servletContext.contextPath}/legal">legal</a></li>
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


	<!-- #EndLibraryItem --> <!-- #EndLibraryItem -->
	</div>
	</footer>


	<!-- modal2 start -->

	<div id="modal2" class="reveal-modal medium show-for-large-up" data-reveal></div>


	<!-- end modal2 -->
	<!-- modal3 -->

	<div id="modal3cart" class="reveal-modal medium" data-reveal></div>
	<!-- end modal3 -->











	<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
	<script src="<c:url value="/js/slick.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.orbit.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.abide.js"/>"></script>



	<!-- Other JS plugins can be included here -->

	<script type="text/javascript"
		src="<c:url value="/js/underscore-min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/backbone-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/handlebars.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/LoginPopupModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/LoginPopupView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/LoginModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/LoginView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/RegisterModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/RegisterView.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/HotelDetailModel.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/CreditCardModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/CreditCardView.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/CartDetailModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/CartDetailView.js"/>"></script>

	<script>
		$(document).foundation();

		$(document).ready(function() {
			$('.slick-slider').slick({
				dots : true, // Current slide indicator dots
				infinite : true, // Infinite looping
				swipe : true, // Enables swipe
				touchMove : true, // Enables slide moving with touch
				cssEase : 'ease', // CSS3 easing
				easing : 'linear', // animate() fallback easing
				accessibility : true, // Enables tabbing and arrow key navigation
			});
		});
	</script>
	<script type="text/javascript">
		function loadCreditCard() {
			var model = Backbone.Model.extend();
			$('.card-div').append(new CreditCardView({
				model : new model({
					"baseUrl" : "${pageContext.servletContext.contextPath}"
				})
			}).render().el);
		}
		loadCreditCard();
		var url = "${pageContext.servletContext.contextPath}";
	</script>
	<script type="text/javascript" src="<c:url value="/js/CartDetail.js"/>"></script>
	<c:if test="${isLogin=='true'}">
		<script type="text/javascript">
			$(document).ready(function() {
				cartDetail(true);
			});
		</script>
	</c:if>
	<jsp:include page="../feedback.jsp">
		<jsp:param value="payment" name="pagename" />
	</jsp:include>
</body>
</html>