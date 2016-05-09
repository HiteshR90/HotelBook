<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	maximum-scale=1, minimum-scale=1, user-scalable=no '/>
<title>One Suite Room | Room List</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>">
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/normalize.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/foundation.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal_2-3.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/leftsidenav.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery.raty.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/slick.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/pagination.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>">
<script src="<c:url value="/js/vendor/modernizr.js"/>"></script>
</head>
<body>
	<!-- Header and Nav  for large screen -->

	<div id="top-bar" class="row collapse hide-for-small hide-for-medium">

		<div id="temp-logo" class=" large-3 medium-3 small-12 columns"
			data-equalizer-watch=""
			style="height: 125px; background-color: #FFFDE9">
			<a href="#"><img src="<c:url value="/img/OSR_logo1.png"/>"
				alt="OSR Home" width="300" height="112" /> </a>
		</div>

		<!-- Right Side Top Bar  for large screen -->
		<!-- #BeginLibraryItem "/Library/TopNav.lbi" -->
		<nav class="large-7 small-9 medium-7  columns" data-equalizer-watch>

		<ul class="inline-list">
			<c:if test="${isLogin=='true'}">
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
			</c:if>
			<c:if test="${isLogin=='false'}">
				<li class="accountMenu"><a
					href="${pageContext.servletContext.contextPath}/user/account">my
						account</a></li>
			</c:if>
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
				src="<c:url value="/img/viewcart.png"/>" alt="cart" /></a>
			<div class="cartCount">-</div>
		</div>

		<!-- #EndLibraryItem -->
	</div>

	<!-- End Header -->
	<script type="text/template" id="room_lists_template">
		<!-- Right Side Content -->

		<!-- end small screen -->
		<div
			class="large-9 small-12 medium-9  push-3  columns roomList-container show-for-large-up">
			<div class="large-12 medium-12 small-12 columns roomList-contente">
					<div class="large-12 medium-12 small-12 columns" id="right-content-Buy"  data-equalizer-watch>
						<div class="row roomContainer"></div>
						<div class="row" id="buyPagination">
							<div class="large-12 medium-12 small-12 columns ">
								<ul id="pagination" class="pagination-sm"></ul>
							</div>
						</div>
					</div>
			</div>
		</div>

		<!-- End Right Content -->
		<!--Left Sidebar -->
		<!-- This is source ordered to be pulled to the left on larger screens -->

		<div
			class="large-3  hide-for-small medium-3 pull-9 columns show-for-large-up buySideBar" data-equalizer-watch>
			<div class="buySBContent">
				<div id="left-side-nav">
					<div class="row siderow">
						<div class="large-12 medium-12 small-12 columns">
							<input type="search" name="city" id="search-city"
								class="cityName" value="${cityName}" /> <label for="seach-city">city</label>

						</div>
					</div>
					<div class="row siderow">
						<div style="z-index: 1;"
							class="large-6 medium-6 small-6 columns date-container">

							<input type="text" class="from" name="from" value="${startDate}"
								class="checkInDate"> <label for="from">check-in</label>

						</div>
						<div style="z-index: 1;"
							class="large-6 medium-6 small-6 columns date-container">

							<input type="text" class="to" id="endDate" name="to"
								class="checkOutDate" value="${endDate}"> <label for="to">check-out</label>

						</div>
					</div>

					<div class="row siderow" style="margin-top:9%;">
						<div id="slider1-container"
							class="large-12 medium-12 small-12 columns">

							<div class="slider-range"></div>

							<label for="amount" style="margin-top: 5%">price range:</label> <input
								type="text" class="amount">

						</div>
					</div>

					<div style="margin-right:10%" class="row siderow">
						<label style="margin-top: 5%; margin-left: 18%" for="starRating">star
							rating</label>
						<div id="starRating" style="margin-left:15%;">
						</div>
					</div>

					<div class="row siderow">
						<div class="large-6 medium-6 small-6 columns">
							<input type="text" class="onlyNumber" id="room-quantity" min="1"
								max="5" style="margin-top:10%;">
							<p style="margin:-4px;text-size:0.8rem;">rooms</p>
						</div>
						<div class="large-6 medium-6 small-6 columns">
							<input type="text" class="onlyNumber" id="guest-quantity" min="1"
								max="5" style="margin-top:10%;">
							<p  style="margin:-4px;text-size:0.8rem;">adults</p>
						</div>
					</div>

					<!-- <div class="row siderow">
						<div class="group large-12 medium-12 small-12 columns">

							<select value="event" id="events">
								<option>EVENTS</option>
								<option name="event0" value="1">example 1</option>
								<option name="event1" value="2">example 2</option>
								<option name="event2" value="3">example 3</option>
								<option name="event3" value="4">example 4</option>
							</select>

						</div>
					</div> -->

					<!-- <div class="row siderow">
						<div class="group large-12 medium-12 small-12 columns">

							<select id="neighborhood">
								<option>NEIGHBORHOOD</option>
								<option name="NBH0" value="1">example 1</option>
								<option name="NBH1" value="2">example 2</option>
								<option name="NBH2" value="3">example 3</option>
								<option name="NBH3" value="4">example 4</option>
							</select>

						</div>
					</div> -->

					<div class="row siderow">
						<div class="group large-12 medium-12 small-12 columns">
							<select id="hotel-brand" style="margin-top:10%;">
								<option value="">HOTEL BRAND</option>
								<option value="1" name="HB0">1</option>
								<option value="2" name="HB1">2</option>
								<option value="3" name="HB2">3</option>
								<option value="4" name="HB3">4</option>
								<option value="5" name="HB3">5</option>
							</select>
						</div>
					</div>

					<!-- <div class="row siderow">
						<div class="group large-12 medium-12 small-12 columns">
							<select id="user-rating">
								<option>USER RATING</option>
								<option name="rate0" value="1">example 1</option>
								<option name="rate1" value="2">example 2</option>
								<option name="rate2" value="3">example 3</option>
								<option name="rate3" value="4">example 4</option>
								<option name="rate4" value="5">example 4</option>
							</select>

						</div>
					</div> -->


					<div class="row siderow">
						<div class="group large-12 medium-12 small-12 columns" style="text-align:center;">
							

							<label style="margin-right: 12%;margin-bottom:3%">Amenites</label>
							<div class="row" style="margin-bottom:3%; text-align:center;"> 
 								<div class="large-6 medium-12 small-12 columns" style="text-align:left;" >
  									<input type="checkbox" value="Wifi" name="amenities" class="amenities"><label>Wifi</label></input>
  								</div>
  								<div class="large-6 medium-12 small-12 columns" style="text-align:left;"  >
  									<input type="checkbox" value="TV" name="amenities" class="amenities"><label>TV</label></input>
								</div>
							</div>
							<div class="row" style="margin-bottom:3%;text-align:center;"> 
 								<div class="large-6 medium-12 small-12 columns" style="text-align:left;"  >
  									<input type="checkbox" value="AC" name="amenities" class="amenities"><label>AC</label></input>
  								</div>
 								<div class="large-6 medium-12 small-12 columns" style="text-align:left;"  >
 									<input type="checkbox" value="Telephone" name="amenities" class="amenities"><label>Telephone</label></input>
  								</div>
							</div>
  
						</div>
					</div>
				</div>

				<div style="text-align: center" class="row siderow hide">
					<div class="large-12 medium-12 columns">
						<img alt="advanced search"
							src="<c:url value="/img/OSR_advancedsearch.png"/>">
					</div>
				</div>

			</div>
		</div>
	</script>
	<div class="row buyRoomList" data-equalizer></div>


	<!-- End main content -->
	<div id="modal2" class="reveal-modal medium show-for-large-up"
		data-reveal></div>
	<div id="modal3cart" class="reveal-modal medium" data-reveal></div>
	<div id="modal1" class="reveal-modal medium" data-reveal></div>
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


	<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.orbit.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>

	<script>
		$(document).ready(function() {
			$(document).foundation();
			$("#modal1").on("authed", function(event, f) {
				$('#modal1').empty();
				//$('#modal1').foundation('reveal', 'close');
				if (isFunction(f))
					f();
			});
		});
		function isFunction(obj) {
			return jQuery.type(obj) === "function";
		}
	</script>
	<!-- Other JS plugins can be included here -->

	<!-- <script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script> -->
	<%-- <script src="<c:url value="/js/jquery-1.10.2.js"/>"></script> --%>
	<script src="<c:url value="/js/jquery-ui-1.10.4.custom.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
	<script src="<c:url value="/js/slick.js"/>"></script>
	<script src="<c:url value="/js/jquery.twbsPagination.js"/>"
		type="text/javascript"></script>
	<script src="<c:url value="/js/jquery.raty.js"/>"
		type="text/javascript"></script>

	<script type="text/javascript"
		src="<c:url value="/js/underscore-min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/backbone-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/handlebars.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/LoginModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/FbLoginModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/RegisterModel.js"/>"></script>
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

	<script type="text/javascript" src="<c:url value="/js/facebook.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/AddToCartModel.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/HotelDetailModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/HotelDetailView.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/CartDetailModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/CartDetailView.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/view/RoomListView.js"/>"></script>

	<script type="text/javascript">
		function getParam(name) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(window.location.href);
			if (results == null)
				return "";
			else
				return results[1];
		}
		Backbone.history.start({
			pushState : true
		})
		var cityName = getParam("cityName");
		var url = "${pageContext.servletContext.contextPath}";
		var roomListModel = Backbone.Model.extend();
		var roomListModel = new roomListModel({
			'baseUrl' : url
		});
		if (cityName != null) {
			roomListModel.set('cityName', cityName);
		}
		roomListModel.set('baseUrl', url);

		
		var roomListView = new RoomListView({
			model : roomListModel
		});
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
		<jsp:param value="rooms" name="pagename" />
	</jsp:include>
</body>
</html>
