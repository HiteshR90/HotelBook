<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Sell Page</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>" />
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/custom-theme/foundation.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal_2-3.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/custom-theme/jquery-ui-1.10.4.custom.1.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/sellRes.css"/>" />

</head>

<body>

	<div id="top-bar" class="row collapse "
		style="z-index: 999; position: absolute;">

		<div id="temp-logo" class=" large-3 medium-3 small-12 columns"
			data-equalizer-watch="" style="height: 112px;">
			<a href="#"><img src="<c:url value="/img/OSR_logo3.png"/>"
				alt="OSR_logo" width="300" height="112"></a>
		</div>
		<!-- Right Side Top Bar  for large screen -->
		<!-- #BeginLibraryItem "/Library/TopNav.lbi" -->
		<nav class="large-7 small-9 medium-7  columns topNav"
			data-equalizer-watch>
		<ul class="inline-list">
			<c:if test="${isLogin=='true'}">
				<li class="accountMenu"><a href="#" data-dropdown="hover1"
					data-options="is_hover:true">${userName}</a>
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
			<li><a href="<c:url value="/hotel/rooms"/>">buy</a></li>
			<li><a href="<c:url value="/hotel/sell"/>">sell</a></li>
			<!--<li><a href="#">map</a></li>-->
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


	<script type="text/template" id="search_template">
	<div class="row" id="sellProgress">
		<a href="#hotelDetail"><div
				class="large-4 medium-4 small-4 columns progContain">
				<div id="progressHotelName">Hotel Detail</div>
			</div></a> <a href="#roomDetail"><div
				class="large-1 medium-1 small-1 columns  progContain">
				<div id="progressRoomType"></div>
			</div></a> <a href="#dateDetail"><div style="padding: 0;"
				class="large-3 medium-3 small-3 columns  progContain">
				<div class="large-6 medium-6 small-6 columns" id="progressCheckIn" style="padding: 0; padding-right: 5px; margin: 0; float: left; border-right: 1px dotted #3a2c1d;"></div>
				<div id="progressCheckOut" class="large-6 medium-6 small-6 columns" style="padding: 0; margin: 0; float: left;"></div>
			</div></a> <a href="#priceDetail"><div
				class="large-2 medium-2 small-2 columns  progContain">
				<div id="processPrice"></div>
			</div></a> <a href="#confirmationCodeDetail"><div
				class="large-2 medium-2 small-2 columns  progContain">
				<div id="confirmationCodeProcess"></div>
			</div></a>
	</div>
	<section class="sellSlide sellOne" id="hotelDetail">
	<h1>My Reservation is at...</h1>
	<div class="row">
		<div class="large-6 medium-6 small-12 columns" id="nameContain">
			<input type="text" id="SearchName" placeholder="Name" class="hotel_name_input"/>
			<div class="hotelNameError control-group">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="large-6 medium-6 small-12 columns" id="adrressContain">
			<input type="text" id="SearchAddress" placeholder="Address"/>
			<div class="addressError control-group">
				<span class="help-inline"></span>
			</div>
		</div>
	</div>
	<!-- if hotel is not stored show row below -->
	<div class="row addHotel" id="cityDiv">
		<div class="large-12 medium-12 small-12 columns"
			style="padding-right: 32%; padding-left: 32%; margin-top: 7px;">
			<input type="text" class="cityName" id="SearchCity" placeholder="Add City"></input>
			<div class="cityError control-group">
				<span class="help-inline"></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="large-12 medium-12 small-12 columns">
				<button class="sellSubmit" id="hotelSelect">Submit</button>
		</div>
	</div>
	</section>

	<section class="sellSlide sellTwo" id="roomDetail">
	<h1>My room is a:</h1>
	<span id="roomTypeError"></span>
	<div class="row" id="sellType">
		<div class="large-6 medium-12 small-12 columns" id="sellTypeA">
				<button class="radius roomType roomTypeEven" id="sellDouble" value="double">Double</button>
				<button class="radius roomType" id="sellQueen" value="queen">Queen</button>
		</div>
		<div class="large-6 medium-12 small-12 columns" id="sellTypeB">
				<button class="radius roomType roomTypeEven" id="sellKing" value="king">King</button>
				<button class="radius roomType" id="sellSuite" value="suite">Suite</button>
		</div>
	</div>
	<div class="row">
		<div class="roomTypeOther">
			<button class="radius roomType roomTypeOtherLink" value="other">Other</button>
		</div>
		<div class="large-12 medium-12 small-12 columns hide otherRoomType"
			style="padding-right: 30%; padding-left: 30%;" id="otherContain">
			<input type="text" id="otherRoom" placeholder="Other" class="hide"/>
		</div>
	</div>
	<div class="row">
		<div class="large-12 medium-12 small-12 columns">
				<button class="sellSubmit" id="roomSelect">Submit
		</div>
	</div>
	</section>

	<section class="sellSlide sellThree" id="dateDetail">
	<h1 id="3title">My reservation dates are:</h1>
	<span id="dateError"></span>
	<div class="row" id="sellCalender">
		<div id="from"
			class="large-6 medium-6 small-12 columns datepicker sellCalender">
			<p>Check-In:</p>
		</div>
		<div id="to"
			class="large-6 medium-6 small-12 columns datepicker sellCalender">
			<p>Check-Out:</p>
		</div>
	</div>
	<button class="sellSubmit" id="dateSelect">Submit</button>
	</section>


	<section class="sellSlide sellFive" id="priceDetail">
	<h1>How much would you like to charge:</h1>
	<span id="priceError"></span>
	<div style="margin: 2% 40% 0 40%">
		<input type="text" id="sellPrice" placeholder="Price Per Night" maxlength="4"/>
	</div>
	<div id="priceTotal">$$$</div>
	<div id="totDet">
		<p>
			*Not including OSR fees, see <a href="#">How it Works</a> for more
			details.
		</p>
	</div>
	<button class="sellSubmit" id="priceSubmit">Submit</button>
	</section>

	<section class="sellSlide sellSix" id="confirmationCodeDetail">
	<h1>Do you have a reservation confirmation code:</h1>
		<div class="large-12 medium-12 small-12 columns" id="ccInput">
			<input type="text" id="confirmationCode" placeholder="Confirmation Code"></input>
			<span id="confirmationCodeError"></span>
		</div>
		<button class="sellSubmit sellRoom">Submit</button>
	</section>
