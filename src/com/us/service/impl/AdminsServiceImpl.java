package com.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.us.mapper.Admin_groupsMapper;
import com.us.mapper.AdminsCustomMapper;
import com.us.mapper.ArticlesCustomMapper;
import com.us.mapper.CategoriesMapper;
import com.us.mapper.GroupsMapper;
import com.us.mapper.MailboxCustomMapper;
import com.us.mapper.TagsMapper;
import com.us.mapper.Tags_articlesMapper;
import com.us.mapper.UsersCustomMapper;
import com.us.po.Admin_groups;
import com.us.po.AdminsCustom;
import com.us.po.ArticlesCustom;

import com.us.po.Authorities;
import com.us.po.Categories;
import com.us.po.Groups;
import com.us.po.Images;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.Tags;
import com.us.po.Tags_articles;
import com.us.po.UsersCustom;
import com.us.service.AdminsService;

public class AdminsServiceImpl implements AdminsService{
	public static final int NUMPERPAGE=10;
	Categories categories  = new Categories();
	Tags_articles tags_articles = new Tags_articles();
	Tags tags  = new Tags();
	Images images1 = new Images();
	
	@Autowired
	AdminsCustomMapper adminsCustomMapper;
    @Autowired
    Admin_groupsMapper admin_groupsMapper;
    @Autowired
    CategoriesMapper categoriesMapper;
    @Autowired
    ArticlesCustomMapper articlesCustomMapper;
    @Autowired
    Tags_articlesMapper tags_articlesMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    UsersCustomMapper usersCustomMapper;
    @Autowired
	GroupsMapper groupsMapper;
    @Autowired
    MailboxCustomMapper mailboxCustomMapper;
	
