package com.zsgz.InterviewPanel.Login;

public class LoginModel {

	private LoginView loginView;
	
	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void validateUser(String name, String emailId, String password) {
		if(validateName(name)) {
			if(validateEmail(emailId)) {
				if(validatePassword(password)) {
					loginView.onLoginSuccess();
				}else {
					loginView.onLoginFailed("Invalid password");
				}
			}else {
				loginView.onLoginFailed("Invalid email address");
			}
		}else {
			loginView.onLoginFailed("Invalid user name");
		}	
	}
	
	public boolean validateName(String name) {
		return (name.equalsIgnoreCase("ZSGS"));
	}
	
	public boolean validateEmail(String emailId) {
		return (emailId.equals("buzz@zoho.corp"));
	}
	
	public boolean validatePassword(String password) {
		return (password.equals("buzz.18.zoho"));
	}
}
