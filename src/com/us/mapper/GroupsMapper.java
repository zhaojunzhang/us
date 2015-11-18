package com.us.mapper;

import com.us.po.Groups;

public interface GroupsMapper {
    //插入用户组
	public int insertGroups(Groups groups)throws Exception;
	//通过用户组gid查询用户组
	public Groups findGroupsByGid(int gid)throws Exception;
	//根据分组名和权限来查询分组id
	public Groups findGroupsByNameAndAuthority(Groups groups)throws Exception;
	
}
