var ReservationDetailModel = Backbone.Model.extend({
	defaults : {
		'roomType' : '',
		'checkInDate' : '',
		'checkOutDate' : '',
		'price' : '',
		'adult':'',
		'childeren':'',
	},

	validate : function(attrs) {
		var errors = [];

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
		
		return errors.length > 0 ? errors : false;

	}
});