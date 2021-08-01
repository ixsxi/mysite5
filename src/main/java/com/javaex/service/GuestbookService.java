package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	//리스트
	public List<GuestbookVo> guestbookList() {
		System.out.println("guestbookServic.guestbookList");
		
		List<GuestbookVo> guestbookList = guestbookDao.guestbookList();
		System.out.println(guestbookList);
		
		return guestbookList;
		
	}
	
	//인서트 (add)
	public GuestbookVo guestbookAdd(GuestbookVo guestbookVo) {
		System.out.println("여기는 게스트북 서비스 입니다");
		System.out.println(guestbookVo);
		
		guestbookDao.guestbookAdd(guestbookVo);
		
		return guestbookVo;
	}
	
	//delete삭제
	public int guestbookDelete(GuestbookVo guestbookVo) {
		System.out.println("여기는 게스북 서비스(삭제)입니다.");
		System.out.println(guestbookVo);
		
		return guestbookDao.guestbookDelete(guestbookVo);
		
		
		
	}
}
