package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.cafeMenuDTO;

public class cafeMenuDTORowMapper implements RowMapper<cafeMenuDTO> {
	@Override
    public cafeMenuDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		cafeMenuDTO cafeMenuDTO = new cafeMenuDTO();
		
		
		cafeMenuDTO.setId(resultSet.getInt("id"));
		cafeMenuDTO.setItemName(resultSet.getString("item_name"));
		cafeMenuDTO.setItemDescription(resultSet.getString("item_description"));
		cafeMenuDTO.setItemPrice(resultSet.getString("item_price"));
		cafeMenuDTO.setCategoryId(resultSet.getInt("category_id"));
		cafeMenuDTO.setCafeId(resultSet.getInt("cafe_id"));
		
        return cafeMenuDTO;
    }
}
