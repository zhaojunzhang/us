package com.us.mapper;

import com.us.po.AdminsCustom;
import com.us.po.UsersCustom;

public interface AdminsCustomMapper {

	//根据用户名密码来登陆管路系统
	public AdminsCustom findAdminsPasswordAndUsername(AdminsCustom adminsCustom)throws Exception;
	//更新管理员的登录时间,和用户客户端系统和用户的ip
    public void updateadminsCustomLoged(AdminsCustom adminsCustom)throws Exception;
    //通过管理员id来查询管理员
    
}
