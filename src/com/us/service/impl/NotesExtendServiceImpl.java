package com.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.us.mapper.NotesExtendMapper;
import com.us.mapper.UsersCustomMapper;
import com.us.po.NotesExtend;
import com.us.po.PageBean;
import com.us.po.UsersCustom;
import com.us.service.NotesExtendService;



public class NotesExtendServiceImpl implements NotesExtendService{
	@Autowired
	UsersCustomMapper usersCustomMapper;
	@Autowired NotesExtendMapper notesExtendMapper;
	public static final int NUMPERPAGE=10;

	//����noteid����uid
		public Integer findUidByNoteid(int noteid)throws Exception{
			return notesExtendMapper.findUidByNoteid(noteid);
		}
	//��ҳ��ѯ����
	public PageBean findByPageNotes(PageBean pageBean)throws Exception{
		//����ҳ���ÿҳ����  ���㿪ʼ����
		  int pNum = pageBean.getpNum();
		 int start = (pNum-1) * NUMPERPAGE;
			// ��װ��ǰҳ��
	 	   pageBean.setStart(start);
		   pageBean.setNumperpage(PageBean.NUMPERPAGE);
		
			pageBean.setpNum(pNum);
			// ����DAO���з�ҳ��ѯ --- �������
		 List<NotesExtend> notesExtend=notesExtendMapper.findByPageNotes(pageBean);
		 for (NotesExtend notesExtend2 : notesExtend) {
			UsersCustom usersCustom =  usersCustomMapper.findUserById(notesExtend2.getUid());
			notesExtend2.setUsername(usersCustom.getUsername());
			notesExtend2.setIp(usersCustom.getIp());
		}
		 pageBean.setNotesExtend(notesExtend);
			//��װ�ܼ�¼����
			int totalRecordNum = notesExtendMapper.findNotesCount();
			pageBean.setTotalRecordNum(totalRecordNum);
			
			//������ҳ��
			int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
			pageBean.setTotalPageNum(totalpageNum);
	   return pageBean;
	}
	//��ҳ��ѯ����
		public PageBean findByPageNotesName(PageBean pageBean)throws Exception{
			//����ҳ���ÿҳ����  ���㿪ʼ����
			  int pNum = pageBean.getpNum();
			 int start = (pNum-1) * NUMPERPAGE;
				// ��װ��ǰҳ��
		 	   pageBean.setStart(start);
			   pageBean.setNumperpage(PageBean.NUMPERPAGE);
				pageBean.setpNum(pNum);
				// ����DAO���з�ҳ��ѯ --- �������
			 List<NotesExtend> notesExtend=notesExtendMapper.findByPageNotesName(pageBean);
			 for (NotesExtend notesExtend2 : notesExtend) {
				UsersCustom usersCustom =  usersCustomMapper.findUserById(notesExtend2.getUid());
				notesExtend2.setUsername(usersCustom.getUsername());
				notesExtend2.setIp(usersCustom.getIp());
			}
			 pageBean.setNotesExtend(notesExtend);
				//��װ�ܼ�¼����

				int totalRecordNum = notesExtendMapper.findNotesCountName(pageBean.getUid());
				pageBean.setTotalRecordNum(totalRecordNum);
				
				//������ҳ��
				int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
				pageBean.setTotalPageNum(totalpageNum);
		   return pageBean;
		}
	//��������
	public int insertNotes(NotesExtend notesExtend) throws Exception {
		long time=System.currentTimeMillis();
		notesExtend.setNcreated(time);
		int n=notesExtendMapper.insertNotes(notesExtend);
		return n;
	}
	@Override
	//��ѯ���е�����
	public List<NotesExtend> findAllNotesWeb() throws Exception {
		
		return   notesExtendMapper.findAllNotesWeb();
	}
	@Override
	//��ѯ���е�����
	public List<NotesExtend> findAllNotes() throws Exception {
		
		return   notesExtendMapper.findAllNotes();
	}
	@Override
	//����idɾ������
	public void deleteNotes(int id) throws Exception {
		notesExtendMapper.deleteNotes(id);
	}
	
	
}
