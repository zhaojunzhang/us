package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.UsersCustom;

public interface UsersCustomMapper {

	// 插入用户
	public int insertUsers(UsersCustom usersCustom) throws Exception;
	
	//根据用户手机号来查询用户
	public UsersCustom findUsersCustomByPhone(String phone)throws Exception;
	

	// 查询所有用户
	public List<UsersCustom> findAllUsers(PageBean pageBean)throws Exception;

	// 根据用户名密码查询用户
	public UsersCustom findUsernameAndPassword(UsersCustom usersCustom)
			throws Exception;

	// 按照用户名查询用户
	public UsersCustom findUserByUserName(String username) throws Exception;

	// 根据用户id来查询用户
	public UsersCustom findUserById(int uid) throws Exception;

	// 根据用户id来更改用户信息
	public void updateUserById(UsersCustom usersCustom) throws Exception;

	// 条件根据手机号来查询用户
	public List<UsersCustom> findUserByPhoneCondition(PageBean pageBean) throws Exception;
	//条件很据用户名来查询用户
	public List<UsersCustom> findUserByUsernameCondition(PageBean pageBean) throws Exception;
	//条件根据昵称来查询用户
	public List<UsersCustom> findUserByNicknameCondition(PageBean pageBean) throws Exception;
    //根据手机号来查询总记录数
	public int findtotalRecordNumByPhone(PageBean pageBean) throws Exception;
	//根据用户名来查询总记录数
	public int findtotalRecordNumByUsername(PageBean pageBean) throws Exception;
	//根据昵称来查询总记录数
	public int findtotalRecordNumByNickname(PageBean pageBean) throws Exception;
	
	// 根据用户的id删除用户
	public void deleteUserById(int uid) throws Exception;

	// 根据邮箱查询用户
	public UsersCustom findUserByEmail(UsersCustom usersCustom) throws Exception;
	//通过用户组gid来查询用户
	public List<UsersCustom> findUserByGid(int id)throws Exception;
    //更新用户的登录时间,和用户客户端系统和ip
	public void updateUserLoged(UsersCustom usersCustom)throws Exception;
	//通过用户id来修改用户的密码
	public void updateUserPasswordById(UsersCustom usersCustom)throws Exception;
	//通过用户id来修改用户的绑定手机
	public void updateUserPhoneById(UsersCustom usersCustom)throws Exception;
	//查询用户的总数
	public int findtotalRecordNum()throws Exception;
	
	//通过用户uid来更改用户的分组gid
	public void updateUsersAuthorityByGid(UsersCustom usersCustom)throws Exception;
	
	//根据邮箱来查用户名
	public String findUsernameByEmail(String email)throws Exception;
	
}
