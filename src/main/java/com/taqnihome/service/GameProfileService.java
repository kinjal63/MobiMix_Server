package com.taqnihome.service;

import java.util.ArrayList;

import com.taqnihome.domain.AppData;
import com.taqnihome.domain.CallDuration;
import com.taqnihome.domain.GameConnectionInfo;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameParticipantsDetail;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserDataUsage;
import com.taqnihome.domain.UserInput;
import com.taqnihome.domain.UserRSSI;

public interface GameProfileService /* extends GenericService<String, GameProfile>*/ {

//    List<String> findByUserIdAndGameLibraryPackageNameNotIn(User user, List<String> packageNameList);
//    List<GameProfile> findByGameLibrary(GameLibrary gameLibrary);
//    GameProfile removeByGameProfileId(String gameProfileId);
    void saveAppData(AppData appData);
    void addGameInfo(GameData gameData);
    public void saveRssi(UserRSSI userRssi);
	public void saveDataUsage(UserDataUsage dataUsage);
	public void aggregateCallDuration(CallDuration callDuration);
	void updateUserAvailablity(UserAvailablity availablity);
	Object getMutualGameList(String userId);
	Object getGameList();
	void editGame(GameData gameData);
	void deleteGame(long gameId);
	void sendConnectionInvite(UserConnectionInfo userConnectionInfo);
	void sendRemoteUserInput(UserInput userInput);
	Object getMutualGameList(String userId, ArrayList<String> userIds);
	void addUserAvailabilityTime(String userId, String fromTime, String toTime);
	
	//SDK API call
	void updateGameConnectionInfo(GameConnectionInfo connectionInfo);
	void fetchGameParticipantsDetail(GameParticipantsDetail gameParticipantsInfo);
}
