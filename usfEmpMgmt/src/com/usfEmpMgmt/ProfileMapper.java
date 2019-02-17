package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProfileMapper implements RowMapper<Profile>  {

	public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profile profile = new Profile();	      
		profile.setAllergies(rs.getString("allergies"));
		profile.setEmpLevel(rs.getString("empLevel"));
		profile.setEmployeeAddr(rs.getString("employeeAddr"));
		profile.setEmployeeCell(rs.getString("employeeCell"));
		profile.setEmployeeDOB(rs.getString("employeeDOB"));
		profile.setEmployeeEmail(rs.getString("employeeEmail"));
		profile.setEmployeeGender(rs.getString("employeeGender"));
		profile.setEmployeeId(rs.getString("employeeId"));
		profile.setEmployeeName(rs.getString("employeeName"));
		profile.setEmployeeSSN(rs.getString("employeeSSN"));
		profile.setEmployeeVisa(rs.getString("employeeVisa"));
		profile.setEmpManagerName(rs.getString("empManagerName"));
		profile.setEmpUserName(rs.getString("empUserName"));
		
	      return profile;
	   }
	
}
