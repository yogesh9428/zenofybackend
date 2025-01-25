package com.application.backend.dto;

import lombok.Data;

@Data
public class ForgotPasswordDto {
    private String email;
    private String new_password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
    
    
}
