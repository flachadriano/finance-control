export default Ember.Route.extend({
  model() {
    return this.store.find('category');
  },
});
