myApp.controller("webServiceController",function($scope,modelService,context) {
	$scope.inputs = [];
	$scope.model = {};
	$scope.getParameters = function() {
		if($scope.appKey == null || $scope.appKey == undefined || $scope.appKey == "") {
			alert("请输入App Key");
			return;
		}
	
		var promise = modelService.preparePredict($scope.appKey);
		promise.success(function(data,status,config,headers) {
			
			if(data.errorCode === 0 && data.data != null) {
				$scope.url = "http://127.0.0.1:8080/IModel/api/model/predict?appKey=" + $scope.appKey;
				$scope.model = data.data;
				$scope.inputs = data.data.modelInput.split(",");
				for(var i = 0; i < $scope.inputs.length;i++) {
					$scope.url += "&" + $scope.inputs[i] + "=value" + i;
				}
			} else {
				alert('App Key错误');
			}
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
		$scope.getModelUsed();
	}
	
	$scope.getModelUsed = function () {

		var promise = modelService.used($scope.appKey,sessionStorage.token);
		promise.success(function(data,status,config,headers) {
			if(data.errorCode === 0 && data.data != null) {
				var records = data.data;
				var graphData = [];
				for (var i = 0 ;i < records.length; i++) {
					var mdate = new Date(records[i].date);
					console.log(mdate);
					graphData.push([Date.UTC(mdate.getFullYear(), mdate.getMonth(), mdate.getDate()), records[i].count]);
				}
				$scope.setGraph(graphData);
			} else {
				alert('App Key错误');
			}
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
	}
	$scope.setGraph = function(data) {
		$(function () {
		    $('#container').highcharts({
		        chart: {
		            type: 'spline'
		        },
		        title: {
		            text: '模型调用情况'
		        },
		        subtitle: {
		            text: 'App Key:' + $scope.appKey
		        },
		        xAxis: {
		        	type: 'datetime',  
                    labels: {  
                        step: 4,   
                        formatter: function () {  
                            return Highcharts.dateFormat('%Y-%m-%d', this.value);  
                        }  
                    }  
		        },
		        yAxis: {
		            title: {
		                text: '次数'
		            },
		            min: 0
		        },
		        tooltip: {
		            headerFormat: '<b>模型调用情况</b><br>',
		            pointFormat: '{point.x:%Y/%m/%e}: {point.y} 次'
		        },
		        plotOptions: {
		            spline: {
		                marker: {
		                    enabled: true
		                }
		            }
		        },
		        series: [{
		            name: '模型调用情况曲线',
		            // Define the data points. All series have a dummy year
		            // of 1970/71 in order to be compared on the same x axis. Note
		            // that in JavaScript, months start at 0 for January, 1 for February etc.
		            data: data
		        }]
		    });
		});

	}

	
	$scope.setGraph(1);
	
	
	
});
