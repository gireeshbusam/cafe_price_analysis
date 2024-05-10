package com.finalproject.cafePriceAnalysis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.finalproject.cafePriceAnalysis.dto.EmpDetailsDTO;
import com.finalproject.cafePriceAnalysis.rowmapper.loginRowMapper;

@Repository
public class LoginService {
	@Autowired
    private JdbcTemplate jdbc;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public LoginService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public HashMap<String, Object> register(EmpDetailsDTO reqBody) {
		HashMap<String, Object> registMessage = new HashMap<String, Object>();
		int update = 0;
		List<EmpDetailsDTO> userDetails = new ArrayList<EmpDetailsDTO>();
		
		String sql = "INSERT INTO t_usermst( `empid`, `password` ) VALUES ( ?, ? );";
		String getUserId = "select * from t_usermst u where u.empid = ?;";
		
		userDetails = jdbc.query(getUserId, new loginRowMapper(), new Object[] {reqBody.getEmpId()});
		
		if(userDetails.size() > 0) {
			registMessage.put("responseMessage", "Userid already exists.");
			registMessage.put("responseCode", 2000);
			return registMessage;
		}
		
		String password = passwordEncoder.encode(reqBody.getPassword());
		
		update = jdbc.update(sql, reqBody.getEmpId(), password);
		
		if(update > 0) {
			registMessage.put("responseMessage", "Registered Successfully.");
			registMessage.put("responseCode", 1000);
		} else {
			registMessage.put("responseMessage", "Error.");
			registMessage.put("responseCode", 9999);
		}
		
		return registMessage;
	}
	
	public HashMap<String, Object> login(EmpDetailsDTO reqBody) {
		HashMap<String, Object> loginResponse = new HashMap<String, Object>();
		
		int updateFetch = 0;
		List<EmpDetailsDTO> empDetails = new ArrayList<EmpDetailsDTO>();
		
		String sql = "select u.empid, u.password from t_usermst u where u.empid = ?;";
		
		empDetails = jdbc.query(sql, new loginRowMapper(), new Object[] {reqBody.getEmpId()});
		
		if(empDetails.size() > 0) {
			String username = empDetails.get(0).getEmpId();
			String password = empDetails.get(0).getPassword();
			
			String encodedPassword = passwordEncoder.encode(password);
			String enteredPassword = passwordEncoder.encode(reqBody.getPassword());
			
			if(passwordEncoder.matches(reqBody.getPassword(), empDetails.get(0).getPassword())) {
				loginResponse.put("responseMessage", "Success");
				loginResponse.put("responseCode", 1000);
			} else {
				loginResponse.put("responseMessage", "Invalid Credentials");
				loginResponse.put("responseCode", 9999);
			}
		} else {
			loginResponse.put("responseMessage", "Invalid Credentials");
			loginResponse.put("responseCode", 8888);
		}		
		
		return loginResponse;
	}
	
	public HashMap<String, Object> resetPassword(EmpDetailsDTO reqBody) {
		HashMap<String, Object> resetResponse = new HashMap<String, Object>();
		int update = 0;
		
		List<EmpDetailsDTO> userDetails = new ArrayList<EmpDetailsDTO>();
		String getUserId = "select * from t_usermst u where u.empid = ?;";
		String resetPass = "update t_usermst u set u.password = ? where u.empid = ?;";
		
		userDetails = jdbc.query(getUserId, new loginRowMapper(), new Object[] {reqBody.getEmpId()});
		
		if(userDetails.size() == 0) {
			resetResponse.put("responseMessage", "Userid doesnt exist. Please create a new profile by Registering.");
			resetResponse.put("responseCode", 2000);
			return resetResponse;
		}
		
		String password = passwordEncoder.encode(reqBody.getPassword());
		
		update = jdbc.update(resetPass, password, reqBody.getEmpId());
		
		if(update > 0) {
			resetResponse.put("responseMessage", "Password has been Reset Successfully.");
			resetResponse.put("responseCode", 1000);
		} else {
			resetResponse.put("responseMessage", "Error.");
			resetResponse.put("responseCode", 9999);
		}
		
		return resetResponse;
	}
}
