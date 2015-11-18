package com.us.mapper;

import java.util.List;

import com.us.po.NotesExtend;
import com.us.po.PageBean;

//����ǽ
public interface NotesExtendMapper {
	
	//����noteid����uid
	public Integer findUidByNoteid(int noteid)throws Exception;
	//ͳ�����Ե�����
	public Integer findNotesCount()throws Exception;
	//ͳ�����Ե�����
		public Integer findNotesCountName(Integer uid)throws Exception;
	// ��ҳ��ѯ����
	public List<NotesExtend> findByPageNotes(PageBean pageBean) throws Exception;
	// ��ҳ��ѯ����
	public List<NotesExtend> findByPageNotesName(PageBean pageBean) throws Exception;
	//��������
	public int insertNotes(NotesExtend notesExtend )throws Exception;
	//���ݲ�����������
	public List<NotesExtend> findAllNotesWeb()throws Exception;
	//���ݲ�����������
	public List<NotesExtend> findAllNotes()throws Exception;
	//����idɾ������
	public void deleteNotes(int id)throws Exception;
}
