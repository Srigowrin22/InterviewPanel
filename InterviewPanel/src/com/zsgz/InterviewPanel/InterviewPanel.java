package com.zsgz.InterviewPanel;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgz.InterviewPanel.Datalayer.PanelDatabase;
import com.zsgz.InterviewPanel.Login.LoginView;
import com.zsgz.InterviewPanel.Model.Candidate;

public class InterviewPanel {

	private static InterviewPanel interviewPanel;
	//private PanelDatabase panelDatabase;
	private String panelName = "Zoho";
	private String panelRole = "Software Developement";
	
	public String getPanelName() {
		return panelName;
	}
	
	public String getPanelRole() {
		return panelRole;
	}
	
	private InterviewPanel(){
//		Object cannot be created from other class
	}
	
	public static InterviewPanel getInstance() {
//		Whenever you refer to the InterviewPanel Object you need to check if the object is referring to any address already!
		if(interviewPanel == null) {
			interviewPanel = new InterviewPanel();
		}
		return interviewPanel;
	}
	
	public static void create() {
		LoginView loginView = new LoginView();
		loginView.init();
	}
		
	public static void main(String[] args) throws IOException {
		PanelDatabase.getInstance().getDataFromJSON();	
		InterviewPanel.getInstance().create();
		PanelDatabase.getInstance().setDataToJSON();
	}
}
