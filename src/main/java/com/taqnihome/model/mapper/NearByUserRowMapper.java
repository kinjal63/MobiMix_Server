package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taqnihome.model.db.NearByUserDBModel;

public class NearByUserRowMapper implements RowMapper<NearByUserDBModel>{

	@Override
	public NearByUserDBModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		NearByUserDBModel model = new NearByUserDBModel();
		model.setUserId(rs.getString("user_id"));
		model.setDeviceToken(rs.getString("push_token"));
		return model;
	}
}
