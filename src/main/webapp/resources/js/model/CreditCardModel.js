var CreditCardModel = Backbone.Model.extend({
	defaults : {
		"cardNumber" : '',
		"cvv" : '',
		"expMonth" : '',
		"expYear" : '',
		"nameOnCard" : '',
		"address" : '',
		"city" : '',
		"state" : '',
		"zipCode" : ''
	},

	validate : function(attrs) {
		var errors = [];
		if (!attrs.cardNumber) {
			errors.push({
				field : 'cardNumber',
				message : 'Please enter card number.'
			});
		}

		if (!attrs.cvv) {
			errors.push({
				field : 'cvv',
				message : 'Please enter cvv number.'
			});
		}

		if (!attrs.expMonth) {
			errors.push({
				field : 'expMonth',
				message : 'Please enter exp month number.'
			});
		}

		if (!attrs.expYear) {
			errors.push({
				field : 'expYear',
				message : 'Please enter exp year number.'
			});
		}

		return errors.length > 0 ? errors : false;

	}
});