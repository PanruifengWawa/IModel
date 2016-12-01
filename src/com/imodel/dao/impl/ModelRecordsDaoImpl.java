package com.imodel.dao.impl;

import org.springframework.stereotype.Repository;

import com.imodel.dao.BaseDao;
import com.imodel.dao.ModelRecordsDao;
import com.imodel.models.ModelRecords;

@Repository
public class ModelRecordsDaoImpl extends BaseDao<ModelRecords> implements ModelRecordsDao {

	@Override
	public boolean saveModelRecords(ModelRecords modelRecords) {
		// TODO Auto-generated method stub
		return save(modelRecords);
	}

}
