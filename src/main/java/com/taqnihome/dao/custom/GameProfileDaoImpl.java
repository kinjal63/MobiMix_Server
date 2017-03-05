package com.taqnihome.dao.custom;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.taqnihome.dao.GameProfileDao;
import com.taqnihome.domain.AppData;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameLibrary;
import com.taqnihome.domain.GameProfile;
import com.taqnihome.domain.User;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserInput;

public class GameProfileDaoImpl implements GameProfileDao {
	
	private SessionFactory sessionFactory;

	public GameProfileDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<String> findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameProfile> findByGameLibrary(GameLibrary gameLibrary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameProfile removeByGameProfileId(String gameProfileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAppData(AppData appData) {
		
		
	}

	@Override
	public void addGameInfo(GameData gameData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserAvailablity(UserAvailablity availablity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getMutualGameList(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getGameList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editGame(GameData gameData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGame(long gameId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendConnectionInvite(UserConnectionInfo userConnectionInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRemoteUserInput(UserInput userInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getMutualGames(long userId, ArrayList<Long> userIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserAvailabilityTime(long userId, String fromTime, String toTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAndNotifyNearByUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkUserAvailability() {
		// TODO Auto-generated method stub
		
	}

}
