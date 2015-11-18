package com.us.service;


import java.util.List;

import com.us.po.NotesExtend;
import com.us.po.PageBean;

public interface NotesExtendService {
	//根据noteid查找uid
		public Integer findUidByNoteid(int noteid)throws Exception;
	// 分页查询留言
		public PageBean findByPageNotes(PageBean pageBean) throws Exception;
		// 分页查询留言
	public PageBean findByPageNotesName(PageBean pageBean) throws Exception;
			
	  //生成留言
	  	public int insertNotes(NotesExtend notesExtend )throws Exception;
	  //根据查找所有留言
		public List<NotesExtend> findAllNotesWeb()throws Exception;
		 //根据查找所有留言
		public List<NotesExtend> findAllNotes()throws Exception;
		//根据id删除留言
		public void deleteNotes(int id)throws Exception;
	 
}
