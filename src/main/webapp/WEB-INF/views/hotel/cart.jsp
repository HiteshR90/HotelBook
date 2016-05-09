<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>One Suite Room | Buy or Sell Hotel Rooms Instantly</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/img/OSR-fav.ico"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/foundation.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/slick.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    <link rel="stylesheet" href="<c:url value="../css/normalize.css" />"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <style type="text/css">
 		.error{color: red;}
    </style>
  </head>
<body>
  <header>
      <nav class="top-bar" data-topbar role="navigation">
        <ul class="title-area">
          <li class="name">
            <h1><a href="${pageContext.servletContext.contextPath}"><img src="<c:url value="/img/OSR-logo.1.png" />"></a></h1>
          </li>
<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
          <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
        </ul>
        <section class="top-bar-section">
<!-- Right Nav Section -->
         <%--  <ul class="right">
            <li><a href="#">How It Works</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">Search For a Room</a></li>
            <li class="modal"><a href="#" onclick="sellRoom()">List Your Room</a></li>
            <c:if test="${isLogin=='false'}">
						<li class="modal"><a href="#" onclick="loginReveal()">Login/Signup</a></li>
					</c:if>
					<c:if test="${isLogin=='true'}">
						<!-- on click on this link you get modal, fill model with correct content, if user is signed in beplace login/signup with My Account. and add class has-dropdown to the li next to class modal -->
						<li class="has-dropdown"><a href="#">${userName}</a>
								<ul class="dropdown">
									<!-- <li><a href="#">Dashboard</a></li> -->
									<li class="active"><a
										href='<c:url value="/user/logout"></c:url>'>Log out</a></li>
								</ul>
						</li>
					</c:if>
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
<div class="main-container">
<!-- page content replace below main-content -->
  <div class="content-container secondBanner">
    <div class="row" id="settingsTitle"> 
      <div class="large-12 medium-12 small-12 columns">
        <h5>Cart</h5>
        <label></label>
      </div>
    </div> 
<!-- emppty cart --> 
  <div class="row noPad allTheWay cartContain emptyCart">
    <div class="large-8 medium-12 small-12 columns cartDetsCont">
      <div class="row cartTitle center">
        <div class="large-12 medium-12 small-12 columns">
          <h5 class="Caps1st maroon">your shopping cart is currently empty</h5>
          <p class="p1 Caps1st"><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">click here</a> to search for a suite room</p>
        </div>
      </div>
    </div>
  </div>
  	 <div id="content" class="row noPad allTheWay cartContain"></div>
   <div id="paymentContent" class="row noPad allTheWay cartContain" style="display: none;">
   <div class="large-8 medium-12 small-12 columns cartDetsCont">
      <form class="selectPayment" action="">  
      <div>
        <h5 class="Caps1st maroon">choose your payment method:</h5>
      </div>
      <div class="ccSection">
        <div class="row ccTemplateTitle">
          <div class="large-4 medium-4 small-4 columns CCTypeTitle">
            <h5 class="Caps1st">Your cards</h5>
          </div>
          <!-- <div class="large-4 medium-4 small-4 columns center ccNameTitle">
            <h5 class="Caps1st">Name on Card</h5>
          </div>
          <div class="large-4 medium-4 small-4 columns center ccDateTitle">
            <h5 class="Caps1st">expires on</h5>
          </div> -->
        </div>
