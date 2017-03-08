package com.taqnihome.service;

import java.util.ArrayList;
import java.util.List;

import com.taqnihome.domain.AppData;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameLibrary;
import com.taqnihome.domain.GameProfile;
import com.taqnihome.domain.User;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserInput;

public interface GameProfileService extends GenericService<String, GameProfile> {

    List<String> findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList);
    List<GameProfile> findByGameLibrary(GameLibrary gameLibrary);
    GameProfile removeByGameProfileId(String gameProfileId);
    void saveAppData(AppData appData);
    void addGameInfo(GameData gameData);
	void updateUserAvailablity(UserAvailablity availablity);
	Object getMutualGameList(String userId);
	Object getGameList();
	void editGame(GameData gameData);
	void deleteGame(long gameId);
	void sendConnectionInvite(UserConnectionInfo userConnectionInfo);
	void sendRemoteUserInput(UserInput userInput);
	Object getMutualGameList(String userId, ArrayList<String> userIds);
	void addUserAvailabilityTime(String userId, String fromTime, String toTime);
}
