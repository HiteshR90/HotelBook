var ForgotPasswordModel=Backbone.Model.extend({
	defaults : {
		"userNameOrEmail" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.userNameOrEmail) {
			errors.push({
				name : 'userNameOrEmail',
				message : 'Please enter user name or email address.'
			});
		}
		return errors.length > 0 ? errors : false;
	}
});