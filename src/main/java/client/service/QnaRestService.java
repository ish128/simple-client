package client.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import client.controller.FileInfo;
import client.controller.Qna;
import client.controller.QnaForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QnaRestService {
	
	private static ConcurrentHashMap<Long, Qna> db = new ConcurrentHashMap<Long, Qna>(); 
	private static AtomicLong atomic = new AtomicLong();
	
	@Value("${file.dir}")
	private String fileDir;
	
	public Qna saveQna(Qna qna, List<MultipartFile> files) {    
		List<FileInfo> fileInfos = saveFile(files);  
		qna.setAttachFiles(fileInfos);
		qna.setId(atomic.incrementAndGet());
		QnaRestService.db.put(qna.getId(), qna);
		return qna; 
	}
	
	
	public List<FileInfo> saveFile(List<MultipartFile> files) {  
		List<FileInfo> fullPaths = Collections.emptyList(); 
		if (files!=null && files.size()>0) { 
			String fullPath = getFolder(); 
			File directory = new File(fullPath);
			if(!directory.exists() && !directory.mkdirs()) {
				throw new RuntimeException("Cannot make upload directory");
			}
			
			fullPaths = files
					.stream()
					.map(f -> { 
						UUID uuid = UUID.randomUUID();
						String fileName =  f.getOriginalFilename() + "_" + uuid.toString();   
						log.info("{},{}", uuid, fileName);   
						try {
							f.transferTo(new File(fullPath, fileName));
						} catch (IllegalStateException | IOException e) {
							throw new RuntimeException(e);
						}
						return FileInfo.builder().fileDesc(new FileInfo.FileDesc(fullPath, fileName)).build();
					})
					.collect(Collectors.toList());
			
		} 
		return fullPaths;
	}
	
	private String getFolder() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();  
		String formattedString = localDate.format(formatter); 
		return fileDir + formattedString.replace("-", File.separator); 
	}

}
