package com.taqnihome.service;

import com.taqnihome.dao.DeviceDetailsDao;
import com.taqnihome.domain.DeviceDetails;
import com.taqnihome.domain.GameCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by songline on 01/10/16.
 */
@Service
public class DeviceDetailsServiceImpl extends GenericServiceImpl<String, DeviceDetails>  implements  DeviceDetailsService{

   private DeviceDetailsDao deviceDetailsDao;
    
   @Autowired
   public DeviceDetailsServiceImpl(DeviceDetailsDao deviceDetailsDao){
        super(deviceDetailsDao);
        this.deviceDetailsDao = deviceDetailsDao;
    }
}
