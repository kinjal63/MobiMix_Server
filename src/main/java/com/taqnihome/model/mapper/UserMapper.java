package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taqnihome.domain.User;

public class UserMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getString("user_id"));
		user.setName(rs.getString("firstname"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}
