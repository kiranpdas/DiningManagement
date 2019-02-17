package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class ShiftMgrMapper implements RowMapper<ShiftMgr>  {
	@Override
	public ShiftMgr mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShiftMgr shiftMgr=new ShiftMgr();
		shiftMgr.setDate(rs.getString("dt"));
		shiftMgr.setStartTime(rs.getString("STARTTIME"));
		shiftMgr.setEndTime(rs.getString("ENDTIME"));
		
		
		   
		   return shiftMgr;  
		   
	   }

}
