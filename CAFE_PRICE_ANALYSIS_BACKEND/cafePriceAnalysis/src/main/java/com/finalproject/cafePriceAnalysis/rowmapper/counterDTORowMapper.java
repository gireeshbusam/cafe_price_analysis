package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.counterDTO;

public class counterDTORowMapper implements RowMapper<counterDTO> {
	@Override
    public counterDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		counterDTO counterDTO = new counterDTO();
        
		counterDTO.setId(resultSet.getInt("id"));
		counterDTO.setCount(resultSet.getInt("counter"));
		
		return counterDTO;
    }
}
