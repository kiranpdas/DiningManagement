package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class TimesheetMapper implements RowMapper<Timesheet>  {
	@Override
	public Timesheet mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Timesheet timesheet=new Timesheet();
		   timesheet.setCheckIn(rs.getString("checkin"));
		   timesheet.setCheckOut(rs.getString("checkout"));
		   
		   return timesheet;  
		   
	   }

}
