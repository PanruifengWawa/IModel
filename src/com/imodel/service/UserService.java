package com.imodel.service;

import com.imodel.utils.DataWrapper;

public interface UserService {
	DataWrapper<String> login(String userName,String password);
}
