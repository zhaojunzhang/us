package com.us.service.impl;


import java.util.List;

import javax.mail.Message;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import com.us.mapper.AdminsCustomMapper;
import com.us.mapper.ArticlesCustomMapper;
import com.us.mapper.CategoriesMapper;
import com.us.mapper.CommentsCustomMapper;
import com.us.mapper.GroupsMapper;
import com.us.mapper.MailboxCustomMapper;
import com.us.mapper.PraiseMapper;
import com.us.mapper.TagsMapper;
import com.us.mapper.Tags_articlesMapper;
import com.us.mapper.UsersCustomMapper;
import com.us.po.AdminsCustom;
import com.us.po.ArticlesCustom;
import com.us.po.Categories;
import com.us.po.CommentsCustom;
import com.us.po.Groups;
import com.us.po.Images;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.Praise;
import com.us.po.SchoolLife;
import com.us.po.Tags;
import com.us.po.Tags_articles;
import com.us.po.UsersCustom;
import com.us.service.UsersService;
import com.us.utils.MD5Utils;
import com.us.utils.MailUtils;

public class UsersServiceImpl implements UsersService {
	Categories categories  = new Categories();
	Tags_articles tags_articles = new Tags_articles();
	Tags tags  = new Tags();
	Images images1 = new Images();
	public static final int NUMPERPAGE=10;
	Groups groups = new Groups();
	MailboxCustom mailboxCustom =new MailboxCustom(); 
	@Autowired
	UsersCustomMapper usersCustomMapper;
	@Autowired
	GroupsMapper groupsMapper;
	@Autowired
	MailboxCustomMapper mailboxCustomMapper;
	@Autowired
	ArticlesCustomMapper articlesCustomMapper;
	@Autowired
	CategoriesMapper categoriesMapper;
    @Autowired
	Tags_articlesMapper tags_articlesMapper;
	@Autowired
	TagsMapper tagsMapper;
	@Autowired
	CommentsCustomMapper commentsCustomMapper;
	@Autowired
	PraiseMapper praiseMapper;
	@Autowired
	AdminsCustomMapper adminsCustomMapper;
	
	
	// 用户注册
	@Override
	public int insertUsers(UsersCustom usersCustom) throws Exception {
		
		UsersCustom usersCustom1 =  usersCustomMapper.findUsersCustomByPhone(usersCustom.getPhone());
	if(usersCustom1!=null){
		return 0;
		}else{
			String authority = "1,0,1,0,0,0,0,0,0";
			String name = "user";
			
			groups.setAuthority(authority);
			groups.setName(name);
			Groups groups1 =  groupsMapper.findGroupsByNameAndAuthority(groups);
			if(groups1==null){
		    groupsMapper.insertGroups(groups);
			usersCustom.setGid(groups.getGid());
			}else{
			usersCustom.setGid(groups1.getGid());
		     }
			String password = MD5Utils.md5(usersCustom.getPassword());
			usersCustom.setPassword(password);
			
	 		int n = usersCustomMapper.insertUsers(usersCustom);
	 		return n;
		}
	
		
	}


	// 按照用户名和密码去查询用户
	@Override
	public UsersCustom findUsernameAndPassword(UsersCustom usersCustom)
			throws Exception {
		String password = MD5Utils.md5(usersCustom.getPassword());
		usersCustom.setPassword(password);
		UsersCustom usersCustom1 = usersCustomMapper.findUsernameAndPassword(usersCustom);	        
        Groups groups = groupsMapper.findGroupsByGid(usersCustom1.getGid());
        usersCustom1.setAuthority(groups.getAuthority());
        usersCustom1.setName(groups.getName()); 
		return usersCustom1;
	}

	// 按照用户名去查询用户
	@Override
	public UsersCustom findUserByUserName(String username) throws Exception {
		
		return usersCustomMapper.findUserByUserName(username);
	}

