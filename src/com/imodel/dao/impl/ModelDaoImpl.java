package com.imodel.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.imodel.dao.BaseDao;
import com.imodel.dao.ModelDao;
import com.imodel.models.Model;

@Repository
public class ModelDaoImpl extends BaseDao<Model> implements ModelDao {

	@Override
	public List<Model> getModelList(Long userId) {
		// TODO Auto-generated method stub
		List<Model> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Model.class);
		criteria.add(Restrictions.eq("userId", userId));
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Model getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public boolean saveModel(Model model) {
		// TODO Auto-generated method stub
		return save(model);
	}

	@Override
	public boolean updateModel(Model model) {
		// TODO Auto-generated method stub
		return update(model);
	}

	@Override
	public boolean deleteModel(Long id) {
		// TODO Auto-generated method stub
		return delete(get(id));
	}

	@Override
	public Model getByAppKey(String appKey) {
		// TODO Auto-generated method stub
		List<Model> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Model.class);
		criteria.add(Restrictions.eq("appKey", appKey));
		criteria.add(Restrictions.eq("published", 1));
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

}
