package com.imodel.dao;

import com.imodel.models.User;

public interface UserDao {
	User getByUserName(String userName);
	User getByToken(String token);
}
