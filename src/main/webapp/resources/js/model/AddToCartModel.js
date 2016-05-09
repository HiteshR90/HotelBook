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