    //管理员登陆
	@Override
	public AdminsCustom findAdminsPasswordAndUsername(AdminsCustom adminsCustom)
			throws Exception {
		
	  AdminsCustom adminsCustom1 = adminsCustomMapper.findAdminsPasswordAndUsername(adminsCustom);	 
	  Admin_groups admingroups =  admin_groupsMapper.findAdminGroupsByAgid(adminsCustom1.getAgid());	 
	  adminsCustom1.setGroupname(admingroups.getGroupname());
	  adminsCustom1.setAuthority(admingroups.getAuthority());
	return adminsCustom1;
	}
//补全登录信息
	@Override
	public void updateadminsCustomLoged(AdminsCustom adminsCustom)
			throws Exception {
		
		adminsCustomMapper.updateadminsCustomLoged(adminsCustom);
		
	}
	//添加文章
	@Override
	public int addArticles(ArticlesCustom articlesCustom) throws Exception {
		
	   Categories categories1 = categoriesMapper.findCategoriesByName(articlesCustom.getCategory());
	   categories.setName(articlesCustom.getCategory());
	   if(categories1==null){
	   int a = categoriesMapper.insertCategories(categories);
	   articlesCustom.setCid(categories.getCid());
	   }else{
		   articlesCustom.setCid(categories1.getCid());
	   }
	   tags.setName(articlesCustom.getTags());
	   Tags tags1 =  tagsMapper.findTagsByName(tags);
	   
	   if(tags1==null){
	    	
	      int b= tagsMapper.insertTags(tags);
		   tags_articles.setTid(tags.getTid());
	  }else{
		 
		  tags_articles.setTid(tags1.getTid());
	   }
	   int c =articlesCustomMapper.insertArticles(articlesCustom);
	   tags_articles.setAid(articlesCustom.getAid());
	   tags_articlesMapper.insertTagsArticles(tags_articles);
	   return c;
	}
	//按照分类名来查询文章
	@Override
	public PageBean findArticlesByName(PageBean pageBean,String category)
			throws Exception {
		
	    
		int pNum = pageBean.getpNum();
		int start = (pNum-1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		Categories categories =  categoriesMapper.findCategoriesByName(category);
		pageBean.setCid(categories.getCid());
		List<ArticlesCustom> articlesCustoms =  articlesCustomMapper.findArticlesCustom(pageBean);
		for (ArticlesCustom articlesCustom : articlesCustoms) {
			Tags_articles tags_articles =  tags_articlesMapper.findTidByAid(articlesCustom.getAid());
			
				Tags tags = tagsMapper.findTagsByTid(tags_articles.getTid());
				articlesCustom.setTags(tags.getName());
			
		}
		pageBean.setArticlesCustoms(articlesCustoms);
		

		//封装总记录条数
		int totalRecordNum = articlesCustomMapper.findtotalRecordNum(categories.getCid());
		pageBean.setTotalRecordNum(totalRecordNum);
		
		//计算总页数
		int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	//根据文章的aid来查询文章
	@Override
	public ArticlesCustom findArticlesCustomByAid(int aid) throws Exception {
	
		ArticlesCustom articlesCustom =  articlesCustomMapper.findArticlesCustomByAid(aid);
		Tags_articles tags_articles =  tags_articlesMapper.findTidByAid(articlesCustom.getAid());
		
		Tags tags = tagsMapper.findTagsByTid(tags_articles.getTid());
		articlesCustom.setTags(tags.getName());
		return articlesCustom;
	}
	//根据文章的aid来删除文章的信息
	@Override
	public void delArticlesCustomByAid(int aid) throws Exception {
		
		tags_articlesMapper.delTidByAid(aid);
		articlesCustomMapper.delArticlesCustomByAid(aid);
		
	}
	 //按条件来查询文章
	@Override
	public PageBean findArticlesByCondition(PageBean pageBean, String category)
			throws Exception {
		int pNum = pageBean.getpNum();
		int start = (pNum-1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		Categories categories =  categoriesMapper.findCategoriesByName(category);
		pageBean.setCid(categories.getCid());
		List<ArticlesCustom> articlesCustoms;
		int totalRecordNum;
		if(pageBean.getConditionName().equals("author")){
			articlesCustoms = articlesCustomMapper.findArticlesByAuthor(pageBean);
			totalRecordNum = articlesCustomMapper.findtotalRecordNumByAuthor(pageBean);
		}else{
			articlesCustoms = articlesCustomMapper.findArticlesByTitle(pageBean);
			totalRecordNum = articlesCustomMapper.findtotalRecordNumByTitle(pageBean);
		}
		for (ArticlesCustom articlesCustom : articlesCustoms) {
			Tags_articles tags_articles =  tags_articlesMapper.findTidByAid(articlesCustom.getAid());
			
				Tags tags = tagsMapper.findTagsByTid(tags_articles.getTid());
				articlesCustom.setTags(tags.getName());
			
		}
		pageBean.setArticlesCustoms(articlesCustoms);
		

		//封装总记录条数
		
		pageBean.setTotalRecordNum(totalRecordNum);
		
		//计算总页数
		int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	
	//查询所有用户
	@Override
	public PageBean findAllUsersCustom(PageBean pageBean) throws Exception {
		
		int pNum = pageBean.getpNum();
		int start = (pNum-1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		List<UsersCustom> usersCustoms = usersCustomMapper.findAllUsers(pageBean);
		for (UsersCustom usersCustom : usersCustoms) {
			 Groups groups = groupsMapper.findGroupsByGid(usersCustom.getGid());
		        usersCustom.setAuthority(groups.getAuthority());
		        usersCustom.setName(groups.getName()); 
		}
		        pageBean.setUsersCustoms(usersCustoms);
		          //封装总记录条数
				int totalRecordNum = usersCustomMapper.findtotalRecordNum();
				
				pageBean.setTotalRecordNum(totalRecordNum);
				
				//计算总页数
				int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
				pageBean.setTotalPageNum(totalpageNum);
				return pageBean;
	}
	
	//按条件来查询用户
	@Override
	public PageBean findAUsersByCondition(PageBean pageBean) throws Exception {
		int pNum = pageBean.getpNum();
		int start = (pNum-1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		List<UsersCustom> usersCustoms=null;
		int totalRecordNum=0;
		if(pageBean.getConditionName().equals("phone")){
			usersCustoms = usersCustomMapper.findUserByPhoneCondition(pageBean);
			totalRecordNum = usersCustomMapper.findtotalRecordNumByPhone(pageBean);
		}else{
			if(pageBean.getConditionName().equals("username")){
				usersCustoms = usersCustomMapper.findUserByUsernameCondition(pageBean);
				totalRecordNum = usersCustomMapper.findtotalRecordNumByUsername(pageBean);
			}else{
				if(pageBean.getConditionName().equals("nickname")){
					usersCustoms = usersCustomMapper.findUserByNicknameCondition(pageBean);
					totalRecordNum = usersCustomMapper.findtotalRecordNumByNickname(pageBean);
				}
			}
		}
		for (UsersCustom usersCustom : usersCustoms) {
			 Groups groups = groupsMapper.findGroupsByGid(usersCustom.getGid());
		        usersCustom.setAuthority(groups.getAuthority());
		        usersCustom.setName(groups.getName()); 
		}
        pageBean.setUsersCustoms(usersCustoms);
		//封装总记录条数
		
		pageBean.setTotalRecordNum(totalRecordNum);
		
		//计算总页数
		int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	//通过用户的uid来删除用户
	@Override
	public void delUsersCustomByUid(int uid) throws Exception {
		usersCustomMapper.deleteUserById(uid);
		
	}
	//通过uid来查询用户
	@Override
	public UsersCustom findUsersCustomByUid(int uid) throws Exception {
		
		UsersCustom usersCustom =  usersCustomMapper.findUserById(uid);
		Groups groups =  groupsMapper.findGroupsByGid(usersCustom.getGid());
		usersCustom.setName(groups.getName());
		usersCustom.setAuthority(groups.getAuthority());
		
		return usersCustom;
	}
	
	//根据uid来修改用户权限
	@Override
	public void updateUsersAuthorityByGid(Authorities authorities)
			throws Exception {
		String name="user";
		String str =authorities.getLeave()+","+authorities.getProduct()+","+authorities.getGossip()+","+authorities.getSituation()
				+","+authorities.getSactivity()+","+authorities.getInformation()+","+authorities.getReputation()+","+authorities.getActivity()
				+","+authorities.getThenlife();
		String[] s =str.split(",");
		
	 	Groups groups = new Groups();
	 	UsersCustom usersCustom = new UsersCustom();
	 	usersCustom.setName(name);
	 	groups.setName(name);
	 	groups.setAuthority(str);
	 	Groups groups1 =  groupsMapper.findGroupsByNameAndAuthority(groups);
	 	usersCustom.setUid(authorities.getUid());
	 	if(groups1==null){
	 		groupsMapper.insertGroups(groups);
	 		usersCustom.setGid(groups.getGid());
	 		usersCustomMapper.updateUsersAuthorityByGid(usersCustom);
	 	}else{
	 	usersCustom.setGid(groups1.getGid());
		usersCustomMapper.updateUsersAuthorityByGid(usersCustom);
	 	}
	 	
	}
	@Override
	public void sendNoticeByUsers(MailboxCustom mailboxCustom) throws Exception {
		mailboxCustomMapper.insertReplyMailbox(mailboxCustom);
		
	}
	

}
