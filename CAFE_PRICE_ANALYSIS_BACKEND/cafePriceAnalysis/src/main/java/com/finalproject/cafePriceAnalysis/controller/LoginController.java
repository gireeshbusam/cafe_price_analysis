package com.finalproject.cafePriceAnalysis.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.cafePriceAnalysis.dto.EmpDetailsDTO;
import com.finalproject.cafePriceAnalysis.service.LoginService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/rest/api")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@PostMapping("/register")
	public HashMap<String, Object> register(@RequestBody EmpDetailsDTO reqBody) {
		return service.register(reqBody);
	}
	
	@PostMapping("/login")
	public HashMap<String, Object> login(@RequestBody EmpDetailsDTO reqBody) {
		return service.login(reqBody);
	}
	
	@PostMapping("/resetPassword")
	public HashMap<String, Object> resetPassword(@RequestBody EmpDetailsDTO reqBody) {
		return service.resetPassword(reqBody);
	}
	
}
