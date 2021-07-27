package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자
	//메소드 gs
	//메소드 일반
	
	
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[userDao.selectUser() 접속]");
		
		System.out.println(userVo);
		return sqlSession.selectOne("user.selectUser",userVo);
		
	}
	
}
