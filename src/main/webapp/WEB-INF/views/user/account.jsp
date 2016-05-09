<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	maximum-scale=1, minimum-scale=1, user-scalable=no '/>
<title>One Suite Room | Account</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/img/OSR-fav.ico"/>" />
<link rel="stylesheet" href="<c:url value="/css/foundation.css" />" />
<link rel="stylesheet" href="<c:url value="/css/normalize.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/slick.css"/>" />
<link rel="stylesheet" type="text/css"  href="<c:url value="/css/styles.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
 <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
 <link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
 <link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery.bxslider.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/multiple-select.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery.raty.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/checkbox.css"/>">
<%--
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/modal_2-3.css"/>">
--%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="<c:url value="/js/vendor/modernizr.js"/>"></script>
<script src="https://apis.google.com/js/client.js"></script>

<style type="text/css">
.ui-datepicker { position: relative; z-index: 10000 !important; }
c
      #settingsTitle h5 {
	margin-bottom: 0px;
}

#settingsCont {
	min-width: 100%;
}

#settingsNavCont {
	padding: 0px;
}

#settingsNav {
	padding: 0px;
	margin: 0px;
}

#settingsNav dd {
	padding: 20px;
	margin: 0px;
	border-bottom: 1px solid #A11E21;
}

#settingsNav dd:hover {
	background-color: #EEE;
}

#settingsNav dd a {
	color: #666;
	font-family: 'Open Sans', sans-serif;
}

#settingsNav dd a:hover {
	color:;
}

#settingsNav dd.active {
	background-color: #A11E21;
	padding: 20px;
	margin: 0px;
}

#settingsNav dd.active a {
	color: #FFF;
}

#settingsContentA {
	border-left: 2px solid #ccc;
	min-height: 450px;
}

#settingsContentB {
	padding: 10px;
}

.setcontentcont {
	padding-bottom: 20px;
}

.setconttitle {
	border-bottom: 1px solid #333;
	padding-top: 10px;
}

.setconttitle h4 {
	color: #A11E21;
}

.setcontsecleft {
	float: left;
	width: 45%;
	border-right: 1px solid #333;
	padding-right: 20px;
	padding-top: 10px;
}

.setcontsecright {
	float: left;
	width: 45%;
	padding-left: 20px;
	padding-top: 10px;
}

.mySettingsF {
	
}

.mySettingsF select {
	margin-top: 5px;
	background-color: #EEE;
	border-top: 1px solid: #CCC;
	border: none;
	width: 100%;
	margin-bottom: auto;
	-webkit-appearance: none;
	outline: none;
}

