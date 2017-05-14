package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.taqnihome.model.db.GameRequest;

public class GameRequestMapper implements RowMapper<GameRequest> {

	@Override
	public GameRequest mapRow(ResultSet rs, int arg1) throws SQLException {
		GameRequest gameRequest = new GameRequest();
		gameRequest.setRemoteUserId(rs.getString("remote_user_id"));
		gameRequest.setRemoteUserName(rs.getString("remote_user_name"));
		gameRequest.setGameId(rs.getLong("game_id"));
		gameRequest.setGameName(rs.getString("game_name"));
		gameRequest.setGamePackageName(rs.getString("game_package_name"));
		gameRequest.setBluetoothAddress(rs.getString("wifidirect_or_bluetooth_name"));
		gameRequest.setWifiAddress(rs.getString("wifidirect_or_bluetooth_name"));
		
		return gameRequest;
	}
}
