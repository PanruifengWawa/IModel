myApp.controller("userController",function($scope,userService,context) {
	
	
	$scope.login = function() {
		if($scope.userName === undefined || $scope.userName === "") {
			alert("用户名不能为空");
			return;
		}
		if($scope.password === undefined || $scope.password === "") {
			alert("密码不能为空");
			return;
		}
		var promise = userService.login($scope.userName,$scope.password);
		promise.success(function(data,status,config,headers) {
			if(data.errorCode === 0) {
				sessionStorage.token = data.data;
				sessionStorage.userName = $scope.userName;
				window.location.href = context + "/index";
			} else {
				alert('账号或密码不正确');
			}
			console.log(data);
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
	}
	
	
	$scope.logout = function() {
		//clear buffer data
		sessionStorage.clear();
		top.location =  context;
	}
});