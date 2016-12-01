package com.imodel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.imodel.models.Model;
import com.imodel.utils.DataWrapper;

public interface ModelService {
	DataWrapper<Void> createModel(Model model,MultipartFile modelFile,String token,HttpServletRequest request);
	DataWrapper<List<Model>> getModelList(String token);
	DataWrapper<Void> updateModelsFrom(Long id,Integer size);
	DataWrapper<Model> getModelDetails(Long modelId,String token);
	DataWrapper<Void> deleteModel(Long modelId,String token);
	DataWrapper<Void> publishModel(Long modelId,String token);
	DataWrapper<Void> updateModel(Long modelId,String modelName,String token);
	DataWrapper<Model> preparePredict(String appKey,String token);
	
	DataWrapper<String> predict(String appKey,HttpServletRequest request);
	
	DataWrapper<String> predictWithAPI(String appKey, HttpServletRequest request);
}
