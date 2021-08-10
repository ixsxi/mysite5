package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;
	// 생성자
	// 메소드 gs
	// 메소드 일반

	public UserVo selectUser(UserVo userVo) {
		System.out.println("[userDao.selectUser() 접속]");

		System.out.println(userVo);
		return sqlSession.selectOne("user.selectUser", userVo);

	}

	// 회원가입
	public int insertJoin(UserVo userVo) {
		System.out.println("[userDao.insertJoin() 접속]");

		System.out.println(userVo);
		return sqlSession.insert("user.insertUser", userVo);

	}

	public UserVo moUser(int authUserNo) {
		System.out.println("[userDao.moUser() 접속]");

		return sqlSession.selectOne("user.selectoneUser", authUserNo);

	}

	public int updateUser(UserVo UserVo) {
		System.out.println("[userDao.moUser() 접속]");

		return sqlSession.update("user.updateUser",UserVo);

	}
	//회원정보 가져오기 --> 아이디체크
	public UserVo selectUser(String id) {
		System.out.println("userDao.selectUser");
		
		System.out.println(id);
		
		return sqlSession.selectOne("user.selectUserById",id);
		
	}
}
