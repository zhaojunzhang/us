package com.us.mapper;

import java.util.List;

import com.us.po.ImagesExtend;

public interface ImagesExtendMapper {
	// ͨ��ͼƬid����ͼƬ
		public List<ImagesExtend> findImagesExtendByPid(int id) throws Exception;

	// ͨ��ͼƬid����ͼƬ
	public ImagesExtend findImagesExtendById(int id) throws Exception;
	// �����ƷͼƬ
	public int insertImages(ImagesExtend imagesExtend) throws Exception;
}
