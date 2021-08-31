package client.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import client.service.QnaRestService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/rest/qna")
@RequiredArgsConstructor
@RestController
public class QnaRestController {
	
	private final QnaRestService qnaRestService;
	
	
	@PostMapping(consumes = {"multipart/form-data"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Qna> save(@RequestPart("qna") Qna qna, 
					@RequestPart("file") List<MultipartFile> files) {
		return new ResponseEntity<Qna>( qnaRestService.saveQna(qna, files), HttpStatus.CREATED);
	}

}
