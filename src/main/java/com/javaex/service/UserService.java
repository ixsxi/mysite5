package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
		@Autowired
		private UserDao userDao;
	
	
		//로그인 사용자 정보 가져오기
		public UserVo getUser(UserVo userVo) {
			
			System.out.println("userService.getUser()");
			UserVo authUser = userDao.selectUser(userVo);
			
			
			return authUser;
		}
		
		//회원가입
		public int userJoin(UserVo userVo) {
			System.out.println("userService.userJoin()접속");
			
			System.out.println(userVo);
			
			
			return userDao.insertJoin(userVo);
			
		}
		
		public UserVo moUser(int authUserNo) {
			System.out.println("UserService.moUser()접속");
			
			return userDao.moUser(authUserNo);
			
		}
		
		public int updateUser(UserVo userVo) {
			System.out.println("UserService.updateUser()접속");
			
			return userDao.updateUser(userVo);
		
}
}
