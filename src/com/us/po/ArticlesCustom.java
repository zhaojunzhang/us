package com.us.po;

import java.util.List;

public class ArticlesCustom extends Articles{

	private String tags;  //��ǩ
	private String category; //����
	private String image;//ͼƬ
	private List<Images> images; //ͼƬ��List����
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
