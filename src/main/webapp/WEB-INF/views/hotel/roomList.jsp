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
<link rel="shortcut icon" type="image/x-icon href="<c:url value="/img/OSR_Favicon.png"/>">
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
	href="<c:url value="/css/star-rating.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/slick.css"/>">


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
				src="<c:url value="/img/OSR_viewcart.png"/>" alt="cart" /></a>
			<div class="cartCount">-</div>
		</div>

		<!-- #EndLibraryItem -->
	</div>

	<!-- End Header -->

	<div data-equalizer="" class="row buyRoomList">
		<!-- Right Side Content -->

		<!-- end small screen -->
		<div data-equalizer-watch=""
			class="large-9 small-12 medium-9  push-3  columns roomList-container show-for-large-up"
			style="height: 937px;">
			<div class="large-12 medium-12 small-12 columns roomList-content">
				<c:if test="${!empty roomList}">
					<div class="large-12 medium-12 small-12 columns "
						id="right-content-Buy">
						<div class="row" style="margin-bottom: 2%">
							<c:forEach var="room" items="${roomList.roomData}">
								<div
									class="large-3 medium-6 small-6 columns thContainer end room-list-div-${room.roomId}">
									<a href="#" onclick="hotelDetail(${room.roomId},true);"><img
										src="<c:url value="/images/hotelRooms/${room.imagePath}"/>" /></a>
									<div class="TH-descript">
										<p>${room.roomDesc}
										<p>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${empty roomList}">
				Data not found
				</c:if>
				<div cla ss="large-2 medium-2 small-2 columns hide"
					id="arrow-container">
					<div class="row hide">
						<div class="large-12 medium-12 small-12 columns hide"
							id="next-arrow">
							<img src="<c:url value="/img/OSR_redarrow.png"/>" alt="next" />
						</div>
					</div>
					<div class="row hide">
						<div class="large-12 medium-12 small-12 columns hide"
							id="prev-arrow">
							<img src="<c:url value="/img/OSR_redarrow.png"/>" alt="prev" />
						</div>
					</div>
				</div>


				<!-- hide pagnation when using data swap -->

				<div class="row" id="buyPagination">
					<div class="large-12 medium-12 small-12 columns ">
						<ul class="pagination">
							<c:if test="${!empty roomList}">
								<c:if test="${roomList.pageNo==1}">
									<li class="arrow unavailable">&laquo;</li>
								</c:if>
								<c:if test="${roomList.pageNo>1}">
									<li class="arrow"><a
										onclick="getRoomData(${roomList.pageNo-1})">&laquo;</a></li>
								</c:if>
								<c:forEach begin="1" end="${roomList.totalPage}" var="page">
									<c:choose>
										<c:when test="${page==roomList.pageNo}">
											<li class="current"><a>${page}</a></li>
										</c:when>
										<c:otherwise>
											<li><a onclick="getRoomData(${page})">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${roomList.pageNo==roomList.totalPage}">
									<li class="arrow unavailable"><a>&raquo;</a></li>
								</c:if>
								<c:if test="${roomList.pageNo<roomList.totalPage}">
									<li class="arrow"><a
										onclick="getRoomData(${roomList.pageNo+1})">&raquo;</a></li>
								</c:if>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- End Right Content -->
		<!--Left Sidebar -->
		<!-- This is source ordered to be pulled to the left on larger screens -->

		<div data-equalizer-watch=""
			class="large-3  hide-for-small medium-3 pull-9 columns show-for-large-up buySideBar"
			style="height: 937px;">
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

					<div class="row siderow">
						<div id="slider1-container"
							class="large-12 medium-12 small-12 columns">

							<div class="slider-range"></div>

							<label for="amount" style="margin-top: 5%">price range:</label> <input
								type="text" class="amount">

						</div>
					</div>

					<div style="margin-right: 15%" class="row siderow">
						<label style="margin-top: 5%; margin-left: 20%" for="starRating">star
							rating</label>
						<div id="starRating"
							class="rating large-12 medium-12 small-12 columns">
							<span><input type="radio" name="rating" id="str1"
								value="5"><label for="str1"></label></span> <span><input
								type="radio" name="rating" id="str2" value="4"><label
								for="str2"></label></span> <span><input type="radio"
								name="rating" id="str3" value="3"><label for="str3"></label></span>
							<span><input type="radio" name="rating" id="str4"
								value="2"><label for="str4"></label></span> <span><input
								type="radio" name="rating" id="str5" value="1"><label
								for="str5"></label></span>
						</div>
					</div>

					<div class="row siderow">
						<div class="large-6 medium-6 small-6 columns">
							<input type="text" class="onlyNumber" id="room-quantity" min="1"
								max="5">
							<p>rooms</p>
						</div>
						<div class="large-6 medium-6 small-6 columns">
							<input type="text" class="onlyNumber" id="guest-quantity" min="1"
								max="5">
							<p>adults</p>
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
							<select id="hotel-brand" onchange="getRoomData()">
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
						<div class="group large-12 medium-12 small-12 columns">

							<label style="margin-right: 12%">Amenites</label>
							<div class="checkboxs">
								<c:if test="${!empty amenities}">
									<c:forEach var="amenity" items="${amenities}">
										<label>${amenity}</label>
										<input type="checkbox" value="${amenity}" name="amenities"
											onchange="getRoomData()"></input>
										<br>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<div style="text-align: center" class="row siderow">
					<div style="margin-top: 5%" class="large-12 medium-12 columns">
						<a href="#"><img alt="advanced search"
							src="<c:url value="/img/OSR_advancedsearch.png"/>"></a>
					</div>
				</div>

			</div>
		</div>

	</div>


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


	<!-- Other JS plugins can be included here -->

	<script
		src="<c:url value="/js/jquery-ui-1.10.4.custom.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.countdownTimer.js"/>"></script>
	<script src="<c:url value="/js/slick.js"/>"></script>

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



	<script>
		$(document).ready(function() {
			$(document).foundation();
			$("#modal1").on("authed", function(event, f) {
				$('#modal1').empty();
				//$('#modal1').foundation('reveal', 'close');
				if (isFunction(f))
					f();
			});
			//  Check Radio-box Star rating
			$('.rating input').click(function() {
				$(".rating span").removeClass('checked');
				$(this).parent().addClass('checked');
			});

			$("input[name=rating]:radio").change(function() {
				//var userRating = this.value;
				getRoomData();
			});

			var cities = new Array();
			$.ajax({
				url : "cityName.json",
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
					getRoomData();
				}
			});
		});

		//  Slider
		$(function() {
			$(".slider-range")
					.slider(
							{
								range : true,
								min : 0,
								max : 3000,
								values : [ '${priceMinSelected}',
										'${priceMaxSelected}' ],
								scale : [ 1, '|', 3, '|', '5', '|', 15, '|', 30 ],
								slide : function(event, ui) {
									$(
											'.ui-slider-handle:eq(0) .price-range-min')
											.html('$' + ui.values[0]);
									$(".amount").val(
											"$" + ui.values[0] + " - $"
													+ ui.values[1]);
								},
								stop : function(event, ui) {
									getRoomData();
								}
							});
			$(".amount").val(
					"$" + $(".slider-range").slider("values", 0) + " - $"
							+ $(".slider-range").slider("values", 1));
		});

		//  date picker
		$(function() {
			$('.onlyNumber').keypress(function(e){
				if (e.which != 8 && e.which != 0
						&& (e.which < 48 || e.which > 57)) {
					return false;
				}
			});
			$(".from").datepicker({
				dateFormat : 'dd/mm/y',
				minDate : 0,
				changeMonth : true,
				onSelect : function(selectedDate) {
					var date2 = $(".from").datepicker('getDate');
					date2.setDate(date2.getDate() + 1);
					$(".to").datepicker('setDate', date2);
					$(".to").datepicker("option", "minDate", date2);
					setTimeout(function() {
						$(".to").datepicker('show');
					}, 16);
				}
			});

			$(".to").datepicker({
				dateFormat : 'dd/mm/y',
				minDate : 0,
				changeMonth : true,
				onSelect : function(dateText, inst) {
					if ($(".from").val() != null && $(".from").val() != "") {
						getRoomData();
					}
				},
				onClose : function() {
					var dt1 = $(".from").datepicker('getDate');
					var dt2 = $(".to").datepicker('getDate');
					//check to prevent a user from entering a date below date of dt1
					if (dt2 <= dt1) {
						var minDate = $(".to").datepicker('option', 'minDate');
						$(".to").datepicker('setDate', minDate);
					}
				}
			});
		});

		$("#guest-quantity").change(function() {
			getRoomData();
		});

		$("#room-quantity").change(function() {
			getRoomData();
		});

		function getRoomData(pageNo) {
			var url = '${pageContext.servletContext.contextPath}'
					+ '/hotel/rooms';
			var params = '?';
			params = params + 'priceMin='
					+ $('.slider-range').slider('values', 0) + '&';
			params = params + 'priceMax='
					+ $('.slider-range').slider('values', 1) + '&';
			//cityname
			if ($('.cityName').val() != null && $('.cityName').val() != '') {
				params = params + 'cityName=' + $('.cityName').val() + '&';
			}
			if ($('.from').val() != null && $('.from').val() != ''
					&& $('.to').val() != null && $('.to').val() != '') {
				params = params + 'startDate=' + $('.from').val() + '&';
				params = params + 'endDate=' + $('.to').val() + '&';
			}
			if ($("#hotel-brand").val() != null
					&& $("#hotel-brand").val() != '') {
				params = params + 'hotelBrand=' + $("#hotel-brand").val() + '&';
			}
			if ($("#room-quantity").val() != null
					&& $("#room-quantity").val() != ''
					&& $.isNumeric($("#room-quantity").val())) {
				params = params + 'noOfRoom=' + $("#room-quantity").val() + '&';
			}
			if ($("#guest-quantity").val() != null
					&& $("#guest-quantity").val() != ''
					&& $.isNumeric($("#guest-quantity").val())) {
				params = params + 'adults=' + $("#guest-quantity").val() + '&';
			}
			/* if ($(".sortByClass option:selected").val() != null
					&& $(".sortByClass option:selected")
							.val() > 0) {
				params = params
						+ 'sortBy='
						+ $(".sortByClass option:selected")
								.val() + '&';
			} */
			if (pageNo != null && pageNo > 0) {
				params = params + 'pageNo=' + pageNo + '&';
			}
			/* $('input[type=checkbox]:checked').each(
					function() {
						params = params + this.name + '='
								+ this.value + '&';
					}); */
			var hotelRating = $("input:radio[name=rating]:checked").val();
			if (hotelRating != null && hotelRating != ''
					&& $.isNumeric(hotelRating)) {
				params = params + 'hotelRatings=' + hotelRating + '&';
			}
			var amenities = $('input[name="amenities"]:checked').map(
					function() {
						return this.value;
					}).get();
			if (amenities != null && amenities != '') {
				params = params + 'amenities=' + amenities + '&';
			}

			if (params.substring(params.length - 1, params.length) == '&'
					|| params.substring(params.length - 1, params.length) == '?') {
				params = params.substring(0, params.length - 1);
			}

			url = url + encodeURI(params);
			location.href = url;
			//alert(url);
		}

		function getUrlVars() {
			var vars = [], hash;
			var hashes = window.location.href.slice(
					window.location.href.indexOf('?') + 1).split('&');
			for (var i = 0; i < hashes.length; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		}

		function loadData() {
			var noOfRoom = getUrlVars()["noOfRoom"];
			if ($.isNumeric(noOfRoom)) {
				$("#room-quantity").val(noOfRoom);
			}
			var adults = getUrlVars()["adults"];
			if ($.isNumeric(adults)) {
				$("#guest-quantity").val(adults);
			}
			var hotelBrand = getUrlVars()["hotelBrand"];
			if ($.isNumeric(hotelBrand)) {
				$("#hotel-brand").val(hotelBrand);
			}
			var hotelRating = getUrlVars()["hotelRatings"];
			if ($.isNumeric(hotelRating)) {
				//$('input:radio[name=rating]:checked').val(hotelRating);
				$('input:radio[name=rating]:nth(' + (5 - hotelRating) + ')')
						.attr('checked', true);
				$(".rating span").removeClass('checked');
				$('input:radio[name=rating]:nth(' + (5 - hotelRating) + ')')
						.parent().addClass('checked');
			}
			var amenities = getUrlVars()["amenities"];
			if (amenities != null && amenities != '') {
				var amenitiesArry = amenities.split(',');
				$('input[name="amenities"]').prop('checked', function() {
					return $.inArray(this.value, amenitiesArry) !== -1;
				});
			}
			//var priceMin = getUrlVars()["priceMin"];
			//var priceMax = getUrlVars()["priceMax"];
		}

		loadData();

		function isFunction(obj) {
			return jQuery.type(obj) === "function";
		}

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
		<jsp:param value="rooms" name="pagename" />
	</jsp:include>
</body>
</html>
