var myApp = angular.module("myApp", []);

myApp.value("context", "/IModel");

if(sessionStorage.token == undefined ||  sessionStorage.token == '') {
	if(location.pathname != '/IModel/' && location.pathname != '/IModel/login') {
		window.location.href = "/IModel";
	}
}