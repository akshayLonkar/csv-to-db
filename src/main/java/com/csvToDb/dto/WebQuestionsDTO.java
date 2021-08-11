package com.csvToDb.dto;

public class WebQuestionsDTO {
	
	private String questions;
	private String username;
	private String askedOn;
	
	public WebQuestionsDTO() {}
	
	public WebQuestionsDTO(String questions, String username, String askedOn) {
		super();
		this.questions = questions;
		this.username = username;
		this.askedOn = askedOn;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAskedOn() {
		return askedOn;
	}

	public void setAskedOn(String askedOn) {
		this.askedOn = askedOn;
	}

}
