angular.module('vodafoneApp').controller('CustomerController',
		function($scope, CustomersResource) {
			CustomersResource.query(function(data) {
				$scope.customers = data;
			});
			$scope.edit = function(id) {
				CustomersResource.get({
					id : id
				}, function(data) {
					$scope.customer = data;
					$scope.editing = true;
					$scope.creating = false;
				});
			};
			$scope.remove = function(id) {
				CustomersResource.remove({
					id : id
				}, function() {
					$scope.cleanCustomer();
					$scope.updateCustomers();
				});
			};
			$scope.update = function(customer) {
				CustomersResource.update({
					id : customer.id
				}, customer, function() {
					$scope.cleanCustomer();
					$scope.updateCustomers();
				});
			};
			$scope.create = function() {
				$scope.customer = {};
				$scope.editing = false;
				$scope.creating = true;
			};
			$scope.save = function(customer) {
				CustomersResource.save(customer, function() {
					$scope.cleanCustomer();
					$scope.updateCustomers();
				});
			};
			$scope.updateCustomers = function() {
				CustomersResource.query(function(data) {
					$scope.customers = data;
				});
			};
			$scope.cleanCustomer = function(){
				$scope.customer = {};
				$scope.editing = false;
				$scope.creating = false;
			}
			$scope.name = 'Vodafone RESTful';
			$scope.editing  = false;
			$scope.creating = false;
			
			$scope.showForm = function(){
				return ($scope.editing || $scope.creating)
			}
		});