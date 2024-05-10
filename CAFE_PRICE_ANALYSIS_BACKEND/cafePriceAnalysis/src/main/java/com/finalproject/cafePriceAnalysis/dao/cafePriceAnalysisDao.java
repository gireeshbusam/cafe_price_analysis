package com.finalproject.cafePriceAnalysis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.finalproject.cafePriceAnalysis.common.InvertedIndexing;
import com.finalproject.cafePriceAnalysis.common.Trie;
import com.finalproject.cafePriceAnalysis.common.ValidateUtil;
import com.finalproject.cafePriceAnalysis.dto.addMoreItemsDTO;
import com.finalproject.cafePriceAnalysis.dto.cafeDTO;
import com.finalproject.cafePriceAnalysis.dto.cafeMenuDTO;
import com.finalproject.cafePriceAnalysis.dto.categoryDTO;
import com.finalproject.cafePriceAnalysis.dto.counterDTO;
import com.finalproject.cafePriceAnalysis.dto.feedbackDTO;
import com.finalproject.cafePriceAnalysis.dto.invertedIndexDTO;
import com.finalproject.cafePriceAnalysis.dto.invertedIndexingBody;
import com.finalproject.cafePriceAnalysis.dto.selectedItemsDTO;
import com.finalproject.cafePriceAnalysis.rowmapper.cafeDTORowMapper;
import com.finalproject.cafePriceAnalysis.rowmapper.cafeMenuDTORowMapper;
import com.finalproject.cafePriceAnalysis.rowmapper.categoryDTORowMapper;
import com.finalproject.cafePriceAnalysis.rowmapper.counterDTORowMapper;
import com.finalproject.cafePriceAnalysis.rowmapper.selectedItemsRowMapper;

@Repository
public class cafePriceAnalysisDao {
	@Autowired
    private JdbcTemplate jdbc;
	//private ValidateUtil valdiate;
	
	public List<cafeMenuDTO> getCafeMenu() {
		String sql = "SELECT * FROM t_cafe_menu_mst";
		return jdbc.query(sql, new cafeMenuDTORowMapper());
	}
	
	public List<categoryDTO> getMenuCategory() {
		String sql = "select * from t_item_category_mst;";
		return jdbc.query(sql, new categoryDTORowMapper());
	}
	
	public List<cafeDTO> getCafeList() {
		String sql = "select * from t_cafe_mst;";
		return jdbc.query(sql, new cafeDTORowMapper());
	}
	
	public HashMap<String, Object> getInvertedIndex(invertedIndexingBody reqBody) {
		HashMap<String, Object> invertIndex = new HashMap<String, Object>();
		ValidateUtil valdiate = new ValidateUtil();
		if(valdiate.isEmptyString(reqBody.getKey())) {
			invertIndex.put("responseMessage", "Please enter an item");
			invertIndex.put("responseCode", 8888);
		} else {
			Trie trie = new Trie();
			InvertedIndexing invertedIndexing = new InvertedIndexing();
			List<cafeMenuDTO> cafeMenuList = reqBody.getCafeMenuList();
			String key = reqBody.getKey().toLowerCase();
			List<cafeDTO> cafeList = new ArrayList<cafeDTO>();
			String arr[];
			
			for(int i = 0; i < cafeMenuList.size(); i++) {
				invertedIndexing.populateTrie(trie, cafeMenuList.get(i).getId(), cafeMenuList.get(i).getItemName().toLowerCase(), String.valueOf(cafeMenuList.get(i).getCafeId()), cafeMenuList.size(), cafeMenuList.get(i).getCafeId(), key);
			}
			
			List<invertedIndexDTO> files = new ArrayList<invertedIndexDTO>();
			invertedIndexDTO invertedIndexDTO = new invertedIndexDTO();
			String value = invertedIndexing.getFileName(trie, key);
			
			if(value == null) {
				invertIndex.put("responseMessage", "No Menu Items found with this name. Please try again with a different name.");
				invertIndex.put("responseCode", 2000);
				invertIndex.put("files", files);
				return invertIndex;
			}
			
			HashSet<String> cafe = new HashSet<String>();
			int itemId = invertedIndexing.getItemId(trie, key);
			
			cafeList = getCafeList();
			arr = value.split(",");
			
			for(int i = 0; i < cafeList.size(); i++) {
				for(int j = 0; j < arr.length; j++) {
					if(String.valueOf(cafeList.get(i).getCafeId()).equals(arr[j].trim())) {
						if(cafeList.get(i).getCafeId() == 1) {
							cafe.add(cafeList.get(i).getCafeName());
						} else if(cafeList.get(i).getCafeId() == 2) {
							cafe.add(cafeList.get(i).getCafeName());
						} else if(cafeList.get(i).getCafeId() == 3) {
							cafe.add(cafeList.get(i).getCafeName());
						}
					}
				}
			}
			invertedIndexDTO.setItemId(itemId);
			
			for(String cafeName: cafe) {
				invertedIndexDTO.setItemName(cafeName);
			}		
			
			files.add(invertedIndexDTO);
			invertIndex.put("responseMessage", "Success");
			invertIndex.put("responseCode", 1000);
			invertIndex.put("files", files);
		}		
		
		return invertIndex;
	}
	
