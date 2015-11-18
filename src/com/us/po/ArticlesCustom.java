package com.us.po;

import java.util.List;

public class ArticlesCustom extends Articles{

	private String tags;  //标签
	private String category; //分类
	private String image;//图片
	private List<Images> images; //图片的List集合
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Images> getImages() {
		return images;
	}
	public void setImages(List<Images> images) {
		this.images = images;
	}
	
	
	
	
}
