package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class LeaveMgrMapper implements RowMapper<LeaveMgr> {

	@Override
	public LeaveMgr mapRow(ResultSet rs, int rowNum) throws SQLException {
		LeaveMgr leaveMgr=new LeaveMgr();
		leaveMgr.setLeaveId(rs.getString("leaveid"));
		leaveMgr.setEmpId(rs.getString("empid"));
		leaveMgr.setEmpName(rs.getString("emp_name"));
		leaveMgr.setStartDate(rs.getString("leavestart"));
		leaveMgr.setEndDate(rs.getString("leaveend"));
		leaveMgr.setReason(rs.getString("leavereason"));
		leaveMgr.setApprovalStatus(rs.getString("approvalstatus"));
		
		return leaveMgr;
		
		
	}

}
