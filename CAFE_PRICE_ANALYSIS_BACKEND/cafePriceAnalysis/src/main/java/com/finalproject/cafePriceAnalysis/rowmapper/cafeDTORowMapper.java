package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.cafeDTO;

public class cafeDTORowMapper implements RowMapper<cafeDTO> {
	@Override
    public cafeDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		cafeDTO cafeDTO = new cafeDTO();
		
		cafeDTO.setCafeId(resultSet.getInt("id"));
		cafeDTO.setCafeName(resultSet.getString("cafe_name"));
        
        return cafeDTO;
    }
}