<!-- all CCs added below selectCC-->      
        <div class="ccContainer">
          <div class="row ccTemplate">
            <div class="large-4 medium-4 small-4 columns CCType">
              <p class="p1 Caps1st"><input type="radio" id="paymentType" name="paymentType" value="Card"> card type </p>
            </div>
            <div class="large-4 medium-4 small-4 columns center ccName" style="display: none;">
              <p class="p1 Caps1st"><select>
							<option>Credit Card</option>
							<option>Debit Card</option>
							</select></p>
            </div>
            <!-- <div class="large-4 medium-4 small-4 columns center ccName">
              <p class="p1 Caps1st">john Doe</p>
            </div>
            <div class="large-4 medium-4 small-4 columns center ccDate">
              <p class="p1 Caps1st">mn/yr</p>
            </div> -->
          </div>
        <!--   <div class="row ccTemplate">
            <div class="large-4 medium-4 small-4 columns CCType">
              <p class="p1 Caps1st"><input type="radio" name="paymentType" value=""> add new card</p>
            </div>
            <div class="large-4 medium-4 small-4 columns center ccName">
            </div>
            <div class="large-4 medium-4 small-4 columns center ccDate">
            </div>
          </div> -->
        </div>
      </div>
      <hr>
      <div class="ppSection">
      <div class="row">
        <div class="large-12 columns ppTitle">
          <h5 class="Caps1st">PayPal Account</h5>
        </div>   
      </div>
        <div class="row">
          <div class="large-12 small-12 columns ppAccount">
            <p class="p1 Caps1st"><input type="radio" checked="checked" name="paymentType" id="paymentType" value="Paypal"> Paypal Account Name</p>
          </div>
        </div>
        <!-- <div class="row">
          <div class="large-12 small-12 columns ppAccount">
            <p class="p1 Caps1st"><input type="radio" name="paymentType" value=""> add a PayPal Account</p>
          </div>
        </div> -->
      </div>
      </form>
      <div style="margin-top:4%">
        <button class="red right" id="paymentBack">back</button><button id="paymentbtn" class="red right">Use this payment method</button>
      </div>
    </div>
    <!-- calculate total of everything in cart in section below -->
  <!--  <div class="large-3 medium-12 small-12 columns center cartSubTotal">
      <div>
        <h5 class="Caps1st">Subtotal (# Rooms(s))</h5>
replace > # with a number, so for example : subtotal ( 1 Room);  if # less than 2 hide the s ; if # greater than or equal to 2 then add s remove () around it; example subtotal ( 5 Rooms )
    </div>
      <div>
        <h5 class="green">$$$$</h5>
      </div>
      <div>
        <button id="payCheckout" class="exspand allTheWay red" >checkout</button>
      </div>
    </div> -->
   </div>
   <div id="bookedContent" class="row noPad allTheWay cartContain"></div>

<!-- confirmation -->
   <div id="confirmedContent" class="row noPad allTheWay cartContain" style="display: none;">
    <div class="large-8 medium-12 small-12 columns cartDetsCont">
      <div class="row cartTitle center">
        <div class="large-12 medium-12 small-12 columns">
          <h5 class="Caps1st maroon">thank you for making your reservation </h5>
          <h5 class="Caps1st">an e-mail confirmation will be sent to you shortly</h5>
          <p class="p1 Caps1st"><a href="${pageContext.servletContext.contextPath}/hotel/rooms-map">click here</a> to search for new suite rooms</p>
        </div>
      </div>
    </div>
  </div>
  
</div>

<!-- modals below -->
	<div id="modal2" class="reveal-modal" data-reveal>
			<div id="viewCartDiv" class="row moreDetsCont"></div>	
	</div>
	<div id="CreditCardModal" class="reveal-modal small" data-reveal>
		<div class="card-div"></div>
	</div>
		<!-- <div class="reveal-modal medium" id="CreditCardModal" data-reveal>
			<a class="close-reveal-modal" aria-label="Close">&#215;</a>
			<div class="commonError">
				<span class="help-inline"></span>
			</div>
			<div class="creditcard-field">
				<tr>
					<td><label>Card Number </label></td>
					<td><input id="cardNumber" maxlength="19" class="numberOnly"
						type="text" />
						<div class="cardNumber">
							<span class="help-inline"></span>
						</div></td>
				</tr>

				
			</div>
			<div class="cc-cvv">
				<tr>
					<td><label>CVV / CID(AMEX) </label></td>
					<td><input id="cvv" class="numberOnly" maxlength="4"
						type="text" />

						<div class="cvv">
							<span class="help-inline"></span>
						</div></td>
				</tr>
			</div>
			<tr>
				<td><label>Card Expiration</label> </td>
				<td>
					<div class="row collapse">
						<div class="large-6 medium-6 small-6 columns">
							<input id="expMonth" class="numberOnly" maxlength="2"
								placeholder="month" type="text" />
								<div class="expMonth">
									<span class="help-inline"></span>
								</div>
						</div>
						<div class="large-6 medium-6 small-6 columns">
							<input id="expYear" class="numberOnly" maxlength="4"
								placeholder="year" type="text" />
							<div class="expYear">
								<span class="help-inline"></span>
							</div>
						</div>
						
					</div>
				</td>
			</tr>

			<div class="CCname-field">
				<tr>
					<td><label>Name on Card </label></td>
					<td><input id="nameOnCard" type="text" /></td>
				</tr>
			</div>

			<div class="CCaddress-field">
				<tr>
					<td><label>Address (if different from mailing.) </label></td>
					<td><input id="address" type="text" /></td>
				</tr>
			</div>

			<div class="city-field">
				<tr>
					<td><label>City</label> </td>
					<td><input id="city" type="text" /></td>
				</tr>
			</div>

			<div class="state-field">
				<tr>
					<td><label>State</label> </td>
					<td><input id="state" type="text" /></td>
				</tr>
			</div>
			<div class="zip-field">
				<tr>
					<td><label>Zip-Code </label></td>
					<td><input id="zipCode" class="numberOnly" type="text" /></td>
				</tr>
			</div>
			<div class="large-12 medium-12 small-12 columns">
				<button class="right radius card-payment">SUBMIT</button>
			</div>
			<div class="tabs-content">
			<div class="card-div" id="panel2-2">
			<table style="width:100%; border : none;">
				<div class="commonError">
					<span class="help-inline"></span>
				</div>
				<div class="creditcard-field">
				<tr>
					<td><label>Card Number</td>
					<td><input id="cardNumber" maxlength="19"
						class="numberOnly" type="text" />
					<div class="cardNumber">
						<span class="help-inline"></span>
					</div> </td></label>
				</tr>
				</div>
				<div class="cc-cvv">
					<tr>
						<td><label>CVV / CID(AMEX)</td>
						<td><input id="cvv" class="numberOnly"
						maxlength="4" type="text" /></td>
					</tr>
					<div class="cvv">
						<span class="help-inline"></span>
					</div></label>
				</div>
				
				<tr>
					<td>
						<label>Card Expiration
					</td>
					<td>
						<div class="row collapse">
						<div class="large-6 medium-6 small-6 columns">
							<input id="expMonth" class="numberOnly" maxlength="2"
								placeholder="month" type="text" />
						</div>
						<div class="large-6 medium-6 small-6 columns">
							<input id="expYear" class="numberOnly" maxlength="4"
								placeholder="year" type="text" />
						</div>
						<div class="expMonth">
							<span class="help-inline"></span>
						</div>
						<div class="expYear">
							<span class="help-inline"></span>
						</div></label>
					</div>
					</td>
				</tr>
				
				<div class="CCname-field">
					<tr>
						<td><label>Name on Card</label></td>
						<td><input id="nameOnCard" type="text" /></td>
					</tr>
					
				</div>
				<div class="CCaddress-field">
					<tr>
						<td><label>Address (if different from mailing.)</label></td>
						<td><input id="address" type="text" /></td>
					</tr>
				</div>
				<div class="city-field">
					<tr>
						<td><label>City</label></td>
						<td><input id="city" type="text" /></td>
					</tr>
				</div>
				<div class="state-field">
					<tr>
						<td><label>State</label></td>
						<td><input id="state" type="text" /></td>
					</tr>
				</div>
				<div class="zip-field">
					<tr>
						<td><label>Zip-Code</label></td>
						<td><input id="zipCode" class="numberOnly" type="text" /></td>
					</tr>
				</div>
				<div class="large-12 medium-12 small-12 columns">
					<tr>
						<td colspan = "2">
							<button class="right radius card-payment">SUBMIT</button>
						</td>
					</tr>
					
				</div>
				</table>
			</div>
		</div>
		</div>
 -->
 <div class="sellContainer"></div>
</div>


  
    <footer>
     <div class="row top-footer">
      <div class="large-12 medium-12 small-12 columns">
      </div>
     </div>
     <div class="row bottom-footer">
      <div class="large-12 medium-12 small-12 columns">
        <div>©2015 One Suite Room. All rights reserved.</div>
      </div>
     </div>
    </footer>
<!-- sticky footer -->

	
	 
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
			
    <script src="<c:url value="../js/sticky-footer.js"/>"></script>
    <script src="<c:url value="../js/jquery.raty.js"/>"></script>
    <script src="<c:url value="/js/foundation/foundation.js"/>"></script>
    <script src="<c:url value="/js/foundation/foundation.accordion.js"/>"></script>
    <script src="<c:url value="/js/foundation/foundation.tab.js"/>"></script>
    <script src="<c:url value="/js/foundation/foundation.topbar.js"/>"></script>
    <script src="<c:url value="/js/foundation/foundation.dropdown.js"/>"></script>
    <%-- <script src="<c:url value="/js/foundation/foundation.equalizer.js"/>"></script> --%>
    <script src="<c:url value="/js/foundation/foundation.reveal.js"/>"></script>
    <script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
    <script type="text/javascript"
		src="<c:url value="/js/underscore-min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/backbone-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/handlebars.js"/>"></script>
	<script src="https://apis.google.com/js/client.js"></script>
	<script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
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
		src="<c:url value="/js/view/ForgotPasswordSuccessView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/ForgotPasswordView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/LoginPopupView.js"/>"></script>
	
	<script type="text/javascript"
			src="<c:url value="/js/model/HotelAddModel.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/facebook.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/CreditCardModel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/view/CreditCardView.js"/>"></script>
	    
	<script type="text/javascript"
		src="<c:url value="/js/view/CartView.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/js/model/CartDetailModel.js"/>"></script>
	
    
    <script type="text/javascript"
			src="<c:url value="/js/model/HotelDetailModel.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/view/HotelDetailView.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/view/RoomListViewMap.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/js/view/SellView.js"/>"></script>
    <script>
    $(document).foundation();
    this.cartDetailModel = new CartDetailModel({
		"baseUrl" : "${pageContext.servletContext.contextPath}"
	});
	this.cartView = new CartView({
		model : this.cartDetailModel
	});
	cartView.render();
    //modal reveal
    $( '.modal' ).click(function(){
     // $('#loginModal').foundation('reveal', 'open'); 
    });

//checkbox for remeber me on this compute in login modal
    function myFunction() {
        var x = document.getElementById("remeberMe");
        x.checked = true;
    }
//image swap 
    var sourceSwap = function () {
        var $this = $(this);
        var newSource = $this.data('alt-src');
        $this.data('alt-src', $this.attr('src'));
        $this.attr('src', newSource);
    }
// social media login in modal 
    $(function () {
        $('img.loginWith').hover(sourceSwap, sourceSwap);
    });

//star rating in advance search reveal
      $('.starRating').raty({ readOnly:  true,
        score: 3 });
      $(function() {
    	  	$(document).foundation();
			$("#modal1").on("authed",function(event, f) 
			{
				//$('#modal1').empty();
				//$('#modal1').foundation('reveal', 'close');
				//alert('hello');
				//location.href = "${pageContext.servletContext.contextPath}/hotel/cart";
				if(f== "login")
				{
					location.href = "${pageContext.servletContext.contextPath}/hotel/cart";
				}
			});

		});
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
		}
	</script>
  </body>
