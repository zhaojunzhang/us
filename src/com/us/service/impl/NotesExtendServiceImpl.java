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

	//根据noteid查找uid
		public Integer findUidByNoteid(int noteid)throws Exception{
			return notesExtendMapper.findUidByNoteid(noteid);
		}
	//分页查询留言
	public PageBean findByPageNotes(PageBean pageBean)throws Exception{
		//根据页码和每页条数  计算开始索引
		  int pNum = pageBean.getpNum();
		 int start = (pNum-1) * NUMPERPAGE;
			// 封装当前页码
	 	   pageBean.setStart(start);
		   pageBean.setNumperpage(PageBean.NUMPERPAGE);
		
			pageBean.setpNum(pNum);
			// 调用DAO进行分页查询 --- 结果数据
		 List<NotesExtend> notesExtend=notesExtendMapper.findByPageNotes(pageBean);
		 for (NotesExtend notesExtend2 : notesExtend) {
			UsersCustom usersCustom =  usersCustomMapper.findUserById(notesExtend2.getUid());
			notesExtend2.setUsername(usersCustom.getUsername());
			notesExtend2.setIp(usersCustom.getIp());
		}
		 pageBean.setNotesExtend(notesExtend);
			//封装总记录条数
			int totalRecordNum = notesExtendMapper.findNotesCount();
			pageBean.setTotalRecordNum(totalRecordNum);
			
			//计算总页数
			int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
			pageBean.setTotalPageNum(totalpageNum);
	   return pageBean;
	}
	//分页查询留言
		public PageBean findByPageNotesName(PageBean pageBean)throws Exception{
			//根据页码和每页条数  计算开始索引
			  int pNum = pageBean.getpNum();
			 int start = (pNum-1) * NUMPERPAGE;
				// 封装当前页码
		 	   pageBean.setStart(start);
			   pageBean.setNumperpage(PageBean.NUMPERPAGE);
				pageBean.setpNum(pNum);
				// 调用DAO进行分页查询 --- 结果数据
			 List<NotesExtend> notesExtend=notesExtendMapper.findByPageNotesName(pageBean);
			 for (NotesExtend notesExtend2 : notesExtend) {
				UsersCustom usersCustom =  usersCustomMapper.findUserById(notesExtend2.getUid());
				notesExtend2.setUsername(usersCustom.getUsername());
				notesExtend2.setIp(usersCustom.getIp());
			}
			 pageBean.setNotesExtend(notesExtend);
				//封装总记录条数

				int totalRecordNum = notesExtendMapper.findNotesCountName(pageBean.getUid());
				pageBean.setTotalRecordNum(totalRecordNum);
				
				//计算总页数
				int totalpageNum = (totalRecordNum+PageBean.NUMPERPAGE-1)/PageBean.NUMPERPAGE;
				pageBean.setTotalPageNum(totalpageNum);
		   return pageBean;
		}
	//插入留言
	public int insertNotes(NotesExtend notesExtend) throws Exception {
		long time=System.currentTimeMillis();
		notesExtend.setNcreated(time);
		int n=notesExtendMapper.insertNotes(notesExtend);
		return n;
	}
	@Override
	//查询所有的留言
	public List<NotesExtend> findAllNotesWeb() throws Exception {
		
		return   notesExtendMapper.findAllNotesWeb();
	}
	@Override
	//查询所有的留言
	public List<NotesExtend> findAllNotes() throws Exception {
		
		return   notesExtendMapper.findAllNotes();
	}
	@Override
	//根据id删除留言
	public void deleteNotes(int id) throws Exception {
		notesExtendMapper.deleteNotes(id);
	}
	
	
}
