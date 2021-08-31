package client.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

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
public class Qna {
	
	private Long id;
	private Long parentId;
	
	private String userId;
	private String title;
	private String content;
	
	private Gender gender;
	private Language[] language;
	private Fruit[] fruit; 
	private List<FileInfo> attachFiles;
	
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;  
	
}
