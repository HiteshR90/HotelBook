var RoomListView = Backbone.View
		.extend({
			el : ".buyRoomList",
			initialize : function() {
				this.render();
			},
			score : 0,
			pagination : null,
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

				$('#starRating').raty({
					starOff : baseUrl + '/img/star-off.png',
					starOn : baseUrl + '/img/star-on.png',
					click : function(score, evt) {
						view.score = score;
						view.loadData();
					}
				});
				view.loadData();
			},
			events : {
				"change #hotel-brand" : "loadData",
				"click .amenities" : "loadData",
				"click .thContainer" : "thContainer"
			},
			working : false,
			thContainer : function(ev) {
				if (!this.working) {
					var view = this;
					var roomId = $(ev.currentTarget).attr('roomId');
					view.hotelDetail(roomId, true);
					this.working = true;
				}
			},
			loadData : function(pageNo) {
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				if (this.model.has('cityName')) {
					var cityName = this.model.get('cityName');
					$('.cityName').val(cityName);
					this.model.unset('cityName');
				}
				var url = baseUrl + '/hotel/getHotelRooms.json';
				var params = '?';
				params = params + 'priceMin='
						+ $('.slider-range').slider('values', 0) + '&';
				params = params + 'priceMax='
						+ $('.slider-range').slider('values', 1) + '&';
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
								var rooms = $('.roomContainer');
								rooms.empty();
								$
										.each(
												response.roomData,
												function(index, room) {
													var roomD = room.roomDesc;
													var roomDesc = roomD.length > 42 ? roomD
															.substring(0, 39)
															+ '...'
															: roomD;
													rooms
															.append('<div class="large-3 medium-6 small-6 columns thContainer end room-list-div-'
																	+ room.roomId
																	+ '" roomId="'
																	+ room.roomId
																	+ '">'
																	+ '<a href="#"><img src="'
																	+ baseUrl
																	+ '/images/hotelRooms/'
																	+ room.imagePath
																	+ '"></a>'
																	+ '<div class="TH-descript">'
																	+ '<p>'
																	+ roomDesc
																	+ '</p><p>'
																	+ '</p></div>'
																	+ '</div>');
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
								var rooms = $('.roomContainer');
								rooms.empty();
								rooms
										.append('<p>Something went wrong. Please try again</p>');
							}
						});
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
			}
		});