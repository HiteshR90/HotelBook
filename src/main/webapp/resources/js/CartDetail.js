Handlebars.registerHelper("prettifyDate", function(timestamp) {
	var date = new Date(timestamp);
	return (date.getMonth() + 1) + "/" + date.getDate() + "/"
			+ date.getFullYear().toString().substr(2, 2)
});
Handlebars.registerHelper("prettifyDate2", function(timestamp) {
	var date = new Date(timestamp);
	return (("0" + (date.getMonth() + 1)).slice(-2) + "/" +("0" + (date.getDate() + 1)).slice(-2) + "/"
			+ date.getFullYear())
});

function hotelDetail(roomId, canAddToCart) {
	$('#modal2').empty();
	this.hotelDetailModel = new HotelDetailModel({
		"baseUrl" : url,
		"canAddToCart" : canAddToCart
	});
	this.hotelDetailModel.url = url + "/rest/hotel/getHotelData/" + roomId
			+ ".json";
	this.hotelDetailModel.fetch({
		success : function(model, response) {
			// console.log("success "+response);
			// console.log(model);
			new HotelDetailView({
				model : model
			}).render();
		},
		error : function(model, response) {
			$('#modal2').append(response);
			$('#modal2').foundation('reveal', 'open');
		}
	});
}

function timeisUp(roomId) {
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
}
function warnUp(roomId) {
	console.log('warn' + roomId);
}

function removeRoomFromCart(roomId) {
	// console.log(window['timer_MStimer' + roomId]);
	$.ajax({
		type : "DELETE",
		url : url + "/hotel/removeFromCart/" + roomId + ".json",
		success : function(response) {
			clearInterval(window['timer_MStimer' + roomId]);
			// console.log(response);
			var room = $('.cart-room-' + roomId);
			var parent = room.parent();
			$('.cart-room-' + roomId).remove();
			var roomsInCart = parent.children().length;
			$('.cartCount').html(roomsInCart);
			if (roomsInCart == 0) {
				$('#modal3-content').empty();
				$('#modal3-content').append('<p>No room in cart</p>');
			}
		},
		error : function(error) {
			// console.log(error);
		}
	});
}

function cartDetail(OpenNotRequire) {
	var modalCart = $('#modal3cart');
	// console.log(modalCart.html());
	modalCart.empty();
	// console.log(modalCart.html());
	var closeButton = $('<a class="close-reveal-modal"><img src="' + url
			+ '/img/OSR_closeX.png" alt="close"/></a>');
	this.cartDetailModel = new CartDetailModel({
		"baseUrl" : url
	});
	this.cartDetailModel.url = url + "/hotel/getCartRooms.json";
	this.cartDetailModel
			.fetch({
				success : function(model, response) {
					// console.log(response.roomData.length);
					var cartDetail = $('<div id="modal3-content"></div>');
					// if(response.roomData.length>0){
					var cartCount = $('.cartCount');
					cartCount.html(response.length);
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
													+ item.roomId + '"></div>');
											var removeTimer = $('<div style="width:11%;float:left;">');
											var remove = $('<div class="roomRemove">X</div> ');
											remove
													.on(
															"click",
															function() {
																removeRoomFromCart(item.roomId);
															});
											removeTimer.append(remove);
											// console.log('t start');
											var timer = $('<div id=timer'
													+ item.roomId
													+ ' class="roomTimer"></div>');

											var time = item.remainTimeSecond;
											var minutes = Math.floor(time / 60);
											var seconds = time - minutes * 60;
											// var hours = Math.floor(time /
											// 3600);
											// console.log(minutes+' minutes');
											// console.log(seconds+' seconds');
											// console.log(time);
											timer.countdowntimer({
												// hours : hours,
												minutes : minutes,
												seconds : seconds,
												size : "lg",
												timeUp : function() {
													timeisUp(item.roomId);
												},
												warnUp : function() {
													warnUp(item.roomId);
												}
											});
											removeTimer.append(timer);
											var roomContainer = $('<div class="large-12 medium-12 small-12 columns">');
											roomContainer.append(removeTimer);

											roomContainer
													.append('<div style="width:23%;float:left;"><a href="#" onClick="hotelDetail('
															+ item.roomId
															+ ',false)"> <p>'
															+ item.hotelName
															+ '</p></a></div>');
											roomContainer
													.append('<div style="width:23%;float:left;"><p>'
															+ item.startDate
															+ '</p></div>');
											roomContainer
													.append('<div style="width:20%;float:left;"><p>'
															+ item.endDate
															+ '</p></div>');
											roomContainer
													.append('<div style="width:23%;float:left;"><p>$'
															+ item.totalCost
															+ '</p></div>');
											roomDetail.append(roomContainer);
											// console.log('t end');
											modalContainer.append(roomDetail);
										});
						cartDetail.append(modalContainer);
						cartDetail
								.append('<div class="row collapse"><div class="large-12 medium-12 small-12 columns"><a href="'
										+ url
										+ '/ payment"><img src="'
										+ url
										+ '/img/OSR_bookroom.png" style="float: right;" alt="book it"/></a></div></div>');
					} else {
						cartDetail.append('<p>No room in cart</p>');
						// console.log('called==>' + cartDetail);
					}
					// modalCart.append(cartDetail);
					modalCart.append(closeButton);
					// console.log('working==>' + modalCart.html());
					this.cartDetailView = new CartDetailView({
						model : this.cartDetailModel,
						el : cartDetail
					});
					// console.log('OpenRequire==>' + OpenNotRequire);
					$('#modal3cart').append(cartDetail);
					// /this.cartDetailView.render();
					if (OpenNotRequire != "undefined" && !OpenNotRequire)
						$('#modal3cart').foundation('reveal', 'open');
				},
				error : function(model, error) {
					// console.log(error);
					if (error.status == 401) {
						// console.log('login require');
						this.loginModel = new LoginPopupModel({
							"baseUrl" : url,
							"callback" : cartDetail
						});
						this.loginView = new LoginPopupView({
							model : this.loginModel
						});
						$('#modal1').empty();
						$('#modal1').append(this.loginView.render().el);
						$('#modal1').foundation('reveal', 'open');
					} else if (error.responseJSON.errorCode == 40408
							|| error.responseJSON.errorCode == 40401
							|| error.responseJSON.errorCode == 40410) {
						// console.log(error.responseJSON.errorCode);
						// console.log($('.modal-add-cart-error'));
						$('.modal-add-cart-error').addClass('error');
						$('.modal-add-cart-error').find('.help-inline').text(
								error.responseJSON.errorMessage);
						// console.log($('.modal-add-cart-error')
						// .find('.help-inline'));
					}
				}
			});

}