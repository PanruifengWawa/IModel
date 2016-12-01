package com.imodel.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.imodel.dao.BaseDao;
import com.imodel.dao.UserDao;
import com.imodel.models.User;


@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		List<User> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

	@Override
	public User getByToken(String token) {
		// TODO Auto-generated method stub
		List<User> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("token", token));
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

}
