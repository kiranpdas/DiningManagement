package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AccessMapper implements RowMapper<AccessoryIssue>  {

	public AccessoryIssue mapRow(ResultSet rs, int rowNum) throws SQLException {
	      AccessoryIssue access = new AccessoryIssue();	      
	      access.setAccessoryName(rs.getString("ACCESSORYNAME"));
	      access.setAccessoryStatus(rs.getString("ACCESSORYISSUESTATUS"));
	      access.setAccessoryIssueDate(rs.getString("ACCESSORYISSUEDATE"));	           
	      return access;
	   }
	
}
