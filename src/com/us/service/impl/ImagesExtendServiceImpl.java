package com.us.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.us.mapper.ImagesExtendMapper;
import com.us.po.ImagesExtend;
import com.us.service.ImagesExtendService;

public class ImagesExtendServiceImpl implements ImagesExtendService {
	@Autowired
	ImagesExtendMapper imagesExtendMapper;
	
	//通过商品的pid查找图片
	@Override
	public List<ImagesExtend> findImagesExtendByPid(int pid) throws Exception {
		ImagesExtend imagesExtend=new ImagesExtend();
		imagesExtend.setPid(pid);
		return 	imagesExtendMapper.findImagesExtendByPid(pid);
	}
}
