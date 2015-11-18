package com.us.service;

import java.util.List;

import com.us.po.ArticlesCustom;
import com.us.po.CommentsCustom;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.Praise;
import com.us.po.SchoolLife;
import com.us.po.UsersCustom;

public interface UsersService {

	//�����û�
	public int insertUsers(UsersCustom usersCustom)throws Exception;
	//�����û��������ѯ�û�
	public UsersCustom findUsernameAndPassword(UsersCustom usersCustom)throws Exception;
	//�����û�����ѯ�û�
	public UsersCustom findUserByUserName(String username)throws Exception;
	
	//ͨ����������ѯ�û�
	public UsersCustom findUsersCustomByEmail(String email,String username)throws Exception;
	//�����û�id����ѯ�û�
	public UsersCustom findUserById(int id)throws Exception;
	//�����û�id�������û���Ϣ
	public UsersCustom updateUserById(UsersCustom usersCustom)throws Exception;
	//�����û��ĵ�¼ʱ���ip
	public void updateUserLoged(UsersCustom usersCustom)throws Exception;
	//ͨ���û�id���޸��û�������
    public void updateUserPasswordById(UsersCustom usersCustom)throws Exception;
  //ͨ���û�id���޸��û��İ��ֻ�
  	public void updateUserPhoneById(UsersCustom usersCustom)throws Exception;
  	//�����û�iD����ѯ�û��ĸ�������
  	public PageBean findMailboxByUid(PageBean pageBean)throws Exception;
  	//�����û���������mailid����ѯ��ϸ��Ϣ
  	public MailboxCustom findMailboxById(int mailid)throws Exception;
  //����������Ϣ
  	public int insertReplyMailbox(MailboxCustom mailboxCustom)throws Exception;
  //ͨ��mailid��ɾ���ʼ���Ϣ
  	public void delMailboxById(int mailid)throws Exception;
  //ͨ��aidʵ�ֵ��޹���
  	public ArticlesCustom updateArticlesCountByAid(ArticlesCustom articlesCustom,Praise praise)throws Exception;
  	//�������
	public int addArticles(ArticlesCustom articlesCustom)throws Exception;
	//ͨ��aid����ѯ��Ʒ����ϸ��Ϣ������
	public ArticlesCustom findArticlesByAid(int aid)throws Exception;	
	//ͨ��aid����ѯ����
	public PageBean findCommentsCustomByAid(PageBean pageBean)throws Exception;
	//�������
	public void insertCommentsCustom(CommentsCustom commentsCustom)throws Exception;
	 //���շ���������ѯ����
    public PageBean findArticlesByName(PageBean pageBean, String category)throws Exception;
    //����������ѯ����
    public PageBean findArticlesByCondition(PageBean pageBean, String category)throws Exception;
    //У԰�������ҳ
    public SchoolLife findSchoolLife()throws Exception;
    //У԰�ܱ���ҳ
	public SchoolLife findSchoolSide()throws Exception;
	//�����û�id����ѯ�Լ���������
	public PageBean  findArticlesByAuthorid(PageBean pageBean)throws Exception;
	//��������aid���޸�����
	public void updateArticlesCustom(ArticlesCustom articlesCustom)throws Exception;
	
	
}
