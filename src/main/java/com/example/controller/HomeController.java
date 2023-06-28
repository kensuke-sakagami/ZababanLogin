package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.form.EnterForm;
import com.example.model.User;
import com.example.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	HomeService service;
	//ホーム用のGETコントローラー
	@GetMapping("/home")
	public String getHome(@ModelAttribute EnterForm form, Model model) {
		//日付を取得しmodelへ格納
		String nowDate = service.getDate();
		model.addAttribute("nowDate", nowDate);

		//時間を取得しmodelへ格納
		String nowTime = service.getTime();
		model.addAttribute("nowTime", nowTime);
		
		////USERテーブルからuserName, entryTime, exitTimeを取得しmodelへ格納
		List<User> usersWithTimeList = service.getUsersWithTime();
		model.addAttribute("usersWithTimeList", usersWithTimeList);
		
		//home.htmlに遷移
		return "home";
	}
}
