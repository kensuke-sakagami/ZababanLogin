package com.example.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserMapper;

@Service
public class HomeService {
	@Autowired
	UserMapper mapper;
	//日付を取得する処理
	public String getDate() {
		LocalDate nowDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String orgNowDate = nowDate.format(formatter);
		return orgNowDate;
	}

	//時間を取得する処理
	public String getTime() {
		LocalTime nowTime = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String orgNowTime = nowTime.format(formatter);
		return orgNowTime;
	}

	//mapperを用いてUSERテーブルからuserName, entryTime, exitTimeを取得
	public List<User> getUsersWithTime(){
		List<User> usersWithTimeList = mapper.getUsersWithTime();
		return 	usersWithTimeList;
	}
}
