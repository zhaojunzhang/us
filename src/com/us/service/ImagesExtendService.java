package com.us.service;

import java.util.List;

import com.us.po.ImagesExtend;

public interface ImagesExtendService {
	//ͨ��ͼƬid����ͼƬ
	public List<ImagesExtend> findImagesExtendByPid (int pid)throws Exception;
}
