package com.us.mapper;

import java.util.List;

import com.us.po.CommentsCustom;
import com.us.po.PageBean;

public interface CommentsCustomMapper {

	//通过aid来查询评论
	public List<CommentsCustom> findCommentsCustomByAid(PageBean pageBean)throws Exception;
	
	//根据aid来查询总记录条数
	public int findtotalRecordNum(int aid)throws Exception;
	
	//通过coid来查询回复的评论
	public List<CommentsCustom> findCommentsCustomByCoid(int coid)throws Exception;
	
	//添加评论
	public void insertCommentsCustom(CommentsCustom commentsCustom)throws Exception;
	
}
