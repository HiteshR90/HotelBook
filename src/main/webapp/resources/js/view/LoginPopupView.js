var LoginPopupView = Backbone.View
		.extend({
			template : Handlebars
					.compile(

					'<!-- modal content -->'
							+ '<div>'
							+ '<div class="row" style="margin-bottom:2%;">'
							+ '<div class="large-12 medium-12 small-12 columns center">'
							+ '<img src="{{baseUrl}}/img/OSR-logo.png" width="200">'
							+ '</div>'
							+ '</div>'
							+ '<dl class="tabs" data-tab>'
							+ '<dd class="tab-title active"><a href="#panel1">Login</a></dd>'
							+ '<dd class="tab-title"><a href="#panel2">Register</a></dd>'
							+ '</dl>'
							+ '<div class="tabs-content">'
							+ '<div class="content active" id="panel1">'
							+ '<div class="row" style="margin-top:3%;">'
							+ '<div class="large-8 mediud-8 small-12 columns" >'

							+ '<!-- login by email or user name -->'
							+ '<div class="grpelem commonMessage">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
						//	+ '<form>'
							+ '<label>Email or Username'
							+ '<input type="text" class="modalInput" id="lUserName">'
							+ '<div class="grpelem lUserName control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							//+ '</form>'
							+ '<div>'
							+ '<label>Password'
							+ '<input type="password" class="modalInput" id="lPassword">'
							+ '<div class="grpelem lPassword control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</div>'
						//	+ '<form>'
							+ '<label class="modal" style="margin-top:10px;">'
							+ '<a href="#" class="forgot-password-link">Forgot my password.</a>'
							+ '</label>'
							//+ '</form>'
						//	+ '<form>'
							+ '<label style="margin-top:10px;">'
							+ '<input type="checkbox" id="rememberMe">'
							+ ' Remeber me on this devise.'
							+ '</label>'
							//+ '</form>'
							+ '<button class="red expand login-link">Login</button>'
							+ '</div>'
							+ '<div class="large-4 mediud-12 small-12 columns login-with">'
							+ '<div class="row center">'
							+ '<!-- login with social media -->'
							+ '<label class="socialLoabel">Login With:</label>'
							+ '<div class="large-12 medium-6 small-6 columns logimg-contain">'
							+ '<img class="loginWith login-fb-link" data-alt-src="{{baseUrl}}/img/facebook.1.png" src="{{baseUrl}}/img/facebook.png" width="55"><br><label style="font-size:.8em">facebook</label>'
							+ '</div>'
							+ '<div class="large-12 medium-6 small-6 columns logimg-contain">'
							+ '<img class="loginWith login-g-link" data-alt-src="{{baseUrl}}/img/google.1.png" src="{{baseUrl}}/img/google.png" width="55"><br><label style="font-size:.8em">google plus</label>'
							+ '</div>'
							+ '<!-- to add twiter change other to small- medium- 4 and  large -12 add a column for twitter below -->'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+

							'</div>'
							+ '<div class="content" id="panel2">'
							+ '<div class="row" style="margin-top:3%;">'
							+ '<div class="large-8 mediud-8 small-12 columns" >'
							+ '<!-- register by email or user name -->'
							+ '<div class="grpelem commonMessageReg">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '<form style="margin-right:10px;width:49%;float:left">'
							+ '<label>First Name *'
							+ '<input type="text" class="modalInput" id="rFname">'
							+ '<div class="grpelem rfname control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form style="width:48.5%;float:left">'
							+ '<label>Last Name *'
							+ '<input type="text" class="modalInput" id="rLname">'
							+ '<div class="grpelem rlname control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'

							+ '<form>'
							+ '<label>Username *'
							+ '<input type="text" class="modalInput" id="rUserName" >'
							+ '<div class="grpelem ruserName control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+

							'<form>'
							+ '<label>Email *'
							+ '<input type="text" class="modalInput" id="rEmail">'
							+ '<div class="grpelem remail control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form  style="margin-right:10px;width:49%;float:left">'
							+ '<label>Password *'
							+ '<input type="password" class="modalInput" id="rPassword">'
							+ '<div class="grpelem rpassword control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<form  style="width:48.5%;float:left">'
							+ '<label>Confirm Password *'
							+ '<input type="password" class="modalInput" id="rRetypePassword">'
							+ '<div class="grpelem rretypePassword control-group">'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+

							'<form style="clear:both">'
							+ '<label>'
							+ '<input type="checkbox" id="rIsAgree">'
							+ ' By clicking this box I agree to the One Suite Room terms of use. *'
							+ '<div class="grpelem risAgree control-group">'
							+ '<!-- custom html -->'
							+ '<span class="help-inline"></span>'
							+ '</div>'
							+ '</label>'
							+ '</form>'
							+ '<button class="red expand register-link">Submit</button>'
							+ '</div>'
							+ '<div class="large-4 mediud-12 small-12 columns login-with">'
							+ '<div class="row center" style="padding-bottom:40%;">'
							+ '<!-- login with social media -->'
							+ '<label class="socialLoabel">Sign up With:</label>'
							+ '<div class="large-12 medium-6 small-6 columns logimg-contain">'
							+ '<img class="loginWith login-fb-link" data-alt-src="{{baseUrl}}/img/facebook.1.png" src="{{baseUrl}}/img/facebook.png" width="55"><br><label style="font-size:.8em">facebook</label>'
							+ '</div>'
							+ '<div class="large-12 medium-6 small-6 columns logimg-contain">'
							+ '<img class="loginWith login-g-link" data-alt-src="{{baseUrl}}/img/google.1.png" src="{{baseUrl}}/img/google.png" width="55"><br><label style="font-size:.8em">google plus</label>'
							+ '</div>'
							+ '<!-- to add twiter change other to small- medium- 4 and  large -12 add a column for twitter below -->'
							+ '</div>' + '</div>' + '</div>' + '</div>'
							+ '</div>' + '</div>'
							+ '<!-- end modal content -->'
							+ '<a class="close-reveal-modal">X</a>'),
			initialize : function() {
				$('#modal1').addClass("medium");
				$('#modal1').removeClass("small");
				this.listenTo(this.model, "change", this.render);
				this.onLoadCallback();
			},
			render : function() {
				$(this.el).empty();
				$(document).foundation();
				this.$el.html(this.template(this.model.attributes));

				return this;
			},
			events : {
				'click .login-link' : 'login',
				'keyup #lPassword' : 'processLogin',
				'keypress #rUserName' :'noSpace',
				'click .login-fb-link' : 'fbLogin',
				'click .login-g-link' : 'googleSignIn',
				'click .register-link' : 'register',
				'click .forgot-password-link' : 'forgotPassword'
			},
			processLogin : function(e) {
				if (e.which === 13) { // enter key
					this.login();
				}
			},
			noSpace : function(e)
			{
				if (e.which == 32)
			        return false;
			},
			forgotPassword : function() {
				// $('#modal1').foundation('reveal', 'close');
				$('#modal1').removeClass("medium");
				$('#modal1').addClass("small");
				var model = this.model;
				this.fpView = new ForgotPasswordView({
					model : model
				});
				// console.log('commp');
				$('#modal1').empty();
				$('#modal1').append(this.fpView.render().el);
				// $('#modal1').foundation('reveal', 'open');
				// console.log('commp end');
			},
			login : function() {
				this.hideErrors();
				var callBack = this.model.get('callback');
				this.loginModel = new LoginModel({
					"userName" : $("#lUserName").val(),
					"password" : $("#lPassword").val()
				});
				var baseUrl = this.model.get('baseUrl');
				this.loginModel.url = baseUrl + '/user/login.json';
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
											var accountMenu = $('.accountMenu');
											if (accountMenu != null) {
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
											return $("#modal1").trigger(
													"authed", [ callBack ]);
										},
										error : function(model, error) {
											console.log(error);
											if (error.responseJSON.errorCode == 40401
													|| error.responseJSON.errorCode == 40101) {
												$(".commonMessage").addClass(
														'error');
												$(".commonMessage")
														.find('.help-inline')
														.text(
																error.responseJSON.errorMessage).css("color","#f78c30");
											}
										}
									});
				}
			},
			register : function() {
				this.hideErrors();
				this.registerModel = new RegisterModel({
					"userName" : $("#rUserName").val(),
					"fName" : $("#rFname").val(),
					"lName" : $("#rLname").val(),
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
							$("#rFname").val('');
							$("#rLname").val('');
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
										.text(error.responseJSON.errorMessage).css("color","#f78c30");
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
							else
							{
								$(".commonMessageReg").addClass('error');
								$(".commonMessageReg").find('.help-inline')
										.text(error.responseJSON.errorMessage).css("color","#f78c30");
							}
						}
					});
				}
			},
			fbLogin : function() {
				this.hideErrors();
				var callBack = this.model.get('callback');
				var baseUrl = this.model.get('baseUrl');
				FB
						.login(
								function(response) {
									if (response.authResponse) {
										var access_token = FB.getAuthResponse()['accessToken'];
										this.fbLoginModel = new FBLoginModel();
										this.fbLoginModel.url = baseUrl
												+ "/user/facebookLogin/"
												+ access_token + ".json";
										this.fbLoginModel
												.save(
														{},
														{
															success : function(
																	model,
																	response) {
																var accountMenu = $('.accountMenu');
																	if (accountMenu != null) {
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
																	return $("#modal1").trigger(
																			"authed", [ callBack ]);
																},
															error : function(
																	model,
																	response) {
																$(
																		".commonMessage")
																		.addClass(
																				'error');
																$(
																		".commonMessage")
																		.find(
																				'.help-inline')
																		.text(
																				error.responseJSON.errorMessage).css("color","#f78c30");
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
			googleSignIn : function() {
				var callBack = this.model.get('callback');
				var view =this;
				var myParams = {
					/*local*/
					'clientid' : '526083978170-f3ul1bjl21hdsjeqk0dn5sha37t4v5df.apps.googleusercontent.com',
					/*amazon*/
					//'clientid' : '526083978170-mlddafd4v9ff12sbqaaai4tuo6p8dqav.apps.googleusercontent.com',
					'cookiepolicy' : 'single_host_origin',
					'approvalprompt' : 'force',
					'callback' : function(result) {
						var access_token = result.access_token;
						var baseUrl = view.model.get('baseUrl');
						if (access_token != null) {

							this.googleLoginModel = new GoogleLoginModel();
							this.googleLoginModel.url = baseUrl
									+ "/user/googleLogin/" + access_token
									+ ".json";
							this.googleLoginModel
									.save(
											{},
											{
												success : function(model,
														response) {
													var accountMenu = $('.accountMenu');
														if (accountMenu != null) {
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
														return $("#modal1").trigger(
																"authed", [ callBack ]);
													},
												error : function(model,
														response) {
													$(".commonMessage")
															.addClass('error');
													$(".commonMessage")
															.find(
																	'.help-inline')
															.text(
																	error.responseJSON.errorMessage).css("color","#f78c30");
												}
											});
						}
					},
					'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
				};
				// gapi.auth.authorize(myParams,this.loginCallback);
				gapi.auth.signIn(myParams, this.loginCallback);
			},
			onLoadCallback : function() {
				/*local*/
				gapi.client.setApiKey('o0CM3o41qCqgbKOL2DHnft0O');
				/*amazon*/
				//gapi.client.setApiKey('lQWtIVvPHJl8PxgLjDq9hLCb');
				gapi.client.load('plus', 'v1', function() {
				});
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
			}
		});