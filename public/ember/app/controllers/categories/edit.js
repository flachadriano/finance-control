export default Ember.ObjectController.extend({
  needs: 'categories',

  actions: {
    update: function() {
      var controller = this;
      var category = this.get('content');

      category.save().then(function() {
        controller.transationToRoute('categories');
      });
    },
  },
});
