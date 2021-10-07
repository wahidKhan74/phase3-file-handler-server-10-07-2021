package com.simplilearn.fileserver.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simplilearn.fileserver.exception.StorageException;

@Service
public class StorageService {
	
	@Value("${upload.path}")
	private String path;
	
	//upload file logic
	public void uploadFile(MultipartFile file) {
		// verify file is empty
		if(file.isEmpty()) {
			throw new StorageException("Falied to upload a file { file is empty !} ");
		}
		// file upload logic
		String fileName = file.getOriginalFilename();
		
		try {
			InputStream inStream = file.getInputStream();
			Files.copy(inStream, Paths.get(path+fileName),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Falied to upload a file !");
		}
	}

	//download file
	public String getFile(String filename) {
		return path+filename;
	}
}
