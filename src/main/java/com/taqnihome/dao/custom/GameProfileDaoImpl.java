package com.taqnihome.dao.custom;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.taqnihome.dao.GameProfileDao;
import com.taqnihome.domain.Aggregate;
import com.taqnihome.domain.AppData;
import com.taqnihome.domain.CallDuration;
import com.taqnihome.domain.GameConnectionInfo;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameDataModel;
import com.taqnihome.domain.GameParticipantsDetail;
import com.taqnihome.domain.GameRemoteUserNotification;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserDataUsage;
import com.taqnihome.domain.UserGameResponse;
import com.taqnihome.domain.UserInput;
import com.taqnihome.domain.UserRSSI;
import com.taqnihome.model.db.GamePlayersInfo;
import com.taqnihome.model.db.GameRequest;
import com.taqnihome.model.db.NearByUserDBModel;
import com.taqnihome.model.mapper.DataUsageMapper;
import com.taqnihome.model.mapper.GamePlayersMapper;
import com.taqnihome.model.mapper.GameRemoteUserNotificationMapper;
import com.taqnihome.model.mapper.GameRequestMapper;
import com.taqnihome.model.mapper.NearByUserRowMapper;
import com.taqnihome.utils.NotificationUtil;

@Service
@Transactional
public class GameProfileDaoImpl implements GameProfileDao {
	private Cloudinary cloudinary;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private HashMap<String, List<String>> queueMap = new HashMap<>();
	private HashMap<String, List<String>> randomQueue = new HashMap<>();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dmffogznf", "api_key", "593785351395324",
				"api_secret", "NhoJNHlIl0yhNzlcB1XAmcS7_Ak"));
	}
	
