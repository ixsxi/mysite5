package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestbookVo> guestbookList() {
		System.out.println("게스트북다오입니다.");
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		System.out.println(guestbookList);
		
		return guestbookList;
		
	}
	
	public GuestbookVo guestbookAdd(GuestbookVo guestbookVo) {
		System.out.println("게스트북 다오입니다");
		System.out.println(guestbookVo);
		
		sqlSession.insert("guestbook.add",guestbookVo);
		
		
		return guestbookVo;
		
	}
	
	public int  guestbookDelete(GuestbookVo guestbookVo) {
		System.out.println("게스트북 다오(삭제)입니다");
		System.out.println(guestbookVo);
		
		return sqlSession.delete("guestbook.delete",guestbookVo);
	
		
		
		
	}
	
	
}
