package com.us.mapper;

import java.util.List;

import com.us.po.ArticlesCustom;
import com.us.po.PageBean;
import com.us.po.SchoolLife;

public interface ArticlesCustomMapper {

	//�������
	public int insertArticles(ArticlesCustom articlesCustom)throws Exception;
	//����cid��ѯ��������
	public List<ArticlesCustom> findArticlesCustom(PageBean pageBean)throws Exception;
	//����cid����ѯ�ܼ�¼����
	public int findtotalRecordNum(int cid)throws Exception;
	//�������µ�aid����ѯ����
	public ArticlesCustom findArticlesCustomByAid(int aid)throws Exception;
	//�������µ�aid��ɾ������
	public void delArticlesCustomByAid(int aid)throws Exception;
	//��������������ģ����ѯ
	public List<ArticlesCustom> findArticlesByAuthor(PageBean pageBean)throws Exception;
	//���ݱ���������ģ����ѯ
	public List<ArticlesCustom> findArticlesByTitle(PageBean pageBean)throws Exception;
	//�������ߺ�cid����ѯ�ܼ�¼��
	public int findtotalRecordNumByAuthor(PageBean pageBean)throws Exception;
	//���ݱ����cid����ѯ�ܼ�¼��
	public int findtotalRecordNumByTitle(PageBean pageBean)throws Exception;
	//ͨ��aidʵ�ֵ��޹���
	public void updateArticlesCountByAid(ArticlesCustom articlesCustom)throws Exception;
	//ͨ��aid�������������
	public void updateArticlesViewsByAid(ArticlesCustom articlesCustom)throws Exception;
	//����cid����ѯ3�����µ�����
	public List<ArticlesCustom> findArticlesByCid(SchoolLife schoolLife)throws Exception;
	//�����û���authorid ����ѯ����
	public List<ArticlesCustom> findArticlesByAuthorId(PageBean pageBean)throws Exception;
	//�����û���authorid����ѯ��������
	public int findtotalRecordNumByAuthorId(PageBean pageBean)throws Exception;
	//��������aid���޸�����
	public void updateArticlesCustomByAid(ArticlesCustom articlesCustom)throws Exception; 
}
