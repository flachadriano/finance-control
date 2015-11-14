export default Ember.ObjectController.extend({
  needs: 'categories',

  actions: {
    destroy: function() {
      this.store.findRecord('category', 1).then(function(category) {
        category.destroyRecord();
      });
    },
  },
});
