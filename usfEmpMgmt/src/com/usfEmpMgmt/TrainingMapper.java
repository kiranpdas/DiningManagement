package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class TrainingMapper implements RowMapper<Training>  {
	@Override
	public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
		Training training=new Training();
		training.setTrainingName(rs.getString("TRAININGDESC"));
		training.setAssignedDate(rs.getString("TRAININGASSIGNEDDATE"));
		training.setStatus(rs.getString("STATUS"));
		   return training;  
		   
	   }

}