	public List<counterDTO> getCount() {
		String sql = "select ic.id, ic.counter from t_id_counter ic order by last_updated desc limit 1;";
		
		return jdbc.query(sql, new counterDTORowMapper());
	}
	
	public HashMap<String, Integer> saveSelectedItems(List<selectedItemsDTO> selectedItemsList) {
		List<counterDTO> counter = new ArrayList<counterDTO>();
		HashMap<String, Integer> saveMessage = new HashMap<String,Integer>();
		int update = 0;
		int updateCounter = 0;
		
		String insertSQL = "INSERT INTO t_selected_items ( `selectionId`, `item_name`, `item_description`, `item_price`, `category_id`, `cafe_id`, `userId` ) VALUES ( ?, ?, ?, ?, ?, ?, ? );";
		String insertCounter = "INSERT INTO t_id_counter ( `counter` ) VALUES ( ? );";
		int id = 0;
		counter = getCount();
		
		for(int i = 0; i < counter.size(); i++) {
			id = counter.get(i).getCount();
		}
		
		for(int i = 0; i < selectedItemsList.size(); i++) {
			update = jdbc.update(insertSQL, (id + 1), selectedItemsList.get(i).getItem_name(), 
					selectedItemsList.get(i).getItem_description(), 
					selectedItemsList.get(i).getItem_price(), 
					selectedItemsList.get(i).getCategory_id(), 
					selectedItemsList.get(i).getCafe_id(),
					selectedItemsList.get(i).getUserId());
		}
		
		if(update > 0) {
			updateCounter = jdbc.update(insertCounter, (id + 1));
		}
		
		if(update > 0 && updateCounter > 0) {
			saveMessage.put("Success", 1000);
		} else {
			saveMessage.put("Error", 9999);
		}
		
		return saveMessage;
	}
	
	public List<selectedItemsDTO> fetchSelectedItems(int userId, int catId) {
		List<selectedItemsDTO> selectedItemsList = new ArrayList<selectedItemsDTO>();
		
		String sql = "select * from t_selected_items si where si.userId = ? and si.category_id = ? and si.active_flag = 'Y';";
		
		selectedItemsList = jdbc.query(sql, new selectedItemsRowMapper(), new Object[] {userId, catId});
		
		//priceAnalysis(selectedItemsList);
		
		return selectedItemsList;
	}
	
