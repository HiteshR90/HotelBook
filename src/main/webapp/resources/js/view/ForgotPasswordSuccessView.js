var ForgotPasswordSuccessView = Backbone.View
		.extend({
			template : Handlebars
					.compile('<div class="row modal1">'
							+ '<div class="large-12 medium-12 small-12 columns" >'
							+ '<h1>Password Recovery</h1>'
							+ '</div>'
							+ '</div>'
							+ '<div class="row">'
							+ '<div class="large-12 medium-12 small-12 columns" style="color: #171717; font-size:20px; text-align:center;padding-bottom:5%">'
							+ 'Confirmation Email Has Been Sent!'
							+ '</div>'
							+ '</div>'
							+

							'<div class="row">'
							+ '<div class="large-12 medium-12 small-12 columns" >'
							+ '<a class="button radius right return-login" style="font-family: Motion Picture; font-size:25px;letter-spacing:2px; padding:3%;">Return to Login</a>'
							+

							'</div>'
							+ '</div>'
							+

							'<div>'
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
				'click .return-login' : 'returnLogin'
			},
			returnLogin : function() {
				$('#modal1').removeClass("small");
				$('#modal1').addClass("medium");
				$('#modal1').empty();
				var model = this.model;
				this.loginView = new LoginPopupView({
					model : model
				});
				$('#modal1').append(this.loginView.render().el);
				//$('#modal1').foundation('reveal', 'open');
			}
		});