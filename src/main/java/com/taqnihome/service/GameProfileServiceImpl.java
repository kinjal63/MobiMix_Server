package com.taqnihome.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taqnihome.dao.GameProfileDao;
import com.taqnihome.domain.AppData;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameLibrary;
import com.taqnihome.domain.GameProfile;
import com.taqnihome.domain.User;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserInput;

@Service
@Transactional
public class GameProfileServiceImpl implements GameProfileService {

    private GameProfileDao gameProfileDao;

    public GameProfileServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public GameProfileServiceImpl(GameProfileDao gameProfileDao) {
        this.gameProfileDao = gameProfileDao;
    }


    @Override
    public List<String> findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList) {
        return gameProfileDao.findByUserIdAndGameLibraryPackageNameNotIn(user, packageNameList);
    }


    @Override
    public GameProfile removeByGameProfileId(String gameProfileId) {
        return gameProfileDao.removeByGameProfileId(gameProfileId);
    }

    @Override
    public List<GameProfile> findByGameLibrary(GameLibrary gameLibrary) {
        return gameProfileDao.findByGameLibrary(gameLibrary);
    }
    
    @Override
	public void saveAppData(AppData appData) {
    	gameProfileDao.saveAppData(appData);
	}
	
	public void addGameInfo(GameData gameData) {
		gameProfileDao.addGameInfo(gameData);
	};
	
	@Override
	public void updateUserAvailablity(UserAvailablity availablity) {
		gameProfileDao.updateUserAvailablity(availablity);
		
	}
	
	@Override
	public Object getMutualGameList(String userId) {
		return gameProfileDao.getMutualGameList(userId);
	}
	
	@Override
	public Object getGameList() {
		return gameProfileDao.getGameList();	
	}

	@Override
	public void editGame(GameData gameData) {
		gameProfileDao.editGame(gameData);
	}
	
	@Override
	public void deleteGame(long gameId) {
		gameProfileDao.deleteGame(gameId);
	}
	
	public void sendConnectionInvite(UserConnectionInfo userConnectionInfo) {
		gameProfileDao.sendConnectionInvite(userConnectionInfo);
	}
	
	public void sendRemoteUserInput(UserInput userInput) {
		gameProfileDao.sendRemoteUserInput(userInput);
	}
	
	@Override
	public Object getMutualGameList(String userId, ArrayList<String> userIds) {
		return gameProfileDao.getMutualGames(userId, userIds);
	}
	
	public void addUserAvailabilityTime(String userId, String fromTime, String toTime) {
		gameProfileDao.addUserAvailabilityTime(userId, fromTime, toTime);
	}

	@Override
	public GameProfile save(GameProfile entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(GameProfile entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameProfile getById(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameProfile> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String pk) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
