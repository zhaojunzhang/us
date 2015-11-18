package com.us.service;

import com.us.po.AdminsCustom;
import com.us.po.ArticlesCustom;
import com.us.po.Authorities;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.UsersCustom;

public interface AdminsService {
	//�����û�����������½��·ϵͳ
	public AdminsCustom findAdminsPasswordAndUsername(AdminsCustom adminsCustom)throws Exception;
	//���¹���Ա�ĵ�¼ʱ��,���û��ͻ���ϵͳ���û���ip
    public void updateadminsCustomLoged(AdminsCustom adminsCustom)throws Exception;
    //�������
    public int addArticles(ArticlesCustom articlesCustom)throws Exception;
    //���շ���������ѯ����
    public PageBean findArticlesByName(PageBean pageBean, String category)throws Exception;
    //����aid����ѯ���µ���ϸ��Ϣ
    public ArticlesCustom findArticlesCustomByAid(int aid)throws Exception;
    //����aid��ɾ�����µ���Ϣ
    public void delArticlesCustomByAid(int aid)throws Exception;
    //����������ѯ����
    public PageBean findArticlesByCondition(PageBean pageBean, String category)throws Exception;
    //��ѯ�����û�
    public PageBean findAllUsersCustom(PageBean pageBean)throws Exception;
    //����������ѯ�û�
    public PageBean findAUsersByCondition(PageBean pageBean)throws Exception;
    
    //����uid��ɾ���û�
    public void delUsersCustomByUid(int uid)throws Exception;
    
    //����uid����ѯ�û���Ϣ
    public UsersCustom findUsersCustomByUid(int uid)throws Exception;
    
    //����uid�������û�Ȩ��
    public void updateUsersAuthorityByGid(Authorities authorities)throws Exception;
    
    //����֪ͨ���û�
    public void sendNoticeByUsers(MailboxCustom mailboxCustom)throws Exception;
}
