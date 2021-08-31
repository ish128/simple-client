package client.test;


import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import client.RestTemplateConfig; 
import client.controller.QnaForm;
import lombok.extern.slf4j.Slf4j;

 

@Slf4j
public class RestTemplateConfigTest {
	
	static RestTemplate restTemplate;
	
	@BeforeAll
	static void before() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(RestTemplateConfig.class);
		restTemplate = ac.getBean(RestTemplate.class);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	@Test
	void getForEntity() {  
		ResponseEntity<QnaForm> responseEntity = restTemplate.getForEntity("http://localhost:3000/qna/{id}", QnaForm.class, 1);   
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().getId()).isEqualTo(1);
	}
	
	@Test
	void postForEntity() {   
		HttpEntity<QnaForm> request = new HttpEntity<>(
				QnaForm.builder()
				.userId(RandomString.make(5))
				.title("test title")
				.content("test content") 
				.build());
		
		QnaForm response =  restTemplate.postForObject("http://localhost:3000/qna", request, QnaForm.class); 

		assertThat(response.getId()).isNotNull();
		log.info(response.toString());
		
		ResponseEntity<QnaForm> responseEntity = restTemplate.getForEntity("http://localhost:3000/qna/{id}", QnaForm.class,response.getId());   
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().getId()).isEqualTo(response.getId());
	}
	
	@Test
	void exchangeGet() {   
		//given
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));  
		HttpEntity<?> entity = new HttpEntity<String>(headers);  
		
		//when
		ResponseEntity<QnaForm> responseEntity = restTemplate.exchange("http://localhost:3000/qna/{id}", HttpMethod.GET, entity, QnaForm.class, 1L); 
		
		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);  
		assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
	}
	
	@Test
	void exchangePost() {   
		//given
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));   
		HttpEntity<?> entity = new HttpEntity<QnaForm>(
				QnaForm.builder()
				.userId(RandomString.make(5))
				.title("test title")
				.content("test content") 
				.build(), headers);   
		
		//when
		ResponseEntity<QnaForm> responseEntity = restTemplate.exchange("http://localhost:3000/qna", HttpMethod.POST, entity, QnaForm.class); 
		
		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);  
		assertThat(responseEntity.getBody().getId()).isNotNull();
		log.info("{}", responseEntity.getBody());
	}
	 

}
