var CartDetailView = Backbone.View
		.extend({
			template : Handlebars
					.compile('{{#if roomData}}'
							+ '<div id="modal3-content">'
							+ '<!-- modal content -->'
							+ '<div class="row" style=" margin-bottom:2%" >'
							+ '<div class="large-12 medium-12 small-12 columns modalTitle" >'
							+ '<h1 >'
							+ 'cart</h1>'
							+ '</div>'
							+ '</div>'
							+ '<div class="row">'
							+ '<div class="large-3 medium-3 small-3 columns">'
							+ '<h2>'
							+ 'Remove</h2>'
							+ '</div>'
							+ '<div class="large-9 medium-9 small-9 columns">'
							+ '<h2>'
							+ 'room details</h2>'
							+ '</div>'
							+ '</div>'
							+ '<!-- inner scrolling window -->'
							+

							'<div id="modal3-cartcontainer">'
							+

							'<div class="row collapse">'
							+

							'<div class="large-1 medium-1 small-1 columns">'
							+ '<div class="roomRemove"> X</div>'
							+

							'<div class="roomTimer">timer</div>'
							+ '</div>'
							+

							'<div class="modal3-roomdetails large-11 medium-11 small-11 columns">'
							+ '<div class="row">'
							+ '<div class="large-3 medium-3 small-3 columns">'
							+ '<a href="#" data-reveal-id="modal2"> <p>Hotel name / location</p></a>'
							+ '</div>'
							+ '<div class="large-3 medium-3 small-3 columns">'
							+ '<p>price</p>'
							+ '</div>'
							+

							'<div class="large-3 medium-3 small-3 columns">'
							+ '<p>Check-In:</p>'
							+ '</div>'
							+ '<div class="large-3 medium-3 small-3 columns">'
							+ '<p>Check-out:</p>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+

							'</div>'
							+

							'</div>'
							+ '<!--end scrolling inner window-->'
							+

							'<div class="row collapse">'
							+

							'<div class="large-12 medium-12 small-12 columns">'
							+ '<a href="#"><img src="img/OSR_bookroom.png" style="float: right;" alt="book it"/></a>'
							+ '</div>'
							+ '</div>'
							+ '</div>'

							+ '{{/if}}'
							+ '<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" alt="close"/></a>'),
			template3 : Handlebars.compile($('#modal3cart').html()),
			initialize : function() {
				this.listenTo(this.model, "change", this.render);
			},
			render : function() {
				return this;
			},
			events : {
				'click .payment-link' : 'payment'
			},
			payment : function() {
				alert(this.model.get('cartId'));
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.name);
					controlGroup.addClass('error');
					controlGroup.find('.help-inline').text(error.message);
				}, this);
			},
			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			}
		});

var ForgotPasswordSuccessView = Backbone.View
.extend({
	template : Handlebars
			.compile('<div class="row modal1">'
					+ '<div class="large-12 medium-12 small-12 columns" >'
					+ '<h1>Password Recovery</h1>'
					+ '</div>'
					+ '</div>'
					+ '<div class="row">'
					+ '<div class="large-12 medium-12 small-12 columns" style="color: #FFFDE9; font-size:20px; text-align:center;padding-bottom:5%">'
					+ 'Confirmation Email Has Been Sent!'
					+ '</div>'
					+ '</div>'
					+

					'<div class="row">'
					+ '<div class="large-12 medium-12 small-12 columns" >'
					+ '<a href="#" class="button radius right return-login" style="font-family: Motion Picture; font-size:25px;letter-spacing:2px; padding:3%;">Return to Login</a>'
					+

					'</div>'
					+ '</div>'
					+

					'<div>'
					+

					'<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" alt="close"/></a>'
					+ '</div>'),
	initialize : function() {
		this.listenTo(this.model, "change", this.render);
	},
	render : function() {
		$(this.el).empty();
		this.$el.html(this.template(this.model.attributes));
		return this;
	},
	events : {
		'click .return-login' : 'returnLogin'
	},
	returnLogin : function() {
		$('#modal1').empty();
		var model = this.model;
		this.loginView = new LoginPopupView({
			model : model
		});
		$('#modal1').append(this.loginView.render().el);
		//$('#modal1').foundation('reveal', 'open');
	}
});

