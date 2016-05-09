var HotelDetailView = Backbone.View
		.extend({
			templateSuccess : Handlebars
					.compile('<!-- page content replace below main-content -->'
							+ '<a class="close-reveal-modal"><span style="text-size:12px;">&#215;</span></a>'
							+ '<div class="row moreDetsCont">'
							+ '<div class="modal-add-cart-error">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '<div class="large-8 medium-7 small-12 columns moreDets">'
							+ '<div class="large-12 columns" style="margin:0px;padding:0px;">'
							+ '<div style="padding:3% 5%;">'
							+ '<div class="row">'
							+ '<div class="large-8 medium-7 small-7 columns Caps1st noPad">'
							+ '<div class="resTemp">'
							+ '<div class="hotelDetails">'
							+ '<h5 class="Caps1st maroon">{{hotelName}}, {{city}} </h5>'
							+ '<p class="p1">Address : {{address}}</p>'
							+ '<p class="p1">Room Details : '
							+ '{{#each roomDetails}}'
							+ '{{this}} '
							+ '{{/each}}</p>'
							+ '<div class="starRating left allTheWay" data-score="{{hotelBrand}}"></div>'
							+ '</div>'
							+ '<div class="resDetails">'
							+ '<h5 class="Caps1st"> Reservation Details </h5>'
							+ '<p class="p1"> room style :  {{roomType}}</p>'
							+ '<p class="p1"> bed type : bed type</p>'
							//+ '<p class="p1">({{prettifyDate startDate}}) to ({{prettifyDate endDate}})</p>'
							/*+ '<p class="p1"><label class="M2ResInfo" id="M2CheckIn">Check-In: *</p>'
							+ '<p class="p1"><input type="text" class="add-to-cart-check-in modalInput" id="addTOCartCheckIn" readonly="readonly" /></p>'
							+'<div class="checkInDate control-group">'
							+'<p class="p1"><span class="help-inline"></span></p>'
							+'</div>'
							+ '</label>'
							+ '<p class="p1"><label class="M2ResInfo" id="M2CheckOut">Check-Out: *</p>'
							+ '<p class="p1"><input type="text" id="addTOCartCheckOut" class="add-to-cart-check-out modalInput" readonly="readonly" />'
							+'<div class="checkOutDate control-group">'
							+'<p class="p1"><span class="help-inline"></span></p>'
							+'</div>'
							+ '</label>'*/
							+ '<div class="M2ResInfo" id="M2CheckIn"><h5>Check-In:'
							+ '{{#if canAddToCart}}'
							+ '{{#if isFullSuite}}'
							+ '<span style="margin-left:5px;">{{prettifyDate2 startDate}}</span>'
							+ '<input type="hidden" class="add-to-cart-check-in" readonly="readonly" value="{{prettifyDate2 startDate}}"/>'
							+ '{{else}}'
							+ '<input type="text" class="add-to-cart-check-in modalInput" id="addTOCartCheckIn" readonly="readonly" value="{{prettifyDate2 startDate}}"/>'
							+'<div class="checkInDate control-group">'
							+'<p class="p1"><span class="help-inline"></span></p>'
							+'</div>'
							+ '{{/if}}'
							+ '{{else}}'
							+ '{{prettifyDate2 startDate}}'
							+ '{{/if}}'

							+ '</h5></div>'
							+ '<div class="M2ResInfo" id="M2CheckOut"><h5>Check-Out:'

							+ '{{#if canAddToCart}}'
							+ '{{#if isFullSuite}}'
							+ '<span style="margin-left:5px;">{{prettifyDate2 endDate}}</span>'
							+ '<input type="hidden" class="add-to-cart-check-out" readonly="readonly" value="{{prettifyDate2 endDate}}"/>'
							+ '{{else}}'
							+ '<input type="text" id="addTOCartCheckOut" class="add-to-cart-check-out modalInput" readonly="readonly"  value="{{prettifyDate2 endDate}}"/>'
							+'<div class="checkOutDate control-group">'
							+'<p class="p1"><span class="help-inline"></span></p>'
							+'</div>'
							+ '{{/if}}'
							+ '{{else}}'
							+ '{{prettifyDate2 endDate}}'
							+ '{{/if}}'
							+ '</h5></div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<div class="large-3 medium-4 small-4 columns tLeft noPad roomDetImg">'
							+ '<a href="{{baseUrl}}/images/{{roomPhotos.[0]}}" target="_blank"><img src="{{baseUrl}}/images/{{roomPhotos.[0]}}"></a>'
							+ '<div class="center nearBCont"><p class="p1"><a class="toglEvents">whats Near By >></a></p></div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<div class="priceBand">'
							+ '<div class="row center">'
							+ '<div class="large-6 medium-6 small-12 columns center">'
							+ '<p class="green bNone left breakUp">${{oneNightCost}}</p><p class="Caps1st bNone left"> this reservation</p>'
							+ '</div>'
							+ '<div class="large-6 columns medium-6 small-12 center">'
							+ '<p class="bNone left breakUp">${{oneNightCost}}</p><p class="Caps1st bNone left"> Current Average Rate</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							/*+ '<div class="bookButton">'
							+ '<button class="red expand add-to-cart">Book Now</button>'
							+ '</div>'*/
							+ '{{#if canAddToCart}}'
							+ '<div class="bookButton" id="modal2-addCart" >'
							+ '<button href="#" class="red expand add-to-cart">Book Now</button>'
							+ '</div>'
							+ '{{/if}}'
							+ '</div>'
							+ '</div>'
							+ '<!-- on click of whats near by animate div evensNear to the right (toggle), down on small screens and also on click of the X button on div -->'
							+ '<div class="large-4 medium-5 small-12 columns evensNear" >'
							+ '<div class="breakUp nearBCont right tRight">'
							/*+ '<a class="right toglEvents">X</a>'*/
							+ '</div>'
							+ '<div class="row center noPad noMar allTheWay">'
							+ '<div class="large-12 medium-12 small-12 columns" style="margin-bottom: 2%;">'
							+ '<h5 class="Caps1st maroon"> near by events:</h5>'
							+ '<div class="nearListCon">'
							+ '<div class="nearListConB">'
							+ '<p class="p1 Caps1st"> list of events</p>'
							+ '<p class="p1 Caps1st">date-venue-location</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<div class="row center noPad noMar allTheWay">'
							+ '<div class="large-12 medium-12 small-12 columns">'
							+ '<h5 class="Caps1st maroon">near by Restaurants:</h5>'
							+ '<div class="nearListCon">'
							+ '<div class="nearListConB">'
							+ '<p class="p1 Caps1st"> list of places</p>'
							+ '<p class="p1 Caps1st">name-location</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '{{#if bookHistory}}'
							+ '<div class="large-4 medium-5 small-12 columns evensNear" >'
							+ '<div class="row center noPad noMar allTheWay">'
							+ '<div class="large-12 medium-12 small-12 columns">'
							+ '<h5 class="Caps1st maroon">Booked History</h5>'
							+ '<div style=" max-height: 190px;overflow-y: scroll;padding: 5px;">'
							+ '<div class="nearListConB">'
							+ '{{#each bookHistory}}'
							+ '<p class="p1 Caps1st">Date : {{prettifyDate2 startDate}} to {{prettifyDate2 endDate}}</p>'
							+ '{{/each}}'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '{{/if}}'
							+ ''),
			templateSuccess2 : Handlebars
					.compile('<div id="modal2-content">'
							+ '<div class="modal-add-cart-error">'
							+ '<span class="help-inline"></span>'
							+ '</div>'

							+ '<!-- header -->'
							+ '<div class="row">'
							+ '<div class="small-12 medium-12 large-12 columns modalTitle" id="m2HotelName">'
							+ '<h1><span>{{hotelName}}</span>,<span style="font-style: italic;margin-left:5px;">{{city}}</span></h1>'
							+ '</div>'
							+ '</div>'
							+ '<!-- end header -->'
							+ '<div class="row">'
							+ '<!-- image slider -->'
							+ '<div id="modal2-imgsection">'
							+ '<div class="large-7 medium-7 small-7 columns" id="roomDetail-slider">'
							+ ' <div class="slick-slider">'
							+ '{{#each roomPhotos}}'
							+ '<div class="M2SlideImg"><img src="{{../baseUrl}}/images/hotelRooms/{{this}}" alt="slide 1"></div>'
							+ '{{/each}}'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<!-- end image slider -->'
							+ '<!-- basic info -->'
							+ '<div class="small-5 medium-5 large-5 columns" id="resInfo-container">'
							+ '<div class="M2ResInfo" id="M2Price"><h5>Price Per Night:<span style="margin-left:5px;">${{oneNightCost}}</span></h5></div>'
							+ '<div class="M2ResInfo" id="M2CheckIn"><h5>Check-In:'

							+ '{{#if canAddToCart}}'
							+ '{{#if isFullSuite}}'
							+ '<span style="margin-left:5px;">{{prettifyDate2 startDate}}</span>'
							+ '<input type="hidden" class="add-to-cart-check-in" readonly="readonly" value="{{prettifyDate2 startDate}}"/>'
							+ '{{else}}'
							+ '<input type="text" class="add-to-cart-check-in" id="addTOCartCheckIn" readonly="readonly" value="{{prettifyDate2 startDate}}"/>'
							+ '{{/if}}'
							+ '{{else}}'
							+ '{{prettifyDate2 startDate}}'
							+ '{{/if}}'

							+ '</h5></div>'
							+ '<div class="M2ResInfo" id="M2CheckOut"><h5>Check-Out:'

							+ '{{#if canAddToCart}}'
							+ '{{#if isFullSuite}}'
							+ '<span style="margin-left:5px;">{{prettifyDate2 endDate}}</span>'
							+ '<input type="hidden" class="add-to-cart-check-out" readonly="readonly" value="{{prettifyDate2 endDate}}"/>'
							+ '{{else}}'
							+ '<input type="text" id="addTOCartCheckOut" class="add-to-cart-check-out" readonly="readonly"  value="{{prettifyDate2 endDate}}"/>'
							+ '{{/if}}'
							+ '{{else}}'
							+ '{{prettifyDate2 endDate}}'
							+ '{{/if}}'

							+ '</h5></div>'

							+ '{{#if canAddToCart}}'
							+ '<div class="M2ResInfo" id="modal2-addCart" >'
							+ '<a href="#" class="button radius add-to-cart">ADD TO CART</a>'
							+ '</div>'
							+ '{{/if}}'

							+ '</div>'
							+ '<!-- end basic info -->'
							+ '</div>'
							+ '<!-- tabs -->'
							+ '<div class="row" id="modal2-tabcontain" >'
							+ '<div class="small-12 medium-12 large-12 columns" id="modal2-tabs">'
							+ '<dl class="tabs" data-tab>'
							+ '<dd class="active"><a href="#panel2Desc">Description</a></dd>'
							+ '<dd><a href="#panel2Amen" >Amenities</a>'
							+ '</dd>'
							+ '<dd><a href="#panel2Dets">Room Details</a>'
							+ '</dd>'
							+ '<dd><a href="#panel2Revw">User Reviews</a>'
							+ '</dd>'
							+ '</dl>'
							+ '<div class="tabs-content" >'
							+ '<div class="content active" id="panel2Desc">'
							+ '<p>Brand:{{hotelBrand}}</p>'
							+ '</div>'
							+ '<div class="content" id="panel2Amen">'
							+ '<p>'
							+ '<ul>'
							+ '{{#each roomDetails}}'
							+ '<li>{{this}}</li>'
							+ '{{/each}}'
							+ '</ul>'
							+ '</p>'
							+ '</div>'
							+ '<div class="content" id="panel2Dets">'
							+ '<p><ul><li>RoomType:{{roomType}}</li><li>Guest:{{guests}}</li></ul></p>'
							+ '</div>'
							+ '<div class="content" id="panel2Revw">'
							+ '<p>Fourth panel content goes here...</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<!-- end tabs -->'
							+ '</div>'
							+ '<a class="close-reveal-modal"><span style="text-size:12px;">&#215;</span></a>'
							+ '</div>'

							+ '<!-- end modal2 -->'),
			callBackData : {
				functionName : null
			},
			initialize : function() {
				return this.loginWindow = $("#modal1"), this.loginWindow.on(
						"authed", function(e) {
							return function() {
								return e.checkIfSubmitForm()
							}
						}(this)), this.listenTo(this.model, "change",
						this.render);
			},
			render : function() {
				$(this.el).empty();
				this.$el.html(this.templateSuccess(this.model.attributes));
				$('#modal2').append(this.el);
				var view = this;
				if ($('#addTOCartCheckIn').length > 0
						&& $('#addTOCartCheckOut').length > 0) {
					$("#addTOCartCheckIn").datepicker(
							{
								dateFormat : 'mm/dd/y',
								minDate : 0,
								changeMonth : true,
								onSelect : function(selectedDate) {
									var date2 = $("#addTOCartCheckIn")
											.datepicker('getDate');
									date2.setDate(date2.getDate() + 1);
									$("#addTOCartCheckOut").datepicker(
											'setDate', date2);
									$("#addTOCartCheckOut").datepicker(
											"option", "minDate", date2);
									setTimeout(function() {
										$("#addTOCartCheckOut").datepicker(
												'show');
									}, 16);
								}
							});
					$("#addTOCartCheckOut")
							.datepicker(
									{
										dateFormat : 'mm/dd/y',
										minDate : 0,
										changeMonth : true,
										onClose : function() {
											var dt1 = $("#addTOCartCheckIn")
													.datepicker('getDate');
											var dt2 = $("#addTOCartCheckOut")
													.datepicker('getDate');
											// check to prevent a user from
											// entering a date
											// below
											// date of dt1
											if (dt2 <= dt1) {
												var minDate = $(
														"#addTOCartCheckOut")
														.datepicker('option',
																'minDate');
												$("#addTOCartCheckOut")
														.datepicker('setDate',
																minDate);
											}
										}
									});
				}
				$('.slick-slider').slick({
					dots : true, // Current slide indicator dots
					infinite : true, // Infinite looping
					swipe : true, // Enables swipe
					touchMove : true, // Enables slide moving with touch
					cssEase : 'ease', // CSS3 easing
					easing : 'linear', // animate() fallback easing
					accessibility : true, // Enables tabbing and arrow key
				// navigation
				});
				var baseUrl = view.model.get('baseUrl');
				$('.starRating').raty({
					starOff : baseUrl + '/img/star-off.png',
					starOn : baseUrl + '/img/star-on.png',
					score: function() {
					    return $(this).attr('data-score');
					  },
					click : function(){
						return false;
					}
				});
				$(document).foundation();
				$('#modal2').foundation('reveal', 'open');
			},
			events : {
				'click .add-to-cart' : 'addToCart'
			},
			addToCart : function() {
				var baseUrl = this.model.get('baseUrl');
				var loginWindowPopUp = this.loginWindow;
				var roomId = this.model.get('roomId');
				this.hideErrors();
				var view = this;
				this.addToCart = new AddToCartModel({
					"roomId" : roomId,
					"checkInDate" : $(".add-to-cart-check-in").val(),
					"checkOutDate" : $(".add-to-cart-check-out").val()
				});
				this.addToCart.url = baseUrl + "/hotel/addToCart.json";
				if (!this.addToCart.isValid()) {
					this.showErrors(this.addToCart.validationError);
				} else {
					this.addToCart.save({}, {
						success : function(model, response) {
/*							$(".room-list-div-" + roomId).remove();
							$("#modal2").empty();
							view.cartDetail(true);*/
							window.location.href="cart";
						},
						error : function(model, error) {
							// console.log(error.responseJSON.errorCode);
							if (error.status == 401) {
								// console.log('login require');
								// $("#modal2").foundation('reveal', 'close');
								view.callBackData.functionName = "addToCart";
								loginWindowPopUp.empty();
								this.loginModel = new LoginPopupModel({
									"baseUrl" : baseUrl
								});
								this.loginView = new LoginPopupView({
									model : this.loginModel
								})
								loginWindowPopUp
										.append(this.loginView.render().el);
								$(document).foundation();
								loginWindowPopUp.foundation('reveal', 'open');
							} else if (error.responseJSON.errorCode == 40408
									|| error.responseJSON.errorCode == 40401
									|| error.responseJSON.errorCode == 40410
									|| error.responseJSON.errorCode == 40411) {
								// console.log(error.responseJSON.errorCode);
								// console.log($('.modal-add-cart-error'));
								$('.modal-add-cart-error').addClass('error');
								$('.modal-add-cart-error').find('.help-inline')
										.text(error.responseJSON.errorMessage).css("color","#f78c30");
								// console.log($('.modal-add-cart-error').find(
								// '.help-inline'));
							}
							else
							{
								_.each(error.responseJSON.errors, function(error) {
									$("."+error.field).text(error.message).css("color","#f78c30");;
								}, this);
								$('.modal-add-cart-error').addClass('error');
								$('.modal-add-cart-error').find('.help-inline')
										.text(error.responseJSON.errorMessage).css("color","#f78c30");
							}
						}
					});
				}
			},
			cartDetail : function(openDialog) {
				var modalCart = $('#modal3cart');
				var view = this;
				// console.log(modalCart.html());
				modalCart.empty();
				// console.log(modalCart.html());
				var baseUrl = this.model.get('baseUrl');
				var closeButton = $('<a class="close-reveal-modal"><img src="'
						+ baseUrl + '/img/OSR_closeX.png" alt="close"/></a>');
				this.cartDetailModel = new CartDetailModel({
					"baseUrl" : baseUrl
				});
				this.cartDetailModel.url = baseUrl + "/hotel/getCartRooms.json";
				this.cartDetailModel
						.fetch({
							success : function(model, response) {

								var cartDetail = $('<div id="modal3-content"></div>');
								// if(response.roomData.length>0){
								$('.cartCount').html(response.length);
								if (response.length > 0) {
									cartDetail
											.append('<div class="row" style=" margin-bottom:2%" ><div class="large-12 medium-12 small-12 columns modalTitle" ><h1>cart</h1></div></div>');
									cartDetail
											.append('<div class="row"><div class="large-12 medium-12 small-12 columns" style="border-bottom:1px dotted #FFFDE9;padding:0;text-align:center; "><div style="width:11%;border-right:1px dotted #FFFDE9;float:left;"><h2 class="hide-for-small hide-for-medium">Remove</h2><h2 class="hide-for-large-up roomRemove">X</h2></div><div style="width:23%;border-right:1px dotted #FFFDE9;float:left; "><h2>Name & location </h2></div><div style="width:23%;border-right:1px dotted #FFFDE9;float:left;"><h2>Check-In </h2></div><div style="width:23%;border-right:1px dotted #FFFDE9;float:left;"><h2>Check-Out </h2></div><div style="width:20%;float:left;"><h2>Price </h2></div></div></div>');
									var modalContainer = $('<div id="modal3-cartcontainer"></div>');
									$
											.each(
													response,
													function(i, item) {
														var roomDetail = $('<div class="row cart-room-'
																+ item.roomId
																+ '"></div>');
														var removeTimer = $('<div style="width:11%;float:left;">');
														var remove = $('<div class="roomRemove">X</div> ');
														remove
																.on(
																		"click",
																		function() {
																			removeRoomFromCart(item.roomId);
																		});
														removeTimer
																.append(remove);
														// console.log('t
														// start');
														var timer = $('<div id=timer'
																+ item.roomId
																+ ' class="roomTimer"></div>');

														var time = item.remainTimeSecond;
														var minutes = Math
																.floor(time / 60);
														var seconds = time
																- minutes * 60;
														// var hours =
														// Math.floor(time /
														// 3600);
														// console.log(minutes+'
														// minutes');
														// console.log(seconds+'
														// seconds');
														// console.log(time);
														timer
																.countdowntimer({
																	// hours :
																	// hours,
																	minutes : minutes,
																	seconds : seconds,
																	size : "lg",
																	timeUp : function() {
																		view
																				.timeisUp(item.roomId);
																	},
																	warnUp : function() {
																		view
																				.warnUp(item.roomId);
																	}
																});
														removeTimer
																.append(timer);
														var roomContainer = $('<div class="large-12 medium-12 small-12 columns">');
														roomContainer
																.append(removeTimer);
														var sDate = new Date(item.startDate);
														var eDate = new Date(item.endDate);
														var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2) + "/" +  sDate.getFullYear();
														var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2) + "/" +("0" + eDate.getDate()).slice(-2) + "/" +  eDate.getFullYear();
														
														roomContainer
																.append('<div style="width:23%;float:left;"><a href="#" onClick="hotelDetail('
																		+ item.roomId
																		+ ',false)"> <p>'
																		+ item.hotelName
																		+ '</p></a></div>');
														roomContainer
																.append('<div style="width:23%;float:left;"><p>'
																		+ startDate
																		+ '</p></div>');
														roomContainer
																.append('<div style="width:20%;float:left;"><p>'
																		+ endDate
																		+ '</p></div>');
														roomContainer
																.append('<div style="width:23%;float:left;"><p>$'
																		+ item.totalCost
																		+ '</p></div>');
														roomDetail
																.append(roomContainer);
														// console.log('t end');
														modalContainer
																.append(roomDetail);
													});
									cartDetail.append(modalContainer);
									var paymentDiv = $('<div class="row collapse"></div>');
									var buttonContainer = $('<div class="large-12 medium-12 small-12 columns"></div>');
									var button = $('<a href="'
											+ baseUrl
											+ '/ payment"><img alt="book it" style="float: right;" src="'
											+ baseUrl
											+ '/img/OSR_bookroom.png"></a>');
									buttonContainer.append(button);
									paymentDiv.append(buttonContainer);
									cartDetail
											.append('<div class="row collapse"><div class="large-12 medium-12 small-12 columns"><a href="'
													+ baseUrl
													+ '/ payment"><img src="'
													+ baseUrl
													+ '/img/OSR_bookroom.png" style="float: right;" alt="book it"/></a></div></div>');
								} else {
									cartDetail.append('<p>No room in cart</p>');
								}
								// modalCart.append(cartDetail);
								$('#modal3cart').append(cartDetail);
								// /this.cartDetailView.render();
								if (openDialog) {
									modalCart.append(closeButton);
									$('#modal3cart').foundation('reveal',
											'open');
								}
							},
							error : function(model, error) {
								// console.log(error);
								if (error.status == 401) {
									// console.log('login require');
									
									loginWindowPopUp.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									})
									loginWindowPopUp
											.append(this.loginView.render().el);
									$(document).foundation();
									loginWindowPopUp.foundation('reveal', 'open');
									
								} else if (error.responseJSON.errorCode == 40408
										|| error.responseJSON.errorCode == 40401
										|| error.responseJSON.errorCode == 40410) {
									// console.log(error.responseJSON.errorCode);
									// console.log($('.modal-add-cart-error'));
									$('.modal-add-cart-error')
											.addClass('error');
									$('.modal-add-cart-error').find(
											'.help-inline').text(
											error.responseJSON.errorMessage);
									// console.log($('.modal-add-cart-error')
									// .find('.help-inline'));
								}
							}
						});
			},
			timeisUp : function(roomId) {
				// Code to be executed when timer expires.
				var room = $('.cart-room-' + roomId);
				var parent = room.parent();
				room.remove();
				var roomsInCart = parent.children().length;
				$('.cartCount').html(roomsInCart);
				if (roomsInCart == 0) {
					$('#modal3-content').empty();
					$('#modal3-content').append('<p>No room in cart</p>');
				}
			},
			warnUp : function(roomId) {
				console.log('warn' + roomId);
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.name);
					controlGroup.addClass('error');
					console.log(error.name);
					controlGroup.find('.help-inline').text(error.message).css("color","#f78c30");
				}, this);
			},

			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			},
			checkIfSubmitForm : function() {
				//this.loginWindow.foundation('reveal', 'close');
				if (this.callBackData.functionName == "addToCart") {
					$("#modal2").foundation('reveal', 'open');
					$(document).foundation();
				}
				
			}
		});