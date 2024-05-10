package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.invertedIndexDTO;

public class invertedIndexDTORowMapper implements RowMapper<invertedIndexDTO> {
	@Override
    public invertedIndexDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		invertedIndexDTO invertedIndexDTO = new invertedIndexDTO();
		
		invertedIndexDTO.setItemName(resultSet.getString("item_name"));
		
        return invertedIndexDTO;
    }
}
