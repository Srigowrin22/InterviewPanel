package com.zsgz.InterviewPanel.Datalayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgz.InterviewPanel.Model.Candidate;
import com.zsgz.InterviewPanel.Model.Recep;

public class PanelDatabase {

	private static PanelDatabase panelDatabase;
	
	private Candidate candidate;
	private Recep recep;
	private static ArrayList<Candidate> canList = new ArrayList();
	private ArrayList<Candidate> result = new ArrayList();
	
	private PanelDatabase() {}
	
	public static PanelDatabase getInstance() {
		if(panelDatabase ==  null) {
			panelDatabase = new PanelDatabase();
		}
		return panelDatabase;
	}
	
	public Recep getRecep() {
		return recep;
	}

	public Recep insertRecep(Recep recep) {
		this.recep = recep;
		return recep;
	}

	public boolean insertCandidate(Candidate candidate) {
		boolean hasCandidate = false;
		for (Candidate can : canList) {
			if (candidate.getName().equals(can.getName()) && candidate.getColg().equals(can.getColg()) && 
					candidate.getDept().equals(can.getDept())) {
				hasCandidate = true;
				break;
			}
		}
		if (hasCandidate) {
			return false;
		} else {
			canList.add(candidate);	
			return true;
		}
	}

	public ArrayList<Candidate> getAllCandidates(){
		return canList;
	}

	public void insertResult(Candidate candidate2) {
		result.add(candidate2);
	}
	
	public ArrayList<Candidate> getResult(){
		return result;
	}
	
	public void getDataFromJSON() {
		ArrayList<Candidate> canList = PanelDatabase.getInstance().getAllCandidates();
		ObjectMapper mapper = new ObjectMapper();
		try {
			canList.addAll(mapper.readValue(new File("C:\\Users\\Srigowri N\\Desktop\\InterviewPanel\\CanList.json"), new TypeReference<List<Candidate>>() {}));	
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDataToJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonObject1 = mapper.writeValueAsString(canList);
			mapper.writeValue(new File("C:\\Users\\Srigowri N\\Desktop\\InterviewPanel\\CanList.json"), canList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
