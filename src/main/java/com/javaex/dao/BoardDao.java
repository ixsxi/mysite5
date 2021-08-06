package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//전체 게시물 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("BoardDao.selectTotalCnt()");
		return sqlSession.selectOne("board.selectTotalCnt",keyword);
	}
	
	
	//게시판 페이징 연습용리스트
	 public List<BoardVo> selectList2(int startRnum,int endRnum,String keyword){
		 System.out.println("BoardDao.selectList2()");
		 System.out.println(startRnum);
		 System.out.println(endRnum);
		 
		Map<String, Object> pMap = new HashMap<String,Object>();
		 pMap.put("startRnum", startRnum);
		 pMap.put("endRnum", endRnum);
		 pMap.put("keyword", keyword);
		 System.out.println(pMap);
		 
		 
		 List<BoardVo> boardList = sqlSession.selectList("board.selectList2",pMap);
		 System.out.println(boardList);
		 
		 return boardList;
	 }
	
	
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		System.out.println(no);
		
		int count = sqlSession.update("board.updateHit",no);
		
		System.out.println(count);
		
		return count;
	}
	
	
	//게시판 1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard]");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard",no);
		
		return boardVo;
	}
	
	
	//리스트
	public List<BoardVo> viewsList() {
		System.out.println("[BoardDao.viewsList]");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
		
		
		
	}
	
	public BoardVo delete(BoardVo boardVo) {
		System.out.println("[BoardDao.delete]");
		System.out.println(boardVo);
		
		
		sqlSession.delete("board.delete",boardVo);
		
		return boardVo;
		
	}
	
	public int write(BoardVo boardVo) {
		System.out.println("BoardDao.write");
		System.out.println(boardVo);
		
		int count = sqlSession.insert("board.write",boardVo);
		
		return count;
		
	}
	
	//수정폼
	public BoardVo listOne(int no) {
		System.out.println("BoardDao.listOne");
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard",no);
		
		return boardVo;
		
	}
	//수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardDao.modify");
		System.out.println(boardVo);
		
		
		int count = sqlSession.update("board.updateModify",boardVo);
		
		return count;
		
	}
	
	
}
