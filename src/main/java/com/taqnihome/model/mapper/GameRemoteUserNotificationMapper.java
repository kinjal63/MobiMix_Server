package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.taqnihome.domain.GameRemoteUserNotification;

public class GameRemoteUserNotificationMapper implements RowMapper<GameRemoteUserNotification> {

	@Override
	public GameRemoteUserNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
		GameRemoteUserNotification gameRemoteUsers = null;
		if(rs != null && rs.next()) {
			gameRemoteUsers = new GameRemoteUserNotification();
			
			gameRemoteUsers.setGroupOwnerUserId(rs.getString("group_owner_user_id"));
			String[] connectedUserIDs = rs.getString("connected_user_ids").split(",");
			
			List<String> gameRemoteUserList = new ArrayList<>();
			gameRemoteUserList.addAll(Arrays.asList(connectedUserIDs));
		}
		return gameRemoteUsers;
	}

	
}
