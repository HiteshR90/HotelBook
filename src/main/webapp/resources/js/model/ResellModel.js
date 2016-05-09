var ReSellModel = Backbone.Model.extend({
	defaults : {
		"oneNightCost" : ''
	},
	validate : function(attrs) {
		var errors = [];
		if (!attrs.oneNightCost) {
			errors.push({
				field : 'oneNightCost',
				message : 'Enter cost per night.'
			});
		}
		return errors.length > 0 ? errors : false;
	}

});