package com.imodel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imodel.dao.UserDao;
import com.imodel.enums.ErrorCodeEnum;
import com.imodel.models.User;
import com.imodel.service.UserService;
import com.imodel.utils.DataWrapper;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public DataWrapper<String> login(String userName, String password) {
		// TODO Auto-generated method stub
		User user = userDao.getByUserName(userName);
		DataWrapper<String> dataWrapper = new  DataWrapper<String>();
		if (user != null) {
			if (user.getPassword().equals(password)) {
				dataWrapper.setData(user.getToken());
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Password_Error.getCode());
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.User_Not_Exists.getCode());
		}
		return dataWrapper;
	}

}
