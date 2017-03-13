package com.taqnihome.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taqnihome.dao.custom.UserDaoCustom;
import com.taqnihome.domain.User;

public interface UserDao extends JpaRepository<User, String>, UserDaoCustom {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByMacAddress(String macAddress);

    @Query(value = "SELECT  * , SQRT(\n" +
            "    POW(69.1 * (latitude - ?1), 2) +\n" +
            "    POW(69.1 * (?2 - longitude) * COS(latitude / 57.3), 2)) AS distance\n" +
            "FROM taqnihome_user user HAVING distance < 4 ORDER BY distance", nativeQuery = true)
    List<User> findByLocation(Double latitude, Double longitude);

//    List<User> findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(List<String> user_id,long gameProfileStartTime, long gameProfileEndTime);

}
