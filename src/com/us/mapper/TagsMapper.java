package com.us.mapper;

import java.util.List;

import com.us.po.Tags;

public interface TagsMapper {

	//��ӱ�ǩ
	public int insertTags(Tags tags)throws Exception;
	//���ݱ�ǩ������ѯ��ǩ
	public Tags findTagsByName(Tags tags)throws Exception;
	//���ݱ�ǩTid����ѯ��ǩ
	public Tags findTagsByTid(int tid)throws Exception;
	
	
}
