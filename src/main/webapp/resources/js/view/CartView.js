var CartView = Backbone.View
		.extend({
			
			el : $("#main-container"),
			initialize : function() {
				//this.listenTo(this.model, "change", this.render);
				return this.loginWindow = $("#modal1"), this.loginWindow.on(
						"authed", function(e) {
						}(this)), this.listenTo(this.model, "change",
						this.render);
			},
			render : function() {
				var self = this;
				self.cartDetail();
				$("#paymentbtn").click(function(){
					$("#paymentContent").css("display","none");
					self.viewBookedDetails();
				});
				$("#paymentBack").click(function(){
					$("#paymentContent").css("display","none");
					$("#content").css("display","block");
				});
				$("#paymentType").change(function(){
					if($("#paymentType").val() == "Card")
					{
						$(".ccName").css("display","block");
					}
					else if($("#paymentType").val() == "Paypal")
					{
						$(".ccName").css("display","none");
					}
				});
			},
			events : {
			},
			onlyNumber : function(e) {
				if (e.which != 8 && e.which != 0
						&& (e.which < 48 || e.which > 57)) {
					e.preventDefault();
					return false;
				}
			},
			cartDetail : function() {
				var totalAmount = 0;
				var baseUrl = this.model.get('baseUrl');
				var loginWindowPopUp = this.loginWindow;
				var self = this;
				this.cartDetailModel = new CartDetailModel({
				 "baseUrl" : baseUrl
				});
				this.cartDetailModel.url = baseUrl +"/hotel/getCartRooms.json";
				this.cartDetailModel
						.fetch({
							success : function(model, response) {
								var cartDetail = $('<div class="large-8 medium-12 small-12 columns cartDetsCont" style="overflow: auto; height: 280px; position: relative;"></div>');
								var subTotalDetails = $('<div class="large-3 medium-12 small-12 columns center cartSubTotal" style="display:none" ></div>');
								//$('.cartCount').html(response.length);
								if (response.length > 0) {
									$(".emptyCart").css("display","none");
									var shopDetail = $('<div class="row cartTitle"><div class="commonError"></div>'
											+ '<div class="large-9 medium-9 small-12 columns cartCount">'
											+ '<h5 class="Caps1st maroon">Your Shopping cart has (' +response.length+ ') room(s): </h5>'
											+ '</div>'
											+ '<div class="large-3 medium-3 hide-for-small columns cartPrice center">'
											+ '<h5 class="Caps1st">Price</h5>'
											+ '</div>');
									cartDetail.append(shopDetail);
									var modalContainer = $('<div id="resContainer"><div class="row resTemp1 allTheWay"></div></div>');
									$
											.each(
													response,
													function(i, item) {
														totalAmount += item.totalCost;
														var sDate = new Date(item.startDate);
														var eDate = new Date(item.endDate);
														var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2) + "/" +  sDate.getFullYear();
														var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2) + "/" +("0" + eDate.getDate()).slice(-2) + "/" +  eDate.getFullYear();
														
														var roomDetail = $('<div class="large-9 medium-9 small-12 columns ResContent cart-room-'
																+ item.roomId
																+ '"></div></div>');

														var roomContainer = $('<div class="hotelDetails">'
																+ '<h5 class="Caps1st maroon">'
																+ item.hotelName
																+ '</h5><div class="starRating left allTheWay"></div></div>');

														roomContainer
																.append('<div class="resDetails1" style="margin-top: 0% !important; margin-bottom: 30px;">'
																		+ '<h5 class="Caps1st"> Reservation Details </h5>'
																		+ '<p class="p1">Room Type : '
																		+ item.roomType
																		+ '</p>'
																		+ '<p class="p1">Date : '
																		+ startDate
																		+ ' to '
																		+ endDate
																		+ '</p>'
																		+ '</div>');

														var viewRoom = $('<div class="large-3 medium-3 small-12 columns resCost center">'
																+ '<h5 class="green">$ '
																+ item.totalCost
																+ '</h5>'
																+ '<button class="red allTheWay viewCartBtn" id="viewCartBtn" data-attr-name = '
																+ item.roomId
																+ '>View</button><button class="red allTheWay removeCartbtn" id="removeCartbtn" data-attr-name = '
																+ item.roomId
																+ '>Remove</button></div>');

														roomDetail
																.append(roomContainer);
														modalContainer
																.append(roomDetail);
														modalContainer
																.append(viewRoom);
													});
									cartDetail.append(modalContainer);
									subTotalDetails.css("display","block");
									if(response.length == 1)
									{
										var checkoutDetails = $('<div><h5 class="Caps1st"> Subtotal('+response.length+' Room)</h5></div>'
												+ '<div><h5 class="green">$ '+ totalAmount +'</h5></div>'
												+ '<div><button id="checkOutButton" class="exspand allTheWay red">Checkout</button></div>');
									}
									else
									{
										var checkoutDetails = $('<div><h5 class="Caps1st"> Subtotal('+response.length+' Rooms)</h5></div>'
												+ '<div><h5 class="green">$ '+ totalAmount +'</h5></div>'
												+ '<div><button id="checkOutButton" class="exspand allTheWay red">Checkout</button></div>');
									}
									
									subTotalDetails.append(checkoutDetails);
									
								} else {
									cartDetail.append('<p>No room in cart</p>');
								}
								$('#content').append(cartDetail);
								$('#content').append(subTotalDetails);
								$("#checkOutButton").click(function() {
									$("#content").css("display","none");
									$("#paymentContent").css("display","block");
								});
								$(".viewCartBtn").click(function() {
									var name = $(this).attr("data-attr-name");
									self.viewCartModel(name);
								});
								$(".removeCartbtn").click(function() {
									this.cartDetailModel = new CartDetailModel({
										 "baseUrl" : baseUrl
									});
									var roomId = $(this).attr("data-attr-name");
									
									var result = confirm("Are you sure you want to remove this from Cart?");
									if (result) {
										
										$.ajax({
											type : "DELETE",
											url : baseUrl + "/hotel/removeFromCart/" + roomId + ".json",
											success : function(response) {
												location.href = baseUrl+'/hotel/cart';
												$(".commonError").html("The Cart has been Removed Successfully").css("color","red");
												//alert("The Cart has been Removed Successfully");
											},
											error : function(error) {
												// console.log(error);
												//location.href = baseUrl+'/hotel/cart';
												$(".commonError").html("Cart is under process").css("color","red");
												//alert("You are not allow to remove Cart");
											}
										});
									}
								});
								
							},
							error : function(model, error) {
								console.log("Error : " + error.status);
								// console.log(error);
								if (error.status == 401) {
									// console.log('login require');
									loginWindowPopUp.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
										"callback" : "login"
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
			viewBookedDetails : function() {
				var self = this;
				var totalAmount = 0;
				var baseUrl = self.model.get('baseUrl');
				this.cartDetailModel = new CartDetailModel({
				 "baseUrl" : baseUrl
				});
				this.cartDetailModel.url = baseUrl +"/hotel/getCartRooms.json";
				this.cartDetailModel
						.fetch({
							success : function(model, response) {
								$('#bookedContent').css("display","block");
								var cartDetail = $('<div class="large-8 medium-12 small-12 columns cartDetsCont"></div>');
								var subTotalDetails = $('<div class="large-3 medium-12 small-12 columns center cartSubTotal" style="display:none" ></div>');
								//$('.cartCount').html(response.length);
								if (response.length > 0) {
									var shopDetail = $('<div class="row cartTitle">'
											+ '<div class="large-9 medium-9 small-12 columns cartCount">'
											+ '<h5 class="Caps1st maroon">Review Your Order</h5>'
											+ '</div>'
											+ '<div class="large-3 medium-3 hide-for-small columns cartPrice center">'
											+ '<h5 class="Caps1st">Price</h5>'
											+ '</div>' );
									cartDetail.append(shopDetail);
									var modalContainer = $('<div id="resContainer" style="overflow-y:auto;overflow-x:hidden;height:150px;"><div class="row resTemp1 allTheWay"></div></div>');
									$
											.each(
													response,
													function(i, item) {
														totalAmount += item.totalCost;
														var sDate = new Date(item.startDate);
														var eDate = new Date(item.endDate);
														var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2) + "/" +  sDate.getFullYear();
														var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2) + "/" +("0" + eDate.getDate()).slice(-2) + "/" +  eDate.getFullYear();
														
														var roomDetail = $('<div class="large-9 medium-9 small-12 columns ResContent cart-room-'
																+ item.roomId
																+ '"></div></div>');

														var roomContainer = $('<div class="hotelDetails">'
																+ '<h5 class="Caps1st maroon">'
																+ item.hotelName
																+ '</h5><div class="starRating left allTheWay"></div></div>');

														roomContainer
																.append('<div class="resDetails1" style="margin-top: 0% !important; margin-bottom: 30px;" >'
																		+ '<h5 class="Caps1st"> Reservation Details </h5>'
																		+ '<p class="p1">'
																		+ item.roomType
																		+ '</p>'
																		+ '<p class="p1">'
																		+ startDate
																		+ ' to '
																		+ endDate
																		+ '</p>'
																		+ '</div>');

														var viewRoom = $('<div class="large-3 medium-3 small-12 columns resCost center">'
																+ '<h5 class="green">$ '
																+ item.totalCost
																+ '</h5></div>');

														roomDetail
																.append(roomContainer);
														modalContainer
																.append(roomDetail);
														modalContainer
																.append(viewRoom);
													});
									 var paymentMethod = $('<hr><div class="row" style="margin-top:6%">'
								        +'<div class="large-12 small-12 columns">'
								        +'<h5 class="Caps1st maroon">Payment Method</h5>'
								        +'</div>'
								        +'<div class="large-12 small-12 columns">' 
								        +'<p class="p1 Caps1st">'+$('input[name=paymentType]:checked').val()+'</p>'
								        +'</div>'
								        +'</div>'
								        +'<div style="margin-top:4%">'
								        +'<button class="red right" id="reviewBack">back</button>'
								        +'</div>');
									cartDetail.append(modalContainer);
									cartDetail.append(paymentMethod);
									
									subTotalDetails.css("display","block");
									if(response.length == 1)
									{
										var checkoutDetails = $('<div><h5 class="Caps1st"> Subtotal('+response.length+' Room)</h5></div>'
												+ '<div><h5 class="green">$ '+ totalAmount +'</h5></div>'
												+ '<div><button id="bookButton" class="exspand allTheWay red">Book Room For $ '+totalAmount+'</button></div>');
									}
									else
									{
										var checkoutDetails = $('<div><h5 class="Caps1st"> Subtotal('+response.length+' Rooms)</h5></div>'
												+ '<div><h5 class="green">$ '+ totalAmount +'</h5></div>'
												+ '<div><button id="bookButton" class="exspand allTheWay red">Book Room For $ '+totalAmount+'</button></div>');
									}
									
									subTotalDetails.append(checkoutDetails);
									
								} else {
									cartDetail.append('<p>No room in cart</p>');
								}
								$('#bookedContent').append(cartDetail);
								$('#bookedContent').append(subTotalDetails);
								$("#bookButton").click(function(){
									
									if($('input[name=paymentType]:checked').val() == "Paypal")
									{
										location.href = baseUrl+'/payment/paypal';
									}
									else if($('input[name=paymentType]:checked').val() == "Card")
									{
										//$("#CreditCardModal").foundation('reveal', 'open');
										var model = Backbone.Model.extend();
										$('.card-div').empty();
										$('.card-div').append(new CreditCardView({
											model : new model({
												"baseUrl" : baseUrl,
											})
										}).render().el);
										$("#CreditCardModal").foundation('reveal', 'open');
									}
									
								});
								$("#reviewBack").click(function(){
									$("#bookedContent").empty();
									$("#paymentContent").css("display","block");
								});
							},
							error : function(model, error) {
								console.log("Error : " + error.status);
								// console.log(error);
								if (error.status == 401) {
									// console.log('login require');
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
										"callback" : cartDetail
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									$('#modal1').empty();
									$('#modal1').append(
											this.loginView.render().el);
									$(document).foundation();
									$('#modal1').foundation('reveal', 'open');
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
			viewCartModel : function(name)
			{
				console.log("name  : "+name);
				var self = this;
				var totalAmount = 0;
				var baseUrl = self.model.get('baseUrl');
				this.cartDetailModel = new CartDetailModel({
					"baseUrl" : baseUrl
				});
				this.cartDetailModel.url = baseUrl + "/hotel/getCartRooms.json";
				this.cartDetailModel
						.fetch({
							success : function(model, response)
							{
								
								$
								.each(response,function(i, item) {

									if(item.roomId == name)
									{
										var sDate = new Date(item.startDate);
										var eDate = new Date(item.endDate);
										var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2) + "/" +  sDate.getFullYear();
										var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2) + "/" +("0" + eDate.getDate()).slice(-2) + "/" +  eDate.getFullYear();
										
										var ViewCart = $('<a class="close-reveal-modal">X</a><div class="large-8 medium-7 small-12 columns moreDets" data-equalizer-watch>'
												+ '<div class="large-12 columns" style="margin:0px;padding:0px;">'
												+ '<div style="padding:3% 5%;">'
												+ '<div class="row">'
												+ '<div class="large-8 medium-7 small-7 columns Caps1st noPad">'
												+ '<div class="resTemp">'
												+ '<div class="hotelDetails">'
												+ '<h5 class="Caps1st maroon">'+ item.hotelName+ ' </h5>'
												+ '<p class="p1">Address : '+ item.address+ '</p>'
												/*+ '<p class="p1">amenities, go, here</p>'*/
												+ '<div class="starRating left allTheWay"></div>'
												+ '</div>'
												+ '<div class="resDetails">'
												+ '<h5 class="Caps1st"> Reservation Details </h5>'
												+ '<p class="p1">Room Type : '+item.roomType+'</p>'
												+ '<p class="p1">Date : ('+ startDate+ ') to ('+ endDate
												+ ')</p>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
												+ '<div class="large-3 medium-4 small-4 columns tLeft noPad roomDetImg">'
												+ '<a href="'+baseUrl +'/images/'+ item.images[0]+'" target="_blank"><img src="'+baseUrl +'/images/'+ item.images[0]+'"></a>'
												+ '<div class="center nearBCont"><p class="p1"><a class="toglEvents">whats Near By >></a></p></div>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
												+ '<div class="priceBand">'
												+ '<div class="row center">'
												+ '<div class="large-6 medium-6 small-12 columns center">'
												+ '<p class="green bNone left breakUp">$ '+item.totalCost+'</p><p class="Caps1st bNone left"> this reservation</p>'
												+ '</div>'
												+ '<div class="large-6 columns medium-6 small-12 center">'
												+ '<p class="bNone left breakUp">$ '+item.totalCost+'</p><p class="Caps1st bNone left"> Current Average Rate</p>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
												+ '<div class="bookButton">'
												+ '<button id="bookNowButton" class="red expand">Book Now</button>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
												+ '<!-- on click of whats near by animate div evensNear to the right (toggle), down on small screens and also on click of the X button on div -->'
												+ '<div class="large-4 medium-5 small-12 columns evensNear"  data-equalizer-watch>'
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
												+ '<h5 class="Caps1st maroon">near by Restuarnts:</h5>'
												+ '<div class="nearListCon">'
												+ '<div class="nearListConB">'
												+ '<p class="p1 Caps1st"> list of places</p>'
												+ '<p class="p1 Caps1st">name-location</p>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
												+ '</div></div>');
										$('#viewCartDiv').html(ViewCart);
									}
								});
								$(document).foundation();
								$("#modal2").foundation('reveal', 'open');
								$("#bookNowButton").click(function() {
									$("#modal2").foundation('reveal', 'close');
									$("#content").css("display","none");
									$("#paymentContent").css("display","block");
								});
							},
							error : function(model, error) {
								console.log("Error : " + error.status);
								// console.log(error);
								if (error.status == 401) {
									// console.log('login require');
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
										//"callback" : cartDetail
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									$('#modal1').empty();
									$('#modal1').append(
											this.loginView.render().el);
									$('#modal1').foundation('reveal', 'open');
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
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.field);
					console.log(controlGroup);
					controlGroup.find('.help-inline').text(error.message).css('color','#f78c30');
					console.log("error ::: "+error.field+ " ::: "+error.message);
				}, this);
			},
			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			}
		});