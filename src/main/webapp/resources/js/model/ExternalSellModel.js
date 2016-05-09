var ExternalSellModel = Backbone.Model.extend({
	defaults : {
		'hotelId' : '',
		'hotelName':'',
		'hotelAddress':'',
		'roomType' : '',
		'checkInDate' : '',
		'checkOutDate' : '',
		'price' : '',
		'confirmationCode' : '',
		'confirmationCodeSecond' : '',
		'adult':'',
		'childeren':'',
		'firstName':'',
		'lastName':'',
		'email':'',
		'bookFrom':'',
		'paymentType':'',
		'paymentStatus':''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.hotelId) {
			errors.push({
				field : 'hotelId',
				message : 'Please select hotel.'
			});
		}

		if (!attrs.roomType) {
			errors.push({
				field : 'roomType',
				message : 'Please select room type.'
			});
		}
		
		if (!attrs.checkInDate) {
			errors.push({
				field : 'checkInDate',
				message : 'Please select check in date.'
			});
		}
		
		if (!attrs.checkOutDate) {
			errors.push({
				field : 'checkOutDate',
				message : 'Please select check out date.'
			});
		}
		
		if (!attrs.price) {
			errors.push({
				field : 'price',
				message : 'Please enter price.'
			});
		}
		else if(attrs.price.length < 2)
		{
			errors.push({
				field : 'price',
				message : 'Price Must be Grater than 10'
			});
		}
		
		if (!attrs.adult) {
			errors.push({
				field : 'adult',
				message : 'Please Enter Number of adult.'
			});
		}
		
		if (!attrs.childeren) {
			errors.push({
				field : 'childeren',
				message : 'Please Enter Number of childeren.'
			});
		}
		
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
		
		if (!attrs.paymentType) {
			errors.push({
				field : 'paymentType',
				message : 'Please select payment type.'
			});
		}
		if (!attrs.paymentStatus) {
			errors.push({
				field : 'paymentStatus',
				message : 'Please select payment status.'
			});
		}
		
		return errors.length > 0 ? errors : false;

	}
});