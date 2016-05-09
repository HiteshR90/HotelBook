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