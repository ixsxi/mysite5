package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
			//회원가입폼
		@RequestMapping(value = "/user/joinForm",method = {RequestMethod.GET,RequestMethod.POST})
		public String joinForm() {
			System.out.println("[userController.joinForm()]접속");
			
			return "user/joinForm";
		}
	
	
		//회원가입
		@RequestMapping(value = "/user/join",method= {RequestMethod.GET,RequestMethod.POST})
		public String join(@ModelAttribute UserVo userVo) {
			
			System.out.println("[userController.join접속]");
			System.out.println(userVo);
			
			userService.userJoin(userVo);
			return "user/joinOk";
	
	
	
		}
		
			//로그아웃
		@RequestMapping(value = "/user/logout",method= {RequestMethod.GET,RequestMethod.POST})
		public String logout(HttpSession session) {
			
			System.out.println("logout으로 들어옴");
			
			session.removeAttribute("authUser");
			session.invalidate();
			return "redirect:/main";
			
}
		
		//회원수정폼
		@RequestMapping(value = "/user/modifyForm",method= {RequestMethod.GET,RequestMethod.POST})
		public String modifyForm(HttpSession session, Model model) {
			
			System.out.println("모디파이 테스트");
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			System.out.println(authUser);
			int authUserNo = authUser.getNo();
			
			UserVo userVo = userService.moUser(authUserNo);
			
			model.addAttribute("userVo",userVo);
			
			return "user/modifyForm";
			
		}
		
		//수정
		@RequestMapping(value = "/user/modify",method= {RequestMethod.GET,RequestMethod.POST})
		public String modify(@ModelAttribute UserVo userVo,HttpSession session) {
			System.out.println("modify 접속");
			
			System.out.println(userVo);
		
			userService.updateUser(userVo);
			
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			int no = authUser.getNo();
			UserVo updateVo = userService.moUser(no);
			
			
			return "user/loginForm";
		
		}
}
