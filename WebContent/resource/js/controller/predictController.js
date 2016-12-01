myApp.controller("predictController",function($scope,modelService,context) {
	$scope.inputs = [];
	$scope.result = "";
	$scope.model = {};
	$scope.preparePredict = function() {
		if($scope.appKey == null || $scope.appKey == undefined || $scope.appKey == "") {
			alert("请输入App Key");
			return;
		}
		var promise = modelService.preparePredict($scope.appKey);
		promise.success(function(data,status,config,headers) {
			
			if(data.errorCode === 0 && data.data != null) {
				$scope.model = data.data;
				$scope.target = data.data.modelTarget;
				$scope.inputs = data.data.modelInput.split(",");
				var target = data.data.modelTarget;
			} else {
				alert('App Key错误');
			}
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
		
		
	}
	
	$scope.predict = function() {
		var queryString = "";
		for(var i = 0; i< $scope.inputs.length;i++) {
			var input = $scope.inputs[i];
			var value = document.getElementById(input).value;
			if(value == null || value == undefined || value == "") {
				alert("请填写所有需要预测的值");
				return;
			}
			queryString += "&" + input + "=" + value;
		}
		
		var promise = modelService.predict($scope.model.appKey,queryString);
		promise.success(function(data,status,config,headers) {
			
			if(data.errorCode === 0 && data.data != null) {
				
				$scope.result = data.data;
			} else {
				alert('请输入有效的App Key,模型状态不正确');
			}
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
	}
	

	
	
	
});
