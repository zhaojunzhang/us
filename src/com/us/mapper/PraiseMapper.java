package com.us.mapper;

import com.us.po.Praise;

public interface PraiseMapper {
	//���������Ϣ
	public int insertPraise(Praise praise)throws Exception;
	//��ѯ���޵���Ϣ
	public Praise findPraiseByUidAndAid(Praise praise)throws Exception;
    //ͨ��Uid �� Aid���޸ĵ���״̬
	public void updatePraiseByUidAndAid(Praise praise)throws Exception;
}