//	@Override
//	public List<String> findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<GameProfile> findByGameLibrary(GameLibrary gameLibrary) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public GameProfile removeByGameProfileId(String gameProfileId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@Override
//	public void sendConnectionInvite(UserConnectionInfo userConnectionInfo) {
//		String whereIn = "(";
//		int size = userConnectionInfo.getRemoteUserIds().size();
//		
//		String gameRequestQuery = "select tu.user_id as remote_user_id, tu.name as remote_user_name, "
//				+ "tu.email as wifidirect_or_bluetooth_name, " //+ (userConnectionInfo.getConnectionInvite() == 1 ? "bluetooth_name" : "wifidirect_name") + " ,"
//				+ "gl.id as game_id, gl.game_name, gl.game_package_name "
//				+ "from taqnihome_user tu, game_library gl "
//				+ "where tu.user_id = '" + userConnectionInfo.getUserId() + "' and gl.game_package_name = '"
//				+ userConnectionInfo.getGamePackageName() + "'";
//		
//		GameRequest gameRequestObject = (GameRequest) jdbcTemplateObject.queryForObject(gameRequestQuery, new GameRequestMapper());
//		
//		if(gameRequestObject != null) {
//			if( userConnectionInfo.getConnectionInvite() == 1 ) {
//				gameRequestObject.setNotificationType(1);
//				gameRequestObject.setConnectionType(1);
//			}
//			else {
//				gameRequestObject.setNotificationType(2);
//				gameRequestObject.setConnectionType(2);
//			}
//		}
//
//		whereIn += "'" + userConnectionInfo.getRemoteUserIds().get(0) + "')";
//		
//		List<String> queueUserIds = new ArrayList<>();
//		for (int i = 1; i < size; i++) {
//			queueUserIds.add(userConnectionInfo.getRemoteUserIds().get(i));
//		}
//		
//		queueMap.put(userConnectionInfo.getUserId(), queueUserIds);
//
//		sendInvitation(userConnectionInfo.getConnectionInvite(), userConnectionInfo.getRemoteUserIds().get(0), gameRequestObject );
//	}

	@Override
	public void sendConnectionInvite(UserConnectionInfo userConnectionInfo) {
		String whereIn = "(";
		int size = userConnectionInfo.getRemoteUserIds().size();

		for(int i = 0; i < size; i++) {
			if(i != size - 1) {
				whereIn += "'" + userConnectionInfo.getRemoteUserIds().get(i) + "',";
			}
			else {
				whereIn += "'" + userConnectionInfo.getRemoteUserIds().get(i) + "')";
			}
		}
		
		String gameRequestQuery = "select d.push_token as group_owner_push_token, gpd.user_id as group_owner_user_id,"
								  + "group_concat(gpd.connected_user_id) as connected_user_ids "
								  + "from game_participants_details gpd "
								  + "join game_library gl on gpd.game_id = gl.id "
								  + "join taqnihome_user tu on tu.user_id = gpd.user_id "
								  + "join device_details_mapping dm on tu.user_id = dm.user_id " 
								  + "join device d on dm.device_id = d.device_id "
								  + "where gl.game_package_name = '" + userConnectionInfo.getGamePackageName() + "' " 
								  + "and gpd.connected_user_id in " + whereIn + " " 
								  + "or gpd.user_id in " + whereIn + " " 
								  + "group by gpd.user_id";
		
		GameRemoteUserNotification gameRemoteUserNotification = (GameRemoteUserNotification) jdbcTemplateObject.queryForObject(gameRequestQuery, new GameRemoteUserNotificationMapper());
		
		if(gameRemoteUserNotification != null) {
			sendInvitationToGroupOwner(userConnectionInfo, gameRemoteUserNotification);
		}
		
		else {
			sendInvitation(userConnectionInfo);			
		}
		
	}

	private void sendInvitationToGroupOwner(UserConnectionInfo userConnectionInfo, GameRemoteUserNotification gameRemoteUser) {
		String groupOwnerId = gameRemoteUser.getGroupOwnerUserId();
		
		List<String> usersTobeAddedInQueue = new ArrayList<>();
		for(String userId : userConnectionInfo.getRemoteUserIds()) {
			if(!gameRemoteUser.getConnectedUserIds().contains(userId)) {
				usersTobeAddedInQueue.add(userId);
			}
		}
		
		if(usersTobeAddedInQueue.size() > 0) {
			queueMap.put(groupOwnerId, usersTobeAddedInQueue);
		}
		
		GameRequest gameRequestObject = getGameRequestObject(userConnectionInfo.getUserId(), userConnectionInfo.getGamePackageName());
		
		if(userConnectionInfo.getConnectionInvite() == 2) {
			gameRequestObject.setConnectionType(2);
			gameRequestObject.setNotificationType(2);
		}
		else if(userConnectionInfo.getConnectionInvite() == 1) {
			gameRequestObject.setConnectionType(1);
			gameRequestObject.setNotificationType(1);
		}
		NotificationUtil.sendInvitation(gameRequestObject, new String[]{gameRemoteUser.getGroupOwnerPushToken()});
	}
	
	private void sendInvitation(UserConnectionInfo userConnectionInfo) {
		GameRequest gameRequestObject = getGameRequestObject(userConnectionInfo.getUserId(), userConnectionInfo.getGamePackageName());
		
		String whereIn = "";
		
		List<String> remoteUserIds = userConnectionInfo.getRemoteUserIds();
		for(int i = 0; i < remoteUserIds.size(); i++) {
			if(i != remoteUserIds.size() - 1) {
				whereIn += "'" + remoteUserIds.get(i) + "',";
			}
			else {
				whereIn += "'" + remoteUserIds.get(i) + "'";
			}
		}
		randomQueue.put(userConnectionInfo.getUserId(), remoteUserIds);
		
		String idQuery = "select d.push_token from taqnihome_user tu join device_details_mapping dm "
				+ "on tu.user_id = dm.user_id join device d on dm.device_id = d.device_id where tu.user_id in (" + whereIn + ")";

		List<String> deviceTokens = (List<String>) jdbcTemplateObject.query(idQuery, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		if( userConnectionInfo.getConnectionInvite() == 2 ) {
			gameRequestObject.setConnectionType(2);
			gameRequestObject.setNotificationType(2);
		
		}
		else if( userConnectionInfo.getConnectionInvite() == 1 ) {
			gameRequestObject.setConnectionType(1);
			gameRequestObject.setNotificationType(1);
		
		}
		NotificationUtil.sendInvitation(gameRequestObject, (String[])deviceTokens.toArray());
	}

	private GameRequest getGameRequestObject(String userId, String gamePackageName) {
		String gameRequestQuery = "select tu.user_id as remote_user_id, tu.name as remote_user_name, "
				+ "tu.email as wifidirect_or_bluetooth_name, "
				+ "gl.id as game_id, gl.game_name, gl.game_package_name "
				+ "from taqnihome_user tu, game_library gl "
				+ "where tu.user_id = '" + userId + "' and gl.game_package_name = '"
				+ gamePackageName + "'";
		
		GameRequest gameRequestObject = (GameRequest) jdbcTemplateObject.queryForObject(gameRequestQuery, new GameRequestMapper());
		
		return gameRequestObject;
	}
	
	@Override
	public void sendRemoteUserInput(UserInput input) {
		String idQuery = "select d.push_token from taqnihome_user tu join device_details_mapping dm "
				+ "on tu.user_id = dm.user_id join device d on dm.device_id = d.device_id where tu.user_id = " + input.getToUserId();

		String deviceToken = (String) jdbcTemplateObject.queryForObject(idQuery, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		NotificationUtil.sendBluetoothAddress(input.getUserId(), deviceToken, input.getBluetoothAddress());
	}

	public void saveRssi(UserRSSI userRssi) {
		String sql = "insert into rssi (user_id, deviceId, rssi, latitude, longitude, operator_name, timestamp) values (?, ?, ?, ?, ?, ?, now())";
		jdbcTemplateObject.update(sql, new Object[] { userRssi.getUserId(), userRssi.getDeviceId(), userRssi.getRssi(),
				userRssi.getLatitude(), userRssi.getLongitude(), userRssi.getOperatorName() });
	}

	@Override
	public void saveAppData(AppData appData) {
		String sql = "insert into game_profile (user_id, game_id) ";
		String sql1 = "(select '" + appData.getUserId() + "', id from game_library where game_package_name in (";
		String whereIn = "";
		for (int j = 0; j < appData.getAppDetail().length; j++) {
			if (j != appData.getAppDetail().length - 1) {
				whereIn += "'" + appData.getAppDetail()[j].getAppPackageName() + "',";
			} else {
				whereIn += "'" + appData.getAppDetail()[j].getAppPackageName() + "')) ";
			}
		}
		sql1 += whereIn;
		sql += sql1;
		sql += "on duplicate key update user_id = user_id, game_id = game_id";
		
		String insertAvailability = "insert into user_availability (user_id, latitude, "
				+ "longitude, availability, os_type, updated_at) values(?, ?, ?, 0, 1, now()) "
				+ "on duplicate key update updated_at=now()";
		
		System.out.println("SQL statement->" + sql1);
		jdbcTemplateObject.update(sql, new Object[]{});
		jdbcTemplateObject.update(insertAvailability, new Object[] {appData.getUserId(), appData.getLatitude(), 
				appData.getLongitude()});
	}

	public void addGameInfo(GameData gameData) {
		String imagePath = getImagePath(gameData);

		String sql = "insert into game_library (game_name, game_publisher_name,"
				+ "game_package_name, game_studio_name, game_image_path, age_rating,"
				+ "supporting_os, network_type, min_players, max_players, created_date) values (" + "'"
				+ gameData.getGameName() + "'," + "'" + gameData.getGamePublisherName() + "'," + "'"
				+ gameData.getGamePackageName() + "'," + "'" + gameData.getGameStudioName() + "'," + "'" + imagePath
				+ "','" + gameData.getAgeRating() + "'," + gameData.getOsType() + "," + gameData.getNetworkType() + ","
				+ "'" + gameData.getMinPlayers() + "'," + "'" + gameData.getMaxPlayers() + "', now())";

		System.out.println("SQL statement->" + sql);
		jdbcTemplateObject.update(sql, new Object[] {});
	}

	private String getImagePath(GameData gameData) {
		if (!gameData.getGameImagePath().isEmpty()) {
			try {
				String filePath = "D:/Images/" + gameData.getGamePackageName() + "-uploaded";
				File fileToUpload = new File(filePath);
				byte[] bytes = gameData.getGameImagePath().getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileToUpload));
				stream.write(bytes);
				stream.close();

				Map uploadResult = cloudinary.uploader().upload(filePath, ObjectUtils.emptyMap());
				String imagePath = (String) uploadResult.get("secure_url");
				fileToUpload.delete();

				return imagePath;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object getGameList() {
		String sql = "select id as gameId, game_package_name as gamePackageName, game_image_path as gameImagePath,"
				+ " game_name as gameName from game_library";

		java.util.List<GameDataModel> userGameResponse = (List<GameDataModel>) jdbcTemplateObject.query(sql,
				new RowMapper<GameDataModel>() {
					@Override
					public GameDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						GameDataModel gameDataModel = new GameDataModel();
						gameDataModel.setGameId(rs.getLong("gameId"));
						gameDataModel.setGameImagePath(rs.getString("gameImagePath"));
						gameDataModel.setGamePackageName(rs.getString("gamePackageName"));
						gameDataModel.setGameName(rs.getString("gameName"));
						return gameDataModel;
					}
				});
		return userGameResponse;
	}

	@Override
	public void editGame(GameData gameData) {
		String imagePath = getImagePath(gameData);

		String sql = "update game_library set game_name = ?,"
				+ "game_studio_name = ?, game_image_path = ?, age_rating = ?,"
				+ "supporting_os = ?, network_type = ?, min_players = ?, max_players = ?, created_date = now() "
				+ "where id = " + gameData.getGameId();

		System.out.println("SQL statement->" + sql);
		jdbcTemplateObject.update(sql,
				new Object[] { gameData.getGameName(), gameData.getGameStudioName(), imagePath, gameData.getAgeRating(),
						gameData.getOsType(), gameData.getNetworkType(), gameData.getMinPlayers(),
						gameData.getMaxPlayers() });
	}

	@Override
	public void deleteGame(long gameId) {

		String sql = "delete from game_library where id = " + gameId;

		System.out.println("SQL statement->" + sql);
		jdbcTemplateObject.execute(sql);
	}

	@Override
	public void updateUserAvailablity(UserAvailablity availablity) {
		String sql = "insert into user_availability (user_id, availability, latitude, longitude, updated_at) values(?, ?, ?, ?, now())"
				+ " ON DUPLICATE KEY UPDATE user_id=" + availablity.getUserId() + ", availability="
				+ availablity.getAvailablity() + ", latitude = " + availablity.getLatitude() + "," + "longitude = "
				+ availablity.getLongitude() + ",updated_at = now()";

		jdbcTemplateObject.update(sql, new Object[] { availablity.getUserId(), availablity.getAvailablity(),
				availablity.getLatitude(), availablity.getLongitude() });
	}

	@SuppressWarnings("unchecked")
	public List<UserGameResponse> getMutualGameList(String userId) {
		String latSql = "select ua.latitude from user_availability ua where ua.user_id = '" + userId + "'";

		String latitude = (String) jdbcTemplateObject.queryForObject(latSql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});

		String sql = "select u.user_id as user_id, (case when gpd.user_id or gpd.connected_user_id is not null then 1 else 0 end) as is_engaged,"
				+ "(case when gpd.user_id or gpd.connected_user_id is not null then gpd.game_id else 0 end) as active_game_id,"
				+ "u.name, GROUP_CONCAT(distinct gl.id) as game_id, GROUP_CONCAT(distinct gl.game_name) as game_name, "
				+ "GROUP_CONCAT(distinct gl.game_image_path) as game_image_path from game_profile gf "
				+ "inner join game_profile gf1 on gf.game_id = gf1.game_id and gf.user_id <> gf1.user_id "
				+ "join game_library gl on gl.id = gf.game_id join taqnihome_user u on u.user_id = gf.user_id "
				+ "left join game_participants_details gpd on u.user_id = gpd.user_id or u.user_id = gpd.connected_user_id "
				+ "join user_availability ua on u.user_id = ua.user_id "
				+ "where ua.availability = 1 and (ua.latitude -  " + latitude + " < 10 ) "
				+ "and gf.game_id in (select gf.game_id from game_profile gf where gf.user_id = '" + userId
				+ "') and u.user_id <> '" + userId + "' group by u.user_id";

		java.util.List<UserGameResponse> userGameResponse = (List<UserGameResponse>) jdbcTemplateObject.query(sql,
				new RowMapper<UserGameResponse>() {
					@Override
					public UserGameResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserGameResponse userGameResponse = new UserGameResponse();
						userGameResponse.setUserId(rs.getString("user_id"));
						userGameResponse.setUserFirstName(rs.getString("name"));
						userGameResponse.setIsEngaged(rs.getInt("is_engaged"));
						userGameResponse.setActiveGame(rs.getLong("active_game_id"));

						List<Long> gameIdList = new ArrayList<>();
						List<String> gameNameList = new ArrayList<>();
						List<String> gameImagePathList = new ArrayList<>();

						String[] gameIds = rs.getString("game_id").split(",");
						String[] gameNames = rs.getString("game_name").split(",");
						String[] gameImagePath = rs.getString("game_image_path").split(",");

						for (String gameId : gameIds) {
							gameIdList.add(Long.parseLong(gameId));
						}

						for (String gameName : gameNames) {
							gameNameList.add(gameName);
						}

						for (String gameIPath : gameImagePath) {
							gameImagePathList.add(gameIPath);
						}

						userGameResponse.setGameId(gameIdList);
						userGameResponse.setGameName(gameNameList);
						userGameResponse.setGameImagePath(gameImagePathList);
						return userGameResponse;
					}
				});

		return userGameResponse;

	}

	@SuppressWarnings("unchecked")
	public List<GameDataModel> getMutualGames(String userId, ArrayList<String> userIds) {
		userIds.add(userId);
		String whereIn = "(";
		for (int i = 0; i < userIds.size(); i++) {
			if (i != userIds.size() - 1) {
				whereIn += "'" + userIds.get(i) + "',";
			} else {
				whereIn += "'" + userIds.get(i) + "')";
			}
		}

		String sql = "select gl.game_name, gl.game_package_name, gl.id as game_id, gl.game_image_path, gl.network_type from game_library gl join "
				+ "(select count(game_id) as cg, game_id, " + "group_concat(user_id) as users from game_profile "
				+ "where user_id in " + whereIn + "group by game_id) as cgk on cgk.game_id = gl.id " + "where cgk.cg > "
				+ (userIds.size() - 1);

		java.util.List<GameDataModel> gameResponse = (List<GameDataModel>) jdbcTemplateObject.query(sql,
				new RowMapper<GameDataModel>() {
					@Override
					public GameDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						GameDataModel gameResponse = new GameDataModel();
						gameResponse.setGameId(rs.getLong("game_id"));
						gameResponse.setGameImagePath(rs.getString("game_image_path"));
						gameResponse.setGameName(rs.getString("game_name"));
						gameResponse.setGameNetworkType(rs.getInt("network_type"));
						gameResponse.setGamePackageName(rs.getString("game_package_name"));

						return gameResponse;
					}
				});

		return gameResponse;

	}

	@SuppressWarnings("unused")
	@Override
	public void addUserAvailabilityTime(String userId, String fromTime, String toTime) {
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		
		java.sql.Time fromTimeValue;
		try {
			fromTimeValue = new java.sql.Time(formatter.parse(fromTime).getTime());
			java.sql.Time toTimeValue = new java.sql.Time(formatter.parse(toTime).getTime());
			String sql = "insert into user_available_times (user_id, from_time, to_time, created_at) "
					+ "values (?, ?, ?, now())";
//			String sql1 = "insert into user_notifications "
//					+ "(user_id, notification_sent, sent_at) values (?, 0, now())";
			jdbcTemplateObject.update(sql, new Object[] { userId, fromTimeValue, toTimeValue });
//			jdbcTemplateObject.update(sql1, new Object[] { userId });
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAndNotifyNearByUsers() {
		List<String> availableUsers = getAvailableUsers();
		updateUsersAvailability(availableUsers);
		notifyNearByUsers(availableUsers);
	}

	private List<String> getAvailableUsers() {
		String sql = "select uat.user_id from user_available_times uat " +
				"left join user_notifications un on uat.user_id = un.user_id " +
				"join taqnihome_user u on uat.user_id = u.user_id " + 
				"where uat.from_time < now() and uat.to_time > now() and un.user_id is null";

		List<String> userIds = (List<String>) jdbcTemplateObject.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("user_id");
			}
		});
		return userIds;
	}
	
	private void updateUsersAvailability(List<String> userIds) {
		String whereIn = "";
		for (int i = 0; i < userIds.size(); i++) {
			if (i != userIds.size() - 1) {
				whereIn += "'" + userIds.get(i) + "',";
			} else {
				whereIn += "'" + userIds.get(i) + "'";
			}
		}

		if( userIds.size() > 0 ) {
			String sql = "update user_availability ua set ua.availability = 1 where user_id in (" + whereIn + ")";
			jdbcTemplateObject.update(sql);
		}
	}

	private void notifyNearByUsers(List<String> userIds) {
		for(String userId : userIds) {
			String latSql = "select ua.latitude, tu.name from user_availability ua join taqnihome_user tu on ua.user_id = tu.user_id"
					+ " where ua.user_id = '" + userId + "'";
			Map<String, Object> map = (Map<String, Object>)jdbcTemplateObject.queryForObject(latSql, new RowMapper<Map<String, Object>>() {
				@Override
				public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
					Map<String, Object> map = new HashMap<>();
					map.put("latitude", rs.getLong("latitude"));
					map.put("name", rs.getString("name"));
					
					return map;
				}
			});
			
			Long latitude = (Long)map.get("latitude");
			String name = (String)map.get("name");
			
			String sql = "select u.user_id, u.name, d.push_token, u.name " +
			"from game_profile gf " + 
			"join game_library gl on gl.id = gf.game_id join taqnihome_user u on u.user_id = gf.user_id join device_details_mapping dm "
				+ "on u.user_id = dm.user_id join device d on dm.device_id = d.device_id where " +
			"u.user_id in (select u.user_id from taqnihome_user u join user_availability ua on u.user_id = ua.user_id " +
			"where ua.availability = 1 and abs(ua.latitude - " + latitude + ") < 10 ) " +
			"and gf.game_id in (select gf.game_id from game_profile gf where gf.user_id = '" + userId +
			"') and u.user_id <> '" + userId + "' group by u.user_id";
			
			@SuppressWarnings("unchecked")
			List<NearByUserDBModel> nearByUserModels = (List<NearByUserDBModel>)jdbcTemplateObject.query(sql, new NearByUserRowMapper());
			
			List<String> deviceTokens = new ArrayList<>();
			List<String> neighbourUserIds = new ArrayList<>();
			for( NearByUserDBModel model : nearByUserModels ) {
				deviceTokens.add(model.getDeviceToken());
				neighbourUserIds.add(model.getUserId());
			}
			NotificationUtil.notifyOtherUsers(name, deviceTokens);
			updateNotifications(userId, neighbourUserIds);
		}
	}
	
	private void updateNotifications(String userId, List<String> userIds) {
		
		String sql = "insert into user_notifications (user_id, neighbour_user_id, notification_sent, sent_at)"
				+ " values (?, ?, ?, now()) on duplicate key update notification_sent = 1";
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
			for (String user : userIds) {
			    ps.setString(1, userId);
			    ps.setString(2, user);
			    ps.setInt(3, 1);
			    ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String sql1 = "insert into user_notifications (user_id, neighbour_user_id, notification_sent, sent_at)"
//				+ " values (?, ?, ?, now()) on duplicate key update notification_sent = 1";
//		try {
//			PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
//			for (String user : userIds) {
//			    ps.setString(1, user);
//			    ps.setString(2, userId);
//			    ps.setInt(3, 1);
//			    ps.addBatch();
//			}
//			ps.executeBatch();
//			
//			dataSource.getConnection().close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void checkUserAvailability() {
		String sql = "select uat.user_id from user_available_times uat " +
				 	 "where uat.from_time > now() or uat.to_time < now()";
		@SuppressWarnings("unchecked")
		List<String> userIds = (List<String>)jdbcTemplateObject.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("user_id"); 
			}
		});
		disableUserAvailability(userIds);
		deleteNotificationsForUsers(userIds);
	}
	
	private void disableUserAvailability(List<String> userIds) {
		if( userIds.size() <= 0 ) {
			return;
		}
		String whereIn = "";
		for (int i = 0; i < userIds.size(); i++) {
			if (i != userIds.size() - 1) {
				whereIn += "'" + userIds.get(i) + "',";
			} else {
				whereIn += "'" + userIds.get(i) + "'";
			}
		}
		
		String sql = "update user_availability ua set ua.availability = 0 where user_id not in (" + whereIn + ")";
		jdbcTemplateObject.update(sql);
	}
	
	private void deleteNotificationsForUsers(List<String> userIds) {
		String whereIn = "";
		for (int i = 0; i < userIds.size(); i++) {
			if (i != userIds.size() - 1) {
				whereIn += "'" + userIds.get(i) + "',";
			} else {
				whereIn += "'" + userIds.get(i) + "'";
			}
		}
		
		for(String userId : userIds) {
			String latSql = "select ua.latitude from user_availability ua where ua.user_id = '" + userId + "'";
			Long latitude = (Long)jdbcTemplateObject.queryForObject(latSql, new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("latitude");
				}
			});
		String sqlStatement = "delete from user_notifications where " +
					"(user_id = '" + userId + "' and neighbour_user_id not in  (select u.user_id as user_id " +
					"from game_profile gf " +
					"join game_library gl on gl.id = gf.game_id join taqnihome_user u on u.user_id = gf.user_id where " +
					"u.user_id in (select u.user_id from taqnihome_user u join user_availability ua on u.user_id = ua.user_id " +
					"where ua.availability = 1 and (abs(ua.latitude - " + latitude + ") < 10)) " +
					"and gf.game_id in (select gf.game_id from game_profile gf where gf.user_id = '" + userId + "') " +
					"and u.user_id <> '" + userId + "' group by u.user_id)) " +
					
 					"or user_id not in (" + whereIn + ")" +

					"or (user_id not in (select u.user_id as user_id " +
					"from game_profile gf " +
					"join game_library gl on gl.id = gf.game_id join taqnihome_user u on u.user_id = gf.user_id where " +
					"u.user_id in (select u.user_id from taqnihome_user u join user_availability ua on u.user_id = ua.user_id " +
					"where ua.availability = 1 and (abs(ua.latitude - " + latitude + ") < 10)) " +
					"and gf.game_id in (select gf.game_id from game_profile gf where gf.user_id = '" + userId + "') " +
					"and u.user_id <> '" + userId + "' group by u.user_id) and neighbour_user_id = '" + userId + "')";
		
			jdbcTemplateObject.execute(sqlStatement);
		}
	}

	@Override
	public void saveDataUsage(UserDataUsage dataUsage) {
		String sql = "insert into data_usage (user_id, country, deviceId, mobileTx, mobileRx, wifiTx, wifiRx, longitude, latitude, operator_name, timestamp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		jdbcTemplateObject.update(sql,
				new Object[] { dataUsage.getUserId(), dataUsage.getCountry(), dataUsage.getDeviceId(),
						dataUsage.getMobileTx(), dataUsage.getMobileRx(), dataUsage.getWifiTx(), dataUsage.getWifiRx(),
						dataUsage.getLongitude(), dataUsage.getLatitude(), dataUsage.getOperatorName() });

	}

	@Override
	public void aggregateCallDuration(CallDuration callDuration) {
		String sql = "insert into call_logs (user_id, country, total_mobileRx, "
				+ "total_mobileTx, total_wifiRx, total_wifiTx, timestamp) (select user_id, country, sum(mobileRx) as totalMobileRx, sum(mobileTx) as totalMobileTx, "
				+ "sum(wifiRx) as totalWifiRx, sum(wifiTx) as totalWifiTx, now() from data_usage group by user_id, country)";

		System.out.println(sql);
		jdbcTemplateObject.update(sql, new Object[] {});
	}

	public Aggregate aggregateRssiAndDataUsage() {
		String sql = "select user_id, country, sum(mobileRx) as totalMobileRx, sum(mobileTx) as totalMobileTx, "
				+ "sum(wifiRx) as totalWifiRx, sum(wifiTx) as totalWifiTx "
				+ "from data_usage group by user_id, country";
		@SuppressWarnings("unchecked")
		Aggregate user = (Aggregate) jdbcTemplateObject.queryForObject(sql, null, new DataUsageMapper());
		return user;
	}
	
	public void updateGameConnectionInfo(GameConnectionInfo gameConnectionInfo) {
		String sql = "insert into game_participants_details (game_id, user_id, is_group_owner, connected_user_id, connection_type,"
				+ "created_at) values (?, ?, ?, ?, ?, now()) on duplicate key update connection_type = ?";
		jdbcTemplateObject.update(sql, new Object[] {gameConnectionInfo.getGameId(), gameConnectionInfo.getUserId(),
				gameConnectionInfo.getIsGroupOwner(), gameConnectionInfo.getConnectedUserId(), 
				gameConnectionInfo.getConnectionType(), gameConnectionInfo.getConnectionType()});
		
		pullQueueIfUserExists(gameConnectionInfo.getUserId(), gameConnectionInfo.getConnectedUserId());
		
		if(gameConnectionInfo.getIsNeedToNotify()) {
			String query = "select gl.game_package_name, (select d.push_token from taqnihome_user tu join device_details_mapping dm "
					+ "on tu.user_id = dm.user_id join device d on dm.device_id = d.device_id where tu.user_id = ?) as push_token "
					+ "from game_library gl where gl.id = ?";
			
			Map<String, Object> mapObj = jdbcTemplateObject.queryForMap(query, new String[]{gameConnectionInfo.getConnectedUserId(), String.valueOf(gameConnectionInfo.getGameId())});

			GameRequest request = new GameRequest();
			request.setRemoteUserId(gameConnectionInfo.getUserId());
			request.setGameId(gameConnectionInfo.getGameId());
			request.setGamePackageName(mapObj.get("game_package_name").toString());
			request.setNotificationType(3);
			request.setConnectionType(gameConnectionInfo.getConnectionType());
			
			NotificationUtil.notifyEstablishedConnectionToRemoteUser(mapObj.get("push_token").toString(), request);
		}
	}
	
	public List<GamePlayersInfo> fetchGameParticipantsDetail(GameParticipantsDetail gameParticipantDetail) {
		String sql = "select tu.name, tu.email as device_name, p.connection_type, p.is_group_owner from taqnihome_user tu, " + 
					 "(select gpd.user_id, gpd.connection_type, gpd.is_group_owner " +
				     "from game_participants_details gpd join taqnihome_user tu on (gpd.user_id = tu.user_id " +
				     "or gpd.connected_user_id = tu.user_id) join game_library gl on gpd.game_id = gl.id " +
				     "where tu.email = ? and gl.game_package_name = ?) p " +
					 "where tu.user_id in (p.user_id) and tu.email <> ?";
		List<GamePlayersInfo> lstGamePlayers = (List<GamePlayersInfo>)jdbcTemplateObject.query(sql, new Object[] {gameParticipantDetail.getWifiorBluetoothName(), 
				gameParticipantDetail.getGamePackageName(), gameParticipantDetail.getWifiorBluetoothName()}, new GamePlayersMapper());
		
		return lstGamePlayers;
		
	}
	
	private void pullQueueIfUserExists(String userId, String connectedUserId) {
		if(queueMap.containsKey(userId)) {
			List<String> userIds = (List<String>)queueMap.get(userId);
			for(String id : userIds) {
				String fetchUserIdToSendRequest = "select game_id, group_concat(connected_user_id) from game_participants_details where user_id = ?";
				String requestTobeSentByUserId = (String)jdbcTemplateObject.queryForObject(fetchUserIdToSendRequest, 
						new Object[] {userId}, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("");
					}
				});
	//			sendConnectionInvite(userConnectionInfo);
			}
		}
	}
		
	public static void main(String args[]) {
		String sql = "select u.user_id as user_id, (case when gpd.user_id or gpd.connected_user_id is not null then 1 else 0 end) as is_engaged, "
				+ "u.name, GROUP_CONCAT(distinct gl.id) as game_id, GROUP_CONCAT(distinct gl.game_name) as game_name, "
				+ "GROUP_CONCAT(distinct gl.game_image_path) as game_image_path from game_profile gf "
				+ "inner join game_profile gf1 on gf.game_id = gf1.game_id and gf.user_id <> gf1.user_id "
				+ "join game_library gl on gl.id = gf.game_id join taqnihome_user u on u.user_id = gf.user_id "
				+ "left join game_participants_details gpd on u.user_id = gpd.user_id or u.user_id = gpd.connected_user_id "
				+ "join user_availability ua on u.user_id = ua.user_id "
				+ "where ua.availability = 1 and (ua.latitude -  73 < 10 ) "
				+ "and gf.game_id in (select gf.game_id from game_profile gf where gf.user_id = '65362ttfnnc cbncb"
				+ "') and u.user_id <> '65362ttfnnc cbncb' group by u.user_id";

		System.out.println(sql);
	}
}
