package com.taqnihome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taqnihome.dao.UserAdminDao;
import com.taqnihome.domain.UserAdmin;
@Service
@Transactional
public class UserAdminServiceImpl extends GenericServiceImpl<String, UserAdmin> implements UserAdminService{

	
	private UserAdminDao userAdminDao;
	
	public UserAdminServiceImpl(){
		
	}
	@Autowired
	public UserAdminServiceImpl(UserAdminDao userAdminDao){
		super(userAdminDao);
		this.userAdminDao = userAdminDao;
	}
	@Override
	public UserAdmin findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userAdminDao.findByEmailAndPassword(email, password);
	}
	@Override
	public UserAdmin findByEmail(String email) {
		// TODO Auto-generated method stub
		return userAdminDao.findByEmail(email);
	}
	
}
