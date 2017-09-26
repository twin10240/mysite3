package com.bigdata2017.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList() {
		List<GalleryVo> list = sqlSession.selectList("gallery.getList");
		return list;
	}
	
	public int insert( GalleryVo vo ) {
		int count = sqlSession.insert("gallery.insert", vo);
		return count;
	}	
}
