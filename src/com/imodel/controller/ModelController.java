package com.imodel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.imodel.models.Model;
import com.imodel.models.Records;
import com.imodel.service.ModelService;
import com.imodel.service.RecordsService;
import com.imodel.utils.DataWrapper;


@Controller
@RequestMapping(value="v1/api/model")
public class ModelController {
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	RecordsService recordsService;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> createModel(
    		@ModelAttribute Model model,
    		@RequestParam(value="modelFile",required=true) MultipartFile modelFile,
    		@RequestParam(value="token",required=true) String token,
    		HttpServletRequest request
    		) {
        return modelService.createModel(model, modelFile, token, request);
    }
	
	@RequestMapping(value="/getModelList", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<List<Model>> getModelList(
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.getModelList(token);
    }
	
	@RequestMapping(value="/updateModelsFrom", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Void> updateModelsFrom(
    		@RequestParam(value="id",required=true) Long id,
    		@RequestParam(value="size",required=true) Integer size
    		) {
        return modelService.updateModelsFrom(id, size);
    }

	@RequestMapping(value="/{modelId}", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Model> getModelDetails(
    		@PathVariable(value="modelId") Long modelId,
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.getModelDetails(modelId, token);
    }
	
	@RequestMapping(value="/{modelId}", method = RequestMethod.DELETE)
    @ResponseBody
    public DataWrapper<Void> deleteModelDetails(
    		@PathVariable(value="modelId") Long modelId,
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.deleteModel(modelId, token);
    }
	
	@RequestMapping(value="/{modelId}", method = RequestMethod.PUT)
    @ResponseBody
    public DataWrapper<Void> publishModel(
    		@PathVariable(value="modelId") Long modelId,
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.publishModel(modelId, token);
    }
	
	@RequestMapping(value="/{modelId}/update", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Void> updateModel(
    		@PathVariable(value="modelId") Long modelId,
    		@RequestParam(value="modelName",required=true) String modelName,
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.updateModel(modelId, modelName, token);
    }
	
	@RequestMapping(value="/preparePredict", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Model> preparePredict(
    		@RequestParam(value="appKey",required=true) String appKey,
    		@RequestParam(value="token",required=true) String token
    		) {
        return modelService.preparePredict(appKey, token);
    }
	
	@RequestMapping(value="/predict", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<String> predict(
    		@RequestParam(value="appKey",required=true) String appKey,
    		HttpServletRequest request
    		) {
        return modelService.predict(appKey, request);
    }
	
	@RequestMapping(value="/used", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<List<Records>> used(
    		@RequestParam(value="appKey",required=true) String appKey,
    		@RequestParam(value="token",required=true) String token
    		) {
        return recordsService.getRecords(appKey, token);
    }
}
