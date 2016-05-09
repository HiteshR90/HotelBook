var RoomListView = Backbone.View
		.extend({
			el : ".buyRoomList",
			callBackData : {
				functionName : null
			},
			initialize : function() {
				//this.listenTo(this.model, "change", this.render);
				return this.loginWindow = $("#modal1"), this.loginWindow.on(
						"authed", function(e) {
							return function() {
								return e.checkIfSubmitForm()
							}
						}(this)), this.listenTo(this.model, "change",
						this.render);
			},
			score : 0,
			pagination : null,
			map : null,
			render : function() {
				var template = Handlebars.compile($("#room_lists_template")
						.html());

				$(this.el).html(template());
				this.loadCity();

				$(document).foundation();

				var view = this;
				var baseUrl = view.model.get('baseUrl');

				$(".from").datepicker({
					dateFormat : 'mm/dd/y',
					minDate : 0,
					changeMonth : true,
					changeYear : true,
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

				$(".to").datepicker(
						{
							dateFormat : 'mm/dd/y',
							minDate : 0,
							changeMonth : true,
							changeYear : true,
							onSelect : function(dateText, inst) {
								if ($(".from").val() != null
										&& $(".from").val() != "") {
									view.loadData();
								}
							},
							onClose : function() {
								var dt1 = $(".from").datepicker('getDate');
								var dt2 = $(".to").datepicker('getDate');
								// check to prevent a user from entering a date
								// below date of
								// dt1
								if (dt2 <= dt1) {
									var minDate = $(".to").datepicker('option',
											'minDate');
									$(".to").datepicker('setDate', minDate);
								}
							}
						});
				$(".slider-range").slider(
						{
							range : true,
							min : 0,
							max : 3000,
							values : [ 0, 3000 ],
							scale : [ 1, '|', 3, '|', '5', '|', 15, '|', 30 ],
							slide : function(event, ui) {
								$('.ui-slider-handle:eq(0) .price-range-min')
										.html('$' + ui.values[0]);
								$(".amount").val(
										"$" + ui.values[0] + " - $"
												+ ui.values[1]);
							},
							stop : function(event, ui) {
								view.loadData();
							}
						});
				$(".amount").val(
						"$" + $(".slider-range").slider("values", 0) + " - $"
								+ $(".slider-range").slider("values", 1));

				$('.onlyNumber').keypress(
						function(e) {
							if (e.which != 8 && e.which != 0
									&& (e.which < 48 || e.which > 57)) {
								return false;
							}
						});

				$("#guest-quantity").on('change', function() {
					view.loadData();
				});

				$("#room-quantity").on('change', function() {
					view.loadData();
				});

				// modal reveal
				$('.modal').click(function() {
					$('#loginModal').foundation('reveal', 'open');
				});

				// checkbox for remeber me on this compute in login modal
				function myFunction() {
					var x = document.getElementById("remeberMe");
					x.checked = true;
				}
				// image swap hover
				var sourceSwap = function() {
					var $this = $(this);
					var newSource = $this.data('alt-src');
					$this.data('alt-src', $this.attr('src'));
					$this.attr('src', newSource);
				}
				$(function() {
					$('img.loginWith').hover(sourceSwap, sourceSwap);
				});
				// change input type for search bar
				$(".resDate").click(function() {
					$(this).prop('type', 'date');
				});
				// below are the reveals for the map and the exit buttons for
				// those reveals ie advanced search
				$('.advsSrch').click(function() {
					$('.advSrcView').toggleClass('hide');
				});
				$('.adsrcbttn').click(function() {
					$('.advSrcView').toggleClass('hide');
				});

				$('.heatMap').click(function() {
					$('.heatMapView').toggleClass('hide');
				});
				$('.exit').click(function() {
					$('.heatMapView').toggleClass('hide');
				});

				$('.mapView').click(function() {
					$('.listView').toggleClass('hide');
				});

				view.loadData();
				$(".subMitBtn")
						.click(
								function() {
									if (($(".from").val() != null && $(".from")
											.val() != "")
											|| $(".cityName").val() || $(".amount").val()) {
										view.loadData();
									}
									view.loadData();
								});
			},
			events : {
				"change #hotel-brand" : "loadData",
				"click .amenities" : "loadData",
				"click .thContainer" : "thContainer",
				"click .focus" : "focus",
				'click .add-to-cart' : 'addToCartRoom'
			},
			working : false,
			thContainer : function(ev) {
				// if (!this.working) {
				var view = this;
				var roomId = $(ev.currentTarget).attr('roomId');
				view.hotelDetail(roomId, true);
				// this.working = true;
				// }
			},
			focus : function(ev) {
				$(".add-cart-error").text('');
				var latitude = $(ev.currentTarget).attr('attr-latitude');
				var longitude = $(ev.currentTarget).attr('attr-longitude');
				var position = new google.maps.LatLng(latitude, longitude);
				this.map.setCenter(position);
				$(".roomId").val($(ev.currentTarget).attr('roomId'));
				$(".add-cart-check-in").val($(ev.currentTarget).attr('startDate'));
				$(".add-cart-check-out").val($(ev.currentTarget).attr('endDate'));
				$(".fullSuite").val($(ev.currentTarget).attr('fullSuite'));
				var baseUrl = this.model.get('baseUrl');
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
			},
			loadData : function(pageNo) {
				var mapOptions = {
						center : new google.maps.LatLng(-34.397, 150.644),
						zoom : 8,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
				this.map = new google.maps.Map(document
							.getElementById("map_canvas"), mapOptions);
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				$(".add-cart-error").text('');
				if (this.model.has('cityName')) {
					var cityName = this.model.get('cityName');
					$('.cityName').val(cityName);
					this.model.unset('cityName');
				}
				var url = baseUrl + '/hotel/getHotelRooms.json';
				var params = '?';
				params = params + 'priceMin='
				+ $('.amount').val() + '&';
				params = params + 'priceMax='
				+ $('.amount').val() + '&';
				// cityname
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
					params = params + 'hotelBrand=' + $("#hotel-brand").val()
							+ '&';
				}
				if ($("#room-quantity").val() != null
						&& $("#room-quantity").val() != ''
						&& $.isNumeric($("#room-quantity").val())) {
					params = params + 'noOfRoom=' + $("#room-quantity").val()
							+ '&';
				}
				if ($("#guest-quantity").val() != null
						&& $("#guest-quantity").val() != ''
						&& $.isNumeric($("#guest-quantity").val())) {
					params = params + 'adults=' + $("#guest-quantity").val()
							+ '&';
				}

				if (pageNo != null && pageNo > 0) {
					params = params + 'pageNo=' + pageNo + '&';
				}

				var hotelRating = view.score;
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
				// load data from server
				$
						.ajax({
							url : url,
							type : "GET",
							success : function(response) {
								//console.log("success");
								var rooms = $('.accordion-navigation');
								rooms.empty();
								$('.results').html(
										response.roomData.length + ' results');
								// alert(response.roomData.length);
								$
										.each(
												response.roomData,
												function(index, room) {
													var roomD = room.roomDesc;
													var sDate = new Date(room.startDate);
													var eDate = new Date(room.endDate);
													var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2)  +  "/" + sDate.getFullYear();
													var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2)+ "/"  + ("0" + eDate.getDate()).slice(-2) + "/" + eDate.getFullYear();
													var roomDesc = roomD.length > 42 ? roomD
															.substring(0, 39)
															+ '...'
															: roomD;
													rooms
															.append('<a href="#panel'
																	+ room.roomId
																	+ '" class="focus"  attr-latitude='
																	+ room.latitude
																	+ ' attr-longitude='
																	+ room.longitude
																	+ ' roomId='
																	+ room.roomId
																	+ ' startDate='
																	+ startDate
																	+ ' endDate='
																	+ endDate
																	+ ' fullSuite='
																	+ room.isFullSuite
																	+ '>'
																	+ '<div class="row">'
																	+ '<div class="large-10 medium-10 small-10 columns" style="text-align: left">'
																	+ '<input type="hidden" class="roomId" value="'+ room.roomId+'"/>'
																	+ '<input type="hidden" class="fullSuite" value="'+ room.isFullSuite+'"/>'
																	+ '<div style="width: 100%; color: #a11e21;">'
																	+ room.hotelName
																	+ '</div>'
																	+ '<div style="width: 100%;">'
																	+ '<span class="left">'
																	+ room.roomType
																	+ '</span><span class="right">'
																	+ startDate
																	+ ' to '
																	+ endDate
																	+ '<input type="hidden" class="add-cart-check-in" readonly="readonly" value="'+ room.startDate+'"/>'
																	+ '<input type="hidden" class="add-cart-check-out" readonly="readonly" value="'+ room.endDate+'"/>'
																	+ '</span>'
																	+ '</div>'
																	+ '</div>'
																	+ '<div class="large-2 medium-2 small-2 columns" style="color: #49EE0E; text-align: right; padding-top: 10px;">$'
																	+ room.oneNightCost
																	+ '</div>'
																	+ '</div>'
																	+ '</a>');

													rooms
															.append('<div id="panel'
																	+ room.roomId
																	+ '" class="content">'
																	+ '<div class="row">'
																	+ '<div class="large-3 medium-3 small-3 columns left">'
																	+ '<img src="'
																	+ baseUrl
																	+ '/images/'
																	+ room.imagePath
																	+ '">'
																	+ '</div>'
																	+ '<div class="large-9 medium-9 small-9 columns" style="text-align: left">'
																	+ '<div>Address : '+ room.address +'</div>'
																	+ '<div style="clear: both">City : '+room.city+' </div>' 
																	+ '<div> zip : '+room.pincode +' </div>'
																	+ '<div style="clear: both">Contact : '+room.phonenumber+'</div>'
																	//+ '<br>'
																	//+ '<div >Hotel Brand :'
																	+ '<div class="starRating left allTheWay" data-score="'+room.hotelBrand+'"></div>'
																	//+ '</div>'

																	+ '</div>'
																	+ '</div>'
																	+ '<div class="row" style="margin-top: 5%;">'
																	+ '<div class="left" style="margin-left: 15px">'
																	+ '<button class="red left small add-to-cart">Book Now</button>'
																	+ '</div>'
																	+ '<div class="left" style="margin-left: 20px">'
																	+ '<button class="red left small thContainer" roomId="'
																	+ room.roomId
																	+ '">More Details</button>'
																	+ '</div>'
																	+ '</div>'
																	+ '</div>');

													// add marker
													var myLatlng = new google.maps.LatLng(
															room.latitude,
															room.longitude);
													var marker = new google.maps.Marker(
															{
																position : myLatlng,
																title : room.hotelName
															});

													// To add the marker to the
													// map, call setMap();
													marker.setMap(view.map);
												});

								if (response.roomData.length == 0)
									rooms.append('<p>No Data found</p>');
								// pagination
								if (response.totalPage > 1) {
									$('#buyPagination').removeClass('hide');
									if (view.pagination != null)
										view.pagination
												.twbsPagination('destroy');
									view.pagination = $('#pagination')
											.twbsPagination(
													{
														totalPages : response.totalPage,
														startPage : response.pageNo,
														onPageClick : function(
																event, page) {
															view.loadData(page);
														}
													});
								} else {
									$('#buyPagination').addClass('hide');
								}
							},
							error : function(response) {
								// alert("error");
								var rooms = $('.roomContainer');
								rooms.empty();
								rooms
										.append('<p>Something went wrong. Please try again</p>');
							}
						});
				/*
				 * $(".add-to-cart").click(function(){ alert("ddsfdsf"); //var
				 * roomId = $(this).attr('roomid'); view.addToCart(); });
				 */
			},
			loadCity : function() {
				var cities = new Array();
				var view = this;
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
							this.value = ui.item.value;
						}
						view.loadData();
					}
				});
			},
			hotelDetail : function(roomId, canAddToCart) {
				$('#modal2').empty();
				var view = this;
				this.hotelDetailModel = new HotelDetailModel({
					"baseUrl" : url,
					"canAddToCart" : canAddToCart
				});
				this.hotelDetailModel.url = url + "/rest/hotel/getHotelData/"
						+ roomId + ".json";
				this.hotelDetailModel.fetch({
					success : function(model, response) {
						// console.log("success "+response);
						// console.log(model);
						new HotelDetailView({
							model : model
						}).render();
						view.working = false;
					},
					error : function(model, response) {
						$('#modal2').append(response);
						$('#modal2').foundation('reveal', 'open');
						view.working = false;
					}
				});
			},
			addToCartRoom : function() {
				$(".add-cart-error").text('');
				var baseUrl = this.model.get('baseUrl');
				var loginWindowPopUp = this.loginWindow;
				var roomId = $(".roomId").val();
				var sDate = new Date($(".add-cart-check-in").val());
				var eDate = new Date($(".add-cart-check-out").val());
				var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2)  +  "/" + sDate.getFullYear();
				var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2)+ "/"  + ("0" + eDate.getDate()).slice(-2) + "/" + eDate.getFullYear();
				/*var startDate = sDate.getDate() + "/" + (sDate.getMonth()+1) + "/" + sDate.getFullYear();
				var endDate = eDate.getDate() + "/" + (eDate.getMonth()+1) + "/" + eDate.getFullYear();*/
				this.hideErrors();
				var view = this;
				
				if($(".fullSuite").val()== "false")
				{
					if($(".to").val()=='')
					{
						view.hotelDetail(roomId, true);
					}
					else
					{
						this.addToCartModel = new AddToCartModel({
							"roomId" : roomId,
							"checkInDate" : $(".from").val(),
							"checkOutDate" : $(".to").val()
						});
						this.addToCartModel.url = baseUrl + "/hotel/addToCart.json";
						if (!this.addToCartModel.isValid()) {
							//alert("Please Select Check In Date");
							$(".add-cart-error").text('Please Select Check In Date').css("color","#f78c30");
							this.showErrors(this.addToCartModel.validationError);
						} else {
							this.addToCartModel.save({}, {
								success : function(model, response) {
									window.location.href="cart";
								},
								error : function(model, error) {
									// console.log(error.responseJSON.errorCode);
									if (error.status == 401) {
										// console.log('login require');
										// $("#modal2").foundation('reveal', 'close');
										view.callBackData.functionName = "addToCartRoom";
										loginWindowPopUp.empty();
										this.loginModel = new LoginPopupModel({
											"baseUrl" : baseUrl,
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
											|| error.responseJSON.errorCode == 40411
											|| error.responseJSON.errorCode == 40001) {
										// console.log(error.responseJSON.errorCode);
										// console.log($('.modal-add-cart-error'));
										//alert(error.responseJSON.errorMessage);
										loginWindowPopUp.foundation('reveal', 'close');
										$(".add-cart-error").text(error.responseJSON.errorMessage).css("color","#f78c30");
										$(".add-cart-error").append("<br><br>");
										$('.modal-add-cart-error').addClass('error');
										$('.modal-add-cart-error').find('.help-inline')
												.text(error.responseJSON.errorMessage).css("color","#f78c30");
										// console.log($('.modal-add-cart-error').find(
										// '.help-inline'));
									}
									/*else
									{
										loginWindowPopUp.foundation('reveal', 'close');
										$(".add-cart-error").text(error.responseJSON.errorMessage).css("color","#f78c30");
										_.each(error.responseJSON.errors, function(error) {
											console.log(error);
											$(".add-cart-error").text(error.message).css("color","#f78c30");;
										}, this);
									}*/
								}
							});
						}
					
					}
					
				}
				else
				{
					this.addToCartModel = new AddToCartModel({
						"roomId" : roomId,
						"checkInDate" : startDate,
						"checkOutDate" : endDate
					});
					this.addToCartModel.url = baseUrl + "/hotel/addToCart.json";
					if (!this.addToCartModel.isValid()) {
						//alert("Please Select Check In Date");
						//$(".add-cart-error").text('Please Select Check In Date').css("color","#f78c30");
						this.showErrors(this.addToCartModel.validationError);
					} else {
						this.addToCartModel.save({}, {
							success : function(model, response) {
								window.location.href="cart";
							},
							error : function(model, error) {
								// console.log(error.responseJSON.errorCode);
								if (error.status == 401) {
									// console.log('login require');
									// $("#modal2").foundation('reveal', 'close');
									view.callBackData.functionName = "addToCartRoom";
									loginWindowPopUp.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
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
										|| error.responseJSON.errorCode == 40411
										|| error.responseJSON.errorCode == 40001) {
									// console.log(error.responseJSON.errorCode);
									// console.log($('.modal-add-cart-error'));
									//alert(error.responseJSON.errorMessage);
									loginWindowPopUp.foundation('reveal', 'close');
									$(".add-cart-error").text(error.responseJSON.errorMessage).css("color","#f78c30");
									$(".add-cart-error").append("<br><br>");
									$('.modal-add-cart-error').addClass('error');
									$('.modal-add-cart-error').find('.help-inline')
											.text(error.responseJSON.errorMessage).css("color","#f78c30");
									// console.log($('.modal-add-cart-error').find(
									// '.help-inline'));
								}
								/*else
								{
									$(".add-cart-error").text(error.responseJSON.errorMessage).css("color","#f78c30");
									_.each(error.responseJSON.errors, function(error) {
										console.log(error);
										$(".add-cart-error").text(error.message).css("color","#f78c30");;
									}, this);
								}*/
							}
						});
					}
				}
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.name);
					controlGroup.addClass('error');
					controlGroup.find('.help-inline').text(error.message).css("color","#f78c30");
				}, this);
			},

			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			},
			checkIfSubmitForm : function(e) {
				//this.loginWindow.foundation('reveal', 'close');
				if (this.callBackData.functionName == "addToCartRoom") {
					//this.loginWindow.foundation('reveal', 'close');
					this.callBackData.functionName = null;
					this.addToCartRoom();
				} 
			}
		});