angular.module('vodafoneApp').factory('CustomersResource', function($resource) {
	return $resource('/Vodafone/vodafone/customers/:id', { id: '@_id' }, {
		update: {
		      method: 'PUT' // this method issues a PUT request
		    }
	});
});