package com.zsgz.InterviewPanel.Model;

public class Candidate {
	private String name;
	private String dept;
	private String colg;
	private float grade;
	private int marks;
	private float overAllMarks;
	private String result;
	private boolean interviewStatus;

	public boolean getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(boolean interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public float getOverAllMarks() {
		return overAllMarks;
	}

	public void setOverAllMarks(float overAllMarks) {
		this.overAllMarks = overAllMarks;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getColg() {
		return colg;
	}
	
	public void setColg(String colg) {
		this.colg = colg;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public int getMarks() {
		return marks;
	}
}
