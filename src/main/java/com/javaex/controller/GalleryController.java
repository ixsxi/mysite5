package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService gallerytService;
	
	
	@RequestMapping(value = "/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("갤러리컨트롤러.list접속");
		
		List<GalleryVo> gList = gallerytService.getList();
		System.out.println("갤러리컨트롤러2:"+gList);
		
		model.addAttribute("gList",gList);
		return "/gallery/list";
		
		
	}
	
	@RequestMapping(value="/upload",method= {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("content")String content,HttpSession session,@RequestParam("file") MultipartFile file) {
		System.out.println("갤러리컨트롤러 업로드");
		System.out.println(content);
		System.out.println(file.getOriginalFilename());
		
		int user_No = ((UserVo)session.getAttribute("authUser")).getNo();
		System.out.println(user_No);
		
		gallerytService.upload(content,user_No,file);
		
		
		return "redirect:/gallery/list";
		
		
		
	}
}
