package com.example.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EnterForm {
	@NotBlank
	private String companyName;//企業名
	
	@NotBlank
	private String userName;//名前
	
	@NotBlank
	private String address;//住所
	
	@NotBlank
	private Integer phoneNumber;//電話番号
	
	@NotBlank
	private String purpose;//来客目的
}
