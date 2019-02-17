package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ActMgrMapper implements RowMapper<ActivityManager>  {

	public ActivityManager mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityManager act = new ActivityManager();
	      act.setShdDate(rs.getString("Shd_date"));
	      act.setStartTime(rs.getString("StartTime"));
	      act.setEndTime(rs.getString("EndTIme"));
	      act.setActivity(rs.getString("Activity"));
	      act.setLocation(rs.getString("Location"));
	      act.setSupervisor(rs.getString("Supervisor"));
	      act.setEmpId(rs.getString("empid"));
	      act.setEmpName(rs.getString("emp_name"));
	      return act;
	   }
	
}
