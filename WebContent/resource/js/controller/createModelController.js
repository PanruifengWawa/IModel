myApp.controller("createModelController",function($scope,modelService,context) {
	$scope.model = {};
	$scope.createModel = function() {
		var dataFile = document.getElementById("dataFile").files[0];
		var name = $scope.model.name;
		var algorithm = $scope.model.algorithm;
		var input = document.getElementById("input").innerHTML;
		var target = document.getElementById("target").innerHTML;
		if(dataFile == null || 
				name == null || name == undefined || name == "" ||
				algorithm == null || algorithm == undefined || algorithm == "" ||
				input == null || input == undefined || input == "" ||
				target == null || target == undefined || target == "" ) {
			alert("请填写所有必要的输入");
			return;
		}
		var formData = new FormData();
		formData.append('modelFile', dataFile);
		formData.append('modelName', name);
		formData.append('algorithm', algorithm);
		formData.append('modelInput', input);
		formData.append('modelTarget', target);
		
		
		var promise = modelService.createModel(formData);
		promise.success(function(data,status,config,headers) {
			if(data.errorCode === 0) {
				window.location.href = context + "/model_list";
			} else {
				alert('用户未登录');
			}
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
		
	}
	
});
$("#dataFile").change(function(){  
    var fileSelector = document.getElementById("dataFile").files;
    var file = fileSelector[0];  
                    
    var reader = new FileReader();  
    reader.onload = function() {  
    	var firstLine = this.result.split("\n",1)[0].split(",");
    	var target = firstLine[firstLine.length-1];
    	var input = this.result.split("\n",1)[0].replace("," + target,"");
    	document.getElementById("input").innerHTML = input;
    	document.getElementById("target").innerHTML = target;
//        console.log(value.split("\n",1));
        document.getElementById("content").innerHTML = this.result.replace(firstLine + "\n","");
    };  
    reader.readAsText(file); 
  
}); 