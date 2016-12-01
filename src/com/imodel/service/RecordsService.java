package com.imodel.service;

import java.util.List;

import com.imodel.models.Records;
import com.imodel.utils.DataWrapper;


public interface RecordsService {
	DataWrapper<List<Records>> getRecords(String appKey,String token);
}
