var AddToCartModel = Backbone.Model.extend({
	defaults : {
		"roomId" : '',
		"checkInDate" : '',
		"checkOutDate" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.roomId) {
			errors.push({
				name : 'roomId',
				message : 'RoomId Not Found'
			});
		}
		if (!attrs.checkInDate) {
			errors.push({
				name : 'checkInDate',
				message : 'Please select checkInDate.'
			});
		}
		if (!attrs.checkOutDate) {
			errors.push({
				name : 'checkOutDate',
				message : 'Please select checkOutDate.'
			});
		}

		return errors.length > 0 ? errors : false;
		
	}
});

var BankModel = Backbone.Model.extend({
	defaults : {
		"bankAccountNumber" : '',
		"bankRoutingNumber" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.bankAccountNumber) {
			errors.push({
				field : 'bankAccountNumber',
				message : 'Please enter bank account number.'
			});
		}

		if (!attrs.bankRoutingNumber) {
			errors.push({
				field : 'bankRoutingNumber',
				message : 'Please enter bank routing number.'
			});
		}

		return errors.length > 0 ? errors : false;

	}
});

var CartDetailModel = Backbone.Model.extend();

var FBLoginModel = Backbone.Model.extend({
	defaults : {
		"token" : ''
	}
});

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

var HotelDetailModel=Backbone.Model.extend({
	defaults:{
		baseUrl:""
	}
});

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

var LoginPopupModel=Backbone.Model.extend({
	defaults:{
		baseUrl:""
	}
});

var PaypalModel = Backbone.Model.extend({
	defaults : {
		"paypalId" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.paypalId) {
			errors.push({
				field : 'paypalId',
				message : 'Please enter paypalid.'
			});
		}

		return errors.length > 0 ? errors : false;

	}
});

var ProfileModel = Backbone.Model.extend({
	defaults : {
		"email" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.email) {
			errors.push({
				field : 'email',
				message : 'Please enter email address.'
			});
		}

		return errors.length > 0 ? errors : false;

	}

});

var ProfilePreferenceModel=Backbone.Model.extend({
	defaults:{
		baseUrl:""
	}
});

var RegisterModel = Backbone.Model.extend({
	defaults : {
		"userName" : '',
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
				message : 'Please aggree terms.'
			});
		}

		return errors.length > 0 ? errors : false;

	}

});

