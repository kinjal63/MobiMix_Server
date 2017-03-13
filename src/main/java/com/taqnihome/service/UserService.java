package com.taqnihome.service;

import com.taqnihome.domain.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;

import java.util.List;

public interface UserService extends GenericService<String, User> {

	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	User findByMacAddress(String macAddress);
	List<User> findByLocation(Double latitude, Double longitude);
//	List<User> findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(List<String> user_id,long gameProfileStartTime, long gameProfileEndTime);
}
