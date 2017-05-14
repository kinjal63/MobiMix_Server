package com.taqnihome.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.taqnihome.dao.GameProfileDao;

@Service
@EnableScheduling
public class ScheduleService {
	
	@Autowired
	private GameProfileDao gameProfileDao;
	
	    @Scheduled(fixedDelay = 20000)
	    public void notificationServiceMethod()
	    {
	    	gameProfileDao.updateAndNotifyNearByUsers();
	    }
	    
	    @Scheduled(fixedDelay = 20000)
	    public void checkUserAvailability()
	    {
//	    	gameProfileDao.checkUserAvailability();
	    }
}
