package com.imodel.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.imodel.dao.BaseDao;
import com.imodel.dao.RecordsDao;
import com.imodel.models.Records;

@Repository
public class RecordsDaoImpl extends BaseDao<Records> implements RecordsDao {

	@Override
	public List<Records> getRecordList(String appKey) {
		// TODO Auto-generated method stub
		List<Records> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Records.class);
		criteria.add(Restrictions.eq("appKey",appKey));
		criteria.addOrder(Order.desc("date"));
		criteria.setMaxResults(7);
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
	

}
