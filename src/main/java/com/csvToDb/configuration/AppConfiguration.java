package com.csvToDb.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.csvToDb.dto.WebQuestionsDTO;
import com.csvToDb.entity.WebQuestions;
import com.csvToDb.listener.JobCompletionNotificationListener;
import com.csvToDb.processor.WebQuestionsItemProcessor;

@Configuration
@EnableBatchProcessing
public class AppConfiguration {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

	
	@Bean
    public FlatFileItemReader<WebQuestionsDTO> reader() {
        return new FlatFileItemReaderBuilder<WebQuestionsDTO>().name("webQuestionsReader")
                .resource(new ClassPathResource("csv-to-db.csv")).delimited()
                .names(new String[] {"questions", "username", "askedOn"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<WebQuestionsDTO>() {
                    {
                        setTargetType(WebQuestionsDTO.class);
                    }
                }).build();
    }
	
	@Bean
	public MongoItemWriter<WebQuestions> writer(MongoTemplate template){
		return new MongoItemWriterBuilder<WebQuestions>().template(template).collection("webQuestions").build();
	}
	
	@Bean
	public WebQuestionsItemProcessor function() {
		return new WebQuestionsItemProcessor();
	}
	
	@Bean
	public Step step(FlatFileItemReader<WebQuestionsDTO> reader, MongoItemWriter<WebQuestions> writer) {
		return this.stepBuilderFactory.get("csv-to-db-step").<WebQuestionsDTO, WebQuestions>chunk(5)
				.reader(reader)
				.processor(function())
				.writer(writer)
				.build();
	}
	
	@Bean
	public Job csvToDbJob(JobCompletionNotificationListener listener, Step step) {
		return this.jobBuilderFactory.get("csv-to-db-job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(step)
				.build();
	}
	
}
