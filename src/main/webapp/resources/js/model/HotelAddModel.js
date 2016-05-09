var HotelAddModel = Backbone.Model.extend({
	defaults : {
		"hotelName" : '',
		"address" : '',
		"city" : '',
		"state" : '',
		"pincode" :''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.hotelName) {
			errors.push({
				field : 'hotelName',
				message : 'Please Enter Hotel Name'
			});
		}

		if (!attrs.address) {
			errors.push({
				field : 'address',
				message : 'Please Enter Hotel Address'
			});
		}
		
		if (!attrs.city) {
			errors.push({
				field : 'city',
				message : 'Please Enter City Name'
			});
		}
		
		if (!attrs.state) {
			errors.push({
				field : 'state',
				message : 'Please Enter State Name'
			});
		}
		
		if (!attrs.pincode) {
			errors.push({
				field : 'pincode',
				message : 'Please Enter Zip Code'
			});
		}

		return errors.length > 0 ? errors : false;

	}
});