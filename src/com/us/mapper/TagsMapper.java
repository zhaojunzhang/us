package com.us.mapper;

import java.util.List;

import com.us.po.Tags;

public interface TagsMapper {

	//添加标签
	public int insertTags(Tags tags)throws Exception;
	//根据标签名来查询标签
	public Tags findTagsByName(Tags tags)throws Exception;
	//根据标签Tid来查询标签
	public Tags findTagsByTid(int tid)throws Exception;
	
	
}
