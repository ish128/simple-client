package client.controller;
 

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QnaValidator implements Validator { 
	
	@Override
	public boolean supports(Class<?> candidate) {
        return Qna.class.isAssignableFrom(candidate);
    }
	
	@Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required", "userId is required."); 
	}

}
