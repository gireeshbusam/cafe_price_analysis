package com.finalproject.cafePriceAnalysis.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.finalproject.cafePriceAnalysis.dto.selectedItemsDTO;

public class selectedItemsRowMapper implements RowMapper<selectedItemsDTO> {
	@Override
    public selectedItemsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		selectedItemsDTO dto = new selectedItemsDTO();
		
		dto.setId(rs.getInt("id"));
		dto.setSelectionId(rs.getInt("selectionId"));
		dto.setItem_name(rs.getString("item_name"));
		dto.setItem_description(rs.getString("item_description"));
		dto.setItem_price(rs.getString("item_price"));
		dto.setCategory_id(rs.getInt("category_id"));
		dto.setCafe_id(rs.getInt("cafe_id"));
		dto.setUserId(rs.getInt("userId"));
		
        return dto;
    }
}