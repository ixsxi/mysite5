package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;




@Controller
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookSevice;
	
	
	//addList리스트
	@RequestMapping(value = "/guestbook/addList",method = {RequestMethod.GET,RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("guestbookController.addList");
		
		List<GuestbookVo> guestbookList = guestbookSevice.guestbookList();  
		System.out.println("----다시컨트롤러---");
		
		System.out.println(guestbookList);
		
		model.addAttribute("guestbookList",guestbookList);
		return "guestbook/addList";
		
	}
	
	//인서트
	@RequestMapping(value = "/guestbook/add",method = {RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("여긴 컨트롤러 게스트북 add 입니다.");
		System.out.println(guestbookVo);
		
		guestbookSevice.guestbookAdd(guestbookVo);
		
		
		return "redirect:/guestbook/addList";
		
	}
	
	@RequestMapping(value = "/guestbook/deleteForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		
		
		
		return "guestbook/deleteForm";
		
	}
	
	@RequestMapping(value = "/guestbook/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("delete 접속");
		System.out.println(guestbookVo);
		
		guestbookSevice.guestbookDelete(guestbookVo);
		return "redirect:/guestbook/addList";
		
	}
	
	//ajax 방명록 메인페이지
	
	@RequestMapping(value = "/guestbook/ajaxMain",method= {RequestMethod.GET,RequestMethod.POST})
	public String ajaxMain() {
		System.out.println("guestbookController.ajaxMain");
		
		
		return "guestbook/ajaxList";
	}
	
	
}
