package com.us.mapper;

import com.us.po.Praise;

public interface PraiseMapper {
	//插入点赞信息
	public int insertPraise(Praise praise)throws Exception;
	//查询点赞的信息
	public Praise findPraiseByUidAndAid(Praise praise)throws Exception;
    //通过Uid 和 Aid来修改点赞状态
	public void updatePraiseByUidAndAid(Praise praise)throws Exception;
}
