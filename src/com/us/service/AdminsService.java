package com.us.service;

import com.us.po.AdminsCustom;
import com.us.po.ArticlesCustom;
import com.us.po.Authorities;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.UsersCustom;

public interface AdminsService {
	//根据用户名密码来登陆管路系统
	public AdminsCustom findAdminsPasswordAndUsername(AdminsCustom adminsCustom)throws Exception;
	//更新管理员的登录时间,和用户客户端系统和用户的ip
    public void updateadminsCustomLoged(AdminsCustom adminsCustom)throws Exception;
    //添加文章
    public int addArticles(ArticlesCustom articlesCustom)throws Exception;
    //按照分类名来查询文章
    public PageBean findArticlesByName(PageBean pageBean, String category)throws Exception;
    //根据aid来查询文章的详细信息
    public ArticlesCustom findArticlesCustomByAid(int aid)throws Exception;
    //根据aid来删除文章的信息
    public void delArticlesCustomByAid(int aid)throws Exception;
    //按条件来查询文章
    public PageBean findArticlesByCondition(PageBean pageBean, String category)throws Exception;
    //查询所有用户
    public PageBean findAllUsersCustom(PageBean pageBean)throws Exception;
    //按条件来查询用户
    public PageBean findAUsersByCondition(PageBean pageBean)throws Exception;
    
    //按照uid来删除用户
    public void delUsersCustomByUid(int uid)throws Exception;
    
    //根据uid来查询用户信息
    public UsersCustom findUsersCustomByUid(int uid)throws Exception;
    
    //根据uid来更改用户权限
    public void updateUsersAuthorityByGid(Authorities authorities)throws Exception;
    
    //发送通知给用户
    public void sendNoticeByUsers(MailboxCustom mailboxCustom)throws Exception;
}
