package com.us.mapper;

import com.us.po.AdminsCustom;
import com.us.po.UsersCustom;

public interface AdminsCustomMapper {

	//�����û�����������½��·ϵͳ
	public AdminsCustom findAdminsPasswordAndUsername(AdminsCustom adminsCustom)throws Exception;
	//���¹���Ա�ĵ�¼ʱ��,���û��ͻ���ϵͳ���û���ip
    public void updateadminsCustomLoged(AdminsCustom adminsCustom)throws Exception;
    //ͨ������Աid����ѯ����Ա
    
}
