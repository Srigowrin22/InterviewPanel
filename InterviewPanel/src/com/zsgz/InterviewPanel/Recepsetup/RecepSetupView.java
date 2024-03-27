package com.zsgz.InterviewPanel.Recepsetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.zsgz.InterviewPanel.InterviewPanel;
import com.zsgz.InterviewPanel.Datalayer.PanelDatabase;
import com.zsgz.InterviewPanel.InterviewProcess.InterView;
import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Model.Recep;

public class RecepSetupView {
	
	public static boolean status = false;
	int choice = 0;
	
	private RecepSetupModel recepSetupModel;
	
	public RecepSetupView() {
		recepSetupModel = new RecepSetupModel(this);
	}

	public void init() {
		recepSetupModel.startSetup();
	}

	public void showAlert(String alertText) {
		System.out.println(alertText);
	}
	
	public void initiateSetup() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the receptionist name: ");
		String name = scanner.nextLine();
		System.out.println("Enter the receptionists email: ");
		String email = scanner.nextLine();
//		Add the Receptionist's Database
		if(recepSetupModel.createRecep(name, email)) {
			System.out.println("\n(Mail the login credentials to his/her mail Id)");
			System.out.print("Enter the access code here -> ");
			String password = scanner.next();
			recepSetupModel.provideCredentials(password);
		}else {
			initiateSetup();
		}	
	}

	public void onSetupComplete(Recep recep) {
		System.out.println("\nHanding over the control to the Receptionist!");
		System.out.println("Receptionist: '"+ recep.getName()+ "' is now eligible to do the operations!");
		Scanner scanner = new Scanner(System.in);
		int listCount = 0;
		while(true) {
			System.out.println("\nPANEL OPERATION: ");
			System.out.println("----------------");
			System.out.println("1.Add Candidates \n2.Conduct Interview \n3.View All Candidates \n4.View Result \n5.EXIT");
			System.out.println("Enter Your Choice: ");
			int ch = scanner.nextInt();
			switch(ch) {
				case 1: addCandidates();
						status = true;
						break;
				case 2: new InterView().proceed();
						break;
				case 3: viewAllCandidates();
						break;
				case 4: viewAllResults();
						break;
				case 5: System.out.println("***CANDIDATE SELECTION PROCESS IS COMPLETED! THANK YOU!***");
						return;
				default: System.out.println("Choose Valid option :)");		
			}
		}
	}

	private void viewAllResults() {
		ArrayList<Candidate> result = PanelDatabase.getInstance().getAllCandidates();
		System.out.println("NAME"+ "\tCOLLEGE"+ "\tDEPARTMENT"+ "\tGRADES"+ "\tMARKS-SCORED"+ "\tRESULT");
		System.out.println("-----------------------------------------------------------------------------------");
		for(Candidate candidate : result) {
			System.out.println(candidate.getName()+"\t"+candidate.getColg()+"\t"+candidate.getDept()+"\t"+candidate.getGrade()+"\t"+candidate.getMarks()+"\t"+candidate.getResult());
		}
		System.out.println("-----------------------------------------------------------------------------------\n");		
	}

	private void viewAllCandidates() {
		ArrayList<Candidate> canList = PanelDatabase.getInstance().getAllCandidates();
		System.out.println("NAME"+"\t"+"COLLEGE"+"\t"+"DEPARTMENT");
		System.out.println("------------------------------------------------");
		for(Candidate candidate : canList) {
			System.out.println(candidate.getName()+"\t"+candidate.getColg()+"\t"+candidate.getDept()+"\t");
		}
		System.out.println("------------------------------------------------\n");
	}

	private void addCandidates() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many candidates do you want to add? ");
		try {
			choice = scanner.nextInt();
		}catch(Exception e) {
			System.out.println("Please Enter the number of candidates");
			addCandidates();
		}
		int n = 0;
		while(choice>0) {
			scanner.nextLine();
			System.out.println("\nCandidate - "+ (++n));
			System.out.println("-----------------");
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Department: ");
			String dept = scanner.nextLine();
			System.out.print("College: ");
			String colg = scanner.nextLine();
			recepSetupModel.addCandidate(name, dept, colg);
			choice--;
		}
	}
}
