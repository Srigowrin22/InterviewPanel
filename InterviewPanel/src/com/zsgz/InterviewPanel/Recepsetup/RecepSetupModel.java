package com.zsgz.InterviewPanel.Recepsetup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zsgz.InterviewPanel.Datalayer.PanelDatabase;
import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Model.Recep;

public class RecepSetupModel {

	private Recep recep;
	private RecepSetupView recepSetupView;
	
//	private String password = "recep.zoho.18";
	
	RecepSetupModel(RecepSetupView recepSetupView) {
		this.recepSetupView = recepSetupView;
		PanelDatabase.getInstance().getRecep();
	}

	public void startSetup() {
		if(recep == null) {
			this.recep = new Recep();
			recepSetupView.initiateSetup();
		}else {
			recepSetupView.onSetupComplete(recep);
		}
	}

	public void provideCredentials(String password) {
		recep.setPassword(password);
		recepSetupView.onSetupComplete(recep);
	}

	public boolean createRecep(String name, String email) {
		this.recep.setName(name);
		this.recep.setEmail(email);
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);        
        Matcher matcher = pattern.matcher(email);
        
		if (recep.getName().length() > 3 && (recep.getEmail().length() < 50 && matcher.matches())) {
			this.recep = PanelDatabase.getInstance().insertRecep(recep);
			return true;
		}else {
			recepSetupView.showAlert("Enter Valid Receptionist Name and Email");
			return false;
		}
	}

	public void addCandidate(String name, String dept, String colg) {
		Candidate candidate = new Candidate();
		candidate.setName(name);
		candidate.setDept(dept);
		candidate.setColg(colg);
		candidate.setInterviewStatus(false);
		boolean hasCandidate = PanelDatabase.getInstance().insertCandidate(candidate);
		if(!hasCandidate) {
			recepSetupView.showAlert("Sorry! This Candidate has already finished his/her interview! \n" );
		}
	}
}
