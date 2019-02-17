package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LeaveMapper implements RowMapper<Leaves>  {

	public Leaves mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Leaves leaves = new Leaves();	      
	      leaves.setLeaveStart(rs.getString("LEAVESTART"));
	      leaves.setLeaveEnd(rs.getString("LEAVEEND"));
	      leaves.setReason(rs.getString("LEAVEREASON"));
	      leaves.setStatus(rs.getString("APPROVALSTATUS"));
	      return leaves;
	   }
	
}
