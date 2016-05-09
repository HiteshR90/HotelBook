var LoginModel=Backbone.Model.extend({
	defaults : {
		"userName" : '',
		"password" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.userName) {
			errors.push({
				name : 'lUserName',
				message : 'Please enter user name.'
			});
		}
		if (!attrs.password) {
			errors.push({
				name : 'lPassword',
				message : 'Please enter password.'
			});
		}
		
		return errors.length > 0 ? errors : false;
		
	}
});