package com.us.mapper;

import com.us.po.Groups;

public interface GroupsMapper {
    //�����û���
	public int insertGroups(Groups groups)throws Exception;
	//ͨ���û���gid��ѯ�û���
	public Groups findGroupsByGid(int gid)throws Exception;
	//���ݷ�������Ȩ������ѯ����id
	public Groups findGroupsByNameAndAuthority(Groups groups)throws Exception;
	
}
