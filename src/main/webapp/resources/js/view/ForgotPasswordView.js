var ForgotPasswordView = Backbone.View
		.extend({
			template : Handlebars
					.compile('<div class="row modal1" style="margin-bottom:5%;">'
							+ '<div class="large-12 medium-12 small-12 columns" >'
							+ '<h1>Password Recovery</h1>'
							+ '</div>'
							+ '</div>'
							+ '<div class="row">'
							+ '<div class="large-12 medium-12 small-12 columns" id="pwRecovery-form">'
							+ '<form style="text-align:left">'
							+ '<p style="color: #171717;float: left;margin-bottom: 0;margin-right: 0;margin-top: 0;">Username or Email</p> '
							+'<input type="text" id="userNameOrEmail"  style="width:55%; margin-left:20px; color: #171717; border:1px solid #ccc; border-radius:5px;"></input>'
							+ '</form>'

							+ '<div class="commonMessage">'
							+ '<span style="color:red; font-size:13px;" class="help-inline"></span>'
							+ '</div>'

							+ '<div class="userNameOrEmail">'
							+ '<span style="color:red; font-size:13px; margin-left: 154px;" class="help-inline"></span>'
							+ '</div>'
							+

							'</div>'
							+ '</div>'
							+ '<div class="row">'
							+ '<div class="large-12 medium-12 small-12 columns" >'
							+ '<a class="button radius small send-password" style="font-family: Motion Picture; font-size:25px;letter-spacing:2px; float:right; padding:3%; margin-top:20px;">Send My Password</a>'
							+

							'</div>'
							+ '</div>'
							+ '<div>'
							+

							'<a class="close-reveal-modal"><img src="{{baseUrl}}/img/OSR_closeX.png" alt="X"/></a>'
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