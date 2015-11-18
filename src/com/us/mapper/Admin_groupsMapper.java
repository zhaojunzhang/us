package com.us.mapper;

import com.us.po.Admin_groups;

public interface Admin_groupsMapper {
	//根据gid来查询管理员信息
	public Admin_groups findAdminGroupsByAgid(int agid)throws Exception;

}
