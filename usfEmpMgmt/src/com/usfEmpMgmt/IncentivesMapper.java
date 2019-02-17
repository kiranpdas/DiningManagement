package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class IncentivesMapper implements RowMapper<Incentives> {

	@Override
	public Incentives mapRow(ResultSet rs, int rowNum) throws SQLException {
		Incentives incentive = new Incentives();
		incentive.setCouponId(rs.getString("couponid"));
		incentive.setCouponDesc(rs.getString("coupondesc"));
		incentive.setRedeemStatus(rs.getString("COUPONREDEEMEDSTATUS"));
		return incentive;
	}

}
