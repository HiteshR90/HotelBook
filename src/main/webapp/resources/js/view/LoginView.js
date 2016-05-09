var LoginView = Backbone.View
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
				
			},
			login : function() {
				var $form = $(this.el).find("#loginForm");
				// serialize the data in the form ready
				// for output
				var serializedData = $form.serialize();
				$
						.ajax({
							url : this.model.get('baseUrl')
									+ "/user/login.json",
							type : "post",
							data : serializedData,
							dataType : 'json',
							success : function(response) {
								// remove error class
								// from control-group
								$(".help-inline").removeClass('error');
								// empty help-inline
								$(".help-inline").empty();
								// remove eror message
								$("#errorMessageCommon").removeClass('error');
								// empty error message
								$("#errorMessageCommon").empty();

								if (response.status == 'FAIL') {
									/*
									 * $( "span.email_val") .empty(); $(
									 * "span.userName_val") .empty();
									 */

									for (var i = 0; i < response.errorMessageList.length; i++) {
										var item = response.errorMessageList[i];
										if (item.fieldName == "errorMessage") {
											$("#errorMessageCommon").addClass(
													'error');
											$("#errorMessageCommon").html(
													item.message);
										} else {
											var $controlGroup = $('#'
													+ item.fieldName
													+ 'ControlGroup');
											$controlGroup.find('.help-inline')
													.addClass('error');

											$controlGroup.find('.help-inline')
													.html(item.message);
										}
									}
								} else {
									$("#errorMessageCommon").removeClass(
											'error');
									$("#errorMessageCommon").empty();
									location.href = response.redirect;
								}
							}
						});
				return false;
			},
			fbLogin : function() {
				// remove error class
				// from control-group
				$(".help-inline").removeClass('error');
				// empty help-inline
				$(".help-inline").empty();
				// remove eror message
				$("#errorMessageCommon").removeClass('error');
				// empty error message
				$("#errorMessageCommon").empty();
				FB
						.login(
								function(response) {
									if (response.authResponse) {
										var access_token = FB.getAuthResponse()['accessToken'];
										$
												.ajax({
													url : this.model
															.get('baseUrl')
															+ "/user/facebookLogin/"
															+ access_token,
													type : "POST",
													success : function(response) {
														if (response.status == 'FAIL') {
															for (var i = 0; i < response.errorMessageList.length; i++) {
																var item = response.errorMessageList[i];
																if (item.fieldName == "errorMessage") {
																	$(
																			"#errorMessageCommon")
																			.addClass(
																					'error');
																	$(
																			"#errorMessageCommon")
																			.html(
																					item.message);
																} else {
																	var $controlGroup = $('#'
																			+ item.fieldName
																			+ 'ControlGroup');

																	$controlGroup
																			.find(
																					'.help-inline')
																			.addClass(
																					'error');

																	$controlGroup
																			.find(
																					'.help-inline')
																			.html(
																					item.message);
																}
															}
														} else {
															$(
																	"#errorMessageCommon")
																	.removeClass(
																			'error');
															$(
																	"#errorMessageCommon")
																	.empty();
															location.href = response.redirect;
														}
													}
												});
									} else {
										return false;
									}
								}, {
									scope : 'email'
								});
				return false;
			}
		});