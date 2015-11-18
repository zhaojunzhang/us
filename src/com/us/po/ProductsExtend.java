package com.us.po;

import java.util.List;


//商品的扩展类
public class ProductsExtend extends Products{
	
        private String pcname;
        private String avator;//用户的时间
		private String count;
        private String url;
        private String username;
        private List<ImagesExtend> imagesExtend;
        private List<Product_commentsExtend>  product_commentsExtend;
        public List<Product_commentsExtend> getProduct_commentsExtend() {
			return product_commentsExtend;
		}

		public void setProduct_commentsExtend(
				List<Product_commentsExtend> product_commentsExtend) {
			this.product_commentsExtend = product_commentsExtend;
		}
		private String mainimages;
		public String getMainimages() {
			return mainimages;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setMainimages(String mainimages) {
			this.mainimages = mainimages;
		}
	     public String getAvator() {
				return avator;
			}

			public void setAvator(String avator) {
				this.avator = avator;
			}
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getPcname() {
			return pcname;
		}

		public void setPcname(String pcname) {
			this.pcname = pcname;
		}
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public List<ImagesExtend> getImagesExtend() {
			return imagesExtend;
		}

		public void setImagesExtend(List<ImagesExtend> imagesExtend) {
			this.imagesExtend = imagesExtend;
		}

}
