package client.controller;
 
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QnaForm { 
	private Long id;
	private Long parentId;
	
	private String userId;
	private String title;
	private String content;
	
	private Gender gender;
	private Language[] language;
	private Fruit[] fruit;  
	
	@JsonIgnore
	private List<MultipartFile> attachFile; 
}
