package com.us.mapper;


import com.us.po.Tags_articles;

public interface Tags_articlesMapper {
	//�����Ϣ
	public int insertTagsArticles(Tags_articles tags_articles)throws Exception;
	//����aid����ѯtid
	public Tags_articles findTidByAid(int aid)throws Exception;
    //����aid��ɾ������
	public void delTidByAid(int aid)throws Exception;
	
}
