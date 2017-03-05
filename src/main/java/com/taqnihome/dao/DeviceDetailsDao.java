package com.taqnihome.dao;

import com.taqnihome.domain.DeviceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by songline on 01/10/16.
 */
public interface DeviceDetailsDao extends JpaRepository<DeviceDetails,String> {
}
