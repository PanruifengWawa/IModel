var routers = {
		"loginAPI" : "/IModel/v1/api/user/login",
		"modelListAPI" : "/IModel/v1/api/model/getModelList",
		"creteModeltAPI" : "/IModel/v1/api/model/create",
		"ModelAPI" : "/IModel/v1/api/model/",
		"preparePredictAPI" : "/IModel/v1/api/model/preparePredict",
		"predictAPI" : "/IModel/v1/api/model/predict",
		"modelUsedAPI" : "/IModel/v1/api/model/used"
}

myApp.value("APIRouters", routers);

