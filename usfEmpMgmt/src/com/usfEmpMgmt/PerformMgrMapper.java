package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class PerformMgrMapper implements RowMapper<PerformMgr> {

	@Override
	public PerformMgr mapRow(ResultSet rs, int rowNum) throws SQLException {
		PerformMgr perform = new PerformMgr();
		perform.setEmpId(rs.getString("empid"));
		perform.setEmpName(rs.getString("emp_name"));
		perform.setRatingPeriod(rs.getString("ratingperiod"));
		perform.setAppraiser(rs.getString("appraiser"));
		perform.setEmpRating(rs.getString("emprating"));
		return perform;
	}

}
