package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/api/gallery")
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	//ajax 리스트 가져오기
	@ResponseBody // list.jsp 로 보내는 역할 
	@RequestMapping(value="/oneView",method = {RequestMethod.POST,RequestMethod.GET})
	public GalleryVo oneView(@RequestParam("no") int no) {
		System.out.println("api one View 접속 ");
		System.out.println(no);
		
		GalleryVo galleryVo = galleryService.oneView(no);
		
		return galleryVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteImg",method = {RequestMethod.POST,RequestMethod.GET})
	public int deleteImg(@RequestParam("no")int no) {
		System.out.println("deleteImg 접속");
		System.out.println(no);
		
		
		return galleryService.galleryDelete(no);
		
	}
}
