package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.UsersCustom;

public interface UsersCustomMapper {

	// �����û�
	public int insertUsers(UsersCustom usersCustom) throws Exception;
	
	//�����û��ֻ�������ѯ�û�
	public UsersCustom findUsersCustomByPhone(String phone)throws Exception;
	

	// ��ѯ�����û�
	public List<UsersCustom> findAllUsers(PageBean pageBean)throws Exception;

	// �����û��������ѯ�û�
	public UsersCustom findUsernameAndPassword(UsersCustom usersCustom)
			throws Exception;

	// �����û�����ѯ�û�
	public UsersCustom findUserByUserName(String username) throws Exception;

	// �����û�id����ѯ�û�
	public UsersCustom findUserById(int uid) throws Exception;

	// �����û�id�������û���Ϣ
	public void updateUserById(UsersCustom usersCustom) throws Exception;

	// ���������ֻ�������ѯ�û�
	public List<UsersCustom> findUserByPhoneCondition(PageBean pageBean) throws Exception;
	//�����ܾ��û�������ѯ�û�
	public List<UsersCustom> findUserByUsernameCondition(PageBean pageBean) throws Exception;
	//���������ǳ�����ѯ�û�
	public List<UsersCustom> findUserByNicknameCondition(PageBean pageBean) throws Exception;
    //�����ֻ�������ѯ�ܼ�¼��
	public int findtotalRecordNumByPhone(PageBean pageBean) throws Exception;
	//�����û�������ѯ�ܼ�¼��
	public int findtotalRecordNumByUsername(PageBean pageBean) throws Exception;
	//�����ǳ�����ѯ�ܼ�¼��
	public int findtotalRecordNumByNickname(PageBean pageBean) throws Exception;
	
	// �����û���idɾ���û�
	public void deleteUserById(int uid) throws Exception;

	// ���������ѯ�û�
	public UsersCustom findUserByEmail(UsersCustom usersCustom) throws Exception;
	//ͨ���û���gid����ѯ�û�
	public List<UsersCustom> findUserByGid(int id)throws Exception;
    //�����û��ĵ�¼ʱ��,���û��ͻ���ϵͳ��ip
	public void updateUserLoged(UsersCustom usersCustom)throws Exception;
	//ͨ���û�id���޸��û�������
	public void updateUserPasswordById(UsersCustom usersCustom)throws Exception;
	//ͨ���û�id���޸��û��İ��ֻ�
	public void updateUserPhoneById(UsersCustom usersCustom)throws Exception;
	//��ѯ�û�������
	public int findtotalRecordNum()throws Exception;
	
	//ͨ���û�uid�������û��ķ���gid
	public void updateUsersAuthorityByGid(UsersCustom usersCustom)throws Exception;
	
	//�������������û���
	public String findUsernameByEmail(String email)throws Exception;
	
}
