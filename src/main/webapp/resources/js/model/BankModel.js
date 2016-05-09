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