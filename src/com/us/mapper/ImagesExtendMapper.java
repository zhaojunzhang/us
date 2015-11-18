package com.us.mapper;

import java.util.List;

import com.us.po.ImagesExtend;

public interface ImagesExtendMapper {
	// 通过图片id查找图片
		public List<ImagesExtend> findImagesExtendByPid(int id) throws Exception;

	// 通过图片id查找图片
	public ImagesExtend findImagesExtendById(int id) throws Exception;
	// 添加商品图片
	public int insertImages(ImagesExtend imagesExtend) throws Exception;
}
