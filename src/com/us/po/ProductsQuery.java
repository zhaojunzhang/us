package com.us.po;


//包装类型
public class ProductsQuery {
	private String pcname;
	private String type;
	public String getPcname() {
		return pcname;
	}
	public void setPcname(String pcname) {
		this.pcname = pcname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private Images images;
	private Product_categories pcategories;
	private Products products;
	private ProductsExtend productsExtend;
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public ProductsExtend getProductsExtend() {
		return productsExtend;
	}
	public void setProductsExttend(ProductsExtend productsExtend) {
		this.productsExtend = productsExtend;
	}
	public Images getImages() {
		return images;
	}
	public void setImages(Images images) {
		this.images = images;
	}
	public Product_categories getPcategories() {
		return pcategories;
	}
	public void setPcategories(Product_categories pcategories) {
		this.pcategories = pcategories;
	}
	public void setProductsExtend(ProductsExtend productsExtend) {
		this.productsExtend = productsExtend;
	}
	
}
