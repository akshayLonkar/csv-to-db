package com.csvToDb.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.csvToDb.dto.WebQuestionsDTO;
import com.csvToDb.entity.WebQuestions;

public class WebQuestionsItemProcessor implements ItemProcessor<WebQuestionsDTO, WebQuestions>{
	
	
	private static final Logger log = LoggerFactory.getLogger(WebQuestionsItemProcessor.class);


	@Override
	public WebQuestions process(WebQuestionsDTO item) throws Exception {
		log.info("processing data...");
		WebQuestions questions = new WebQuestions();
		questions.setUsername(item.getUsername());
		questions.setQuestions(item.getQuestions());
		questions.setAskedOn(item.getAskedOn());
		return questions;
	}

}
