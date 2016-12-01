package com.imodel.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imodel.dao.ModelDao;
import com.imodel.dao.ModelRecordsDao;
import com.imodel.dao.UserDao;
import com.imodel.enums.ErrorCodeEnum;
import com.imodel.models.Model;
import com.imodel.models.ModelRecords;
import com.imodel.models.User;
import com.imodel.server.Predict;
import com.imodel.server.Train;
import com.imodel.service.ModelService;
import com.imodel.utils.DataWrapper;
import com.imodel.utils.FileUtils;
import com.imodel.utils.UUIDGenerator;

@Service("modelService")
public class ModelServiceImpl implements ModelService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ModelDao modelDao;
	
	@Autowired
	ModelRecordsDao modelRecordsDao;

	@Override
	public DataWrapper<Void> createModel(Model model, MultipartFile modelFile, String token,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		if (user != null) {
			String filePath = request.getSession().getServletContext().getRealPath("/")  + user.getId() + "/";
			String newFileName = System.currentTimeMillis() + modelFile.getOriginalFilename();
			model.setId(null);
			model.setFileDir(filePath);
			model.setFileName(newFileName);
			model.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			model.setPublished(0);
			model.setUserId(user.getId());
			model.setState(0);
			model.setSize(null);
			model.setAppKey(UUIDGenerator.getCode("SK"));
			if (FileUtils.saveFile(filePath, newFileName, modelFile) && modelDao.saveModel(model)) {
				Train train = new Train(model);
				train.start();
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Model>> getModelList(String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<List<Model>> dataWrapper = new  DataWrapper<List<Model>>();
		if (user != null) {
			List<Model> models = modelDao.getModelList(user.getId());
			dataWrapper.setData(models);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateModelsFrom(Long id, Integer size) {
		// TODO Auto-generated method stub
		Model model = modelDao.getById(id);
		if (model != null) {
			model.setSize(size);
			model.setState(1);
			modelDao.updateModel(model);
		}
		return null;
	}

	@Override
	public DataWrapper<Model> getModelDetails(Long modelId,String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Model> dataWrapper = new DataWrapper<Model>();
		if (user != null) {
			Model model = modelDao.getById(modelId);
			dataWrapper.setData(model);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteModel(Long modelId, String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (user != null) {
			if (!modelDao.deleteModel(modelId)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Inner_Error.getCode());
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> publishModel(Long modelId, String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (user != null) {
			Model model = modelDao.getById(modelId);
			if (model != null && model.getState() == 1) {
				model.setPublished(1);
				modelDao.updateModel(model);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Not_Finished.getCode());
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateModel(Long modelId, String modelName, String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (user != null) {
			Model model = modelDao.getById(modelId);
			if (model != null) {
				model.setModelName(modelName);
				modelDao.updateModel(model);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Not_Finished.getCode());
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Model> preparePredict(String appKey, String token) {
		// TODO Auto-generated method stub
		User user = userDao.getByToken(token);
		DataWrapper<Model> dataWrapper = new DataWrapper<Model>();
		if (user != null) {
			Model model = modelDao.getByAppKey(appKey);
			dataWrapper.setData(model);
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Login.getCode());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<String> predict(String appKey, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Model model = modelDao.getByAppKey(appKey);
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		if (model != null && model.getState() == 1 && model.getPublished() == 1) {
			Predict predict = new Predict(model, request.getParameterMap());
			String result = predict.predict();
			dataWrapper.setData(result);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Model_State_Error.getCode());
		}
		return dataWrapper;
	}
	
	@Override
	public DataWrapper<String> predictWithAPI(String appKey, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Model model = modelDao.getByAppKey(appKey);
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		if (model != null && model.getState() == 1 && model.getPublished() == 1) {
			ModelRecords modelRecords = new ModelRecords();
			modelRecords.setId(null);
			modelRecords.setAppKey(appKey);
			modelRecords.setDate(new Date());
			modelRecords.setIp(request.getRemoteAddr());
			modelRecordsDao.saveModelRecords(modelRecords);
			Predict predict = new Predict(model, request.getParameterMap());
			String result = predict.predict();
			dataWrapper.setData(result);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Model_State_Error.getCode());
		}
		return dataWrapper;
	}

}
