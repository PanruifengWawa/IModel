myApp.service("userService",function($http,APIRouters) {
	this.login = function(userName,password) {
	     return  $http({
			　　method: 'GET',
			　　url: APIRouters.loginAPI + "?userName=" + userName + "&password=" + password,
			   headers: {
			     'Content-Type': undefined
			   }
	     });
	};

});