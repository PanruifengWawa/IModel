package com.imodel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	@RequestMapping(value="/login")
    public String loginPage(){
        return "/login";
	}
	
	@RequestMapping(value="/index")
    public String mainPage(){
        return "/index";
	}
	
	
	@RequestMapping(value="/model_list")
    public String modelListPage(){
        return "/model-list";
	}
	
	@RequestMapping(value="/create_model")
    public String createModelPage(){
        return "/create-model";
	}
	@RequestMapping(value="/model_details")
    public String modelDetailsPage(){
        return "/model-details";
	}
	
	@RequestMapping(value="/published_model_list")
    public String publishedModelListPage(){
        return "/published-model-list";
	}
	@RequestMapping(value="/onLine_predict")
    public String onLinePredictPage(){
        return "/onLine-predict";
	}
	@RequestMapping(value="/web_service")
    public String webServicePage(){
        return "/web-service";
	}
//	@RequestMapping(value="/admin_pwd_change")
//    public String adminPWDChangePage(){
//        return "/admin-pwd-change";
//	}
//
//	@RequestMapping(value="/data_statistics")
//    public String dataStatistics(){
//        return "/data-statistics";
//	}
//
//
//	@RequestMapping(value="/data_update")
//    public String dataUpdate(){
//        return "/data-update";
//	}
}
