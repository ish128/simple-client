package client.test;

import java.util.ArrayList; 
import java.util.List;

import org.junit.jupiter.api.Test;

import client.controller.FileInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileInfoTest {
	
	@Test
	void test() {
		FileInfo.FileDesc desc = FileInfo.FileDesc.builder().path("path").fileName("fileName").build();
		FileInfo info = FileInfo.builder().fileDesc(desc).thumnailDesc(desc).build();
		
		List<FileInfo> fullPaths = new ArrayList<FileInfo>();
		fullPaths.add(info);
		log.info(desc.toString());
		log.info(info.toString());
		
	}

}
