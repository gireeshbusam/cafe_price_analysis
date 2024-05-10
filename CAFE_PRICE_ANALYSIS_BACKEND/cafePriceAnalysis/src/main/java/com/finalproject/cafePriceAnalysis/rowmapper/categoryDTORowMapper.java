package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.categoryDTO;

public class categoryDTORowMapper implements RowMapper<categoryDTO> {
	@Override
    public categoryDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		categoryDTO categoryDTO = new categoryDTO();
        categoryDTO.setCatId(resultSet.getInt("id"));
        categoryDTO.setCatName(resultSet.getString("cat_name"));
        return categoryDTO;
    }
}
