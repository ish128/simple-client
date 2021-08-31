package client.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTest {
	
	@Test
	void test() {
		 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();  
		String formattedString = localDate.format(formatter); 
		String result = formattedString.replace("-", File.separator); 
		
		log.info("formattedString:"+ formattedString + ", result:"+result);
		
		Assertions.assertThat(result).isEqualTo("2021"+File.separator+"08"+File.separator+"28");
	}

}
