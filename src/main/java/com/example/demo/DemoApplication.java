package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private Storage storage;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RestController
	@RequestMapping("gcp/api")
	public class TestController {

		/*
		 * Below code is used for uploading file in GCP storage bucket
		 */
		
		@GetMapping("/send")
		public String sendFile() throws IOException {
			BlobId blobId = BlobId.of("mchadda-bucket","Test.txt");
			BlobInfo blobInfo =  BlobInfo.newBuilder(blobId).build();
			File file = new File("D:\\Personal","Test.txt");
			byte[] allBytes = Files.readAllBytes(Paths.get(file.toURI()));
			storage.create(blobInfo, allBytes);
			return "File uploaded done";
		}
		
		/*
		 *  Below code is used for fetching file from GCP storage bucket
		 */
		
		@GetMapping("/fetch")
		public String fetchFile() throws IOException {
			
			StringBuffer sf = new StringBuffer();
			try(ReadChannel ch = storage.reader("mchadda-bucket","Test.txt")){
				ByteBuffer allocate = ByteBuffer.allocate(64 * 1024);
				while(ch.read(allocate) > 0)
				{
					allocate.flip();
					String data = new String(allocate.array(), 0 , allocate.limit());
					sf.append(data);
					allocate.clear();
				}
				
				return "GCP response :: "+sf.toString();
			}
			
		}
		
		
		
		
		
	}


}
