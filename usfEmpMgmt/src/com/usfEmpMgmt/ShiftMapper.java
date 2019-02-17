package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class ShiftMapper implements RowMapper<Shift>  {
	@Override
	public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Shift shift=new Shift();
		   shift.setDate(rs.getString("dt"));
		   shift.setStartTime(rs.getString("STARTTIME"));
		   shift.setEndTime(rs.getString("ENDTIME"));
		   
		   return shift;  
		   
	   }

}