var ForgotPasswordView = Backbone.View
.extend({
	template : Handlebars
			.compile('<div class="row modal1" style="margin-bottom:5%;">'
					+ '<div class="large-12 medium-12 small-12 columns" >'
					+ '<h1>Password Recovery</h1>'
					+ '</div>'
					+ '</div>'
					+ '<div class="row">'
					+ '<div class="large-12 medium-12 small-12 columns" id="pwRecovery-form" style="color: #FFFDE9">'
					+ '<form>'
					+ 'username or email <input type="text" id="userNameOrEmail"  style="margin-top:1%; "></input>'
					+ '</form>'

					+ '<div class="commonMessage">'
					+ '<span class="help-inline"></span>'
					+ '</div>'

					+ '<div class="userNameOrEmail">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+

					'</div>'
					+ '</div>'
					+ '<div class="row">'
					+ '<div class="large-12 medium-12 small-12 columns" >'
					+ '<a href="#" class="button radius small send-password" style="font-family: Motion Picture; font-size:25px;letter-spacing:2px; float:right; padding:3%;">Send My Password</a>'
					+

					'</div>'
					+ '</div>'
					+ '<div>'
					+

					'<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" alt=""/></a>'
					+ '</div>'),
	initialize : function() {
		this.listenTo(this.model, "change", this.render);
	},
	render : function() {
		$(this.el).empty();
		this.$el.html(this.template(this.model.attributes));
		return this;
	},
	events : {
		'click .send-password' : 'sendPassword'
	},
	sendPassword : function() {
		this.hideErrors();
		var modelMain = this.model;
		this.forgotPasswordModel = new ForgotPasswordModel({
			"userNameOrEmail" : $("#userNameOrEmail").val()
		});
		this.forgotPasswordModel.url = this.model.get('baseUrl')
				+ '/user/forgotPassword.json';
		if (!this.forgotPasswordModel.isValid()) {
			this.showErrors(this.forgotPasswordModel.validationError);
		} else {
			this.forgotPasswordModel.save({}, {
				success : function(model, response) {
					// redirect from here

					this.fpsView = new ForgotPasswordSuccessView({
						model : modelMain
					});
					// console.log('commp');
					$('#modal1').empty();
					$('#modal1').append(this.fpsView.render().el);
					// $('#modal1').foundation('reveal', 'open');
				},
				error : function(model, error) {
					if (error.responseJSON.errorCode == 40401
							|| error.responseJSON.errorCode == 40101) {
						$(".commonMessage").addClass('error');
						$(".commonMessage").find('.help-inline').text(
								error.responseJSON.errorMessage);
					}
				}
			});
		}
	},
	showErrors : function(errors) {
		_.each(errors, function(error) {
			var controlGroup = this.$('.' + error.name);
			controlGroup.addClass('error');
			controlGroup.find('.help-inline').text(error.message);
		}, this);
	},
	hideErrors : function() {
		this.$('.control-group').removeClass('error');
		this.$('.help-inline').text('');
	}
});

