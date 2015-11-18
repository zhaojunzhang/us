package com.us.mapper;

import java.util.List;

import com.us.po.ArticlesCustom;
import com.us.po.PageBean;
import com.us.po.SchoolLife;

public interface ArticlesCustomMapper {

	//添加文章
	public int insertArticles(ArticlesCustom articlesCustom)throws Exception;
	//根据cid查询所有文章
	public List<ArticlesCustom> findArticlesCustom(PageBean pageBean)throws Exception;
	//根据cid来查询总记录条数
	public int findtotalRecordNum(int cid)throws Exception;
	//根据文章的aid来查询文章
	public ArticlesCustom findArticlesCustomByAid(int aid)throws Exception;
	//根据文章的aid来删除文章
	public void delArticlesCustomByAid(int aid)throws Exception;
	//根据作者来进行模糊查询
	public List<ArticlesCustom> findArticlesByAuthor(PageBean pageBean)throws Exception;
	//根据标题来进行模糊查询
	public List<ArticlesCustom> findArticlesByTitle(PageBean pageBean)throws Exception;
	//根据作者和cid来查询总记录数
	public int findtotalRecordNumByAuthor(PageBean pageBean)throws Exception;
	//根据标题和cid来查询总记录数
	public int findtotalRecordNumByTitle(PageBean pageBean)throws Exception;
	//通过aid实现点赞功能
	public void updateArticlesCountByAid(ArticlesCustom articlesCustom)throws Exception;
	//通过aid来整加浏览次数
	public void updateArticlesViewsByAid(ArticlesCustom articlesCustom)throws Exception;
	//根据cid来查询3条最新的数据
	public List<ArticlesCustom> findArticlesByCid(SchoolLife schoolLife)throws Exception;
	//根据用户的authorid 来查询文章
	public List<ArticlesCustom> findArticlesByAuthorId(PageBean pageBean)throws Exception;
	//根据用户的authorid来查询文章总数
	public int findtotalRecordNumByAuthorId(PageBean pageBean)throws Exception;
	//根据文章aid来修改文章
	public void updateArticlesCustomByAid(ArticlesCustom articlesCustom)throws Exception; 
}
