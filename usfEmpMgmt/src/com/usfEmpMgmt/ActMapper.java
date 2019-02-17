package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ActMapper implements RowMapper<Activity>  {

	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Activity act = new Activity();
	      act.setShdDate(rs.getString("Shd_date"));
	      act.setStartTime(rs.getString("StartTime"));
	      act.setEndTime(rs.getString("EndTIme"));
	      act.setActivity(rs.getString("Activity"));
	      act.setLocation(rs.getString("Location"));
	      act.setSupervisor(rs.getString("Supervisor"));
	      return act;
	   }
	
}