var HotelDetailView = Backbone.View
.extend({
	templateSuccess : Handlebars
			.compile('<div id="modal2-content">'
					+ '<div class="modal-add-cart-error">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '<div class="row collapsed">'
					+ '<div class="small-12 medium-6 large-6 columns modalTitle" style="padding: 0; margin: 0;">'
					+ '<h1>{{hotelName}}</h1>'
					+ '</div>'
					+ '<div class="small-12 medium-6 large-6 columns modalTitle" style="float: right; padding-bottom: 2%; margin: 0;">'
					+ '<h1>{{city}}</h1>'
					+ '</div>'
					+ '</div>'
					+

					'<div class="row collapse" id="modal2-imgsection">'
					+ '<div class="large-12 medium-12 small-12 columns" id="roomDetail-orbit">'
					+ '<ul data-orbit>'
					+ '{{#each roomPhotos}}'
					+ '<li><img src="{{../baseUrl}}/images/hotelRooms/{{this}}" alt="slide 1"></li>'
					+ '{{/each}}'
					+ '</ul>'
					+ '</div>'
					+ '</div>'
					+

					'<div class="row collapsed">'
					+ '<div class="small-4 medium-4 large-4 columns" style="padding-top: 2%;">Price:{{oneNightCost}}</div>'
					+ '<div class="small-4 medium-4 large-4 columns control-group checkInDate" style="padding-top: 2%;">'
					+ 'Check-In:'
					+ '{{#if isFullSuite}}'
					+ '<input type="text" class="add-to-cart-check-in" readonly="readonly" value="{{startDate}}"/>'
					+ '{{else}}'
					+ '<input type="text" class="add-to-cart-check-in" id="addTOCartCheckIn" readonly="readonly" value="{{startDate}}"/>'
					+ '{{/if}}'
					+ '<br>'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '<div class="small-4 medium-4 large-4 columns control-group checkOutDate" style="padding-top: 2%;">'
					+ 'Check-Out:'
					+ '{{#if isFullSuite}}'
					+ '<input type="text" class="add-to-cart-check-out" readonly="readonly" value="{{endDate}}"/>'
					+ '{{else}}'
					+ '<input type="text" id="addTOCartCheckOut" class="add-to-cart-check-out" readonly="readonly"  value="{{endDate}}"/>'
					+ '{{/if}}'
					+ '<br>'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div class="row collapsed">'
					+ '<div class="small-12 medium-12 large-12 columns" id="modal2-addCart">'
					+ '<a href="#" class="button radius right add-to-cart">ADD TO CART</a>'
					+ '</div>'
					+ '</div>'

					+ '<div class="row" id="modal2-tabcontain">'
					+ '<div class="small-12 medium-12 large-12 columns" id="modal2-tabs">'
					+ '<dl class="tabs radius" data-tab>'
					+ '<dd class="active">'
					+ '<a href="#panel2-1">Description</a>'
					+ '</dd>'
					+ '<dd>'
					+ '<a href="#panel2-2">Amenities</a>'
					+ '</dd>'
					+ '<dd>'
					+ '<a href="#panel2-3">Room Details</a>'
					+ '</dd>'
					+ '<dd>'
					+ '<a href="#panel2-4">User Reviews</a>'
					+ '</dd>'
					+

					'</dl>'
					+ '<div class="tabs-content">'
					+ '<div class="content active" id="panel2-1">'
					+ '<p><ul><li>Brand:{{hotelBrand}}</li></ul></p>'
					+ '</div>'
					+ '<div class="content" id="panel2-2">'
					+ '<p>'
					+ '<ul>'
					+ '{{#each roomDetails}}'
					+ '<li>{{this}}</li>'
					+ '{{/each}}'
					+ '</ul>'
					+ '</p>'
					+ '</div>'
					+ '<div class="content" id="panel2-3">'
					+ '<p><ul><li>RoomType:{{roomType}}</li><li>Guest:{{guests}}</li></ul></p>'
					+ '</div>'
					+ '<div class="content" id="panel2-4">'
					+ '<p>Fourth panel content goes here...</p>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" style="width: 37px; height: 37px;" alt="close" /></a>'),
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
		return this;
	},
	events : {
		'click .add-to-cart' : 'addToCart'
	},
	addToCart : function() {
		var baseUrl = this.model.get('baseUrl');
		var loginWindowPopUp = this.loginWindow;
		var roomId=this.model.get('roomId');
		this.hideErrors();
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
					$(".room-list-div-"+roomId).remove();
					$("#modal2").foundation('reveal', 'close');
					$("#modal2").empty();
				},
				error : function(model, error) {
					// console.log(error.responseJSON.errorCode);
					if (error.status == 401) {
						// console.log('login require');
						this.loginModel = new LoginPopupModel({
							"baseUrl" : baseUrl
						});
						this.loginView = new LoginPopupView({
							model : this.loginModel
						});
						$("#modal2").foundation('reveal', 'close');
						loginWindowPopUp.empty();
						loginWindowPopUp
								.append(this.loginView.render().el);
						loginWindowPopUp.foundation('reveal', 'open');
					} else if (error.responseJSON.errorCode == 40408
							|| error.responseJSON.errorCode == 40401
							|| error.responseJSON.errorCode == 40410) {
						// console.log(error.responseJSON.errorCode);
						// console.log($('.modal-add-cart-error'));
						$('.modal-add-cart-error').addClass('error');
						$('.modal-add-cart-error').find('.help-inline')
								.text(error.responseJSON.errorMessage);
						console.log($('.modal-add-cart-error').find(
								'.help-inline'));
					}
				}
			});
		}
	},
	showErrors : function(errors) {
		_.each(errors, function(error) {
			var controlGroup = this.$('.' + error.name);
			controlGroup.addClass('error');
			controlGroup.find('.help-inline').text(error.message);
		}, this);
	},

	hideErrors : function() {
		this.$('.control-group').removeClass('error');
		this.$('.help-inline').text('');
	},
	checkIfSubmitForm : function() {
		this.loginWindow.foundation('reveal', 'close');
		$("#modal2").foundation('reveal', 'open');
		this.addToCart();
	}
});


