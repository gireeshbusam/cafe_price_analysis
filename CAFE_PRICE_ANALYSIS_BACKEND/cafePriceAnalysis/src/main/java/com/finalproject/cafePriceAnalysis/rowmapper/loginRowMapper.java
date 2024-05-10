package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.EmpDetailsDTO;

public class loginRowMapper implements RowMapper<EmpDetailsDTO> {
	@Override
    public EmpDetailsDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		EmpDetailsDTO empDetailsDTO = new EmpDetailsDTO();
		
		empDetailsDTO.setEmpId(resultSet.getString("empid"));
		empDetailsDTO.setPassword(resultSet.getString("password"));
		
        return empDetailsDTO;
    }
}