	public List<selectedItemsDTO> priceAnalysis(int userId, int catId) {
		String sql = "SELECT * FROM t_selected_items t WHERE CAST(SUBSTRING(item_price, 2) AS DECIMAL(10, 2)) = ( SELECT MIN(CAST(SUBSTRING(item_price, 2) AS DECIMAL(10, 2))) FROM t_selected_items st where st.category_id = t.category_id and st.active_flag = 'Y' and st.userId = t.userId) and t.category_id = ? and t.userId = ? and t.active_flag = 'Y';";
		List<selectedItemsDTO> priceAnalysisList = new ArrayList<selectedItemsDTO>();
		
		priceAnalysisList = jdbc.query(sql, new selectedItemsRowMapper(), new Object[] {catId, userId});
		
 		return priceAnalysisList;
	}
	
	public HashMap<String, Object> deleteSelectedItems(selectedItemsDTO deleteSelectedList) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		int update = 0;
		
		String sql = "UPDATE t_selected_items st SET st.active_flag = 'N' where st.active_flag = 'Y' and st.selectionId = ? and st.id = ? and st.userId = ?;";
		
		update = jdbc.update(sql, deleteSelectedList.getSelectionId(), deleteSelectedList.getId(), deleteSelectedList.getUserId());
		
		if(update > 0) {
			response.put("responseMessage", "Items removed Successfully.");
			response.put("responseCode", 1000);
		} else {
			response.put("responseMessage", "Error removing items.");
			response.put("responseCode", 9999);
		}
		
		return response;
	}
	
	public HashMap<String, Object> selectMoreItems(addMoreItemsDTO addMoreItemsBody) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<cafeMenuDTO> cm = new ArrayList<cafeMenuDTO>();
		String fetchMenuItem = "select * from t_cafe_menu_mst cmm where cmm.id = ?;";
		String insertSQL = "INSERT INTO t_selected_items ( `selectionId`, `item_name`, `item_description`, `item_price`, `category_id`, `cafe_id`, `userId` ) VALUES ( ?, ?, ?, ?, ?, ?, ? );";
		String insertCounter = "INSERT INTO t_id_counter ( `counter` ) VALUES ( ? );";
		
		List<counterDTO> counter = new ArrayList<counterDTO>();
		
		int update = 0;
		int updateCounter = 0;
		
		cm = jdbc.query(fetchMenuItem, new cafeMenuDTORowMapper(), new Object[] {addMoreItemsBody.getItemId()});
		
		int id = 0;
		counter = getCount();
		
		for(int i = 0; i < counter.size(); i++) {
			id = counter.get(i).getCount();
		}
		
		for(int i = 0; i < cm.size(); i++) {
			update = jdbc.update(insertSQL, (id + 1), cm.get(i).getItemName(), 
					cm.get(i).getItemDescription(), 
					cm.get(i).getItemPrice(), 
					cm.get(i).getCategoryId(), 
					cm.get(i).getCafeId(),
					addMoreItemsBody.getUserId());
		}
		
		if(update > 0) {
			updateCounter = jdbc.update(insertCounter, (id + 1));
		}
		
		if(update > 0 && updateCounter > 0) {
			response.put("responseMessage", "Items added Successfully.");
			response.put("responseCode", 1000);
		} else {
			response.put("responseMessage", "Error adding items.");
			response.put("responseCode", 9999);
		}
		
		return response;
	}
	
	public HashMap<String, Object> feedback(feedbackDTO feedbackBody) {
		HashMap<String, Object> feedbackResponse = new HashMap<String, Object>();
		int update = 0;
		String sql = "INSERT INTO t_feedback ( `userid`, `dashboard_rating`, `analysis_rating`, `comments` ) VALUES ( ?, ?, ?, ? );";
		
		update = jdbc.update(sql, feedbackBody.getUserid(), feedbackBody.getDashboard_rating(), feedbackBody.getAnalysis_rating(), feedbackBody.getComments());
		
		if(update > 0) {
			feedbackResponse.put("responseMessage", "Feedback saved Successfully.");
			feedbackResponse.put("responseCode", 1000);
		} else {
			feedbackResponse.put("responseMessage", "Error saving Feedback.");
			feedbackResponse.put("responseCode", 9999);
		}
		
		return feedbackResponse;
	}
}

