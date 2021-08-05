package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {
		
	
		//파일 업로드 처리
	public String restore(MultipartFile file) {
		System.out.println("Fileupservice.restore접속");
		
		String saveDir = "C:\\java Study\\upload";
		
		System.out.println(file.getSize());
		
		//파일 서버하드디스크에 저장
		//파일정보를 db에 저장 
		
		
		//뭔파일이름
		String orgName =file.getOriginalFilename();
		System.out.println("orgName:"+orgName);
		
		//확정자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:"+exName);
		
		//저장파일이름(관리때문에 겹치지 않는 새이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println("saveName:"+saveName);
		
		//파일패스
		String filePath = saveDir+"\\"+saveName;
		System.out.println("filePath:"+filePath);
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize:"+fileSize);
		
		//파일을 서버의 하드 디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//2. 파일정보를 db에 저장 --과제
				return saveName;
	}


}
