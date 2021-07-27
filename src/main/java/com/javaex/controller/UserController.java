package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	//필드
	@Autowired
	private UserService userService;
	
	
	//생성자
	//메소드 gs
	//메소드일반
	
	
	
	//로그인폼
	@RequestMapping(value = "/user/loginForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("[userController.loginForm()]");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/user/login",method= {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		
		System.out.println("[userController.login접속]");
		System.out.println(userVo);
		
		 UserVo authUser = userService.getUser(userVo);
		 
		//로그인 성공하면 (authUser 있으면)
		 if(authUser != null) {
			 System.out.println("로그인성공");
		session.setAttribute("authUser", authUser);
			return "redirect:/main";
		 }else { //로그인 실패 authUser 이 없으면
			 System.out.println("로그인 실패");
			 
			 return "redirect:/user/loginForm?result=fail";
		 }
		
		
		
	}
	
}
