package com.us.service;


import java.util.List;

import com.us.po.NotesExtend;
import com.us.po.PageBean;

public interface NotesExtendService {
	//����noteid����uid
		public Integer findUidByNoteid(int noteid)throws Exception;
	// ��ҳ��ѯ����
		public PageBean findByPageNotes(PageBean pageBean) throws Exception;
		// ��ҳ��ѯ����
	public PageBean findByPageNotesName(PageBean pageBean) throws Exception;
			
	  //��������
	  	public int insertNotes(NotesExtend notesExtend )throws Exception;
	  //���ݲ�����������
		public List<NotesExtend> findAllNotesWeb()throws Exception;
		 //���ݲ�����������
		public List<NotesExtend> findAllNotes()throws Exception;
		//����idɾ������
		public void deleteNotes(int id)throws Exception;
	 
}
