<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>One Suite Room | Buy or Sell Hotel Rooms Instantly</title>
<link rel="icon" type="image/x-icon" href="img/OSR-fav.ico" />
<link rel="stylesheet"
	href="<c:url value="/css/foundation.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/slick.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/styles.css"></c:url>" />
<link rel="stylesheet" href="<c:url value="/css/normalize.css"></c:url>" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<style type="text/css">
</style>
</head>
<body>
	<header>
		<nav class="top-bar" data-topbar role="navigation">
			<ul class="title-area">
				<li class="name">
					<h1>
						<a href="${pageContext.servletContext.contextPath}"><img src="img/OSR-logo.1.png"></a>
					</h1>
				</li>
				<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
			</ul>
			<section class="top-bar-section">
				<!-- Right Nav Section -->
				<ul class="right">
					<li><a href="#">How It Works</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">Search For a Room</a></li>
					<li class="modal"><a href="#" onclick="sellRoom()">List Your Room</a></li>
					<!-- <li class="modal"><a href="#" onclick="loginReveal()">Login/Signup</a>
						on click on this link you get modal, fill model with correct content, if user is signed in beplace login/signup with My Account. and add class has-dropdown to the li next to class modal
						<ul class="dropdown">
							<li><a href="#">Settings</a></li>
							<li class="active"><a href="#">Logout</a></li>
						</ul></li>
					end My account drop down -->
					<c:if test="${isLogin=='false'}">
						<li class="accountMenu has-dropdown"><a href="#" onclick="loginReveal()">Login/Signup</a></li>
					</c:if>
					<c:if test="${isLogin=='true'}">
						<!-- on click on this link you get modal, fill model with correct content, if user is signed in beplace login/signup with My Account. and add class has-dropdown to the li next to class modal -->
						<li class="has-dropdown accountMenu">
						<a href="#">Hi, ${userName}</a>
						<ul class="dropdown">
                			<li><a href="${pageContext.servletContext.contextPath}/user/account">My Account</a></li>
                			<li class="active"><a href="${pageContext.servletContext.contextPath}/user/logout">Log out</a></li>
              			</ul>
						</li>
					</c:if>
					<li class="hide"><a href="#">Cart</a></li>
				</ul>
			</section>
		</nav>
	</header>
	<!-- end Header -->

	<div class="main-container">
		<!-- page content replace below main-content -->
		<div class="content-container">
			<div class="row sliderArea">
				<div class="large-12 medium-12 small-12 columns"
					id="slider-container">
					<div class="slider-container">
						<div class="OSR-slider">
							<div>
								<img src="img/sampleSlide.2.png" />
							</div>
							<div>
								<img src="img/sampleSlide.png" />
							</div>
							<div>
								<img src="img/sampleSlide.1.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="underSliderTxt">
					<div>
						<h1>No Room? No Problem!</h1>
					</div>
					<div>
						<h5>We have the best rooms available</h5>
					</div>
				</div>
			</div>
			<div class="row" id="box-B" data-equalizer>
				<div class="large-4 medium-12 small-12 columns" data-equalizer-watch>
					<a href="#"><div class="box-B security" style="">
							<div class="box-title">suite security</div>
							<div></div>
						</div></a>
				</div>
				<div class="large-4 medium-12 small-12 columns" data-equalizer-watch>
					<a href="#"><div class="box-B suite">
							<div class="box-title">Resell Your Room</div>
							<div></div>
						</div></a>
				</div>
				<div class="large-4 medium-12 small-12 columns" data-equalizer-watch>
					<a href="#"><div class="box-B travel">
							<div class="box-title">Business Travel Perks</div>
							<div></div>
						</div></a>
				</div>
			</div>
		</div>
		<!-- end main-content conatiner replace content above this div-->
	</div>

	<footer>
		<div class="row top-footer">
			<div class="large-12 medium-12 small-12 columns"></div>
		</div>
		<div class="row bottom-footer">
			<div class="large-12 medium-12 small-12 columns">
				<div>©2015 One Suite Room. All rights reserved.</div>
			</div>
		</div>
	</footer>
	<!-- sticky footer -->
	<!-- modals below -->

	<!-- modal1 start -->



	<!-- <div id="modal1" class="small" style="display: none;">jklsjdflksdjlkjlkjlk</div> -->
	<div id="modal1" class="reveal-modal medium" data-reveal></div>

	<!--end modal1-->
	<div id="modalPW" class="reveal-modal small" data-reveal></div>
	<!--end password recovery modal -->

	<!-- start email confirmation modal -->
	<div id="modalPWcon" class="reveal-modal small" data-reveal></div>
	<!-- end confirmation email modal -->

	<!-- Start sell modal -->
		<div id="sellModal" class="reveal-modal medium" data-reveal></div>
	<!-- End sell modal -->

	<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>

	<script type="text/javascript"
			src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/sticky-footer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/slick.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.topbar.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/underscore-min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/backbone-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/handlebars.js"/>"></script>
	<script src="https://apis.google.com/js/client.js"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/LoginModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/FbLoginModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/GoogleLoginModel.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/model/HotelAddModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/RegisterModel.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/model/ReservationDetailModel.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/model/BookingModel.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/model/ExternalSellModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/ForgotPasswordModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/LoginPopupModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/ForgotPasswordSuccessView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/ForgotPasswordView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/LoginPopupView.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/view/SellView.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/facebook.js"/>"></script>
	
	
	<script>
		//Slick slider 
		$(document).ready(function() {
			$('.OSR-slider').slick({
				dots : false, // Current slide indicator dots
				infinite : true, // Infinite looping
				swipe : true, // Enables swipe
				touchMove : true, // Enables slide moving with touch
				cssEase : 'ease', // CSS3 easing
				easing : 'linear', // animate() fallback easing
				accessibility : true, // Enables tabbing and arrow key navigation
			});
		});
		$(function() {
			$("#modal1").on("authed",function(event, f) 
			{
				if(f== "login")
				{
					location.href = "${pageContext.servletContext.contextPath}/hotel/rooms-map";
				}
			});

		});
		//image swap 
		var sourceSwap = function() {
			var $this = $(this);
			var newSource = $this.data('alt-src');
			$this.data('alt-src', $this.attr('src'));
			$this.attr('src', newSource);
		}
		// social media login in modal 
		$(function() {
			$('img.loginWith').hover(sourceSwap, sourceSwap);
		});
	</script>

	<script>
		$(document).foundation();
		
		function loginReveal() {
			$('#modal1').removeClass("reveal-modal small");
			$('#modal1').addClass("reveal-modal medium");
			$('#modal1').empty();
			this.loginModel = new LoginPopupModel({
				"baseUrl" : "${pageContext.servletContext.contextPath}",
				"callback" : "login"
			});
			this.loginView = new LoginPopupView({
				model : this.loginModel
			});
			$('#modal1').append(this.loginView.render().el);
			$(document).foundation();
			$('#modal1').foundation('reveal', 'open');
		}
		function sellRoom()
		{
			$('#sellModal').removeClass("reveal-modal small");
			$('#sellModal').addClass("reveal-modal medium");
			$('#sellModal').empty();
			var sellModel = Backbone.Model.extend();
			var sellView = new SellView({
				model : new sellModel({
					"baseUrl" : "${pageContext.servletContext.contextPath}"
				})
			}).render();
		}
	</script>
	<jsp:include page="feedback.jsp">
		<jsp:param value="splash" name="pagename" />
	</jsp:include>

</body>