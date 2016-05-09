<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>One Suite Room | Buy or Sell Hotel Rooms Instantly</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/img/OSR-fav.ico"/>">

<link rel="stylesheet" href="<c:url value="/css/foundation.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery.raty.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/styles.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/normalize.css"/>" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<style type="text/css">
.main-container {
	min-height: 500px;
}
.ui-datepicker { position: relative; z-index: 10000 !important; }
button.white {
	font-family: 'Open Sans', sans-serif !important;
	font-weight: normal;
	background-color: #fff !important;
	border: 1px solid #333 !important;
	text-transform: capitalize;
	color: #333;
	font-size: .8437em;
	box-shadow: none;
	padding: 7px 7px;
	top: 4.4px;
	transition: all .2s ease-in-out;
}
</style>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
</head>
<body style="overflow: hidden;">
	<header>
		<nav class="top-bar" data-topbar role="navigation" id="theTop">
			<ul class="title-area">
				<li class="name">
					<h1>
						<a href="${pageContext.servletContext.contextPath}"><img src="<c:url value="/img/OSR-logo.1.png"/>"></a>
					</h1>
				</li>
				<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
			</ul>
			<section class="top-bar-section">
				<!-- Right Nav Section -->
				 <%-- <ul class="right">
            <li><a href="#">How It Works</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">Search For a Room</a></li>
            <li class="modal"><a href="#" onclick="sellRoom()">List Your Room</a></li>
			<li class="accountMenu"><a href="#" onclick="loginReveal()">Login/Signup</a></li>
			<!-- end My account drop down -->
            <li class="hide"><a href="#">Cart</a></li>
          </ul> --%>
				<ul class="right">
					<li><a href="#">How It Works</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">Search For a Room</a></li>
					<li><a href="#" onclick="sellRoom()">List Your Room</a></li>
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
					<!-- <li  class="accountMenu"><a href="#" onclick="loginReveal()">Login/Signup</a>
						on click on this link you get modal, fill model with correct content, if user is signed in beplace login/signup with My Account. and add class has-dropdown to the li next to class modal
						<ul class="dropdown">
							<li><a href="#">Dashboard</a></li>
							<li class="active"><a href="#">Logout</a></li>
						</ul></li> -->
					<!-- end My account drop down -->
					<li class="hide"><a href="#">Cart</a></li>
				</ul>
			</section>
		</nav>
	</header>
	<!-- end Header -->
	<div class="buyRoomList main-container"></div>
	<script type="text/template" id="room_lists_template">
		<!-- page content replace below main-content -->
		<div>
			<div class="top-bar" id="search-bar" data-topbar role="navigation">
				<ul class="title-area">
					<li class="has-form">
						<div class="row">
							<div class="large-12 medium-11 small-6 columns searchContainer">
								<form>
									<input type="text" class="cityName"
										placeholder="Search a Location, Event, Conference, Hotel, etc.">
								</form>
							</div>
						</div>
					</li>
					<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
					<li class="toggle-topbar menu-icon"><a href="#"><span></span></a></li>
				</ul>

				<section class="top-bar-section">
					<ul class="left;">
						<li class="srchgen">
							<form class="srchinp">
								<input class="from" type="text" placeholder="Check In">
							</form>
						</li>
						<li class="srchgen">
							<form class="srchinp">
								<input class="to" type="text" placeholder="Check Out">
							</form>
						</li>
						<li class="srchgen">
							<form class="srchinp">
								<input class="amount" type="number" placeholder="Price">
							</form>
						</li>
						<li class="srchgen">
							<form class="srchinp">
								<input class="subMitBtn" type="button" value="Search" style="margin:0 0 0 10px; float:left"/>
							</form>
						</li>

						<ul>
							<!-- Right Nav Section -->
							<ul class="right">

								<li><button class="white advsSrch mapPopBttn">Advanced
										Search</button></li>
								<li><button class="white heatMap mapPopBttn">Heatmaps</button></li>
								<li><button class="white mapView mapPopBttn">Map
										Results</button></li>
							</ul>
				</section>

			</div>
			<!-- map-->
			<div class="row" style="min-width: 100%; margin: 0px; padding: 0px">
				<div class="large-12 medium-12 small-12 columns" id="mapContainer">
					<!-- Advanced Search View -->
					<div class="left advSrcView hide searchBoxes"
						style="text-align: left; height: 30%;">
						<div class="adsrcbttn right" style="padding: 5px;">
							<a>X</a>
						</div>
						<div class="row"
							style="margin: 0px; padding: 10px; font-family: 'Open Sans', sans-serif; text-transform: capitalize;">
							<div>
								<div>
									<label class="left"
										style="width: 20%; margin-bottom: 10px; font-size: .75em">booking
										type</label>
									<div class="left">
										<input type="text" class="modalInput"
											style="height: 2em; margin-bottom: 15px;">
									</div>
								</div>
								<div style="clear: both;">
									<label class="left"
										style="width: 20%; margin-bottom: 10px; font-size: .75em">guest</label>
									<div class="left">
										<input type="number" class="modalInput"
											style="height: 2em; margin-bottom: 10px">
									</div>
								</div>
								<div style="clear: both;">
									<label class="left"
										style="width: 20%; margin-bottom: 10px; font-size: .75em">hotel
										rating</label>
									<div class="starRating left"></div>
								</div>
								<div style="clear: both;">
									<label class="left"
										style="width: 20%; margin-bottom: 10px; font-size: .75em">chains</label>
									<div class="left">
										<select style="height: 2em;">
											<option value=""></option>
											<option value="">example 1</option>
											<option value="">example 2</option>
											<option value="">example 3</option>
											<option value="">example 4</option>
										</select>
									</div>
								</div>
								<!--<div style="clear: both;">
									<label class="left"
										style="width: 20%; margin-bottom: 0px; font-size: .75em">property
										type</label>
									<div class="left">
										<select style="height: 2em;">
											<option value=""></option>
											<option value="">example 1</option>
											<option value="">example 2</option>
											<option value="">example 3</option>
											<option value="">example 4</option>
										</select>
									</div>
								</div>-->
							</div>
						</div>
					</div>
					<!-- heat map view -->
					<div class="right heatMapView hide searchBoxes"
						style="text-align: left; z-index: 5; right: 10px !important; height: auto;">
						<div class="exit right" style="padding: 5px;">
							<a>X</a>
						</div>
						<div class="row"
							style="margin: 0; padding: 5px; font-family: 'Open Sans', sans-serif;">
							<div class="large-6 medium-6 small-12 columns"
								style="margin-top: 10%">
								<label style="font-weight: bold; color: #a11e21;">Heatmaps</label>
								<form>
									<label> <input type="checkbox" id="rememberMe">
										Tourism
									</label>
								</form>
								<form>
									<label> <input type="checkbox" id="rememberMe">
										Shopping
									</label>
								</form>
								<form>
									<label> <input type="checkbox" id="rememberMe">
										Nightlife
									</label>
								</form>
								<form>
									<label> <input type="checkbox" id="rememberMe">
										Food
									</label>
								</form>
							</div>
							<div class="large-6 medium-6 small-12 columns"
								style="margin-top: 2%">
								<label style="font-weight: bold; color: #a11e21;">Events</label>
								<form>
									<label> <input type="checkbox" id="rememberMe">
										Show Events
									</label>
								</form>
							</div>

						</div>
					</div>
					<!-- results list view map overlay-->
					<div class="right listView searchBoxes">
						<div class="row"
							style="margin: 0px; padding: 20px 10px; font-family: 'Open Sans', sans-serif; font-size: .9em; font-weight: bold;">
							<div class="large-12 medium-12 small-12 columns add-cart-error"
								style="text-align: left; margin-bottom:10px; display:block;"></div>
							<div class="large-6 medium-6 small-6 columns results"
								style="text-align: left"># results</div>
							<div class="large-6 medium-6 small-6 columns"
								style="text-align: right">sort by</div>
						</div>
						<div style="overflow-y: scroll; height: 80%;">
							<dl class="accordion" data-accordion>
								<dd class="accordion-navigation"></dd>
							</dl>
						</div>
					</div>
					<!-- insert map ipa below here -->
					<div id="map_canvas" style="width: 100%;height: 890px;">map div</div>				
				</div>
			</div>
		</div>
		<!-- end main-content conatiner replace content above this div-->
	</script>
	
	<!-- <footer>
		<div class="row top-footer">
			<div class="large-12 medium-12 small-12 columns"></div>
		</div>
		<div class="row bottom-footer">
			<div class="large-12 medium-12 small-12 columns">
				<div>©2015 One Suite Room. All rights reserved.</div>
			</div>
		</div>
	</footer> -->
		<!-- sticky footer -->
		<!-- modals below -->

		<!-- <div id="modal1" class="small" style="display: none;">jklsjdflksdjlkjlkjlk</div> -->
		<div id="modal1" class="reveal-modal medium" data-reveal></div>
		<div id="modal2" class="reveal-modal" data-reveal></div>

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

		<%-- 	<script type="text/javascript"
		src="<c:url value="/js/sticky-footer.js"/>"></script> --%>
		<script type="text/javascript"
			src="<c:url value="/js/jquery.raty.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.topbar.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>


		<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
		<script src="<c:url value="/js/slick.js"/>"></script>
		<script src="<c:url value="/js/jquery.twbsPagination.js"/>"
			type="text/javascript"></script>

		<script type="text/javascript"
			src="<c:url value="/js/underscore-min.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/backbone-min.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/handlebars.js"/>"></script>
		<script src="https://apis.google.com/js/client.js"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/LoginModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/FbLoginModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/GoogleLoginModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/RegisterModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/ForgotPasswordModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/LoginPopupModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/ReservationDetailModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/BookingModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/ExternalSellModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/HotelAddModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/AddToCartModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/HotelDetailModel.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/model/CartDetailModel.js"/>"></script>
				
		<script type="text/javascript"
			src="<c:url value="/js/view/LoginPopupView.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/ForgotPasswordSuccessView.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/ForgotPasswordView.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/facebook.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/HotelDetailView.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/CartDetailView.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/RoomListViewMap.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/view/SellView.js"/>"></script>
			
		<script>
		$(document).ready(function() {
			$(document).foundation();
			$("#modal1").on("authed", function(event, f) {
				//$('#modal1').empty();
				//$('#modal1').foundation('reveal', 'close');
				if(f== "login")
				{
					location.href = "${pageContext.servletContext.contextPath}/hotel/rooms-map";
				}
			});
		});
		function isFunction(obj) {
			return jQuery.type(obj) === "function";
		}
	</script>
		<script type="text/javascript">
		/*function getParam(name) {
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
		})*/
		//var cityName = getParam("cityName");
		var url = "${pageContext.servletContext.contextPath}";
		var roomListModel = Backbone.Model.extend();
		var roomListModel = new roomListModel({
			'baseUrl' : url
		});
		/* if (cityName != null) {
			roomListModel.set('cityName', cityName);
		} */
		roomListModel.set('baseUrl', url);

		var roomListView = new RoomListView({
			model : roomListModel
		});
		roomListView.render();
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
		</script>
		<script>
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
			$(document).foundation();
		}
	</script>

		<script type="text/javascript"
			src="<c:url value="/js/CartDetail.js"/>"></script>
		<c:if test="${isLogin=='true'}">
			<script type="text/javascript">
			$(document).ready(function() {
				cartDetail(true);
			});
		</script>
		</c:if>
		
</body>