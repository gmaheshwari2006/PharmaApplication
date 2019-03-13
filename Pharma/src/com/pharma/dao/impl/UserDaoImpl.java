package com.pharma.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.CollectionSubqueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pharma.dao.UserDao;
import com.pharma.entity.UserEntity;

@Repository
@Transactional(readOnly = true) 
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = false)
	public void registerUser(UserEntity userEntity) {
		Session session = sessionFactory.openSession();
		session.persist(userEntity);
		session.flush();
	}

	@Override
	public boolean loginSubmit(UserEntity userEntity) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select user from UserEntity user where user.userName =:userName"+
				" and user.userPassword=:userPassword");
		query.setParameter("userName", userEntity.getUserName());
		query.setParameter("userPassword", userEntity.getUserPassword());
		List<UserEntity> list = query.list();
		if(!CollectionUtils.isEmpty(list)) {
			return true;
		} else {
			return false;
		}
	}

}
