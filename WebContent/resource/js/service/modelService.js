myApp.service("modelService",function($http,APIRouters) {
	this.getModelList = function(token) {
	     return  $http({
			　　method: 'GET',
			　　url: APIRouters.modelListAPI + "?token=" + token,
			   headers: {
			     'Content-Type': undefined
			   }
	     });
	};
	
	this.createModel = function(formData) {
		var promise = $http({
			method: 'POST',
		　　 url:  APIRouters.creteModeltAPI + "?token=" + sessionStorage.token,
		    headers: {
		       'Content-Type': undefined
		    },
		    data: formData
		});
		return promise;
	};

	this.preparePredict = function(appKey) {
		var promise = $http({
			method: 'GET',
		　　 url:  APIRouters.preparePredictAPI + "?token=" + sessionStorage.token + "&appKey=" + appKey,
		    headers: {
		       'Content-Type': undefined
		    }
		});
		return promise;
	};
	
	this.predict = function(appKey,queryString) {
		var promise = $http({
			method: 'GET',
		　　 url:  APIRouters.predictAPI + "?appKey=" + appKey + queryString,
		    headers: {
		       'Content-Type': undefined
		    }
		});
		return promise;
	};
	
	this.used = function(appKey,token) {
		var promise = $http({
			method: 'GET',
		　　 url:  APIRouters.modelUsedAPI + "?appKey=" + appKey + "&token=" + token,
		    headers: {
		       'Content-Type': undefined
		    }
		});
		return promise;
	};
});