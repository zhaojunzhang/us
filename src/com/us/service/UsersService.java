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

	//插入用户
	public int insertUsers(UsersCustom usersCustom)throws Exception;
	//根据用户名密码查询用户
	public UsersCustom findUsernameAndPassword(UsersCustom usersCustom)throws Exception;
	//按照用户名查询用户
	public UsersCustom findUserByUserName(String username)throws Exception;
	
	//通过邮箱来查询用户
	public UsersCustom findUsersCustomByEmail(String email,String username)throws Exception;
	//根据用户id来查询用户
	public UsersCustom findUserById(int id)throws Exception;
	//根据用户id来更改用户信息
	public UsersCustom updateUserById(UsersCustom usersCustom)throws Exception;
	//更新用户的登录时间和ip
	public void updateUserLoged(UsersCustom usersCustom)throws Exception;
	//通过用户id来修改用户的密码
    public void updateUserPasswordById(UsersCustom usersCustom)throws Exception;
  //通过用户id来修改用户的绑定手机
  	public void updateUserPhoneById(UsersCustom usersCustom)throws Exception;
  	//根据用户iD来查询用户的个人信箱
  	public PageBean findMailboxByUid(PageBean pageBean)throws Exception;
  	//根据用户个人信箱mailid来查询详细信息
  	public MailboxCustom findMailboxById(int mailid)throws Exception;
  //插入信箱信息
  	public int insertReplyMailbox(MailboxCustom mailboxCustom)throws Exception;
  //通过mailid来删除邮件信息
  	public void delMailboxById(int mailid)throws Exception;
  //通过aid实现点赞功能
  	public ArticlesCustom updateArticlesCountByAid(ArticlesCustom articlesCustom,Praise praise)throws Exception;
  	//添加文章
	public int addArticles(ArticlesCustom articlesCustom)throws Exception;
	//通过aid来查询商品的详细信息和评论
	public ArticlesCustom findArticlesByAid(int aid)throws Exception;	
	//通过aid来查询评论
	public PageBean findCommentsCustomByAid(PageBean pageBean)throws Exception;
	//添加评论
	public void insertCommentsCustom(CommentsCustom commentsCustom)throws Exception;
	 //按照分类名来查询文章
    public PageBean findArticlesByName(PageBean pageBean, String category)throws Exception;
    //按条件来查询文章
    public PageBean findArticlesByCondition(PageBean pageBean, String category)throws Exception;
    //校园生活的首页
    public SchoolLife findSchoolLife()throws Exception;
    //校园周边首页
	public SchoolLife findSchoolSide()throws Exception;
	//根据用户id来查询自己发的文章
	public PageBean  findArticlesByAuthorid(PageBean pageBean)throws Exception;
	//根据文章aid来修改文章
	public void updateArticlesCustom(ArticlesCustom articlesCustom)throws Exception;
	
	
}