.side-nav .tab-title dd.active a {
	background-color: rgba(161, 30, 33, 1);
	/* Fallback for web browsers that don't support RGBa */
	background-color: #a11e21;
	/* RGBa with 0.5 opacity */
	filter: alpha(opacity = 60);
	/* For IE 5.5 - 7*/
	background-color: rgba(161, 30, 33, 1) !important;
	/* For IE8 and earlier */
}
</style>
</head>
<body>
	<header> <nav class="top-bar" data-topbar role="navigation">
	<ul class="title-area">
		<li class="name">
			<h1>
				<a href="${pageContext.servletContext.contextPath}"><img src="<c:url value="/img/OSR-logo.1.png" />"></a>
			</h1>
		</li>
		<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
		<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
	</ul>
	<section class="top-bar-section"> <!-- Right Nav Section -->
	 <ul class="right">
            <li><a href="#">How It Works</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">Search For a Room</a></li>
            <li><a href="#" onclick="sellRoom()">List Your Room</a></li>
			<li class="accountMenu has-dropdown"><a href="#" onclick="loginReveal()">Login/Signup</a></li>
			<!-- end My account drop down -->
            <li class="hide"><a href="#">Cart</a></li>
          </ul>
	</section> </nav> </header>
	<!-- end Header -->
	<script type="text/template" id="account_template"> 
	<div class="main-container">
		<!-- page content replace below main-content -->
		<div class="content-container secondBanner">
			<div class="row" id="settingsTitle">
				<div class="large-12 medium-12 small-12 columns">
					<h5>Settings</h5>
					<p class="p1"></p>
				</div>
			</div>
		<div class="row" id="settingsCont">
			<div class="large-3 columns" id="settingsNavCont">
					<dl id="settingsNav" class="side-nav" data-tab >
						<dd class="tab-title active">
							<a href="#myProfile" class="myProfile">Profile</a>
						</dd>
						<dd>
							<a href="#preferences"  class="hotelSearch">Preferences</a>
						</dd>
						<dd>
							<a href="#payment" class="bankAccount">Payment</a>
						</dd>
						<dd>
							<a href="#pendTrans" class="pendTrans">Pending Transactions</a>
						</dd>
						<dd>
							<a href="#history" class="mySales">History</a>
						</dd>

					</dl>
				</div>
			<div class="large-9 columns" id="settingsContentA" style="height: auto;">
				<div class="row" id="settingsContentB">
					<div class="large-12 columns setcontentcont accountPages transbox">
						<div class="tabs-content">
							<div class="content active" id="myProfile">
								<div class="setconttitle">
									<h4>My Profile</h4>
								</div>
								<div class="setcontsecleft">
									<span id="profileCommonMSG"></span>
									<h5>My Information</h5>
									<form>
										<label>First Name <input type="text"
											class="modalInput" id="fNameEdit" tabindex="1">
										</label>
									</form>
									<form>
										<label>Last Name <input type="text" class="modalInput" id="lNameEdit" tabindex="2">
										</label>
									</form>
									<form>
										<label>Username <input type="text" id="nameDisplay"
											class="modalInput" disabled="disabled">
										</label>
									</form>
									<form>
										<label>Email <input type="text" class="modalInput"
											id="emailDisplay" disabled="disabled">
										</label>
									</form>
									<form>
										<label>Phone Number <input type="text"
											id="phoneEdit" class="modalInput" tabindex="3">
											<div class="control-group phoneNumberError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<form>
										<label>Address <input type="text" id="addressEdit"
											class="modalInput" tabindex="4">
											<div class="control-group addressError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<form>
										<label>Country <input type="text" id="countryEdit"
											class="modalInput" tabindex="5">
											<div class="control-group countryError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<!--<button class="red left">cancel</button>-->
									<button class="red right saveProfile" tabindex="6">save</button>
								</div>
								<div class="setcontsecright" id="changePW">
									<span id="passwordCommonMSG"></span>
									<h5>My password</h5>
									<form>
										<label>Current password *<input type="password"
											id="CPWCurrentPW" class="modalInput" tabindex="7">
											<div class="control-group currentPasswordError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<form>
										<label>New password *<input type="password"
											id="CPWNewPW" class="modalInput" tabindex="8">
											<div class="control-group newPasswordError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<form>
										<label>confirm password *<input type="password"
											id="CPWRetypeNewPW" class="modalInput" tabindex="9">
											<div class="control-group retypeNewPasswordError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<!--<button class="red left">cancel</button>-->
									<button id="changePassword" class="red right" tabindex="10">save</button>
								</div>
							</div>

							<!-- profile
							-----------------
	 						END TAB1 CONTENT
							------------------
	 						-->

							<div class="content" id="preferences">
								<div class="setconttitle">
									<h4>My Prefereces</h4>
								</div>
								<div class="setcontsecleft">
									<h5>My Search Settings</h5>
									<form class="mySettingsF">
										<label>Floor Level <select tabindex="1">
												<option value=""></option>
												<option value="">example 1</option>
												<option value="">example 2</option>
												<option value="">example 3</option>
												<option value="">example 4</option>
										</select>
										</label>
									</form>
									<form class="mySettingsF">
										<label>Bed Type <select tabindex="2">
												<option value=""></option>
												<option value="">example 1</option>
												<option value="">example 2</option>
												<option value="">example 3</option>
												<option value="">example 4</option>
										</select>
										</label>
									</form>
									<form class="mySettingsF">
										<label>Check in &amp; check out <select tabindex="3">
												<option value=""></option>
												<option value="">example 1</option>
												<option value="">example 2</option>
												<option value="">example 3</option>
												<option value="">example 4</option>
										</select>
										</label>
									</form>
									<!--<button class="red left">cancel</button>-->
									<button class="red right save-preferene-all" tabindex="4">save</button>
								</div>
								<div class="setcontsecright">
									<div style="padding-bottom: 10px;">
										<h5>My Buy Notifications</h5>
										<form class="mySettingsF">
											<label>Email me results that match my search: <select tabindex="1">
													<option value=""></option>
													<option value="">Immediatley</option>
													<option value="">Once a Day</option>
													<option value="">If Still Available</option>
													<option value="">All</option>
											</select>
											</label>
										</form>
										<form class="mySettingsF">
											<label>Allow Push Notifications: <select tabindex="2">
													<option value=""></option>
													<option value="">Yes</option>
													<option value="">No</option>
											</select>
											</label>
										</form>
										<div style="display: inline-block; width: 100%;">
											<!--<button class="red left">cancel</button>-->
											<button class="red right" tabindex="3">save</button>
										</div>
									</div>
									<div style="border-top: 1px solid #333; padding-top: 10px;">
										<h5>My Sell Notifications</h5>
										<form class="mySettingsF">
											<label>Email Me Competitive Listings: <select tabindex="1">
													<option value=""></option>
													<option value="">Yes</option>
													<option value="">No</option>
											</select>
											</label>
										</form>
										<form class="mySettingsF">
											<label>Allow Push Notifications: <select tabindex="2">
													<option value=""></option>
													<option value="">Yes</option>
													<option value="">No</option>
											</select>
											</label>
										</form>
										<div style="display: inline-block; width: 100%;">
											<!--<button class="red left">cancel</button>-->
											<button class="red right" tabindex="3">save</button>
										</div>
									</div>
								</div>
							</div>

							<!-- preferences
							-----------------
	 						END TAB2 CONTENT
							------------------
	 						-->
							<div class="content" id="payment">
								<div class="setconttitle">
									<h4>My Payment Options</h4>
								</div>
								<div class="setcontsecleft">
									<span id="paypalCommonMSG"></span>
									<h5>PayPal</h5>
									<form class="mySettingsF">
										<label>Username / Email *<input type="text"
											class="modalInput" id="paypalId" tabindex="1">
											<div class="control-group paypalIdError">
												<span class="help-inline"></span>
											</div>
										</label>
									</form>
									<!--<button class="red left">cancel</button>-->
									<button class="red right updatePaypal" tabindex="2">save</button>
								</div>
								<div class="setcontsecright">
									<div style="padding-bottom: 10px;">
										<span id="bankCommonMSG"></span>
										<h5>Bank Account</h5>
										<form class="mySettingsF">
											<label>Account Number *<input type="text"
												class="modalInput onlyNumber" id="accountNumber" tabindex="1">
												<div class="control-group bankAccountNumberError">
													<span class="help-inline"></span>
												</div>
											</label>
										</form>
										<form class="mySettingsF">
											<label>Routing Number *<input type="text"
												class="modalInput onlyNumber" id="routingNumber" tabindex="2">
												<div class="control-group bankRoutingNumberError">
													<span class="help-inline"></span>
												</div>
											</label>
										</form>
										<!--<button class="red left">cancel</button>-->
										<button class="red right updateBank" tabindex="3">save</button>
									</div>
								</div>
							</div>
							<!-- payment
							-----------------
	 						END TAB3 CONTENT
							------------------
	 						-->
							<div class="content" id="pendTrans">
								<div class="setconttitle">
									<h4>My Pending Transactions</h4>
								</div>
								<%--<div class="setcontsecleft">
									<h5>Buy</h5>
								</div>
								<div class="setcontsecright">
									<div style="padding-bottom: 10px;">
										<h5>Sell</h5>
									</div>
								</div>
								<div id="pendTransDiv"></div> --%>
								<div id="pendTransDiv" style='overflow-y:scroll; overflow-x:hidden; height:580px; margin-top:30px;'></div>
							</div>
							<!-- pending transactions
							-----------------
					 		END TAB4 CONTENT
							------------------
					 		-->
							<div class="content" id="history">
								<div class="setconttitle">
									<h4>History</h4>
								</div>
								<div class="setcontsecleft">
									<h5>Buy History</h5>
									<div id="purchaseHistory">
  
        							</div>
								</div>
								<div class="setcontsecright">
									<div style="padding-bottom: 10px;">
										<h5>Sell History</h5>
										<div id="saleHistory">
        
        								</div>
									</div>
								</div>
							</div>
							<!-- History
							-----------------
					 		END TAB5 CONTENT
							------------------
					 		-->
						</div>
					</div>
				</div>
			</div>
	
			<!-- End Right Content -->
			<!--Left Sidebar -->
			<!-- This is source ordered to be pulled to the left on larger screens -->

				
			<!-- End Nav Sidebar -->
		</div>
		</div>
	</div>
	<!-- end main-content conatiner replace content above this div-->
	<footer>
	<div class="row top-footer">
		<div class="large-12 medium-12 small-12 columns"></div>
	</div>
	<div class="row bottom-footer" style="margin-top: 80px;">
		<div class="large-12 medium-12 small-12 columns">
			<div>© 2015 One Suite Room. All rights reserved.</div>
		</div>
	</div>
	</footer>
	</script>
	<!-- sticky footer -->
	<!-- modals below -->
	<div id="reList" class="reveal-modal tiny" data-reveal></div>
	<div class="container"></div>
	<div class="sellContainer"></div>

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

	<script src="<c:url value="/js/vendor/jquery.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script src="<c:url value="/js/jquery.bxslider.min.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.offcanvas.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.topbar.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
	<script src="<c:url value="/js/slick.js"/>"></script>
	<!-- Other JS plugins can be included here -->

	<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
	<script src="<c:url value="/js/jquery.raty.js"/>"
		type="text/javascript"></script>
	<script src="<c:url value="/js/jquery.twbsPagination.js"/>"
			type="text/javascript"></script>
	<script src="<c:url value="/js/jquery.multiple.select.js"/>"></script>
	
	<script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
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
		src="<c:url value="/js/model/PaypalModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/BankModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/ResellModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/ChangePassword.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/ProfileModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/PreferenceModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/AccountView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/SellView.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/CartDetail.js"/>"></script>
	<script>
		var accountModel = Backbone.Model.extend();
		var accountView = new AccountView({
			model : new accountModel({
				"baseUrl" : "${pageContext.servletContext.contextPath}"
			})
		});
		accountView.render();
		var url = "${pageContext.servletContext.contextPath}";
	
		$(document).ready(function() {
			$(document).foundation();
			$("#modal1").on("authed", function(event, f) {
				//$('#modal1').empty();
				//$('#modal1').foundation('reveal', 'close');
				//window.location = "${pageContext.servletContext.contextPath}/user/account"
				if(f== "login")
				{
					location.href = "${pageContext.servletContext.contextPath}/user/account";
				}
			});
		});

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
		//modal reveal
		/* $('.modal').click(function() {
			$('#loginModal').foundation('reveal', 'open');
		}); */
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

		//end slider

		//checkbox for remeber me on this compute in login modal
		function myFunction() {
			var x = document.getElementById("remeberMe");
			x.checked = true;
		}
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
</body>