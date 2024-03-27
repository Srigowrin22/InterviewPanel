package com.zsgz.InterviewPanel.InterviewProcess;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.zsgz.InterviewPanel.Datalayer.PanelDatabase;
import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Model.Recep;
import com.zsgz.InterviewPanel.Recepsetup.RecepSetupModel;
import com.zsgz.InterviewPanel.Recepsetup.RecepSetupView;

public class InterModel {

	static int slot = 0, c = 0, count=0;
	int sum = 0, totalMarks = 0, ans = 0;
	
	private InterView interView;
	private RecepSetupView recepSetupView;
	private RecepSetupModel recepSetupModel;
	private Recep recep;
	
	public InterModel(InterView interView) {
		this.interView = interView;
	}
	
	public void initiate() {
		ArrayList<Candidate> canList = PanelDatabase.getInstance().getAllCandidates();
		slot++;
		sum = canList.size();
		interView.showList(canList, sum, slot);
	}
	
	public int checkAnswer(String answer, int n) {
		String line;
		int curr = 1;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Srigowri N\\Desktop\\InterviewPanel\\ReadAnswer.txt"));
			while((line = reader.readLine())!=null && curr < n) {
				curr++;
			}
			if(curr == n) {
				c++;
				if(line.equalsIgnoreCase(answer)) {
					ans++;
				}
			}
			if(c == 5 ) {
				c = 0;
				totalMarks = ans;
				ans = 0;
				return totalMarks;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void putGrades(Candidate candidate, float grade, int res, int size) {
		float total = (grade*res)/5;
		candidate.setGrade(grade);
		candidate.setMarks(res);
		candidate.setOverAllMarks(total);
		if(total>7.5) {
			candidate.setResult("SELECTED");
		}else {
			candidate.setResult("REJECTED");
		}
		PanelDatabase.getInstance().insertResult(candidate);
		count++;
		if(count == size) {
			System.out.println("^^^^^^^^^^^^^^ Interview for the current slot is finished! ^^^^^^^^^^^^^^");
		}
	}
}
