package com.pharma.dao;

import com.pharma.entity.UserEntity;

public interface UserDao {

	public void registerUser(UserEntity userEntity);
	public boolean loginSubmit(UserEntity userEntity);
}
