var AccountView = Backbone.View
		.extend({
			el : $('.container'),
			callBackData : {
				functionName : null
			},
			sellSlider : null,
			purchaseSlider : null,
			pendingTranSlider : null,
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
				var template = Handlebars
						.compile($("#account_template").html());
				$(this.el).html(template());
				this.profileData(false);
				$(document).foundation();
				var baseUrl = this.model.get('baseUrl');
				sellSlider = $('#saleHistory').bxSlider({
					mode : 'vertical',
					slideWidth : "auto",
					infiniteLoop : false,
					minSlides : 2,
					slideMargin : 10,
					hideControlOnEnd : true
				});
				purchaseSlider = $('#purchaseHistory').bxSlider({
					mode : 'vertical',
					slideWidth : "auto",
					infiniteLoop : false,
					minSlides : 2,
					slideMargin : 10,
					hideControlOnEnd : true
				});
				/*pendingTranSlider = $('#pendingTransaction').bxSlider({
					mode : 'vertical',
					slideWidth : "auto",
					infiniteLoop : false,
					minSlides : 2,
					slideMargin : 10,
					hideControlOnEnd : true
				});*/
				$('#hotelStar-all').raty({
					starOff : baseUrl + '/img/star-off.png',
					starOn : baseUrl + '/img/star-on.png',
				});
				$('#hotelStar-first').raty({
					starOff : baseUrl + '/img/star-off.png',
					starOn : baseUrl + '/img/star-on.png',
				});
				$('#hotelBrand-all').multipleSelect({
					width : '100%'
				});
				$('#hotelBrand-first').change(function() {
					console.log($(this).val());
				}).multipleSelect({
					width : '100%'
				});		
				$('.onlyNumber').keypress(
						function(e) {
							if (e.which != 8 && e.which != 0
									&& (e.which < 48 || e.which > 57)) {
								return false;
							}
						});
			},
			events : {
				"click .editProfile" : "editProfile",
				"click .saveProfile" : "saveProfile",
				"click .myProfile" : "profileData",
				//"click .changePW" : "changePW",
				"click .bankAccount" : "bankAccount",
				"click .pendTrans" : "pendTrans",
				"click .mySales" : "mySales",
				"click .myPurchase" : "myPurchase",
				"click #changePassword" : "changePassword",
				"click .editPaypal" : "editPaypal",
				"click .updatePaypal" : "updatePaypal",
				"click .editBank" : "editBank",
				"click .updateBank" : "updateBank",
				"click .accountRelist" : "accountRelist",
				"click .cartDetail" : "cartDetail",
				"click .hotelSearch" : "hotelSearch",
				//"click .notification" : "notification",
				"click .settings" : "settings",
				"click .save-preferene-first" : "savePreferenceFirst",
				"click .save-preferene-all" : "savePreferenceAll",
				'keypress #reSellPrice' : 'onlyNumber',
			},
			editPaypal : function() {
				$("#pageName").val('BankAccount');
				$('#paypalCommonMSG').html('');
				$('.paypalDisplay').addClass('hide');
				$('.paypalEdit').removeClass('hide');
				$('.editPaypal').addClass('hide');
				$('.updatePaypal').removeClass('hide');
			},
			savePreferenceFirst : function() {
				$("#preferenceCommonMSG").html('');
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				var roomTypes = new Array();
				$.each($("input[name='roomType-first']:checked"), function() {
					roomTypes.push($(this).val());
				});
				
				this.preferenceModel = new PreferenceModel({
					'highFloor':$('#highFloor-first').is(":checked")?"true":"false",
					'earlyCheckIn':$('#earlyCheckIn-first').is(":checked"),
					'lateCheckOut':$('#lateCheckOut-first').is(":checked"),
					'hotelStar':$('#hotelStar-first').raty('score'),
					'roomType':roomTypes,
					'hotelBrand':$("#hotelBrand-first").multipleSelect("getSelects")
				});
				
				var baseUrl = this.model.get('baseUrl');
				this.preferenceModel.url = baseUrl + '/user/saveFirstUserPreference.json';
				if (!this.preferenceModel.isValid()) {
					view.showErrors(this.preferenceModel.validationError);
				} else {
					this.preferenceModel
							.save(
									{},
									{
										success : function(model, response) {
											$("#preferenceCommonMSG").html('saved successfully').css("color","#f78c30");
										},
										error : function(model, response) {
											if (response.status == 401) {
												view.callBackData.functionName = "savePreferenceFirst";
												view.loginWindow.empty();
												this.loginModel = new LoginPopupModel({
														"baseUrl" : baseUrl
												});
												this.loginView = new LoginPopupView(
														{
															model : this.loginModel
														});
												view.loginWindow
														.append(this.loginView
																.render().el);
												$(document).foundation();
												view.loginWindow.foundation(
														'reveal', 'open');
											} else if (response) {
												console.log(response);
											}
										}
									});
				}
			},
			savePreferenceAll : function() {
				$("#preferenceCommonMSG").html('');
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				var roomTypes = new Array();
				$.each($("input[name='roomType-all']:checked"), function() {
					roomTypes.push($(this).val());
				});
				
				this.preferenceModel = new PreferenceModel({
					'highFloor':$('#highFloor-all').is(":checked")?"true":"false",
					'earlyCheckIn':$('#earlyCheckIn-all').is(":checked"),
					'lateCheckOut':$('#lateCheckOut-all').is(":checked"),
					'hotelStar':$('#hotelStar-all').raty('score'),
					'roomType':roomTypes,
					'hotelBrand':$("#hotelBrand-all").multipleSelect("getSelects")
				});
				
				var baseUrl = this.model.get('baseUrl');
				this.preferenceModel.url = baseUrl + '/user/saveAllUserPreference.json';
				if (!this.preferenceModel.isValid()) {
					view.showErrors(this.preferenceModel.validationError);
				} else {
					this.preferenceModel
							.save(
									{},
									{
										success : function(model, response) {
											$("#preferenceCommonMSG").html('saved successfully');
										},
										error : function(model, response) {
											if (response.status == 401) {
												view.callBackData.functionName = "savePreferenceAll";
												view.loginWindow.empty();
												this.loginModel = new LoginPopupModel({
														"baseUrl" : baseUrl
												});
												this.loginView = new LoginPopupView(
														{
															model : this.loginModel
														});
												view.loginWindow
														.append(this.loginView
																.render().el);
												$(document).foundation();
												view.loginWindow.foundation(
														'reveal', 'open');
											} else if (response) {
												console.log(response);
											}
										}
									});
				}
			},
			hotelSearch : function() {
				$("#pageName").val('Preferences');
				$("#preferenceCommonMSG").html('');
				$('.right-header').html('Preferences');
				var view = this;
				view.notification();
				var baseUrl = this.model.get('baseUrl');
				$
						.ajax({
							url : baseUrl + "/user/getUserPreference.json",
							type : "GET",
							success : function(response) {
								var firstPreference = response.first;
								if (firstPreference != null) {
									if (firstPreference.highFloor != null) {
										if (firstPreference.highFloor === true)
											$("#highFloor-first").prop("checked",
													true);
									}
									if (firstPreference.earlyCheckIn != null) {
										if (firstPreference.earlyCheckIn === true)
											$("#earlyCheckIn-first").prop(
													"checked", true);
									}
									if (firstPreference.lateCheckOut != null) {
										if (firstPreference.lateCheckOut === true)
											$("#lateCheckOut-first").prop(
													"checked", true);
									}
									var roomTypes = [];
									$.each(firstPreference.roomType, function(
											i, val) {
										roomTypes.push(val);
									});
									$('input[name="roomType-first"]').prop(
											'checked',
											function() {
												return $.inArray(this.value,
														roomTypes) !== -1;
											});
									var hotelBrands = [];
									$.each(firstPreference.hotelBrand,
											function(i, val) {
												hotelBrands.push(val);
											});
									$("#hotelBrand-first").multipleSelect(
											"setSelects", hotelBrands);
									$('#hotelStar-first').raty('set', {
										score : firstPreference.hotelStar
									});
								}
								var allPreference = response.all;
								if (allPreference != null) {
									if (allPreference.highFloor != null) {
										if (allPreference.highFloor === true)
											$("#highFloor-all").prop(
													"checked", true);
									}
									if (allPreference.earlyCheckIn != null) {
										if (allPreference.earlyCheckIn === true)
											$("#earlyCheckIn-all").prop(
													"checked", true);
									}
									if (allPreference.lateCheckOut != null) {
										if (allPreference.lateCheckOut === true)
											$("#lateCheckOut-all").prop(
													"checked", true);
									}
									var roomTypesAll = [];
									$.each(allPreference.roomType, function(i,
											val) {
										roomTypesAll.push(val);
									});
									$('input[name="roomType-all"]').prop(
											'checked',
											function() {
												return $.inArray(this.value,
														roomTypesAll) !== -1;
											});
									var hotelBrandsAll = [];
									$.each(allPreference.hotelBrand, function(
											i, val) {
										hotelBrandsAll.push(val);
									});
									$("#hotelBrand-all").multipleSelect(
											"setSelects", hotelBrandsAll);
									$('#hotelStar-all').raty('set', {
										score : allPreference.hotelStar
									});
								}
							},
							error : function(error) {
								if (error.status == 401) {
									view.callBackData.functionName = "hotelSearch";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								}
							}
						});
			},
			notification : function() {
				$("#pageName").val('Notification');
				$('.right-header').html('Notification');
			},
			settings : function() {
				$('.content').removeClass('active');
				$("#pageName").val('Settings');
				$('.right-header').html('Settings');
			},
			accountRelist : function(event) {
				$("#pageName").val('PurchaseHistory');
				var currentDiv = $(event.currentTarget);
				var roomId = currentDiv.attr("roomid");
				var orderId = currentDiv.attr("orderid");
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				var reListModel = $('#reList');
				reListModel.empty();
				reListModel
						.append('<h2 style="font-family: Motion Picture; color: #C11A22; font-size: 45px; text-transform: capitalize; text-align:center">Re-List your reservation</h2>');
				reListModel
						.append('<h4 style="text-align: center; font-size: 18px;">What would you like to charge?</h4>');
				var costFieldHolder = $('<div style="display: block; text-align: center;"></div>');
				var costField = $('<input type="text" class="onlyNumber" id="reSellPrice" placeholder="cost per night" maxlength="5"/>');
				costFieldHolder.append(costField);
				reListModel.append(costFieldHolder);
				reListModel
						.append('<label><div class="control-group oneNightCostError"><span class="help-inline" style="text-align: center;display: block; margin-top: 6px;"></span></div></label>');
				var button = $('<button id="sellIntern" class="right sell-btn">Sell</button>');
				reListModel.append(button);
				reListModel.append('<a class="close-reveal-modal"><span style="text-size:12px;">&#215;</span></a>');
				/*reListModel.append('<a class="close-reveal-modal"><img src="'
						+ baseUrl + '/img/OSR_closeX.png" alt="&#215;" /></a>');*/
				$('.onlyNumber').keypress(
						function(e) {
							if (e.which != 8 && e.which != 0
									&& (e.which < 48 || e.which > 57)) {
								return false;
							}
						});
				button
						.click(function() {
							this.resellModel = new ReSellModel({
								'oneNightCost' : $("#reSellPrice").val()
							});
							this.resellModel.url = baseUrl
									+ '/hotel/sellRoomIntern/' + orderId + '/'
									+ roomId + '.json';
							if (!this.resellModel.isValid()) {
								_.each(this.resellModel.validationError,
										function(error) {
											var controlGroup = this.$('.'
													+ error.field + 'Error');
											controlGroup.addClass('error');
											controlGroup.find('.help-inline')
													.text(error.message);
										});
							} else {
								this.resellModel
										.save(
												{},
												{
													success : function(model,
															response) {
														currentDiv.remove();
														$("#reList")
																.foundation(
																		'reveal',
																		'close');
														view.mySales();
													},
													error : function(model,
															response) {
														if (response.status == 401) {
															view.callBackData.functionName = "updatePaypal";
															view.loginWindow.empty();
															this.loginModel = new LoginPopupModel(
																	{
																		"baseUrl" : baseUrl
																	});
															this.loginView = new LoginPopupView(
																	{
																		model : this.loginModel
																	});
															view.loginWindow
																	.append(this.loginView
																			.render().el);
															$(document).foundation();
															view.loginWindow
																	.foundation(
																			'reveal',
																			'open');
														} else if (response.status == 400
																&& response.responseJSON.errors != null) {
															_
																	.each(
																			response.responseJSON.errors,
																			function(
																					error) {
																				var controlGroup = this
																						.$('.'
																								+ error.field
																								+ 'Error');
																				controlGroup
																						.addClass('error');
																				controlGroup
																						.find(
																								'.help-inline')
																						.text(
																								error.message);
																			});
														}
													}
												});
							}
						});
				$("#reList").foundation('reveal', 'open');
			},
			updatePaypal : function() {
				$("#pageName").val('BankAccount');
				$('#paypalCommonMSG').html('');
				this.hideErrors();
				var view = this;
				this.paypalModel = new PaypalModel({
					'paypalId' : $('#paypalId').val()
				});
				var baseUrl = this.model.get('baseUrl');
				this.paypalModel.url = baseUrl + '/user/updatePaypalInfo.json';
				if (!this.paypalModel.isValid()) {
					this.showErrors(this.paypalModel.validationError);
				} else {
					this.paypalModel
							.save(
									{},
									{
										success : function(model, response) {
											$('.paypalDisplay').removeClass(
													'hide');
											$('.paypalEdit').addClass('hide');
											$('.editPaypal')
													.removeClass('hide');
											//$('.updatePaypal').addClass('hide');
											$('.paypalDisplay').html(
													$('#paypalId').val());
											$('#paypalCommonMSG').html(
													response.message).css("color","#f78c30");
										},
										error : function(model, response) {
											if (response.status == 401) {
												view.callBackData.functionName = "updatePaypal";
												view.loginWindow.empty();
												this.loginModel = new LoginPopupModel(
														{
															"baseUrl" : baseUrl
														});
												this.loginView = new LoginPopupView(
														{
															model : this.loginModel
														});
												view.loginWindow
														.append(this.loginView
																.render().el);
												$(document).foundation();
												view.loginWindow.foundation(
														'reveal', 'open');
											}else if (response.status == 400
													&& response.responseJSON.errors != null) {
												view.showErrors(response.responseJSON.errors);
											}
										}
									});
				}
			},
			editBank : function() {
				$("#pageName").val('BankAccount');
				$('#bankCommonMSG').html('');
				$('.bankAccountDisplay').addClass('hide');
				$('.bankAccountEdit').removeClass('hide');
				$('.routingNumberDisplay').addClass('hide');
				$('.routingNumberEdit').removeClass('hide');
				$('.editBank').addClass('hide');
				$('.updateBank').removeClass('hide');
			},
			updateBank : function() {
				$("#pageName").val('BankAccount');
				$('#bankCommonMSG').html('');
				this.hideErrors();
				var view = this;
				this.bankModel = new BankModel({
					'bankAccountNumber' : $('#accountNumber').val(),
					'bankRoutingNumber' : $('#routingNumber').val()
				});
				var baseUrl = this.model.get('baseUrl');
				this.bankModel.url = baseUrl + '/user/updateBankInfo.json';
				if (!this.bankModel.isValid()) {
					this.showErrors(this.bankModel.validationError);
				} else {
					this.bankModel.save({}, {
						success : function(model, response) {
							$('#bankCommonMSG').html(response.message).css("color","#f78c30");
							$('.bankAccountDisplay').removeClass('hide');
							$('.bankAccountEdit').addClass('hide');
							$('.routingNumberDisplay').removeClass('hide');
							$('.routingNumberEdit').addClass('hide');
							$('.editBank').removeClass('hide');
							//$('.updateBank').addClass('hide');

							$('.bankAccountDisplay').html(
									$('#accountNumber').val());
							$('.routingNumberDisplay').html(
									$('#routingNumber').val());
						},
						error : function(model, response) {
							// console.log(response);
							if (response.status == 401) {
								view.callBackData.functionName = "updateBank";
								view.loginWindow.empty();
								this.loginModel = new LoginPopupModel({
									"baseUrl" : baseUrl
								});
								this.loginView = new LoginPopupView({
									model : this.loginModel
								});
								view.loginWindow
										.append(this.loginView.render().el);
								$(document).foundation();
								view.loginWindow.foundation('reveal', 'open');
							} else if (response.status == 400
									&& response.responseJSON.errors != null) {
								view.showErrors(response.responseJSON.errors);
							}
						}
					});
				}
			},
			changePW : function() {
				$("#pageName").val('MyPassword');
				$('.right-header').html('Profile');
				$("#CPWCurrentPW").val('');
				$("#CPWNewPW").val('');
				$("#CPWRetypeNewPW").val('');
				$("#passwordCommonMSG").html('');
			},
			bankAccount : function() {
				$("#pageName").val('BankAccount');
				$('.right-header').html('Wallet');
				$('#paypalCommonMSG').html('');
				$('#bankCommonMSG').html('');
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				$
						.ajax({
							url : baseUrl + "/user/wallet.json",
							type : "GET",
							success : function(response) {
								var bankInfo = response.bankInfo;
								var paypalInfo = response.paypalInfo;
								if (bankInfo.bankAccountNumber != null) {
									$("#accountNumber").val(
											bankInfo.bankAccountNumber);
									/*$('.bankAccountDisplay').html(
											bankInfo.bankAccountNumber);*/
								}
								if (bankInfo.bankRoutingNumber != null) {
									$("#routingNumber").val(
											bankInfo.bankRoutingNumber);
									/*$('.routingNumberDisplay').html(
											bankInfo.bankRoutingNumber);*/
								}
								if (paypalInfo.paypalId != null) {
									$("#paypalId").val(paypalInfo.paypalId);
									/*$('.paypalDisplay').html(
											paypalInfo.paypalId);*/
								}
							},
							error : function(error) {
								if (error.status == 401) {
									view.callBackData.functionName = "profileData";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								}
							}
						});
			},
			pendTrans : function() {
				$("#pageName").val('PendingTransaction');
				$('.right-header').html('Wallet');
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				var transactionDiv = $('#pendTransDiv');
				transactionDiv.empty();
				$
						.ajax({
							url : baseUrl + "/user/getPendingTransactions.json",
							type : "GET",
							success : function(response) {
								// console.log(response);
								if (response.length > 0) {
									$
											.each(
													response,
													function(i, data) {
														var rowDiv = $('<div class="row accountTransCon"></div>');
														var imgDiv = $('<div class="large-3 columns accountTransImg"></div>');
														imgDiv
																.append('<img src="'
																		+ baseUrl
																		+ '/images/'
																		+ data.images[0]
																		+ '">');
														/*imgDiv
																.append('<div class="overlay"><span><p>SOLD</p></span></div>');*/
														var pDate = new Date(data.purchaseDate);
														var dDate = new Date(data.dipositDate);
														var purchaseDate =("0" + (pDate.getMonth() + 1)).slice(-2) + "/" + ("0" + pDate.getDate()).slice(-2)  +  "/" + pDate.getFullYear();
														var dipositDate =("0" + (dDate.getMonth() + 1)).slice(-2) + "/" + ("0" + dDate.getDate()).slice(-2)  +  "/" + dDate.getFullYear();
														rowDiv.append(imgDiv);
														var detailDiv = $('<div class="large-9 columns left"></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns"><a href="#" onclick="hotelDetail('
																		+ data.roomId
																		+ ',false);">'
																		+ data.hotelName
																		+ '</a></div></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns">purchase Date:<div style="float:right;padding-right:10%;">'
																		+ purchaseDate
																		+ '</div></div></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns">Exspected Desposit:<div style="float:right;padding-right:10%;">'
																		+ data.dipositAmount
																		+ ' </div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">Exspected Transaction deposit date:<div style="float:right;padding-right:10%;">'
																		+ dipositDate
																		+ ' </div></div></div>');
														rowDiv
																.append(detailDiv);
														transactionDiv
																.append(rowDiv);
													});
								} else {
									transactionDiv
											.append('<div>data not available</div>');
								}
								//pendingTranSlider.reloadSlider();
							},
							error : function(error) {
								if (error.status == 401) {
									view.callBackData.functionName = "myPurchase";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel,
										"callback" : "login"
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								}
							}
						});
			},
			mySales : function() {
				$("#pageName").val('SellHistory');
				$('.right-header').html('History');
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				view.myPurchase();
				var sellDiv = $('#saleHistory');
				sellDiv.empty();
				$
						.ajax({
							url : baseUrl + "/user/getSellHistory.json",
							type : "GET",
							success : function(response) {
								// console.log(response);
								if (response.length > 0) {
									$
											.each(
													response,
													function(i,data) {
														var rowDiv = $('<div class="row accountTransCon"></div>');
														var imgDiv = $('<div class="large-3 columns accountTransImg"></div>');
														imgDiv
																.append('<img style="width:300px;" src="'
																		+ baseUrl
																		+ '/images/'
																		+ data.images[0]
																		+ '">');
														if (data.isSold) {
															imgDiv
																	.append('<div class="overlay"><span><p style="position: relative; left: -10px; ">SOLD</p></span></div>');
														}
														var seDate = new Date(data.sellDate);
														var sDate = new Date(data.startDate);
														var eDate = new Date(data.endDate);
														var sellDate =("0" + (seDate.getMonth() + 1)).slice(-2) + "/" + ("0" + seDate.getDate()).slice(-2)  +  "/" + seDate.getFullYear();
														var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2)  +  "/" + sDate.getFullYear();
														var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2)+ "/"  + ("0" + eDate.getDate()).slice(-2) + "/" + eDate.getFullYear();
														rowDiv.append(imgDiv);
														var detailDiv = $('<div class="large-9 columns left"></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns"><a href="#" onclick="hotelDetail('
																		+ data.roomId
																		+ ',false);">'
																		+ data.hotelName
																		+ '</a></div></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns">Price:<div style="float:right;padding-right:10%;font-weight:bold;">$'
																		+ data.oneNightCost
																		+ '</div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">Sell Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ sellDate
																		+ ' </div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">Start Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ startDate
																		+ ' </div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">End Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ endDate
																		+ ' </div></div></div>');
														rowDiv
																.append(detailDiv);
														sellDiv.append(rowDiv);
													});

								} else {
									sellDiv
											.append('<div>data not available</div>');
								}
								sellSlider.reloadSlider();
							},
							error : function(error) {
								if (error.status == 401) {
									view.callBackData.functionName = "mySales";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
										"callback" : "login"
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								}
							}
						});
			},
			myPurchase : function() {
				$("#pageName").val('PurchaseHistory');
				$('.right-header').html('History');
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				var purchaseDiv = $('#purchaseHistory');
				purchaseDiv.empty();
				$
						.ajax({
							url : baseUrl + "/user/getBookHistory.json",
							type : "GET",
							success : function(response) {
								if (response.length > 0) {
									$
											.each(
													response,
													function(i, data) {
														var rowDiv = $('<div class="row accountTransCon"></div>');
														var imgDiv = $('<div class="large-3 columns accountTransImg"></div>');
														imgDiv
																.append('<img style="width:300px;" src="'
																		+ baseUrl
																		+ '/images/'
																		+ data.images[0]
																		+ '">');
														var startDate = new Date(
																data.startDate);
														var todayDate = new Date();
														if (!data.sellFlag
																&& startDate > todayDate) {
															imgDiv
																	.append('<div class="overlay"><span><p class="accountRelist" roomId="'
																			+ data.roomId
																			+ '" orderId="'
																			+ data.orderId
																			+ '"><a href="#">Re-List</a></p></span></div>');
														}
														var oDate = new Date(data.orderDate);
														var sDate = new Date(data.startDate);
														var eDate = new Date(data.endDate);
														var orderDate =("0" + (oDate.getMonth() + 1)).slice(-2) + "/" + ("0" + oDate.getDate()).slice(-2)  +  "/" + oDate.getFullYear();
														var startDate =("0" + (sDate.getMonth() + 1)).slice(-2) + "/" + ("0" + sDate.getDate()).slice(-2)  +  "/" + sDate.getFullYear();
														var endDate = ("0" + (eDate.getMonth() + 1)).slice(-2)+ "/"  + ("0" + eDate.getDate()).slice(-2) + "/" + eDate.getFullYear();
														rowDiv.append(imgDiv);
														var detailDiv = $('<div class="large-9 columns left"></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns"><a href="#" onclick="hotelDetail('
																		+ data.roomId
																		+ ',false);">'
																		+ data.hotelName
																		+ '</a></div></div>');
														detailDiv
																.append('<div class="row accountInfo"><div class="large-12 columns">Price:<div style="float:right;padding-right:10%;font-weight:bold;">$'
																		+ data.oneNightCost
																		+ '</div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">Reservation Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ orderDate
																		+ '</div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">Start Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ startDate
																		+ '</div></div></div>');
														detailDiv
																.append('<div class="row"><div class="large-12 columns">End Date:<div style="float:right;padding-right:10%;font-weight:bold;">'
																		+ endDate
																		+ '</div></div></div>');
														rowDiv
																.append(detailDiv);
														purchaseDiv
																.append(rowDiv);
													});
								} else {
									purchaseDiv
											.append('<div>data not available</div>');
								}
								purchaseSlider.reloadSlider();
							},
							error : function(error) {
								if (error.status == 401) {/*
									view.callBackData.functionName = "myPurchase";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								*/}
							}
						});
			},
			changePassword : function() {
				$("#pageName").val('CHANGEPASSWORD');
				this.hideErrors();
				$("#passwordCommonMSG").html('');
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				this.changePasswordModel = new ChangePasswordModel({
					"currentPassword" : $("#CPWCurrentPW").val(),
					"newPassword" : $("#CPWNewPW").val(),
					"retypeNewPassword" : $("#CPWRetypeNewPW").val()
				});
				this.changePasswordModel.url = baseUrl
						+ "/user/changePassword.json";
				if (!this.changePasswordModel.isValid()) {
					this.showErrors(this.changePasswordModel.validationError);
				} else {
					this.changePasswordModel
							.save(
									{},
									{
										success : function(model, response) {
											$("#CPWCurrentPW").val('');
											$("#CPWNewPW").val('');
											$("#CPWRetypeNewPW").val('');
											$("#passwordCommonMSG").html(
													response.message).css("color","#f78c30");
										},
										error : function(model, error) {
											if (error.status == 401) {
												view.callBackData.functionName = "changePassword";
												view.loginWindow.empty();
												this.loginModel = new LoginPopupModel(
														{
															"baseUrl" : baseUrl
														});
												this.loginView = new LoginPopupView(
														{
															model : this.loginModel
														});
												view.loginWindow
														.append(this.loginView
																.render().el);
												$(document).foundation();
												view.loginWindow.foundation(
														'reveal', 'open');
											} else if (error.status == 400
													&& error.responseJSON.errors != null) {
												view
														.showErrors(error.responseJSON.errors);
											}
										}
									});
				}
			},
			profileData : function(NotLoadCart) {
				$("#pageName").val('Profile');
				$('.right-header').html('Profile');
				var baseUrl = this.model.get('baseUrl');
				var view = this;
				view.changePW();
				$
						.ajax({
							url : baseUrl + "/user/profile.json",
							type : "GET",
							success : function(response) {
								if (!NotLoadCart) {
									view.cartDetail(false);
									var accountMenu = $('.accountMenu');

									accountMenu.empty();
									accountMenu
											.append('<a href="#">Hi, '
													+ response.userName
													+ '</a>');
									accountMenu
											.append('<ul class="dropdown"><li><a href="'
													+ baseUrl
													+ '/user/account">My Account</a></li><li class="active"><a href="'
													+ baseUrl
													+ '/user/logout">Log out</a></li></ul>');
									$(document).foundation();
								
								}
								$('#nameDisplay').val(response.userName);
								$('#emailDisplay').val(response.email);
								if (response.phoneNumber != null
										&& response.phoneNumber != "") {
									$('#phoneDisplay').html(
											response.phoneNumber);
									$("#phoneEdit").val(response.phoneNumber);
								}

								var address = "";
								if (response.fName != null
										&& response.address != "") {
									$("#fNameEdit").val(response.fName);
								}
								if (response.lName != null
										&& response.address != "") {
									$("#lNameEdit").val(response.lName);
								}
								
								if (response.address != null
										&& response.address != "") {
									address = address + response.address;
									$("#addressEdit").val(response.address);
								}

								if (response.city != null
										&& response.city != "") {
									address = address + "," + response.city;
									$("#cityEdit").val(response.city);
								}
								if (response.state != null
										&& response.state != "") {
									address = address + "," + response.state;
									$("#stateEdit").val(response.state);
								}
								if (response.zipcode != null
										&& response.zipcode != "") {
									address = address + "," + response.zipcode;
									$("#zipEdit").val(response.zipcode);
								}
								$('#addressDisplay').html(address);

								if (response.country != null
										&& response.country != "") {
									$('#countryDisplay').html(response.country);
									$("#countryEdit").val(response.country);
								}

							},
							error : function(error) {
								// console.log(error);
								if (error.status == 401) {
									view.callBackData.functionName = "profileData";
									view.loginWindow.empty();
									this.loginModel = new LoginPopupModel({
										"baseUrl" : baseUrl,
										"callback" : "login"
									});
									this.loginView = new LoginPopupView({
										model : this.loginModel
									});
									view.loginWindow.append(this.loginView
											.render().el);
									$(document).foundation();
									view.loginWindow.foundation('reveal',
											'open');
								}
							}
						});
			},
			editProfile : function() {
				$("#myProfile .accountInfoContain").css("margin-bottom", "2%");
				$("#pageName").val('Profile');
				$("#profileCommonMSG").html('');
				$('.display').addClass('hide');
				$('.edit').removeClass('hide');
			},
			saveProfile : function() {
				$("#pageName").val('Profile');
				this.hideErrors();
				$("#profileCommonMSG").html('');
				var view = this;
				var baseUrl = this.model.get('baseUrl');
				this.profileModel = new ProfileModel({
					"firstName" : $("#fNameEdit").val(),
					"lastName" : $("#lNameEdit").val(),
					"address" : $("#addressEdit").val(),
					"city" : $("#cityEdit").val(),
					"state" : $("#stateEdit").val(),
					"country" : $("#countryEdit").val(),
					"zipcode" : $("#zipEdit").val(),
					"phoneNumber" : $("#phoneEdit").val()
				});
				this.profileModel.url = baseUrl + "/user/updateUserInfo.json";
				if (!this.profileModel.isValid()) {
					this.showErrors(this.profileModel.validationError);
				} else {
					this.profileModel
							.save(
									{},
									{
										success : function(model, response) {
											$("#myProfile .accountInfoContain")
													.css("margin-bottom", "7%");
											$('.display').removeClass('hide');
											$('.edit').addClass('hide');
											$("#profileCommonMSG").html(
													response.message).css("color","#f78c30");

											$('#phoneDisplay').html(
													$("#phoneEdit").val());

											var address = "";
											if ($("#addressEdit").val() != "") {
												address = address
														+ $("#addressEdit")
																.val();
											}

											if ($("#cityEdit").val() != "") {
												address = address + ","
														+ $("#cityEdit").val();
											}
											if ($("#stateEdit").val() != "") {
												address = address + ","
														+ $("#stateEdit").val();
											}
											if ($("#zipEdit").val() != "") {
												address = address + ","
														+ $("#zipEdit").val();
											}
											$('#addressDisplay').html(address);

											$('#countryDisplay').html(
													$("#countryEdit").val());

										},
										error : function(model, error) {
											if (error.status == 401) {
												view.callBackData.functionName = "saveProfile";
												view.loginWindow.empty();
												this.loginModel = new LoginPopupModel(
														{
															"baseUrl" : baseUrl
														});
												this.loginView = new LoginPopupView(
														{
															model : this.loginModel
														});
												view.loginWindow
														.append(this.loginView
																.render().el);
												$(document).foundation();
												view.loginWindow.foundation(
														'reveal', 'open');
											} else if (error.status == 400
													&& error.responseJSON.errors != null) {
												view
														.showErrors(error.responseJSON.errors);
											}
										}
									});
				}
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.field + 'Error');
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
				if (this.callBackData.functionName == "profileData") {
					this.loginWindow.foundation('reveal', 'close');
					this.callBackData.functionName = null;
					this.profileData();
				} else if (this.callBackData.functionName == "changePassword") {
					this.callBackData.functionName = null;
					this.changePassword();
				} else if (this.callBackData.functionName == "saveProfile") {
					this.callBackData.functionName = null;
					this.saveProfile();
				} else if (this.callBackData.functionName == "updatePaypal") {
					this.callBackData.functionName = null;
					this.updatePaypal();
				} else if (this.callBackData.functionName == "updateBank") {
					this.callBackData.functionName = null;
					this.updateBank();
				} else if (this.callBackData.functionName == "myPurchase") {
					this.loginWindow.foundation('reveal', 'close');
					this.callBackData.functionName = null;
					this.myPurchase();
				} else if (this.callBackData.functionName == "hotelSearch") {
					this.loginWindow.foundation('reveal', 'close');
					this.callBackData.functionName = null;
					this.hotelSearch();
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
			}
		});