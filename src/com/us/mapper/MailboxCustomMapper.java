package com.us.mapper;

import java.util.List;

import com.us.po.MailboxCustom;
import com.us.po.PageBean;

public interface MailboxCustomMapper {
	//��ѯ�ܼ�¼����
	public int findtotalRecordNum(int uid)throws Exception;
	//�����ҳ��ѯ���ݿ�
	public List<MailboxCustom> findByPageByUid(PageBean pageBean)throws Exception;
    //��������mailid����ѯ��������
	public MailboxCustom findMailboxById(int mailid)throws Exception;
	//����������Ϣ
	public int insertReplyMailbox(MailboxCustom mailboxCustom)throws Exception;
	//ͨ��mailid��ɾ���ʼ���Ϣ
	public void delMailboxById(int mailid)throws Exception;
	
}
