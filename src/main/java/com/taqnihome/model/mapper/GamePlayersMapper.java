package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taqnihome.domain.Aggregate;
import com.taqnihome.model.db.GamePlayersInfo;

public class GamePlayersMapper implements RowMapper {

	public GamePlayersInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		GamePlayersInfo playerInfo = new GamePlayersInfo();
		playerInfo.setUsername(rs.getString("name"));
		playerInfo.setConnectionType(rs.getInt("connection_type"));
		playerInfo.setIsGroupOwner(rs.getInt("is_group_owner"));
		playerInfo.setDeviceName(rs.getString("device_name"));
		
		return playerInfo;
	}
}
