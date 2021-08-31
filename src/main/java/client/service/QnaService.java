package client.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import client.controller.FileInfo;
import client.controller.Qna;
import client.controller.QnaForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class QnaService {

	
	private final RestTemplate restTemplate; 

	public Qna saveQna(QnaForm qnaForm) {   
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>(); 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);//Main request's headers   
		
		qnaForm.getAttachFile()
			.stream()
			.filter(Objects::nonNull)
			.forEach(f-> body.add("file", f.getResource()));   

		HttpHeaders headersJSON = new HttpHeaders();
		headersJSON.setContentType(MediaType.APPLICATION_JSON); 
		HttpEntity<QnaForm> entityJSON = new HttpEntity<>(qnaForm, headersJSON); 
		body.add("qna",entityJSON); 

		HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers); 
		ResponseEntity<Qna> responseEntity = restTemplate.exchange("http://localhost:8080/client/rest/qna",HttpMethod.POST,requestEntity,Qna.class);
		
		if(responseEntity.getStatusCode() != HttpStatus.CREATED) {
			throw new RuntimeException("failed created!");
		}
		return responseEntity.getBody();
	} 
	
	public QnaForm saveQnaOrg(QnaForm qna) {    
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));   
		HttpEntity<?> entity = new HttpEntity<QnaForm>(qna, headers);    
		ResponseEntity<QnaForm> responseEntity = restTemplate.exchange("http://localhost:8080/client/rest/qna", HttpMethod.POST, entity, QnaForm.class);  
		
		if(responseEntity.getStatusCode() != HttpStatus.CREATED) {
			throw new RuntimeException("failed created!");
		}
		return responseEntity.getBody();
	} 
}