var LoginPopupView = Backbone.View
.extend({
	template : Handlebars
			.compile('<div>'
					+ '<form name="loginForm" id="loginForm" action="user/login" commandName="user">'
					+ '<section id="existinguser" style="float: left">'
					+ '<div id="modal1-topimg">'
					+ '<img src="{{baseUrl}}/img/OSR_existinguser.png" width="155" height="43" alt="exsiting user" />'
					+ '</div>'
					+ '<div id="modal1-contentA">'
					+ '<div class="grpelem commonMessage">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '<div id="username-form">'
					+ 'username<br> or email'
					+ '<input type="text" id="lUserName"/>'
					+ '<div class="grpelem lUserName control-group">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div>'
					+ 'password'
					+ '<input type="password" id="lPassword" />'
					+ '<div class="grpelem lPassword control-group">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div style="float: right">'
					+ '<a href="#" class="forgot-password-link"><img src="{{baseUrl}}/img/password-recovery.png" alt="forgot password?" /></a>'
					+ '</div>'
					+ '<div class="row">'
					+ '<div id="modal1-bottomimg" class="large-12 medium-12 small-12 columns">'
					+ '<a href="#" class="login-link"><img src="{{baseUrl}}/img/OSR_redloginLG.png" alt="login"/></a>'
					+ '</div>'
					+ '</div>'
					+ '<div>'
					+ '<img src="{{baseUrl}}/img/OSR_loginoption.png" alt="or" />'
					+ '</div>'
					+ '<div class="row">'
					+ '<div id="modal1-fb" class="large-12 medium-12 small-12 columns">'
					+ '<a href="#" class="login-fb-link"><img src="{{baseUrl}}/img/OSR_FBLG.png" alt="FB login" /></a>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '</section>'
					+ '</form>'
					+ '<form id="registrationForm" name="registrationForm" commandName="registerUser" method="post" action="user/registration">'
					+ '<section id="newuser" style="float: left">'
					+ '<div id="modal1-topimg">'
					+ '<img src="{{baseUrl}}/img/OSR_newuser.png" width="131" height="39" alt="New User" />'
					+ '</div>'
					+ '<div class="grpelem commonMessageReg">'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '<div id="modal1-contentB">'
					+ '<div>'
					+ 'email'
					+ '<input type="text" id="rEmail" />'
					+ '<div class="grpelem remail control-group">'
					+ '<!-- custom html -->'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div>'
					+ 'username'
					+ '<input type="text" id="rUserName" />'
					+ '<div class="grpelem ruserName control-group">'
					+ '<!-- custom html -->'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div>'
					+ 'password'
					+ '<input type="password" id="rPassword" />'
					+ '<div class="grpelem rpassword control-group">'
					+ '<!-- custom html -->'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div>'
					+ 'confirm<br> password'
					+ '<input type="password" id="rRetypePassword" />'
					+ '<div class="grpelem rretypePassword control-group">'
					+ '<!-- custom html -->'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div id="terms-box" style="float: left">'
					+ '<input type="checkbox" id="rIsAgree" />'
					+ 'By clicking this box I agree to the One Suite Room '
					+ '<a href="#" style="color: #A01E20">terms of use.</a>'
					+ '<div class="grpelem risAgree control-group">'
					+ '<!-- custom html -->'
					+ '<span class="help-inline"></span>'
					+ '</div>'
					+ '</div>'
					+ '<div id="modal1-bottomimg" style="float: left">'
					+ '<a href="#" class="register-link"><img src="{{baseUrl}}/img/OSR_redcreate.png" width="180" height="53" alt="Create Account"/></a>'
					+ '</div>'
					+ '</div>'
					+ '</section>'
					+ '</form>'
					+ '</div>'
					+ '<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" width="37"	height="37" alt="Close" /></a>'),
	initialize : function() {
		this.listenTo(this.model, "change", this.render);
	},
	render : function() {
		$(this.el).empty();
		this.$el.html(this.template(this.model.attributes));
		return this;
	},
	events : {
		'click .login-link' : 'login',
		'click .login-fb-link' : 'fbLogin',
		'click .register-link' : 'register',
		'click .forgot-password-link' : 'forgotPassword'
	},
	forgotPassword : function(){
		//$('#modal1').foundation('reveal', 'close');
		var model=this.model;
		this.fpView=new ForgotPasswordView({
			model : model
		});
		//console.log('commp');
		$('#modal1').empty();
		$('#modal1').append(this.fpView.render().el);
		//$('#modal1').foundation('reveal', 'open');
		//console.log('commp end');
	},
	login : function() {
		this.hideErrors();
		var callBack = this.model.get('callback');
		this.loginModel = new LoginModel({
			"userName" : $("#lUserName").val(),
			"password" : $("#lPassword").val()
		});
		this.loginModel.url = this.model.get('baseUrl')
				+ '/user/login.json';
		if (!this.loginModel.isValid()) {
			this.showErrors(this.loginModel.validationError);
		} else {
			this.loginModel
					.save(
							{},
							{
								success : function(model, response) {
									// redirect from here
									// this.trigger("auth");
									return $("#modal1").trigger(
											"authed", [ callBack ]);
								},
								error : function(model, error) {
									if (error.responseJSON.errorCode == 40401
											|| error.responseJSON.errorCode == 40101) {
										$(".commonMessage").addClass(
												'error');
										$(".commonMessage")
												.find('.help-inline')
												.text(
														error.responseJSON.errorMessage);
									}
								}
							});
		}
	},
	register : function() {
		this.hideErrors();
		this.registerModel = new RegisterModel({
			"userName" : $("#rUserName").val(),
			"email" : $("#rEmail").val(),
			"password" : $("#rPassword").val(),
			"retypePassword" : $("#rRetypePassword").val(),
			"isAgree" : $('#rIsAgree').is(":checked")
		});
		this.registerModel.url = this.model.get('baseUrl')
				+ '/user/signup.json';
		if (!this.registerModel.isValid()) {
			this.showErrors(this.registerModel.validationError);
			console.log(this.registerModel.validationError);
		} else {
			this.registerModel.save({}, {
				success : function(model, response) {
					this.$('.control-group').removeClass('error');
					this.$('.help-inline').text('');
					$("#lUserName").val('');
					$("#lPassword").val('');
					$("#rUserName").val('');
					$("#rEmail").val('');
					$("#rPassword").val('');
					$("#rRetypePassword").val('');
					$("#rIsAgree").val('');
					$(".commonMessageReg").find('.help-inline').text(
							response.message);
				},
				error : function(model, error) {
					if (error.responseJSON.errorCode == 40401) {
						$(".commonMessageReg").addClass('error');
						$(".commonMessageReg").find('.help-inline')
								.text(error.responseJSON.errorMessage);
					} else if (error.status == 400) {
						_.each(error.responseJSON.errors, function(
								error) {
							var controlGroup = this.$('.r'
									+ error.field);
							controlGroup.addClass('error');
							controlGroup.find('.help-inline').text(
									error.message);
						}, this);
					}
				}
			});
		}
	},
	fbLogin : function() {
		this.hideErrors();
		var baseUrl = this.model.get('baseUrl');
		FB.login(function(response) {
			if (response.authResponse) {
				var access_token = FB.getAuthResponse()['accessToken'];
				this.fbLoginModel = new FBLoginModel();
				this.fbLoginModel.url = baseUrl
						+ "/user/facebookLogin/" + access_token
						+ ".json";
				this.fbLoginModel.save({}, {
					success : function(model, response) {
						return $("#modal1").trigger("authed");
					},
					error : function(model, response) {
						$(".commonMessage").addClass('error');
						$(".commonMessage").find('.help-inline').text(
								error.responseJSON.errorMessage);
					}
				});
			} else {
				return false;
			}
		}, {
			scope : 'email'
		});
		return false;
	},
	showErrors : function(errors) {
		_.each(errors, function(error) {
			var controlGroup = this.$('.' + error.name);
			controlGroup.addClass('error');
			controlGroup.find('.help-inline').text(error.message);
		}, this);
	},
	hideErrors : function() {
		this.$('.control-group').removeClass('error');
		this.$('.help-inline').text('');
	}
});


var ProfilePreferenceView = Backbone.View
.extend({
	initialize : function() {
		this.listenTo(this.model, "change", this.render);
	},
	render : function() {

	},
	events : {

	},
	updateProfile : function() {
		this.profileModel = new ProfileModel({
			'email' : $('#email').val(),
			'firstName' : $('#firstName').val(),
			'lastName' : $('#lastName').val(),
			'lastName' : $('#email').val(),
			'gender' : $("input:radio[name='gender']:checked").val(),
			'dob' : $('#dob').val(),
			'address' : $('#address').val(),
			'address-city' : $('#address-city').val(),
			'address-state' : $('#address-state').val(),
			'address-zip' : $('#address-zip').val()
		});
		this.profileModel.url = '${pageContext.servletContext.contextPath}/user/updateUserInfo.json';
		if (!this.profileModel.isValid()) {
			this.showErrors(this.registerModel.validationError);
			console.log(this.registerModel.validationError);
		} else {
			
		}
	}
});