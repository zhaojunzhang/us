package com.us.service;

import java.util.List;

import com.us.po.ImagesExtend;

public interface ImagesExtendService {
	//Õ®π˝Õº∆¨id≤È’“Õº∆¨
	public List<ImagesExtend> findImagesExtendByPid (int pid)throws Exception;
}
