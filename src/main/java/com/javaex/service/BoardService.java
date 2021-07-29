package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard()]");
		System.out.println(no);

		// 조회수 올리기
		int count = boardDao.updateHit(no);

		// 게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	public List<BoardVo> viewsList() {
		System.out.println("BoardService.viewsList");

		List<BoardVo> boardList = boardDao.viewsList();

		return boardList;

	}
	
	public BoardVo delete(BoardVo boardVo) {
		
		System.out.println("BoardService.delete");
		System.out.println(boardVo);
		
		boardDao.delete(boardVo);
		return boardVo;
		
	}
	
	public int write(BoardVo boardVo) {
		System.out.println("BoardService.write");
		System.out.println(boardVo);
		
		int count = boardDao.write(boardVo);
		
		return count;
		
	}
	

}
