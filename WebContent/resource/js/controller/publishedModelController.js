myApp.controller("publishedModelController",function($scope,modelService,context) {
	Date.prototype.Format = function (fmt) { //author: meizz
		  var o = {
		    "M+": this.getMonth() + 1, //月份
		    "d+": this.getDate(), //日
		    "h+": this.getHours(), //小时
		    "m+": this.getMinutes(), //分
		    "s+": this.getSeconds(), //秒
		    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
		    "S": this.getMilliseconds() //毫秒
		  };
		  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		  for (var k in o)
		  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		  return fmt;
	}
	
	$scope.getModelList = function() {
	
		var promise = modelService.getModelList(sessionStorage.token);
		promise.success(function(data,status,config,headers) {
			
			if(data.errorCode === 0) {
				$scope.modelList = data.data;
				
				 for (var i = 0; i < $scope.modelList.length; i++) {
					 var item = $scope.modelList[i];
					 if(item.published != 1) {
						continue;
					 }
					 var tbody = '<tr class="text-c">' +
							         '<td>' + item.modelName + '</td>' +
							         '<td>' + item.algorithm + '</td>' +
							         '<td>' + new Date(item.createdTime).Format("yyyy-MM-dd hh:mm:ss") + '</td>' +
							         '<td>' + (item.state == 1? '训练完成':'正在训练') + '</td>' +
							         '<td>' + (item.published == 1? '已发布':'未发布') + '</td>' +
							         '<td>' + (item.size == null ? '未知' : item.size) + '</td>' +
							         '<td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="getModel(' + item.id + ')"  href="javascript:;" title="模型详情"><i class="Hui-iconfont">&#xe627;</i></a>' +
							         '</td>' +								
							      '</tr>';
					 $("#my-tbody").append(tbody);
				 }
				 
				
				 $scope.dataTable();
			} else {
				alert('用户未登录');
			}
			
			
	    });
		
		promise.error(function() {
			alert("服务器错误");
	    });
	}
	
	$scope.dataTable = function() {
		$('.table-sort').dataTable({
			"aaSorting": [[ 2, "desc" ]],//默认第几个排序
			//"bStateSave": true,//状态保存
			"aoColumnDefs": [
			  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			  {"orderable":false,"aTargets":[5]}// 制定列不参与排序
			],
			"bLengthChange": false,
			"iDisplayLength": 10
		});

	}
	
	
	$scope.getModelList();
	
	
});

function getModel(id) {
	layer.open({
		  type: 2,
		  area: ['700px', '530px'],
		  fixed: false, //不固定
		  maxmin: true,
		  content: 'model_details?modelId='+id
	});
}


