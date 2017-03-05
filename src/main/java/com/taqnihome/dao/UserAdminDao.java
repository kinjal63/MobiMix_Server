package com.taqnihome.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taqnihome.domain.UserAdmin;

public interface UserAdminDao extends JpaRepository<UserAdmin, String> {
	UserAdmin findByEmail(String email);
	UserAdmin findByEmailAndPassword(String email, String password);
}
