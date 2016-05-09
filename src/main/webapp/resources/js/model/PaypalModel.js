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