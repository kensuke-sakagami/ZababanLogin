package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	//ログイン用のGETコントローラー
	@GetMapping("/login")
	public String getLogin() {
		//login.htmlに遷移
		return "login";
	}
}
