package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class AvailabilityMgrMapper implements RowMapper<AvailabilityMgr>  {
	@Override
	public AvailabilityMgr mapRow(ResultSet rs, int rowNum) throws SQLException {
		AvailabilityMgr availability=new AvailabilityMgr();
		availability.setEmpId(rs.getString("empid"));
		   availability.setEmpName(rs.getString("emp_name"));
		   availability.setStartTime(rs.getString("START_TIME"));
		   availability.setEndTime(rs.getString("END_TIME"));
		   return availability;  
		   
	   }

}
