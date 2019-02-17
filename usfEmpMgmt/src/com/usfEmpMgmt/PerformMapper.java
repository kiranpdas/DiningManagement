package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class PerformMapper implements RowMapper<Performance> {

	@Override
	public Performance mapRow(ResultSet rs, int rowNum) throws SQLException {
		Performance perform = new Performance();
		perform.setRatingPeriod(rs.getString("ratingperiod"));
		perform.setAppraiser(rs.getString("appraiser"));
		perform.setEmpRating(rs.getString("emprating"));
		return perform;
	}

}
