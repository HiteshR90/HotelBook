var RegisterView = Backbone.View
		.extend({
			template : Handlebars
					.compile(),
			initialize : function() {
				this.listenTo(this.model, "change", this.render);
			},
			render : function() {
				$(this.el).empty();
				this.$el.html(this.template(this.model.attributes));
				// this.resetFields();
				return this;
			},
			events : {
				'click .register-link' : 'register'
			},
			register : function() {
				var $form = $("#registrationForm");
				var serializedData = $form.serialize();
				console.log(serializedData);
				$
						.ajax({
							url : "user/signup.json",
							type : "post",
							data : serializedData,
							dataType : 'json',
							success : function(response) {

								$(".help-inline").removeClass('error');
								// empty help-inline
								$(".help-inline").empty();
								// remove eror message
								$("#errorMessageCommonReg")
										.removeClass('error');
								// empty error message
								$("#errorMessageCommonReg").empty();

								if (response.status == 'FAIL') {
									for (var i = 0; i < response.errorMessageList.length; i++) {
										var item = response.errorMessageList[i];
										if (item.fieldName == "errorMessageCommon") {
											/*
											 * var loginBox = $(
											 * 'a.login-window') .attr( 'href'); $(
											 * loginBox) .fadeOut(); $(
											 * "table#tableRegister
											 * .help-inline") .removeClass(
											 * 'error'); $( "table#tableRegister
											 * .help-inline") .empty(); $(
											 * "#errorMessageCommon") .addClass(
											 * 'error'); $(
											 * "#errorMessageCommon") .html(
											 * item.message);
											 */
										} else {

											var $controlGroup = $('#'
													+ item.fieldName
													+ 'RegisterControlGroup');
											$controlGroup.find('.help-inline')
													.addClass('error');
											$controlGroup.find('.help-inline')
													.html(item.message);
											$('span.email_val').empty();
										}
									}

								} else {
									$("#errorMessageCommonReg").removeClass(
											'error');
									$("#errorMessageCommonReg").empty();
									$('#loginForm')[0].reset();
									$('#registrationForm')[0].reset();

									$(".help-inline").removeClass('error');
									// empty help-inline
									$(".help-inline").empty();
									// remove eror message
									$("#errorMessageCommonReg").removeClass(
											'error');
									// empty error message
									$("#errorMessageCommonReg").empty();

									$("#errorMessageCommon").removeClass(
											'error');
									// empty error message
									$("#errorMessageCommon").empty();
									$("#errorMessageCommonReg").addClass(
											'error');
									$("#errorMessageCommonReg").html(
											response.successMessage);
								}

							}

						});
			}
		});