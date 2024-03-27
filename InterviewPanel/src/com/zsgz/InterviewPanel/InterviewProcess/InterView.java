package com.zsgz.InterviewPanel.InterviewProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Recepsetup.RecepSetupView;

public class InterView {
	
	private InterModel interModel;
	private RecepSetupView recepSetupView;
	
	public InterView() {
		interModel = new InterModel(this);
	}

	public void proceed() {
		if(RecepSetupView.status == true) {
			interModel.initiate();
			
		}else {
			System.out.println("Add Candidates to conduct the interview!");
		}
	}

	public void showList(ArrayList<Candidate> canList, int sum, int slot) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("             Slot No." +slot );
		System.out.println("--------------------------------------------------");
		System.out.println("NAME \t"+"COLLEGE \t"+"DEPARTMENT\t");
		System.out.println("--------------------------------------------------");
//		int start = Math.abs(listCount-sum);
		for(Candidate candidate: canList) {
			if(candidate.getInterviewStatus() == false) {
				System.out.println(candidate.getName()+"\t"+candidate.getColg()+"\t"+candidate.getDept());
			}
		}
		System.out.println("--------------------------------------------------");
		System.out.println("              INTERVIEW STARTED");
		System.out.println("--------------------------------------------------");
		startInterview(canList);
	}

	private void startInterview(ArrayList<Candidate> canList) {
		RecepSetupView.status = false;
		for(Candidate candidate: canList) {
			if(candidate.getInterviewStatus() == false) {
				System.out.println("\n---------------------------------------------------------------------------------");
				System.out.println("Interview Process for '"+candidate.getName()+"\t"+candidate.getColg()
				+"\t"+candidate.getDept()+"' has been started!");
				System.out.println("----------------------------------ALL THE BEST!!!--------------------------------\n");
				candidate.setInterviewStatus(true);
				Scanner scanner = new Scanner(System.in);
				
				try {
					String line;
					BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Srigowri N\\Desktop\\InterviewPanel\\Rules.txt"));
					while((line = reader.readLine())!=null) {
						System.out.println(line);
					}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				int count = 5;
				int res = 0;
				int arr[] = new int[5];
				while(count>0) {
					try {
						String line;
						int quest;
						scanner.nextLine();
						BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Srigowri N\\Desktop\\InterviewPanel\\Read.txt"));
						System.out.print("\nChoose the question num: ");
						int curr = 1;
						try {
							quest = scanner.nextInt();
							if(validateQuestion(quest, arr)) {
								continue;
							}
						}catch(Exception e) {
							System.out.println("Please enter a number from 1 - 10");
							continue;
						}
						while((line = reader.readLine())!=null && curr < (quest)) {
							curr++;
						}
						if(curr==quest && curr!=0) {
							scanner.nextLine();
							System.out.println("-------------------------------");
							System.out.println(line);
							System.out.print("Enter your answer here: ");
							String answer = scanner.nextLine();
							
							res = interModel.checkAnswer(answer, quest);
						}else {
							System.out.println("Choose a number from 1 - 10");
							count++;
						}
						reader.close();
					} catch (Exception e) {
						System.out.println("Enter a valid input");
					}
					count--;
				}
				scanner.nextLine();
				System.out.println("\n----------------------------------------------------------------");
				System.out.println("            Interview Process is done! Thankyou :)");
				System.out.println("---------------------------Call Ended---------------------------\n");
				System.out.println("Enter the Score Points: ");
				float grade = scanner.nextFloat();
				interModel.putGrades(candidate, grade, res, canList.size());
			}
		}
	}
	
	int idx = 0;
	private boolean validateQuestion(int quest, int arr[]) {
		for(int i : arr) {
			if(i == quest) {
				System.out.println("You have already attended this question!");
				System.out.println("Choose a different question! ");
				return true;
			}
		}
		arr[idx++] = quest;	
		if(idx == arr.length) {
			Arrays.fill(arr, 0);
			idx = 0;
		}
		return false;
	}
}
