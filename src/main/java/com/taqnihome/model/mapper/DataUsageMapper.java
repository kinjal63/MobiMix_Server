package com.taqnihome.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taqnihome.domain.Aggregate;

public class DataUsageMapper implements RowMapper {

	public Aggregate mapRow(ResultSet rs, int arg1) throws SQLException {
		Aggregate aggregate = new Aggregate();
		aggregate.setUserId(rs.getString("userId"));
		aggregate.setTotalMobileRx(rs.getLong("totalMobileRx"));
		aggregate.setTotalMobileTx(rs.getLong("totalMobileTx"));
		aggregate.setTotalWifiRx(rs.getLong("totalWifiRx"));
		aggregate.setTotalWifiTx(rs.getLong("totalWifiTx"));
		return aggregate;
	}

}
