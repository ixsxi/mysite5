package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트 
	public List<GalleryVo> selectList(){
		System.out.println("갤러리 다오 .selectList접속");
		
		List<GalleryVo> gList = sqlSession.selectList("gallery.selectList");
		System.out.println("gallery.xml에서 가지고온 :" +gList);
		return gList;
	}


	public void insert(GalleryVo galleryVo) {
		System.out.println("갤러리 다오접속");
		System.out.println(galleryVo);
		
		
		
		sqlSession.insert("gallery.insert",galleryVo);
	}


	public GalleryVo oneView(int no) {
		System.out.println("갤러리 다오 oneView");
		System.out.println(no);
		
		 GalleryVo galleryVo = sqlSession.selectOne("gallery.selectOneImg",no);
		
		return galleryVo;
	
		
	}


	public int galleryDelete(int no) {
		
		
		
		return sqlSession.delete("gallery.deletegallery",no);
		
	}


	
	
}
