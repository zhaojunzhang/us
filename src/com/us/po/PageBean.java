package com.us.po;

import java.util.List;

public class PageBean {
	public static final int NUMPERPAGE=10;//每条多少页
	private int pNum;//当前第几页
	private int totalPageNum;//总页数
	private int totalRecordNum;//总记录数
	private String type;
	private String usersName;
	private List<OrdersExtend>OrdersExtend;
	public List<OrdersExtend> getOrdersExtend() {
		return OrdersExtend;
	}
	public void setOrdersExtend(List<OrdersExtend> ordersExtend) {
		OrdersExtend = ordersExtend;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	private List<MailboxCustom> mailboxCustoms;
	public List<Product_commentsExtend> getProduct_commentsExtend() {
		return product_commentsExtend;
	}
	public void setProduct_commentsExtend(
			List<Product_commentsExtend> product_commentsExtend) {
		this.product_commentsExtend = product_commentsExtend;
	}
	private List<Product_commentsExtend> product_commentsExtend;
	private int start;
	private int numperpage;
	private int uid;
	private List<ArticlesCustom> articlesCustoms;
	private int cid;
	private String conditionName;
	private String conditionValue;
	private List<UsersCustom> usersCustoms;
	private List<CommentsCustom> commentsCustoms;
	private int aid;
    private String name;
	private List<ProductsExtend> productsExtend;
	private List<NotesExtend> notesExtend;
	private int pid;
	private int orderid;
	private List<OrderitemsExtend> orderitemsExtend;
	private List<OrdersExtend> ordersExtends;
	private String pname;
	private int status;
	private int pcid;
	private int authorid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNumperpage() {
		return numperpage;
	}
	public void setNumperpage(int numperpage) {
		this.numperpage = numperpage;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	
	public int getTotalRecordNum() {
		return totalRecordNum;
	}
	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}
	public List<MailboxCustom> getMailboxCustoms() {
		return mailboxCustoms;
	}
	public void setMailboxCustoms(List<MailboxCustom> mailboxCustoms) {
		this.mailboxCustoms = mailboxCustoms;
	}
	public List<ArticlesCustom> getArticlesCustoms() {
		return articlesCustoms;
	}
	public void setArticlesCustoms(List<ArticlesCustom> articlesCustoms) {
		this.articlesCustoms = articlesCustoms;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public List<UsersCustom> getUsersCustoms() {
		return usersCustoms;
	}
	public void setUsersCustoms(List<UsersCustom> usersCustoms) {
		this.usersCustoms = usersCustoms;
	}
	public List<CommentsCustom> getCommentsCustoms() {
		return commentsCustoms;
	}
	public void setCommentsCustoms(List<CommentsCustom> commentsCustoms) {
		this.commentsCustoms = commentsCustoms;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductsExtend> getProductsExtend() {
		return productsExtend;
	}
	public void setProductsExtend(List<ProductsExtend> productsExtend) {
		this.productsExtend = productsExtend;
	}
	public List<NotesExtend> getNotesExtend() {
		return notesExtend;
	}
	public void setNotesExtend(List<NotesExtend> notesExtend) {
		this.notesExtend = notesExtend;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public List<OrderitemsExtend> getOrderitemsExtend() {
		return orderitemsExtend;
	}
	public void setOrderitemsExtend(List<OrderitemsExtend> orderitemsExtend) {
		this.orderitemsExtend = orderitemsExtend;
	}
	
	public List<OrdersExtend> getOrdersExtends() {
		return ordersExtends;
	}
	public void setOrdersExtends(List<OrdersExtend> ordersExtends) {
		this.ordersExtends = ordersExtends;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPcid() {
		return pcid;
	}
	public void setPcid(int pcid) {
		this.pcid = pcid;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	
	
	
	
}
