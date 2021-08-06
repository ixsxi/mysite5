package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	
	//리스트
	public List<GalleryVo> getList() {
		System.out.println("갤러리서비스.getList접속");
		
		
		List<GalleryVo> gList = galleryDao.selectList();
		System.out.println("갤러리서비스2:"+gList);
		return gList;
		
		
	}


	public void upload(String content, int user_No, MultipartFile file) {
		System.out.println("갤러리서비스.upload접속");
		
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
		//2. 파일정보를  vo로 붂어서 db에 저장 --
		GalleryVo galleryVo = new GalleryVo(user_No,content,filePath,orgName,saveName,(int) fileSize);
		galleryDao.insert(galleryVo);
				return;
				
		
	}
	
	
}
