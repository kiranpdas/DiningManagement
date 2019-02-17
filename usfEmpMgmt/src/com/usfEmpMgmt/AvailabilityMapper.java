package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class AvailabilityMapper implements RowMapper<Availability>  {
	@Override
	public Availability mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Availability availability=new Availability();
		   availability.setDayOfWeek(rs.getString("AVAIL_DAY"));
		   availability.setStartTime(rs.getString("START_TIME"));
		   availability.setEndTime(rs.getString("END_TIME"));
		   return availability;  
		   
	   }

}
