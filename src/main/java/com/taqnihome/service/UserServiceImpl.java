package com.taqnihome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taqnihome.dao.UserDao;
import com.taqnihome.domain.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<String, User>
        implements UserService {

    private UserDao userDataDao;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDataDao = userDao;
    }

    @Override
    public User findByEmail(String email) {

        return userDataDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDataDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByMacAddress(String macAddress) {

        return userDataDao.findByMacAddress(macAddress);
    }

    @Override
    public List<User> findByLocation(Double latitude, Double longitude) {
        return userDataDao.findByLocation(latitude, longitude);
    }

//    @Override
//    public List<User> findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(List<String> user_id, long gameProfileStartTime, long gameProfileEndTime) {
//        return userDataDao.findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(user_id, gameProfileStartTime, gameProfileEndTime);
//    }
}