	// 根据用户ID查询用户信息
	@Override
	public UsersCustom findUserById(int uid) throws Exception {		
	         UsersCustom usersCustom = usersCustomMapper.findUserById(uid);	        
	         Groups groups = groupsMapper.findGroupsByGid(usersCustom.getGid());
	         usersCustom.setAuthority(groups.getAuthority());
	         usersCustom.setName(groups.getName());  
		return usersCustom;
	}

	// 通过用户ID来更新用户信息
	@Override
	public UsersCustom updateUserById(UsersCustom usersCustom) throws Exception {
		String username = usersCustomMapper.findUsernameByEmail(usersCustom.getEmail());
		String username1=usersCustom.getUsername();

		
		if(username==null||username.equals(usersCustom.getUsername())){
			
			usersCustomMapper.updateUserById(usersCustom);
			UsersCustom usersCustom1 =  usersCustomMapper.findUserById(usersCustom.getUid());
			return usersCustom1;
		}else{
		return null;
		}
	}


    //更新用户的登录时间
	@Override
	public void updateUserLoged(UsersCustom usersCustom) throws Exception {
		
		usersCustomMapper.updateUserLoged(usersCustom);
	}
     //通过用户id来修改用户密码
	@Override
	public void updateUserPasswordById(UsersCustom usersCustom)
			throws Exception {
		usersCustomMapper.updateUserPasswordById(usersCustom);
		
	}
    //通过用户id来修改用户绑定的手机号
	@Override
	public void updateUserPhoneById(UsersCustom usersCustom) throws Exception {
		usersCustomMapper.updateUserPhoneById(usersCustom);
		
	}
     //根据用户的uid来查询用户的个人信箱
	@Override
	public  PageBean findMailboxByUid(PageBean pageBean)
			throws Exception {
		//根据页码和每页条数  计算开始索引
		        int pNum = pageBean.getpNum();
				int start = (pNum-1) * NUMPERPAGE;
				 pageBean.setStart(start);
				 pageBean.setNumperpage(PageBean.NUMPERPAGE);
				
				
				
				// 调用DAO进行分页查询 --- 结果数据
				
				List<MailboxCustom> mailboxCustoms = mailboxCustomMapper.findByPageByUid(pageBean);
				for (MailboxCustom mailboxCustom : mailboxCustoms) {
					UsersCustom usersCustom =usersCustomMapper.findUserById(mailboxCustom.getSendid());
					if(usersCustom!=null){
						mailboxCustom.setUsername(usersCustom.getUsername());
						mailboxCustom.setNickname(usersCustom.getNickname());
					}
				}
				pageBean.setMailboxCustoms(mailboxCustoms);
				
				//封装总记录条数
				int totalRecordNum = mailboxCustomMapper.findtotalRecordNum(pageBean.getUid());
				pageBean.setTotalRecordNum(totalRecordNum);
				
				//计算总页数
				int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
				pageBean.setTotalPageNum(totalpageNum);
				
	  
	  return pageBean;
	}
	//根据用户个人信箱mailid来查询详细信息
	@Override
	public MailboxCustom findMailboxById(int mailid) throws Exception {
		MailboxCustom mailboxCustom = mailboxCustomMapper.findMailboxById(mailid);
		UsersCustom usersCustom =usersCustomMapper.findUserById(mailboxCustom.getSendid());
		if(usersCustom!=null){
		mailboxCustom.setUsername(usersCustom.getUsername());
		mailboxCustom.setNickname(usersCustom.getNickname());
		}
		return mailboxCustom;
	}
	//插入信箱信息
	@Override
	public int insertReplyMailbox(MailboxCustom mailboxCustom) throws Exception {
		
		int n = mailboxCustomMapper.insertReplyMailbox(mailboxCustom);
		return n;
	}
	//通过mailid来删除邮件信息
	@Override
	public void delMailboxById(int mailid) throws Exception {
		mailboxCustomMapper.delMailboxById(mailid);
		
	}

//点赞功能
	@Override
	public ArticlesCustom updateArticlesCountByAid(ArticlesCustom articlesCustom,Praise praise)
			throws Exception {
	
		       Praise praise1 =  praiseMapper.findPraiseByUidAndAid(praise);
		      
		       if(praise1==null||praise1.getStatus()==0){
		    	   
				articlesCustomMapper.updateArticlesCountByAid(articlesCustom);
				if(praise1==null){
					
					praise.setStatus(1);
					praiseMapper.insertPraise(praise);
				
				}else{
				if(praise1.getStatus()==0){
					praise1.setStatus(1);
					praiseMapper.updatePraiseByUidAndAid(praise1);
				}
				}
	            }else{
		       if(praise1.getStatus()==1){
		    	   return null;
		       }
	            }
				return articlesCustomMapper.findArticlesCustomByAid(articlesCustom.getAid());
		
		
	}

//添加文章
	@Override
	public int addArticles(ArticlesCustom articlesCustom)
			throws Exception {

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

	//通过aid来查询商品的详细信息和评论
	@Override
	public ArticlesCustom findArticlesByAid(int aid) throws Exception {
		ArticlesCustom articlesCustom =  articlesCustomMapper.findArticlesCustomByAid(aid);
		Categories category =  categoriesMapper.findCategoriesById(articlesCustom.getCid());
		articlesCustom.setCategory(category.getName());
		articlesCustom.setViews(articlesCustom.getViews()+1);
		articlesCustomMapper.updateArticlesViewsByAid(articlesCustom);
		Tags_articles tags_articles =  tags_articlesMapper.findTidByAid(articlesCustom.getAid());
		
		Tags tags = tagsMapper.findTagsByTid(tags_articles.getTid());
		articlesCustom.setTags(tags.getName());
		return articlesCustom;
	}
	
	

	//通过aid来查询评论
	@Override
	public PageBean findCommentsCustomByAid(PageBean pageBean)	
			throws Exception {
	
		    int pNum = pageBean.getpNum();
			 int start = (pNum-1) * NUMPERPAGE;
			 pageBean.setStart(start);
			 pageBean.setNumperpage(PageBean.NUMPERPAGE);
		List<CommentsCustom> commentsCustoms = commentsCustomMapper.findCommentsCustomByAid(pageBean);
		for (CommentsCustom commentsCustom : commentsCustoms) {
			UsersCustom usersCustom  = usersCustomMapper.findUserById(commentsCustom.getAuthorid());
			commentsCustom.setAvator(usersCustom.getAvator());
		}
		pageBean.setCommentsCustoms(commentsCustoms);
		
		//封装总记录条数
		int totalRecordNum = commentsCustomMapper.findtotalRecordNum(pageBean.getAid());
		pageBean.setTotalRecordNum(totalRecordNum);
		
		//计算总页数
		int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		/*if(commentsCustoms!=null){
		for (CommentsCustom commentsCustom : commentsCustoms) {
			if(commentsCustom.getParent()!=0){
						findCommentsCustomByAid(commentsCustom.getParent());
			}
		}
		}*/
		return pageBean;
	}

	//添加评论
	@Override
	public void insertCommentsCustom(CommentsCustom commentsCustom)
			throws Exception {
		
	    commentsCustomMapper.insertCommentsCustom(commentsCustom);
		
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

        //查询校园生活首页
		@Override
		public SchoolLife findSchoolLife() throws Exception {
			SchoolLife schoolLife = new SchoolLife();
			Categories categories = categoriesMapper.findCategoriesByName("校园八卦");
			schoolLife.setCid(categories.getCid());
			List<ArticlesCustom> schoolGossips = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setSchoolGossips(schoolGossips);
			
			Categories categories1 = categoriesMapper.findCategoriesByName("校园风云");
			schoolLife.setCid(categories1.getCid());
			List<ArticlesCustom> schoolSituations = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setSchoolSituations(schoolSituations);
			
			Categories categories2 = categoriesMapper.findCategoriesByName("校园资讯");
			schoolLife.setCid(categories2.getCid());
			List<ArticlesCustom> campusConsultations = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setCampusConsultations(campusConsultations);
			
			Categories categories3 = categoriesMapper.findCategoriesByName("校园活动");
			schoolLife.setCid(categories3.getCid());
			List<ArticlesCustom> schoolActivities = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setSchoolActivities(schoolActivities);
			return schoolLife;
		}

         //校园周边首页
		@Override
		public SchoolLife findSchoolSide() throws Exception {
			SchoolLife schoolLife = new SchoolLife();
			Categories categories = categoriesMapper.findCategoriesByName("口碑商");
			schoolLife.setCid(categories.getCid());
			List<ArticlesCustom> reputationBusinessman = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setReputationBusinessman(reputationBusinessman);
			
			Categories categories1 = categoriesMapper.findCategoriesByName("有活动");
			schoolLife.setCid(categories1.getCid());
			List<ArticlesCustom> activities = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setActivities(activities);
			
			Categories categories2 = categoriesMapper.findCategoriesByName("便生活");
			schoolLife.setCid(categories2.getCid());
			List<ArticlesCustom> thenlifes = articlesCustomMapper.findArticlesByCid(schoolLife);
			schoolLife.setThenlifes(thenlifes);
			return schoolLife;
		}

           //通过邮箱来查询用户
		@Override
		public UsersCustom findUsersCustomByEmail(String email,String username) throws Exception {
			UsersCustom usersCustom  = new UsersCustom();
			usersCustom.setEmail(email);
			usersCustom.setUsername(username);
			UsersCustom usersCustom1 =  usersCustomMapper.findUserByEmail(usersCustom);
			//创建Session
		
	  		Session session = MailUtils.createSession();
	  
	  		//编写邮件
	  		try {
	  		
				Message message = MailUtils.generateMessage(session, usersCustom1);
				//发送邮件
				MailUtils.sendMail(message, session);
				return usersCustom1;
			} catch (Exception e) {
				throw new RuntimeException("发送激活邮件失败");
			}
			
		}

        //根据用户的id来查询文章
		@Override
		public PageBean findArticlesByAuthorid(PageBean pageBean)
				throws Exception {
			int pNum = pageBean.getpNum();
			int start = (pNum-1) * NUMPERPAGE;
			pageBean.setStart(start);
			pageBean.setNumperpage(PageBean.NUMPERPAGE);
		    List<ArticlesCustom> articlesCustoms = articlesCustomMapper.findArticlesByAuthorId(pageBean);
		    for (ArticlesCustom articlesCustom : articlesCustoms) {
		    	Categories category = categoriesMapper.findCategoriesById(articlesCustom.getCid());
		    	articlesCustom.setCategory(category.getName());
		    	
		    	Tags_articles tags_articles =  tags_articlesMapper.findTidByAid(articlesCustom.getAid());
				Tags tags = tagsMapper.findTagsByTid(tags_articles.getTid());
				articlesCustom.setTags(tags.getName());
			}
              pageBean.setArticlesCustoms(articlesCustoms);
			

			//封装总记录条数
            int  totalRecordNum = articlesCustomMapper.findtotalRecordNumByAuthorId(pageBean);
			pageBean.setTotalRecordNum(totalRecordNum);
			
			//计算总页数
			int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
			pageBean.setTotalPageNum(totalpageNum);
			return pageBean;
		}

        //根据文章的aid来修改文章
		@Override
		public void updateArticlesCustom(ArticlesCustom articlesCustom)
				throws Exception {
			Categories category =  categoriesMapper.findCategoriesByName(articlesCustom.getCategory());
			articlesCustom.setCid(category.getCid());
			 tags.setName(articlesCustom.getTags());
			 Tags tags1 =  tagsMapper.findTagsByName(tags);
			   
			   if(tags1==null){
			    	
			      int b= tagsMapper.insertTags(tags);
				   tags_articles.setTid(tags.getTid());
			  }else{
				 
				  tags_articles.setTid(tags1.getTid());
			   }
			   articlesCustomMapper.updateArticlesCustomByAid(articlesCustom);
			
		}

     
		
	
	

}
