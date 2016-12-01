package com.imodel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imodel.service.ModelService;
import com.imodel.utils.DataWrapper;

@Controller
@RequestMapping(value="api/predict")
public class PredictController {
	@Autowired
	ModelService modelService;
	
	@RequestMapping(value="/predict", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<String> predict(
    		@RequestParam(value="appKey",required=true) String appKey,
    		HttpServletRequest request
    		) {
        return modelService.predictWithAPI(appKey, request);
    }
}
