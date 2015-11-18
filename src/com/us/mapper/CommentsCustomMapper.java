package com.us.mapper;

import java.util.List;

import com.us.po.CommentsCustom;
import com.us.po.PageBean;

public interface CommentsCustomMapper {

	//ͨ��aid����ѯ����
	public List<CommentsCustom> findCommentsCustomByAid(PageBean pageBean)throws Exception;
	
	//����aid����ѯ�ܼ�¼����
	public int findtotalRecordNum(int aid)throws Exception;
	
	//ͨ��coid����ѯ�ظ�������
	public List<CommentsCustom> findCommentsCustomByCoid(int coid)throws Exception;
	
	//�������
	public void insertCommentsCustom(CommentsCustom commentsCustom)throws Exception;
	
}
