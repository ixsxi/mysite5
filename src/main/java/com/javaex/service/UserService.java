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
		
		//회원가입폼 id사용유무 체크
		public boolean getUser(String id) {
			System.out.println("유저 서비스 .getUser");
			
			UserVo userVo = userDao.selectUser(id);
			System.out.println(userVo);
			
			if(userVo == null) { //db에 없는 경우 --> 사용할 수 있는 아이디
				return true;
			}else { // db에 있는 경우 --> 사용중인 아이디
				return false;
			}
			
			
		}
}
