package com.imodel.dao;

import java.util.List;

import com.imodel.models.Records;

public interface RecordsDao {
	List<Records> getRecordList(String appKey);
}
