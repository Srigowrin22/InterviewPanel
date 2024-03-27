package com.zsgz.InterviewPanel.Login;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgz.InterviewPanel.InterviewPanel;
import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Recepsetup.RecepSetupModel;
import com.zsgz.InterviewPanel.Recepsetup.RecepSetupView;

public class LoginView {

	private LoginModel loginModel;
	
	public LoginView() {
		loginModel = new LoginModel(this);
	}
	
	public void init() {
		System.out.println("-------------INTERVIEW PANEL--------------");
		System.out.println("Welcome to the interview panel of '" 
		+ InterviewPanel.getInstance().getPanelName()+ "' \n for the role '" 
				+ InterviewPanel.getInstance().getPanelRole()+ "'");
		System.out.println("------------------------------------------\n");
		System.out.println("Login to join the panel! \n");
		proceedToLogin();
	}
	
	private void proceedToLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello Panel Team! Nice to see you help us find talented candidates :)");
		System.out.println("Please Enter Your Name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Your EmailId: ");
		String emailId = scanner.nextLine();
		System.out.println("Enter your login password: ");
		String password = scanner.next();
		loginModel.validateUser(name,emailId,password);
	}

	public void onLoginSuccess() {
		System.out.println("\n    *-*-*-*-*-Login Successful!-*-*-*-*-*\n");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you scheduling the interview now? Yes/No");
		String choice = scanner.next();
		if(choice.equalsIgnoreCase("YES")) {
			System.out.println("\nPlease Enter The Receptionist Details: ");
			RecepSetupView recepSetupView = new RecepSetupView();
			recepSetupView.init();
		}else if(choice.equalsIgnoreCase("NO")) {
			System.out.println("Looks like you dont want to schedule the interview!");
			System.out.println("Login when you want to schedule one :) Thank You!");
			init();
		}else {
			System.out.println("Invalid Choice! Hence you have been logged out! ");
			checkForLogin();
		}
	}
	
	int count=0;
	public void onLoginFailed(String alertText) {
		System.out.println(alertText);
		count++;
		if(count<=3) {
			checkForLogin();
		}else {
			System.out.println("Sorry! You have lost all your trials! Please try after sometime");
		}
	}

	private void checkForLogin() {
		System.out.println("Do you want to try again? Yes/No: ");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.next();
		if(choice.equalsIgnoreCase("YES")) {
			proceedToLogin();
		}else if(choice.equalsIgnoreCase("No")) {
			System.out.println("Thank YOU! See You Soon!");
		}else {
			System.out.println("Invalid Choice! Enter a valid choice!");
			checkForLogin();
		}
	}
}
