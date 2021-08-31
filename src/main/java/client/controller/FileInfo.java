package client.controller;

import lombok.AllArgsConstructor;
import lombok.Builder; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileInfo {
	
	private FileDesc fileDesc; 
	private FileDesc thumnailDesc;  
	
	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FileDesc{
		private String path;
		private String fileName;
	}

}
