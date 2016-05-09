var SellView = Backbone.View
		.extend({
			sellDetail : new ExternalSellModel(),
			template : Handlebars
					.compile('<a class="close-reveal-modal">X</a>'
							+ '<!-- modal content -->'
							+ '<div id="reservationDetails">'
							+ '<div class="row" style="margin-bottom: 0;">'
							+ '<div class="large-4 medium-12 small-12 columns center">'
							+ '<img src="{{baseUrl}}/img//OSR-logo.png" width="200">'
							+ '</div>'
							+ '<div class="large-8 medium-12 small-12 columns center"'
							+ 'style="padding-top: 20px;">'
							+ '<p style="margin-bottom: 0px; color: #a11e21">Need to sell your'
							+ ' room? No problem!</p>'
							+ "<p style='color: #a11e21'>Let's get started!</p>"
							+ '</div>'
							+ '</div>'
							+ '<div class="row" style="margin: 0 25px;">'
							+ '<h5>Step 1: Reservation Details</h5>'
							+ '<div class="large-7 medium-8 small-12 columns"'
							+ 'style="padding-left: 0px;">'
							+ '<div>'
							+ '<span id="commonMessage"></span>'
							+ '<form>'
							+ '<div id="hotelContainer">'
							+ '<label>Hotel Name *<input type="text" id="SearchName"'
							+ 'class="modalInput hotel_name_input" tabindex="1">'
							+ '<div class="hotelNameError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '</form>'
							
							+ '<form>'
							+ '<label>Hotel Address *<input type="text" id="SearchAddress"'
							+ 'class="modalInput" tabindex="2">'
							+ '<div class="addressError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form>'
							+ '<div id="cityContainer">'
							+ '<label>City *<input type="text" id="SearchCity"'
							+ 'class="modalInput cityName" tabindex="3">'
							+ '<div class="cityError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '</form>'
							+ '<form>'
							+ '<div id="stateContainer">'
							+ '<label>State *<input type="text" id="SearchState"'
							+ 'class="modalInput stateName" tabindex="4" readonly="readonly">'
							+ '<div class="stateError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '</form>'
							+ '<form>'
							+ '<div id="zipContainer">'
							+ '<label>Zip Code *<input type="text" id="SearchZip"'
							+ 'class="modalInput pincode" tabindex="5" maxlength="6">'
							+ '<div class="pincodeError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '</form>'
							+ '<form>'
							+ '<label>Room Type  *<select name="select-choice-1" id="roomType"'
							+ 'style="margin-top: 5px; background-color: #eee; border-top: 1px solid:#ccc; border: none; width: 100%; margin-bottom:'
							+ 'auto;  outline: none" tabindex="6">'
							+ '<option value="0"></option>'
							+ '<option value="1">Single Room</option>'
							+ '<option value="2">Double Bed</option>'
							+ '<option value="3">Twin Room</option>'
							+ '<option value="5">Interconnecting Room</option>'
							+ '<option value="6">Adjoining Room</option>'
							+ '<option value="7">Hollywood Twin Room</option>'
							+ '<option value="8">Duplex Room</option>'
							+ '<option value="9">Cababa</option>'
							+ '<option value="10">Studio Room</option>'
							+ '<option value="11">Parlor</option>'
							+ '<option value="12">Lanai</option>'
							+ '<option value="13">Efficiency Room.</option>'
							+ '<option value="14">Suite</option>'
							+ '<option value="15" class="roomTypeOtherLink">Other</option>'
							+ '</select>'
							+ '<input type="text" id="otherRoom" placeholder="Other" class="hide modalInput"'
							+ 'tabindex="7"/>'
							+ '<div class="roomTypeError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div></label>'
							+ '</form>'
							+ '<form style="margin-right: 10px; width: 48.5%; float: left">'
							+ '<label>Adults *<input type="text" id="adults" maxlength="1" placeholder="max 6" class="modalInput" tabindex="8">'
							+ '<div class="adultError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form style="width: 48.5%; float: left">'
							+ '<label>Children *<input type="text" id="childrens" maxlength="1"maxlength="1" placeholder="max 6" class="modalInput" tabindex="9">'
							+ '<div class="childerenError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form style="margin-right: 10px; width: 48.5%; float: left">'
							+ '<label>Check In *<input type="text" id="from"'
							+ 'class="modalInput" readonly="readonly" tabindex="10">'
							+ '<div class="checkInDateError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form style="width: 48.5%; float: left">'
							+ '<label>Check Out *<input type="text" id="to"'
							+ 'class="modalInput" readonly="readonly" tabindex="11">'
							+ '<div class="checkOutDateError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<button class="red right sellSubmit" id="hotelSelect" tabindex="12">Next</button>'
							+ '</div>'
							+ '</div>'
							+ '<div class="large-5 medium-4 small-12 columns login-with">'
							+ '<div style="padding-right: 20px; padding-bottom: 27%;">'
							+ '<p>Need to sell a non-refundable room, or one for a trip you'
							+ " just can't make anymore? Are you ready to find someone to take"
							+ ' that reservation off your hands? We have you covered!</p>'
							+ '<p>Follow the prompts and fill out the booking details as they'
							+ ' appeared on your original reservation.</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '<!-- end modal content -->'

							+ '<div id="bookingDetails" style="display:none">'
							+ '<div class="row" style="margin-bottom: 0;">'
							+ '<div class="large-4 medium-12 small-12 columns center">'
							+ '<img src="{{baseUrl}}/img//OSR-logo.png" width="200">'
							+ '</div>'
							+ '<div class="large-8 medium-12 small-12 columns center"'
							+ 'style="padding-top: 20px;">'
							+ "<p style='color: #a11e21'>Now let's enter your booking details.</p>"
							+ '</div>'

							+ '</div>'
							+ '<div class="row" style="margin: 0 25px;">'
							+ '<h5>Step 2: Booking Information</h5>'
							+ '<div class="large-7 medium-12 small-12 columns"'
							+ 'style="padding-left: 0px;">'
							+ '<div>'
							+ '<form style="margin-right: 10px; width: 48.5%; float: left">'
							+ '<label>First Name *<input type="text" id="firstName"'
							+ 'class="modalInput" tabindex="1">'
							+ '<div class="firstNameError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form style="width: 48.5%; float: left">'
							+ '<label>Last Name *<input type="text" id="lastName"'
							+ 'class="modalInput" tabindex="2">'
							+ '<div class="lastNameError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form>'
							+ '<label>Email *<input type="text" id="email"'
							+ 'class="modalInput" tabindex="3">'
							+ '<div class="emailError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form>'
							+ '<label>Where Did You Book From? *<input type="text"'
							+ 'id="bookFrom" class="modalInput" tabindex="4">'
							+ '<div class="bookFromError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form>'
							+ '<label>Confirmation Code *<input type="text"'
							+ 'id="confirmationCode" class="modalInput" tabindex="5">'
							+ '<div class="confirmationCodeError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<button type="button" class="red left bookingBack" tabindex="7">Back</button>'
							+ '<button class="red right bookingSubmit" tabindex="6">Next</button>'
							+ '</div>'
							+ '</div>'
							+ '<div class="large-5 medium-12 small-12 columns login-with">'
							+ '<div style="padding-right: 20px; padding-bottom: 25%;">'
							+ '<p>Enter in the information for your booking. We will use this '
							+ 'to confirm the reservation with the hotel.</p>'
							+ '<p>This way, we can transfer everything correctly to the '
							+ 'purchaser.</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'

							+ '<div id="priceDetail" style="display:none">'
							+ '<div class="row" style="margin-bottom: 0;">'
							+ '<div class="large-4 medium-12 small-12 columns center">'
							+ '<img src="{{baseUrl}}/img//OSR-logo.png" width="200">'
							+ '</div>'
							+ '<div class="large-8 medium-12 small-12 columns center"'
							+ 'style="padding-top: 20px;">'
							+ '<p style="color: #a11e21">How much do you want to charge for the '
							+ 'room?</p>'
							+ '</div>'
							+ '</div>'
							+ '<div class="row" style="margin: 0 25px;">'
							+ '<h5>Step 3: Pricing Information</h5>'
							+ '<div class="large-7 medium-12 small-12 columns"'
							+ 'style="padding-left: 0px;">'
							+ '<div>'
							+ '<form>'
							+ '<label>Set Your Price Per Night *<input type="text"'
							+ 'class="modalInput" id="sellPrice" maxlength="4" tabindex="1">'
							+ '<div class="priceError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<div>'
							+ '<h6>'
							+ 'Total Booking:<span> $ </span> + OSR FEE'
							+ '</h6>'
							+ '</div>'
							+ '<form>'
							+ '<label>How would you like to get paid? *<select id="selectPaymentType"'
							+ 'style="margin-top: 5px; background-color: #eee; border-top: 1px solid:#ccc; border: none; width: 100%; margin-bottom:'
							+ 'auto;  outline: none" tabindex="2">'
							+ '<option value=""></option>'
							+ '<option value="1">Paypal</option>'
							+ '<option value="2">Authorize.net</option>'
							+ '</select>'
							+ '<div class="paymentTypeError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form>'
							+ '<label>Were You Already Charged For The Room? *'
							+ '<select id="paymentStatus" style="margin-top: 5px; background-color: #eee; border-top: 1px solid:#ccc; border: none; width: 100%; margin-bottom:'
							+ 'auto;  outline: none" tabindex="3">'
							+ '<option value=""></option>'
							+ '<option value="">Yes</option>'
							+ '<option value="">No</option>'
							+ '</select>'
							+ '<div class="paymentStatusError control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<button type="button" class="red left priceBack" tabindex="5">Back</button>'
							+ '<button class="red right priceSubmit" tabindex="4">Next</button>'
							+ '</div>'
							+ '</div>'
							+ '<div class="large-5 medium-12 small-12 columns login-with">'
							+ '<div style="padding-right: 20px; padding-bottom: 70%;">'
							+ '<p>How much do you want to charge per night for this '
							+ 'reservation?</p>' + '</div>' + '</div>'
							+ '</div>' + '</div>'),
			callBackData : {
				functionName : null
			},
			initialize : function() {
				// this.listenTo(this.model, "change", this.render);
				return this.loginWindow = $("#modal1"), this.loginWindow.on(
						"authed", function(e) {
							return function() {
								return e.checkIfSubmitForm()
							}
						}(this)), this.listenTo(this.model, "change",
						this.render);
			},
			render : function() {
				// $(this.el).empty();
				this.$el.html(this.template(this.model.attributes));
				$('#sellModal').append(this.el);
				var sellModal = $('#sellModal');
				sellModal.foundation('reveal', 'open');
				$(document).foundation();
				var sellDetailTemp = this.sellDetail;
				var view = this;
				$("#from").datepicker({
					dateFormat : 'mm/dd/y',
					minDate : 0,
					onSelect : function(selectedDate) {
						sellDetailTemp.set('checkInDate', selectedDate);
						/* $('#progressCheckIn').html(selectedDate); */
						sellDetailTemp.set('checkOutDate', '');
						view.sellDetail.set('checkInDate', selectedDate);
						/* $('#progressCheckOut').html(''); */
						var date2 = $("#from").datepicker('getDate');
						date2.setDate(date2.getDate() + 1);
						$("#to").datepicker('setDate', date2);
						$("#to").datepicker("option", "minDate", date2);
					}
				});
				$("#to").datepicker(
						{
							dateFormat : 'mm/dd/y',
							minDate : 0,
							onSelect : function(selectedDate) {
								var dt1 = $("#from").datepicker('getDate');
								var dt2 = $("#to").datepicker('getDate');
								view.sellDetail.set('checkOutDate',
										selectedDate);
								// check to prevent a user from entering a date
								// below date of
								// dt1
								if (dt2 <= dt1) {
									var minDate = $("#to").datepicker('option',
											'minDate');
									$("#to").datepicker('setDate', minDate);
								} else {
									sellDetailTemp.set('checkOutDate',
											selectedDate);
									/* $('#progressCheckOut').html(selectedDate); */
								}
							}
						});

				var hotelResponse;
				$(".hotel_name_input")
						.autocomplete(
								{
									source : function(request, response) {
										var baseUrl = view.model.get('baseUrl');

										$
												.ajax({
													url : baseUrl
															+ "/hotel/find-hotel-by-name.json?query="
															+ request.term,
													type : "GET",
													success : function(data) {
														// console.log(response);
														if (data.length > 0) {
															hotelResponse = data;
															response($
																	.map(
																			data,
																			function(
																					hotelDetail) {
																				return {
																					label : hotelDetail.name,
																					id : hotelDetail.id
																				}
																			}));
														} else {
														}
													},
													error : function(response) {
														// console.log(response);
													}
												});
									},
									select : function(event, ui) {
										var selectedHotel;
										$.each(hotelResponse, function(index) {
											if (this.id === ui.item.id) {
												selectedHotel = this;
											}
										});
										$("#cityContainer").hide();
										$("#stateContainer").hide();
										$("#zipContainer").hide();
										$("#SearchAddress").val(
												selectedHotel.address);
										view.sellDetail.set('hotelId',
												selectedHotel.id);
										view.sellDetail.set('hotelName',selectedHotel.name);
										view.sellDetail.set('hotelAddress',selectedHotel.address);
										var textLength = selectedHotel.name.length;
										if (textLength > 25) {
											var textVal = selectedHotel.name;
											textVal = textVal.substring(0, 25)
													+ '...';
										} else {
											console.log("else");
										}
										$("#hotelSelect").focus();
									},
									appendTo : "#hotelContainer"
								});
				this.loadCity();
				$("#roomType").change(function() {
					if ($("#roomType :selected").text() === "Other") {
						$("#otherRoom").removeClass("hide");
						
					} else {
						$("#otherRoom").addClass("hide");
						//view.sellDetail.set('roomType', '');
					}
				});
				$(".roomTypeOtherLink").click(function() {

				});
				/*$("#sellPrice").blur(function(e){
					if (e.value.length < 2) {
						$(".priceError").find('.help-inline').text("Price Must be greater than 10").css(
								'color', '#f78c30');
					}
				});*/
				// $(document).foundation();
				/*
				 * if(view.sellDetail.get('hotelId')!=null) {
				 * $("#SearchName").val(view.sellDetail.get('hotelId')); }
				 * if(view.sellDetail.get('roomType')!= null) {
				 * 
				 * $("#roomType option").each(function() { if($(this).text() ==
				 * view.sellDetail.get('roomType')) { $(this).attr('selected',
				 * 'selected'); } }); } if(view.sellDetail.get('checkInDate')!=
				 * null) {
				 * $('#from').datepicker().val(view.sellDetail.get('checkInDate')); }
				 * if(view.sellDetail.get('checkOutDate')!= null) {
				 * $('#to').datepicker().val(view.sellDetail.get('checkOutDate')); }
				 * if(view.sellDetail.get('adult')!= null) {
				 * $('#adults').val(view.sellDetail.get('adult')); }
				 * if(view.sellDetail.get('childeren')!= null) {
				 * $('#childrens').val(view.sellDetail.get('childeren')); }
				 */
			},
			events : {
				"click #hotelSelect" : "hotelSelect",
				"click .sellRoom" : "sellRoom",
				'keyup .hotel_name_input' : 'keyUpOnInputHandler',
				'keypress #sellPrice' : 'onlyNumber',
				'keypress #SearchZip' : 'onlyNumber',
				'keypress #adults' : 'numberLength',
				'keypress #childrens' : 'numberLength',
				'keyup #sellPrice' : 'setPrice',
				// 'keypress #adults' : 'onlyNumber',
				// 'keypress #childrens' : 'onlyNumber',
				"click .bookingBack" : "bookingBack",
				"click .bookingSubmit" : "sellRoom",
				"click .priceBack" : "priceBack",
				"click .priceSubmit" : "submitSellForm",
				//"blur #sellPrice" : "minLength"
			/*
			 * "click .confirmButton" : "confirmMessage", "click .confirmBack" :
			 * "confirmBack"
			 */
			},
			loadCity : function() {
				var cities = new Array();
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				$.ajax({
					url : baseUrl + "/hotel/cityName.json",
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
							/*$.ajax({
								url : baseUrl + "/hotel/stateName.json",
								type : "GET",
								data : {"cityName" : this.value},
								success : function(data)
								{
									concole.log("data == "+data);
								},
								error : function(error)
								{
									console.log("error");
								}
							});*/
							$.ajax({
								url : baseUrl + "/hotel/stateName.json",
								type : 'POST',
								data : {"cityName" : this.value},
								/*headers : 
									{
										'Accept' : 'application/json',
										'Content-Type' : 'application/json; charset=UTF-8'
									},*/
								success :function(data)
								{
									console.log(data.message);
									$("#SearchState").val(data.message);
								},
								error : function(data) {
									console.log("Error");
								}
							});
						}
					},
					appendTo : "#cityContainer"
				});
			},
			loadState : function(cityName) {
				$.ajax({
					url : baseUrl + "/hotel/StateName.json",
					type : "POST",
					data : {"cityName" : cityName},
					success : function(response) {
						$("#SearchState").val(response);
					},
					error : function(error)
					{
						console.log("error");
					}
				});
			},
			numberLength : function(e) {
				if (e.which != 8 && e.which != 0
						&& (e.which < 48 || e.which > 54)) {
					return false;
				}
			},
			minLength : function(e)
			{
				if (e.value.length < 2) {
					$(".priceError").find('.help-inline').text("Price Must be greater than 10").css(
							'color', '#f78c30');
				}
			},
			bookingBack : function() {
				$("#reservationDetails").show();
				$('#bookingDetails').hide();
				$('#priceDetail').hide();
				$('#conformDetail').hide();
			},
			priceBack : function() {
				$("#reservationDetails").hide();
				$('#bookingDetails').show();
				$('#priceDetail').hide();
				$('#conformDetail').hide();
			},
			confirmBack : function() {
				$("#reservationDetails").hide();
				$('#bookingDetails').hide();
				$('#priceDetail').show();
				$('#conformDetail').remove();
			},
			hotelSelect : function() {
				console.log('clicked');
				var sellDetailTemp = this.sellDetail;
				var view = this;
				view.hideErrors();
				var roomType = "";
				if ($("#roomType :selected").text() === "Other") {
					roomType = $('#otherRoom').val();
				}
				else
				{
					$('#otherRoom').val("");
					roomType = $("#roomType :selected").text();
				}
				if (sellDetailTemp.get('hotelId') == null
						|| sellDetailTemp.get('hotelId') == '') {
					// $("#cityError").html('');
					var baseUrl = this.model.get('baseUrl');
					
					this.hotelAddModel = new HotelAddModel({
						"hotelName" : $("#SearchName").val(),
						"address" : $("#SearchAddress").val(),
						"city" : $("#SearchCity").val(),
						"state" : $("#SearchState").val(),
						"pincode" : $("#SearchZip").val()
					});
					this.reservationDetailModel = new ReservationDetailModel({
						'roomType' : roomType,
						'checkInDate' : $("#from").val(),
						'checkOutDate' : $("#to").val(),
						'adult' : $("#adults").val(),
						'childeren' : $("#childrens").val()
					});
					this.hotelAddModel.url = baseUrl + "/hotel/add.json";
					if (!this.hotelAddModel.isValid()
							&& !this.reservationDetailModel.isValid()) {
						this.showErrors(this.hotelAddModel.validationError);
						this.showErrors(this.reservationDetailModel.validationError);
					} else {
						this.hotelAddModel.save({}, {
							success : function(model, response) {
								console.log("sucess");
								
								 //$("#commonMessage").html("New Hotel Added.").css("color", "#f78c30");
								 
								sellDetailTemp.set('hotelId', response);
								sellDetailTemp.set('hotelName',$(".hotel_name_input").val());
								sellDetailTemp.set('hotelAddress',$("#SearchAddress").val());
								// $("#SearchCity").val("");
								// $("#SearchAddress").val("");
								var textLength = $("#SearchName").val().length;
								if (textLength > 25) {
									var textVal = $("#SearchName").val();
									textVal = textVal.substring(0, 25) + '...';
								}
								sellDetailTemp.set('roomType', roomType);
								sellDetailTemp.set('checkInDate', $("#from").val());
								sellDetailTemp.set('checkOutDate', $("#to").val());
								sellDetailTemp.set('adult', $("#adults").val());
								sellDetailTemp.set('childeren', $("#childrens").val());
								// $('#sellModal').empty();
								if(!(sellDetailTemp.get('hotelId'))==null || !(sellDetailTemp.get('hotelId')) == '')
								{
									$("#reservationDetails").hide();
									$('#bookingDetails').css("display", "block");
								}
							},
							error : function(model, response) {
								
								// $("#commonMessage").html(error.errorMessage);
								sellDetailTemp.set('hotelId', '');
								// sellDetailTemp.hotelId = null;
								if (response.status == 404) {
									var cityError = $(".cityError");
									cityError.find('.help-inline').text(
											response.responseJSON.errorMessage)
											.css("color", "#f78c30");
								}
								else
								{
									console.log(response);
									_.each(response.responseJSON.errors, function(e) {
										var controlGroup = this.$('.' + e.field + 'Error');

										controlGroup.find('.help-inline').text(e.message).css(
												'color', '#f78c30');
										console.log("error ::: " + e.field + " ::: "
												+ e.message);
									}, this);
								}
							}
						});
					}
				} else {

					this.reservationDetailModel = new ReservationDetailModel({
						'roomType' : roomType,
						'checkInDate' : $("#from").val(),
						'checkOutDate' : $("#to").val(),
						'adult' : $("#adults").val(),
						'childeren' : $("#childrens").val()
					});
					if (!this.reservationDetailModel.isValid()) {
						this
								.showErrors(this.reservationDetailModel.validationError);
					} else {
						this.sellDetail.set('roomType', roomType);
						this.sellDetail.set('checkInDate', $("#from").val());
						this.sellDetail.set('checkOutDate', $("#to").val());
						this.sellDetail.set('adult', $("#adults").val());
						this.sellDetail.set('childeren', $("#childrens").val());
						// $('#sellModal').empty();
						$("#reservationDetails").hide();
						$('#bookingDetails').css("display", "block");
					}
					/*
					 * if(this.sellDetail.get('confirmationCode')!=null) {
					 * $("#confirmationCode").val(this.sellDetail.get('confirmationCode')); }
					 */
				}
			},
			sellRoom : function(event) {
				this.hideErrors();
				this.bookingModel = new BookingModel({
					'confirmationCode' : $("#confirmationCode").val(),
					'firstName' : $("#firstName").val(),
					'lastName' : $("#lastName").val(),
					'email' : $("#email").val(),
					'bookFrom' : $("#bookFrom").val()
				});
				if (!this.bookingModel.isValid()) {
					this.showErrors(this.bookingModel.validationError);
				} else {
					this.sellDetail.set('confirmationCode', $(
							"#confirmationCode").val());
					this.sellDetail.set('firstName', $("#firstName").val());
					this.sellDetail.set('lastName', $("#lastName").val());
					this.sellDetail.set('email', $("#email").val());
					this.sellDetail.set('bookFrom', $("#bookFrom").val());

					$("#reservationDetails").hide();
					$('#bookingDetails').hide();
					$('#priceDetail').css("display", "block");
				}

			},
			submitSellForm : function() {
				var baseUrl = this.model.get('baseUrl');
				this.sellDetail.set('price', $("#sellPrice").val());
				this.sellDetail.set('paymentType', $(
						"#selectPaymentType :selected").text());
				this.sellDetail.set('paymentStatus',$(
						"#paymentStatus :selected").text())
				this.hideErrors();
				var view = this;
				if (!this.sellDetail.isValid()) {
					this.showErrors(this.sellDetail.validationError);
				} else {
					
											$("#reservationDetails").hide();
											$('#bookingDetails').hide();
											$('#priceDetail').hide();
											$("#conformDetail").remove();
											var sellConfirm = $('#sellModal');
											sellConfirm
													.append('<div id="conformDetail">'
															+ '<div class="row" style="margin-bottom:0;">'
															+ '<div class="large-4 medium-12 small-12 columns center">'
															+ '<img src="'
															+ baseUrl
															+ '/img/OSR-logo.png" width="200">'
															+ '</div>'
															+ '<div class="large-8 medium-12 small-12 columns center" style="padding-top:20px;" >'
															+ '<p style=" color:#a11e21">Almost Done!  Please confirm the details below.</p>'
															+ '</div>'
															+ '</div>'
															+ '<div class="row" style="margin:0 25px;">'
															+ '<div class="large-7 medium-12 small-12 columns" style="padding-left:0px;">'
															+ '<div>'
															+ '<div>'
															+ '<h6 style="margin-bottom:0px; margin-top:10px; display:block;">Reservation Details</h6>'
															+ '<label>Hotel Name:<span> '
															+ view.sellDetail.get('hotelName')
															//+ response.hotelName
															+ '</span></label>'
															+ '<label>Hotel Address:<span> '
															+ view.sellDetail.get('hotelAddress')
															//+ response.hotelAddress
															+ '</span></label>'
															+ '<label>Room Type:<span> '
															+ view.sellDetail
																	.get('roomType')
															+ '</span></label>'
															+ '<label>Adults: <span>'
															+ view.sellDetail
																	.get('adult')
															+ '</span></label>'
															+ '<label>Children:<span> '
															+ view.sellDetail
																	.get('childeren')
															+ '</span></label>'
															+ '<label>Booking Dates:<span> '
															+ view.sellDetail.get('checkInDate')
															//+ response.checkInDate
															+ '</span> to <span> '
															+ view.sellDetail.get('checkOutDate')
															//+ response.checkOutDate
															+ ' </span></label>'
															+ '</div>'
															+ '<div>'
															+ '<h6 style="margin-bottom:0px; margin-top:10px; display:block;">Booking Information</h6>'
															+ '<label>First Name:<span> '
															+ view.sellDetail
																	.get('firstName')
															+ '</span></label> <label>Last Name:<span> '
															+ view.sellDetail
																	.get('lastName')
															+ '</span></label>'
															+ '<label>Email: <span style="text-transform: lowercase;">'
															+ view.sellDetail.get('email')
															+ '</span></label>'
															+ '<label>Reservation Origination:<span> '
															+ view.sellDetail
																	.get('bookFrom')
															+ '</span></label>'
															+ '<label>Confirmation Code:<span> '
															+ view.sellDetail
																	.get('confirmationCode')
															+ '</span></label>'
															+ '</div>'
															+ '<div>'
															+ '<h6 style="margin-bottom:0px; margin-top:10px; display:block;">Price Information</h6>'
															+ '<label>Price Per Night:<span> '
															+ view.sellDetail.get('price')
															//+ response.price
															+ '</span></label>'
															+ '<label>How to receive Funds: <span>'
															+ view.sellDetail
																	.get('paymentType')
															+ '</span></label>'
															+ '<label>Room payment Status: <span>'+ view.sellDetail.get('paymentStatus')
															+'</span></label>'
															+ '</div>'
															+ '<div style="margin-top:20px;">'
															+ '<button type="button" class="red left confirmBack">Back</button><button class="red right confirmButton">Confirm</button>'
															+ '</div>'
															+ '</div>'
															+ '</div>'
															+ '<div class="large-5 medium-12 small-12 columns login-with" >'
															+ '<div style="padding-right:20px;padding-bottom:90%;">'
															// +'<p>txt</p>'
															+ '</div>'
															+ '</div>'
															+ '</div>'
															+ '</div>');
											$(".confirmBack").click(function() {
												view.confirmBack();
											});
											$(".confirmButton").click(
													function() {
														view.confirmMessage();
											});
				}
										
			},
			confirmMessage : function() {
				var baseUrl = this.model.get('baseUrl');
				var loginWindowPopUp = this.loginWindow;
				var view = this;
				this.sellDetail.url = baseUrl + "/hotel/sellRoomExtern.json";
				if (!this.sellDetail.isValid()) {
					this.showErrors(this.sellDetail.validationError);
				} else {
				this.sellDetail
				.save(
						{},
						{
							success : function(model, response) {
								// console.log(response);
								
									$('#sellModal').empty();
									$('#sellModal').append('<div>'
										+ '<div class="row">'
										+ '<div class="large-12 medium-12 small-12 columns left">'
										+ '<img src="'
										+ baseUrl
										+ '/img/OSR-logo.png" width="200">'
										+ '</div>'
										+ '</div>'
										+ '<div class="row">'
										+ '<div class="large-12 medium-12 small-12 columns center">'
										+ '<h5>Congratulations</h5>'
										+ '<p style="margin-bottom:0;">Your Listing is now in que for review.</p>'
										+ '<p style="margin-bottom:0;">You will receive an e-mail confirmation once your reservation has posted.</p>'
										+ '<p style="margin-bottom:0;">You can view the status of your sale under your profile.</p>'
										+ '</div>'
										+ '</div>'
										+ '</div><a class="close-reveal-modal">X</a>');
									$('#sellModal').foundation('reveal', 'open');
									$(document).foundation();
							},
							error : function(model, error) {
								console.log(error);
								if (error.status == 401) {
									view.callBackData.functionName = "confirmMessage";
									loginWindowPopUp.empty();
									this.loginModel = new LoginPopupModel(
											{
												"baseUrl" : baseUrl
											});
									this.loginView = new LoginPopupView(
											{
												model : this.loginModel
											})
									loginWindowPopUp
											.append(this.loginView
													.render().el);
									$(document).foundation();
									loginWindowPopUp.foundation(
											'reveal', 'open');
								} else if (error.status == 404) {
								} else {
								}
							}
						});
				}
				// $(document).foundation();
			},
			keyUpOnInputHandler : function(e) {
				var view = this;
				if (e.which != 13 && e.which != 37 && e.which != 39
						&& e.which != 38 && e.which != 40) {
					view.sellDetail.set('hotelId', '');
					$('#progressHotelName').html('');
					// sellDetailInt.hotelId = null;
					$("#cityDiv").removeClass('hide');
					$("#SearchAddress").val('');
				}
			},
			onlyNumber : function(e) {
				if (e.which != 8 && e.which != 0
						&& (e.which < 48 || e.which > 57)) {
					return false;
				}
			},
			setPrice : function() {
				var oneNightPrice = $("#sellPrice").val();
				var sellDetailTemp = this.sellDetail;

				var strDate = sellDetailTemp.get('checkInDate');
				var enDate = sellDetailTemp.get('checkOutDate');

				var strDateParts = strDate.split("/");
				var endDateParts = enDate.split("/");

				var startDate = new Date(strDateParts[2],
						(strDateParts[1] - 1), strDateParts[0]);
				var endDate = new Date(endDateParts[2], (endDateParts[1] - 1),
						endDateParts[0]);

				var t1 = startDate.getTime();
				var t2 = endDate.getTime();
				var days = parseInt((t2 - t1) / (24 * 3600 * 1000));
				console.log($("#sellPrice").val());
				var totalCost = oneNightPrice * days;
				$("#priceTotal").html("$" + totalCost);
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.field + 'Error');

					controlGroup.find('.help-inline').text(error.message).css(
							'color', '#f78c30');
					console.log("error ::: " + error.field + " ::: "
							+ error.message);
				}, this);
			},
			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			},
			checkIfSubmitForm : function() {
				if (this.callBackData.functionName == "confirmMessage") {
					this.confirmMessage();
					
				}
				
			}
		});
