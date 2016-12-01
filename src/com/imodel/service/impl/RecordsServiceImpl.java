package com.imodel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imodel.dao.ModelDao;
import com.imodel.dao.RecordsDao;
import com.imodel.enums.ErrorCodeEnum;
import com.imodel.models.Model;
import com.imodel.models.Records;
import com.imodel.service.RecordsService;
import com.imodel.utils.DataWrapper;

@Service("recordsService")
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	ModelDao modelDao;
	
	@Autowired
	RecordsDao recordsDao;
	@Override
	public DataWrapper<List<Records>> getRecords(String appKey,String token) {
		// TODO Auto-generated method stub
		Model model = modelDao.getByAppKey(appKey);
		DataWrapper<List<Records>> dataWrapper = new DataWrapper<List<Records>>();
		if (model != null && model.getState() == 1 && model.getPublished() == 1) {
			dataWrapper.setData(recordsDao.getRecordList(appKey));
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Model_State_Error.getCode());
		}
		return dataWrapper;
	}

}
