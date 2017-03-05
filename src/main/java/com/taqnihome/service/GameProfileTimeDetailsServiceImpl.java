package com.taqnihome.service;

import com.taqnihome.dao.GameProfileDao;
import com.taqnihome.dao.GameProfileTimeDetailsDao;
import com.taqnihome.domain.GameProfileTimeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by songline on 21/12/16.
 */

@Service
@Transactional
public class GameProfileTimeDetailsServiceImpl extends GenericServiceImpl<String, GameProfileTimeDetails> implements GameProfileTimeDetailsService {

    private GameProfileTimeDetailsDao gameProfileTimeDetailsDao;

    public GameProfileTimeDetailsServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public GameProfileTimeDetailsServiceImpl(GameProfileTimeDetailsDao gameProfileTimeDetailsDao) {
        super(gameProfileTimeDetailsDao);
        this.gameProfileTimeDetailsDao = gameProfileTimeDetailsDao;
    }

    @Override
    public GameProfileTimeDetails findByGameProfileTimeScheduleAndUser_Id(String gameProfileTimeSchedule, String id) {
        return gameProfileTimeDetailsDao.findByGameProfileTimeScheduleAndUser_Id(gameProfileTimeSchedule, id);
    }
}
