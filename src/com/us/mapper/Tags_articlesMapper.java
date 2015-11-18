package com.us.mapper;


import com.us.po.Tags_articles;

public interface Tags_articlesMapper {
	//添加信息
	public int insertTagsArticles(Tags_articles tags_articles)throws Exception;
	//根据aid来查询tid
	public Tags_articles findTidByAid(int aid)throws Exception;
    //根据aid来删除数据
	public void delTidByAid(int aid)throws Exception;
	
}
