package client.controller;

 
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult; 
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import client.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/qna")
@Controller
public class QnaController {	
	
	private final RestTemplate restTemplate; 
	private final QnaValidator qnaValidator; 
	private final QnaService qnaService; 
	
	@InitBinder
	public void init(WebDataBinder dataBinder) {
		log.info("init binder {}", dataBinder);
		dataBinder.addValidators(qnaValidator);
	}
	
	@ModelAttribute("genderItem")
	public Gender[] genderItem() {
		return Gender.values();
	}
	
	@ModelAttribute("languageItem")
	public Language[] languageItem() {
		return Language.values();
	}
	
	@ModelAttribute("fruitItem")
	public Map<String,String> fruitItem() {
		return Fruit.toMap();
	}
	
	@GetMapping
	public String qnaForm(HttpServletRequest request, Model model) {  
//		model.addAttribute("genderItem", Gender.values());
//		model.addAttribute("languageItem", Language.values());  
//		model.addAttribute("fruitItem", Fruit.toMap());  
		model.addAttribute("qnaForm", request.getAttribute("qnaForm")!=null? request.getAttribute("qnaForm") : new QnaForm()); 
		return "qna/main";
	}
	
	@PostMapping
	public String saveQna(
			@Validated 
			@ModelAttribute("qnaForm") QnaForm qnaForm, BindingResult bindingResult,  
			Model model, RedirectAttributes redirectAttributes) {  
		
		if (bindingResult.hasErrors()) { 
//			model.addAttribute("genderItem", Gender.values());
//			model.addAttribute("languageItem", Language.values());  
//			model.addAttribute("fruitItem", Fruit.toMap());  
			return "qna/main";
		}     
		 
		Qna result = qnaService.saveQna(qnaForm);  
		redirectAttributes.addAttribute("qnaId", result.getId());
		return "redirect:/qna/{qnaId}";
	}
	
	
	@GetMapping("/{qnaId}")
	public String qna(@PathVariable("qnaId") Long qnaId, Model model) { 
		ResponseEntity<QnaForm> responseEntity = restTemplate.getForEntity("http://localhost:3000/qna/{id}", QnaForm.class, qnaId);     
		model.addAttribute("qnaForm", responseEntity.getBody());  
		return "forward:/qna";
	}  
 
}

 
