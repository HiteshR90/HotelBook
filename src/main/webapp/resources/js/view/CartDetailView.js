var CartDetailView = Backbone.View
		.extend({
			initialize : function() {
				this.listenTo(this.model, "change", this.render);
			},
			render : function() {
				return this;
			},
			events : {
				'click .payment-link' : 'payment'
			},
			payment : function() {
				location.href = this.model.get('baseUrl') + '/payment';
			},
			showErrors : function(errors) {
				_.each(errors, function(error) {
					var controlGroup = this.$('.' + error.name);
					controlGroup.addClass('error');
					controlGroup.find('.help-inline').text(error.message);
				}, this);
			},
			hideErrors : function() {
				this.$('.control-group').removeClass('error');
				this.$('.help-inline').text('');
			}
		});