package com.taqnihome.service;

import com.taqnihome.domain.UserAdmin;

public interface UserAdminService extends GenericService<String, UserAdmin> {
	UserAdmin findByEmailAndPassword(String email, String password);
	UserAdmin findByEmail(String email);

}
