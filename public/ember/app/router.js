import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
	this.route('categories', function() {
		this.route('new');
	});
	this.route('transactions');
});

export default Router;
