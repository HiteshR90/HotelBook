var BookingModel = Backbone.Model.extend({
	defaults : {
		'confirmationCode' : '',
		'firstName':'',
		'lastName':'',
		'email':'',
		'bookFrom':''
	},

	validate : function(attrs) {
		var errors = [];
		var emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
		
		if (!attrs.firstName) {
			errors.push({
				field : 'firstName',
				message : 'Please enter firstName.'
			});
		}
		
		if (!attrs.lastName) {
			errors.push({
				field : 'lastName',
				message : 'Please enter lastName.'
			});
		}
		
		if (!attrs.email) {
			errors.push({
				field : 'email',
				message : 'Please enter email.'
			});
		}
		else if (!emailPattern.test(attrs.email)) {
			errors.push({
				field : 'email',
				message : '* Please enter valid email.'
			});
		}
		
		if (!attrs.confirmationCode) {
			errors.push({
				field : 'confirmationCode',
				message : 'Please enter confirmation code.'
			});
		}
		
		if (!attrs.bookFrom) {
			errors.push({
				field : 'bookFrom',
				message : 'Please enter bookFrom.'
			});
		}
		
		return errors.length > 0 ? errors : false;

	}
});