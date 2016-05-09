var ChangePasswordModel = Backbone.Model.extend({
	defaults : {
		"newPassword" : '',
		"retypeNewPassword" : '',
		"currentPassword" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.newPassword) {
			errors.push({
				field : 'newPassword',
				message : 'Please enter password'
			});
		}
		if (!attrs.retypeNewPassword) {
			errors.push({
				field : 'retypeNewPassword',
				message : 'Please enter retype password'
			});
		}
		if (!attrs.currentPassword) {
			errors.push({
				field : 'currentPassword',
				message : 'Please enter old password'
			});
		}

		return errors.length > 0 ? errors : false;

	}
});