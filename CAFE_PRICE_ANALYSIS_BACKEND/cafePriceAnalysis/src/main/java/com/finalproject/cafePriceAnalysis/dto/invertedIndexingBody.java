package com.finalproject.cafePriceAnalysis.dto;

import java.util.List;

public class invertedIndexingBody {
	List<cafeMenuDTO> cafeMenuList;
	String key;
	public List<cafeMenuDTO> getCafeMenuList() {
		return cafeMenuList;
	}
	public void setCafeMenuList(List<cafeMenuDTO> cafeMenuList) {
		this.cafeMenuList = cafeMenuList;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
