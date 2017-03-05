package com.taqnihome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taqnihome.domain.AppData;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameLibrary;
import com.taqnihome.domain.GameProfile;
import com.taqnihome.domain.User;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserInput;

public interface GameProfileDao {

    List<String>  findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList);
    List<GameProfile> findByGameLibrary(GameLibrary gameLibrary);
    GameProfile removeByGameProfileId(String gameProfileId);

    void saveAppData(AppData appData);
	void addGameInfo(GameData gameData);
	void updateUserAvailablity(UserAvailablity availablity);
	Object getMutualGameList(long userId);
	Object getGameList();
	void editGame(GameData gameData);
	void deleteGame(long gameId);
	void sendConnectionInvite(UserConnectionInfo userConnectionInfo);
	void sendRemoteUserInput(UserInput userInput);
	Object getMutualGames(long userId, ArrayList<Long> userIds);
	void addUserAvailabilityTime(long userId, String fromTime, String toTime);
	
	//cron job functions
	void updateAndNotifyNearByUsers();
	void checkUserAvailability();
}
