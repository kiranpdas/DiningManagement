package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class PaymentMapper implements RowMapper<Payments>  {
	@Override
	public Payments mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payments payment=new Payments();
		payment.setPaymentDate(rs.getString("paymentdate"));
		payment.setAmount(rs.getString("amountpaid"));
		   
		   return payment;  
		   
	   }

}
