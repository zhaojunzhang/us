package com.us.mapper;

import com.us.po.Admin_groups;

public interface Admin_groupsMapper {
	//����gid����ѯ����Ա��Ϣ
	public Admin_groups findAdminGroupsByAgid(int agid)throws Exception;

}
