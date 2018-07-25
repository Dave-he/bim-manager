package com.mjwise.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
	@GetMapping("/login")
	public String index(){
		return "login";
	}

	@GetMapping("/home")
	public String home(){
		return "home";
	}

	@GetMapping("/register")
	public String register(){
		return "register";
	}

}
