var RegisterModel = Backbone.Model.extend({
	defaults : {
		"userName" : '',
		"fName" : '',
		"lName" : '',
		"email" : '',
		"password" : '',
		"retypePassword" : '',
		"isAgree" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.userName) {
			errors.push({
				name : 'ruserName',
				message : 'Please enter user name.'
			});
		}
		if (!attrs.fName) {
			errors.push({
				name : 'rfname',
				message : 'Please enter first name.'
			});
		}
		if (!attrs.lName) {
			errors.push({
				name : 'rlname',
				message : 'Please enter last name.'
			});
		}
		if (!attrs.email) {
			errors.push({
				name : 'remail',
				message : 'Please enter email.'
			});
		}
		if (!attrs.password) {
			errors.push({
				name : 'rpassword',
				message : 'Please enter password.'
			});
		}
		if (!attrs.retypePassword) {
			errors.push({
				name : 'rretypePassword',
				message : 'Please match password here.'
			});
		}
		if (attrs.isAgree===false) {
			errors.push({
				name : 'risAgree',
				message : 'Please agree terms.'
			});
		}

		return errors.length > 0 ? errors : false;

	}

});