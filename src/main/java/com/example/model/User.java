package com.example.model;

import java.text.SimpleDateFormat;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {
	private int userId;
	private String companyName;
	private String userName;
	private String address;
	private String phoneNumber;
	private String purpose;
	private String entryTime;
	private String exitTime;
	private int userStatus;

	public void setEntryTime(Timestamp entryTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		this.entryTime = sdf.format(entryTime);
	}
	
	public void setExitTime(Timestamp exitTime) {
	    if (exitTime != null) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        this.exitTime = sdf.format(exitTime);
	    } else {
	        this.exitTime = null;
	    }
	}

	public String getExitTime() {
	    if (exitTime == null) {
	        return "-";
	    } else {
	        return exitTime;
	    }
	}
}