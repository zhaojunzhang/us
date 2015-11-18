package com.us.mapper;

import java.util.List;

import com.us.po.MailboxCustom;
import com.us.po.PageBean;

public interface MailboxCustomMapper {
	//查询总记录条数
	public int findtotalRecordNum(int uid)throws Exception;
	//物理分页查询数据库
	public List<MailboxCustom> findByPageByUid(PageBean pageBean)throws Exception;
    //根据信箱mailid来查询个人信箱
	public MailboxCustom findMailboxById(int mailid)throws Exception;
	//插入信箱信息
	public int insertReplyMailbox(MailboxCustom mailboxCustom)throws Exception;
	//通过mailid来删除邮件信息
	public void delMailboxById(int mailid)throws Exception;
	
}
