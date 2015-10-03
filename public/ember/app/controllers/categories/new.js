export default Ember.ObjectController.extend({
	needs: 'categories',

	actions: {
		create: function(){
			var controller = this;
			var category = this.store.createRecord('category', {
				name: this.get('name')
			});

			category.save().then(function(data) {
				controller.transationToRoute('categories');
			});
		}
	}
});