</script>
	<div class="sellContainer"></div>
	<div id="modal2" class="reveal-modal medium show-for-large-up"
		data-reveal></div>
	<div id="modal3cart" class="reveal-modal medium" data-reveal></div>
	<div id="modal1" class="reveal-modal medium" data-reveal></div>


	<div id="SellConf" class="reveal-modal" data-reveal></div>

	<!-- footer -->

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

	<div class="row" id="footer_bottom">

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
	<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.abide.js"/>"></script>

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
		src="<c:url value="/js/model/ExternalSellModel.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/js/model/HotelAddModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/SellView.js"/>"></script>

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

	<script>
		var sellModel = Backbone.Model.extend();
		var sellView = new SellView({
			model : new sellModel({
				"baseUrl" : "${pageContext.servletContext.contextPath}"
			})
		});
		sellView.render();

		//datePicker

		$(function() {
			$(document).foundation();
			var cities = new Array();
			$
					.ajax({
						url : "${pageContext.servletContext.contextPath}/hotel/cityName.json",
						type : "GET",
						success : function(response) {
							for (var i = 0; i < response.length; i++) {
								cities.push(response[i]);
							}
						}
					});

			$(".cityName").autocomplete({
				source : cities,
				select : function(event, ui) {
					if (event.keyCode = $.ui.keyCode.TAB) {
						//this.value =this.value + " ";
						this.value = ui.item.value;
					}
				}
			});

		});
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
		<jsp:param value="sell" name="pagename" />
	</jsp:include>
</body>
</html>