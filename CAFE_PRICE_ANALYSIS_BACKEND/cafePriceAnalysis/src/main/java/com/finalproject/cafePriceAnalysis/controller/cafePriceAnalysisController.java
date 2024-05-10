package com.finalproject.cafePriceAnalysis.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.cafePriceAnalysis.dao.cafePriceAnalysisDao;
import com.finalproject.cafePriceAnalysis.dto.addMoreItemsDTO;
import com.finalproject.cafePriceAnalysis.dto.cafeDTO;
import com.finalproject.cafePriceAnalysis.dto.cafeMenuDTO;
import com.finalproject.cafePriceAnalysis.dto.categoryDTO;
import com.finalproject.cafePriceAnalysis.dto.counterDTO;
import com.finalproject.cafePriceAnalysis.dto.feedbackDTO;
import com.finalproject.cafePriceAnalysis.dto.invertedIndexingBody;
import com.finalproject.cafePriceAnalysis.dto.selectedItemsDTO;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/rest/api")
public class cafePriceAnalysisController {

	@Autowired
	private cafePriceAnalysisDao dao;
	
	@GetMapping("/getMenu")
	public List<cafeMenuDTO> getCafeMenu() {
		return dao.getCafeMenu();
	}
	
	@GetMapping("/getCategory")
	public List<categoryDTO> getMenuCategory() {
		return dao.getMenuCategory();
	}
	
	@GetMapping("/getCafes")
	public List<cafeDTO> getCafeList() {
		return dao.getCafeList();
	}
	
	@PostMapping("/invertedIndex")
	public HashMap<String, Object> getInvertedIndex(@RequestBody invertedIndexingBody reqBody) {
		return dao.getInvertedIndex(reqBody);
	}
	
	@GetMapping("/getCount")
	public List<counterDTO> getCount() {
		return dao.getCount();
	}
	
	@PostMapping("/saveSelectedItems")
	public HashMap<String, Integer> saveSelectedItems(@RequestBody List<selectedItemsDTO> reqBody) {
		return dao.saveSelectedItems(reqBody);
	}
	
	@GetMapping("/fetchSelectedItems")
	public List<selectedItemsDTO> fetchSelectedItems(@RequestParam int userId, @RequestParam int catId) {
		return dao.fetchSelectedItems(userId, catId);
	}
	
	@GetMapping("/priceAnalysis")
	public List<selectedItemsDTO> priceAnalysis(@RequestParam int userId, @RequestParam int catId) {
		return dao.priceAnalysis(userId, catId);
	}
	
	@PostMapping("/deleteItems")
	public HashMap<String, Object> deleteSelectedItems(@RequestBody selectedItemsDTO deleteSelectedList) {
		return dao.deleteSelectedItems(deleteSelectedList);
	}
	
	@PostMapping("/addMoreItems")
	public HashMap<String, Object> selectMoreItems(@RequestBody addMoreItemsDTO addMoreItemsBody) {
		return dao.selectMoreItems(addMoreItemsBody);
	}
	
	@PostMapping("/feedback")
	public HashMap<String, Object> feedback(@RequestBody feedbackDTO feedbackBody) {
		return dao.feedback(feedbackBody);
	}
}
