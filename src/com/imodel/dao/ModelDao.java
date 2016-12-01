package com.imodel.dao;

import java.util.List;

import com.imodel.models.Model;

public interface ModelDao {
	List<Model> getModelList(Long userId);
	Model getById(Long id);
	boolean saveModel(Model model);
	boolean updateModel(Model model);
	boolean deleteModel(Long id);
	Model getByAppKey(String appKey);
}
