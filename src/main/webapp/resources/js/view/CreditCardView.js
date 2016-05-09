var CreditCardView = Backbone.View
		.extend({
			template : Handlebars
					.compile(' <a class="close-reveal-modal" aria-label="Close">&#215;</a>'
							+'<div class="commonError">'
							+'<span class="help-inline"></span>'
							+'</div>'
							+'<div class="creditcard-field">'
							+'<tr>'
							+'<td><label>Card Number *</label></td>'
							+'<td><input id="cardNumber" maxlength="19" class="numberOnly modalInput" type="text" tabindex="1"/>'
							+'<label><div class="cardNumber">'
							+'<span class="help-inline"></span>'
							+'</div></label></td>'
							+'</tr>'
							+'</div>'
							+'<div class="cc-cvv">'
							+'<tr>'
							+'<td><label>CVV / CID(AMEX) *</label></td>'
							+'<td><input id="cvv" class="numberOnly modalInput" maxlength="4" type="text" tabindex="2"/>'
							+'<label><div class="cvv">'
							+'<span class="help-inline"></span>'
							+'</div></label></td>'
							+'</tr>'
							+'</div>'
							+'<tr>'
							+'<td><label>Card Expiration *</label> </td>'
							+'<td>'
							+'<div class="row collapse">'
							+'<div class="large-6 medium-6 small-6 columns">'
							+'<input id="expMonth" class="numberOnly modalInput" maxlength="2"	placeholder="month" type="text" tabindex="3"/>'
							+'<label><div class="expMonth">'
							+'<span class="help-inline"></span>'
							+'</div></label>'
							+'</div>'
							+'<div class="large-6 medium-6 small-6 columns">'
							+'<input id="expYear" class="numberOnly modalInput" maxlength="4" placeholder="year" type="text" tabindex="4"/>'
							+'<label><div class="expYear">'
							+'<span class="help-inline"></span>'
							+'</div></label>'
							+'</div>'
							+'</div>'
							+'</td>'
							+'</tr>'
							+'<div class="CCname-field">'
							+'<tr>'
							+'<td><label>Name on Card </label></td>'
							+'<td><input id="nameOnCard" class="modalInput" type="text" tabindex="5"/></td>'
							+'</tr>'
							+'</div>'
							+'<div class="CCaddress-field">'
							+'<tr>'
							+'<td><label>Address (if different from mailing.) </label></td>'
							+'<td><input id="address" class="modalInput" type="text" tabindex="6"/></td>'
							+'</tr>'
							+'</div>'
							+'<div class="city-field">'
							+'<tr>'
							+'<td><label>City</label> </td>'
							+'<td><input id="city" class="modalInput" type="text" tabindex="7"/></td>'
							+'</tr>'
							+'</div>'
							+'<div class="state-field">'
							+'<tr>'
							+'<td><label>State</label> </td>'
							+'<td><input id="state" class="modalInput" type="text" tabindex="8"/></td>'
							+'</tr>'
							+'</div>'
							+'<div class="zip-field">'
							+'<tr>'
							+'<td><label>Zip-Code </label></td>'
							+'<td><input id="zipCode" class="numberOnly modalInput" type="text" tabindex="9"/></td>'
							+'</tr>'
							+'</div>'
							+'<div class="large-12 medium-12 small-12 columns">'
							+'<button class="right red radius card-payment" tabindex="10">SUBMIT</button>'
							+'</div>'
							
							
							
							/*
							+ '<div class="commonError">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '<div class="creditcard-field">'
							+ '<label>Card Number <input id="cardNumber" maxlength="19" class="numberOnly" type="text"/>'
							+ '<div class="cardNumber">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '<div class="cc-cvv">'
							+ '<label>CVV / CID(AMEX) <input id="cvv" class="numberOnly" maxlength="4"  type="text"/>'
							+ '<div class="cvv">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</div>'
							+ '<label>Card Expiration'
							+ '<div class="row collapse">'
							+ '<div class="large-6 medium-6 small-6 columns">'
							+ '<input id="expMonth" class="numberOnly" maxlength="2" placeholder="month"  type="text"/>'
							+ '</div>'
							+ '<div class="expMonth">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '<div class="large-6 medium-6 small-6 columns">'
							+ '<input id="expYear" class="numberOnly" maxlength="4" placeholder="year"  type="text"/>'
							+ '</div>'
							+ '<div class="expYear">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</div>'
							+ '</label>'
							+ '<div class="CCname-field">'
							+ '<label>Name on Card<input id="nameOnCard"  type="text"/></label>'
							+ '</div>'
							+ '<div class="CCaddress-field">'
							+ '<label>Address (if different from mailing.)'
							+ '<input id="address"  type="text"/></label>'
							+ '</div>'
							+ '<div class="city-field">'
							+ '<label>City<input id="city"  type="text"/></label>'
							+ '</div>'
							+ '<div class="state-field">'
							+ '<label>State<input id="state"  type="text"/></label>'
							+ '</div>'
							+ '<div class="zip-field">'
							+ '<label>Zip-Code<input id="zipCode" class="numberOnly" type="text"/></label>'
							+ '</div>'
							+ '<div class="large-12 medium-12 small-12 columns">'
							+ '<button class="right radius card-payment">SUBMIT</button>'
							+ '</div>'*/),
			initialize : function() {
				this.listenTo(this.model, "change", this.render);
			},
			render : function() {
				$(this.el).empty();
				this.$el.html(this.template(this.model.attributes));
				return this;
			},
			events : {
				'click .card-payment' : 'cardPayment',
				'keypress .numberOnly' : 'onlyNumber'
			},
			onlyNumber : function(e) {
				if (e.which != 8 && e.which != 0
						&& (e.which < 48 || e.which > 57)) {
					return false;
				}
			},
			returnLogin : function() {
				$('#modal1').empty();
				var model = this.model;
				this.loginView = new LoginPopupView({
					model : model
				});
				$('#modal1').append(this.loginView.render().el);
				// $('#modal1').foundation('reveal', 'open');
			},
			cardPayment : function() {
				this.hideErrors();
				// var modelMain = this.model;
				var baseUrl = this.model.get('baseUrl');
				this.creditCardModel = new CreditCardModel({
					"cardNumber" : $("#cardNumber").val(),
					"cvv" : $("#cvv").val(),
					"expMonth" : $("#expMonth").val(),
					"expYear" : $("#expYear").val(),
					"nameOnCard" : $("#nameOnCard").val(),
					"address" : $("#address").val(),
					"city" : $("#city").val(),
					"state" : $("#state").val(),
					"zipCode" : $("#zipCode").val()
				});
				this.creditCardModel.url = baseUrl
						+ '/payment/credit-card.json';
				if (!this.creditCardModel.isValid()) {
					this.showErrors(this.creditCardModel.validationError);
				} else {
					this.creditCardModel
							.save(
									{},
									{
										success : function(model, response) {
											// console.log(response);
											// console.log(baseUrl
											// + '/hotel/rooms');
											/*location.href = baseUrl
													+ '/hotel/rooms';*/
											$("#CreditCardModal").foundation('reveal', 'close');
											$("#bookedContent").empty();
											$("#confirmedContent").css("display","block");
										},
										error : function(model, error) {
											if (error.status == 400) {
												if (error.responseJSON.errors != null) {
													_
															.each(
																	error.responseJSON.errors,
																	function(
																			error) {
																		var controlGroup = this
																				.$('.'
																						+ error.field);
																		controlGroup
																				.addClass('error');
																		controlGroup
																				.find(
																						'.help-inline')
																				.text(
																						error.message);
																	}, this);
												}
											} else if (error.status == 401) {
												//console.log(error.responseJSON.errorCode);
												if (error.responseJSON.errorCode == 40102)
													location.href = baseUrl;
											} else if (error.status == 402) {
												console.log(error);
												if (error.responseJSON.errorCode == 40201) {
													$('.commonError').addClass(
															'error');
													$('.commonError')
															.find(
																	'.help-inline')
															.text(
																	error.responseJSON.errorMessage);
												}
											}
											else
											{
												
													$('.commonError').addClass(
															'error');
													$('.commonError')
															.find(
																	'.help-inline')
															.text(
																	error.responseJSON.errorMessage);
											}
										}
									});
				}
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.field);
					controlGroup.addClass('error');
					controlGroup.find('.help-inline').text(error.message).css("color","#f78c30");
				}, this);
			},
			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			}
		});