package com.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.us.mapper.ImagesExtendMapper;
import com.us.mapper.OrderitemsExtendMapper;
import com.us.mapper.OrdersExtendMapper;
import com.us.mapper.Product_categoriesMapper;
import com.us.mapper.Product_commentsMapper;
import com.us.mapper.ProductsExtendMapper;
import com.us.mapper.UsersCustomMapper;
import com.us.po.ImagesExtend;
import com.us.po.OrderitemsExtend;
import com.us.po.OrdersExtend;
import com.us.po.PageBean;
import com.us.po.Product_categories;
import com.us.po.Product_commentsExtend;
import com.us.po.ProductsExtend;
import com.us.po.ProductsQuery;
import com.us.po.UsersCustom;
import com.us.service.ProductsExtendService;

public class ProductsExtendServiceImpl implements ProductsExtendService {
	OrderitemsExtend orderitemsExtend = new OrderitemsExtend();
	ProductsExtend productsExtend = new ProductsExtend();
	OrdersExtend ordersExtend = new OrdersExtend();
	Product_categories product_categories = new Product_categories();
	ImagesExtend imagesExtend = new ImagesExtend();
	public static final int NUMPERPAGE = 10;
	@Autowired
	ProductsExtendMapper productsExtendMapper;
	@Autowired
	ImagesExtendMapper imagesExtendMapper;
	@Autowired
	Product_categoriesMapper product_categoriesMaper;
	@Autowired
	UsersCustomMapper usersCustomMapper;
	@Autowired
	OrdersExtendMapper ordersExtendMapper;
	@Autowired
	OrderitemsExtendMapper orderitemsExtendMapper;
	@Autowired
	Product_categoriesMapper product_categoriesMapper ;
	@Autowired
	Product_commentsMapper product_commentsMapper; 
	// 查询所有status=1，quantity！=0的商品
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception{
		return productsExtendMapper.findAllProducts(productsExtend);
		
	}
	
	
	//通过orderid查找用户uid
			public int findUIdByOrderid(int orderid)throws Exception{
			return  ordersExtendMapper.findUIdByOrderid(orderid);
			}
	//1.根据pid找到orderID。
		public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception{
			
			return orderitemsExtendMapper.findOrderidByPid(pid);
		}

	//按pid查询商品评论的总数
		public int countProduct_ommentsByPid(int pid)throws Exception{
			
			return product_commentsMapper.countProduct_ommentsByPid(pid);			
		}
	//查看商品的浏览次数
		public int productsExtendViews(int pid)throws Exception{
			System.out.println("-pid--------------"+pid);
			productsExtendMapper.productsExtendView(pid);
		
			return 1;
			
		}
	//插入评论
		public int  insertProduct_comments(Product_commentsExtend product_commentsExtend ) throws Exception{
			//获得pid,uid,created
			long time=System.currentTimeMillis();
			product_commentsExtend.setCreated(time);
			product_commentsMapper.insertProduct_comments(product_commentsExtend);
			return 1;		
		}
	//通过orderitemsid查找pid
		public int  findPidByOrderitemsid(int orderitemsid)throws Exception{
			
			return orderitemsExtendMapper.findPidByOrderitemsid(orderitemsid);
		}
	//通过商品id号查找商品分类
		public int findPcateByPid(int pid)throws Exception{
			
			return  productsExtendMapper.findPcateByPid(pid);
		}
	

	// 根据商品的名找到商品id
	public List<ProductsExtend> findPidByName(String name) throws Exception {
		return productsExtendMapper.findPidByName(name);

	}

	// 根据商品的名称进行分页
	public PageBean findOrderitemsByPid(PageBean pageBean) throws Exception {

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		// 调用DAO进行分页查询 --- 结果数据

		List<OrderitemsExtend> orderitemsExtend = orderitemsExtendMapper
				.findOrderitemsExtendByPid(pageBean);
		for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
			// 通过orderid查找用户id
			int uid = ordersExtendMapper.findUIdByOrderid(orderitemsExtend2
					.getOrderid());
			// 通过uid查找用户信息
			UsersCustom usersCustom = usersCustomMapper.findUserById(uid);
			//
			// 通过orderitemsid查找pid
			int pid = orderitemsExtendMapper
					.findPidByOrderitemsid(orderitemsExtend2.getOrderitemid());
			// 通过pid查找商品的信息
			ProductsExtend productsExtend = productsExtendMapper
					.findProductsById(pid);
			orderitemsExtend2.setUsername(usersCustom.getUsername());// 用户名
			orderitemsExtend2.setIp(usersCustom.getIp());// 用户IP地址
			orderitemsExtend2.setName(productsExtend.getName());// 名称
			orderitemsExtend2.setSoldprice(productsExtend.getSoldprice());// 价格
			orderitemsExtend2.setQuantity(productsExtend.getQuantity());// 数量
			orderitemsExtend2.setType(productsExtend.getType());// 类型
		}

		pageBean.setOrderitemsExtend(orderitemsExtend);
		// 封装总记录条数
		int totalRecordNum = orderitemsExtendMapper.countOrdritemsByPid(pageBean);
		pageBean.setTotalRecordNum(totalRecordNum);

		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;

	}

	// 分页查询商品
	public PageBean findOrderitemsByOrderid(PageBean pageBean) throws Exception {

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// 封装当前页码
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		pageBean.setpNum(pNum);
		// 调用DAO进行分页查询 --- 结果数据
		List<OrderitemsExtend> orderitemsExtend = orderitemsExtendMapper
				.findOrderitemsExtendByPage(pageBean);
		for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
			// 通过orderid查找用户id
			int uid = ordersExtendMapper.findUIdByOrderid(orderitemsExtend2
					.getOrderid());
			// 通过uid查找用户信息
			UsersCustom usersCustom = usersCustomMapper.findUserById(uid);
			// 通过orderitemsid查找pid
			int pid = orderitemsExtendMapper
					.findPidByOrderitemsid(orderitemsExtend2.getOrderitemid());
			// 通过pid查找商品的信息
			ProductsExtend productsExtend = productsExtendMapper
					.findProductsById(pid);
			orderitemsExtend2.setUsername(usersCustom.getUsername());// 用户名
			orderitemsExtend2.setIp(usersCustom.getIp());// 用户IP地址
			orderitemsExtend2.setName(productsExtend.getName());// 名称
			orderitemsExtend2.setSoldprice(productsExtend.getSoldprice());// 价格
			orderitemsExtend2.setQuantity(productsExtend.getQuantity());// 数量
			orderitemsExtend2.setType(productsExtend.getType());// 类型
		}

		pageBean.setOrderitemsExtend(orderitemsExtend);
		// 封装总记录条数
		int totalRecordNum = orderitemsExtendMapper.orderitemsCount();
		pageBean.setTotalRecordNum(totalRecordNum);

		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}

	// 限量抢购商品
	public void updateQuantity(ProductsExtend productsExtend) throws Exception {
		
		productsExtend.setQuantity(productsExtend.getQuantity() - 1);
		productsExtendMapper.updateQuantity(productsExtend);
	}

	// 查询所有订单
	public PageBean findOrderitemsExtendByPage(PageBean pageBean)
			throws Exception {

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// 封装当前页码
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
         int totalRecordNum=0;
		pageBean.setpNum(pNum);
		//订单表，查询所有的订单
		List<OrdersExtend> ordersExtends = ordersExtendMapper.findAllOrdersExtend(pageBean);
		//遍历订单，通过uid查找用户，获得用户名和手机号
		for (OrdersExtend ordersExtend : ordersExtends) {
			UsersCustom usersCustom  = usersCustomMapper.findUserById(ordersExtend.getUid());
			ordersExtend.setUname(usersCustom.getUsername());
			ordersExtend.setPhone(usersCustom.getPhone());
			//通过orderID查找订单明细表，再通过找到商品。
			List<OrderitemsExtend> orderitemsExtends =  orderitemsExtendMapper.findOrderitemsByOrderid(ordersExtend.getOrderid());
			for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
				ProductsExtend productsExtend = productsExtendMapper.findProductsById(orderitemsExtend.getPid());
				orderitemsExtend.setProductsExtend(productsExtend);
			}
			totalRecordNum = orderitemsExtends.size()+totalRecordNum;
			ordersExtend.setOrderitemsExtend(orderitemsExtends);
		}
		pageBean.setOrdersExtends(ordersExtends);
		pageBean.setTotalRecordNum(totalRecordNum);
		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}

	// 删除订单
	public void deleteOrdersByOrderid(int orderid) throws Exception {
		ordersExtendMapper.deleteOrdersByOrderid(orderid);
	}

	
	// 通过orderid查找订单明细表
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)
			throws Exception {
		return orderitemsExtendMapper.findOrderitemsByOrderid(orderid);
	}

	// 根据uid查找订单表
	public List<OrdersExtend> findOrdersExtendByUid(int uid) throws Exception {
		return ordersExtendMapper.findOrdersExtendByUid(uid);
	}

	// 根据用户id生成订单
	public int insertOrders(OrdersExtend ordersExtend) throws Exception {
		// 假设用户的id为3.
		long time = System.currentTimeMillis();
		ordersExtend.setCreated(time);
		return ordersExtendMapper.insertOrders(ordersExtend);

	}

	// 生成订单明细
	public int insertOrderitems(int uid,int pid) throws Exception {

		// 在生成订单明细之前得先有orderid, pid.,所以要先生成orders表by uid
		ordersExtend.setUid(uid);
		long time = System.currentTimeMillis();
		ordersExtend.setCreated(time);// 获得时间

		// 判断库存量是否大于1.如果小于1则不能抢购
		// 生成订单，更新库存
		ProductsExtend productsExtend2 = productsExtendMapper
				.findProductsById(pid);
		if (productsExtend2.getQuantity()!=0) {
			ordersExtendMapper.insertOrders(ordersExtend);// 插入订单
			productsExtend2.setQuantity(productsExtend2.getQuantity() - 1);
			productsExtendMapper.updateQuantity(productsExtend2);//更新库存
			orderitemsExtend.setPid(pid);
			orderitemsExtend.setOrderid(ordersExtend.getOrderid());
			orderitemsExtend.setUid(uid);
			orderitemsExtend.setCreated(time);
			orderitemsExtend.setOquantity(1);
			return orderitemsExtendMapper.insertOrderitems(orderitemsExtend);
		} else {

			return 0;
		}

	}

	// 通过商品名称查询商品的总数
	public Integer findProductsCountByName(PageBean pageBean) throws Exception {
		Integer n = productsExtendMapper.findProductsCountByName(pageBean);
		return n;

	}
	//分页查询个人购买记录
	public PageBean  findPersonOrderitemsByPage (PageBean pageBean) throws Exception{

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(10);
		// 调用DAO进行分页查询 --- 结果数据
		List<OrdersExtend> ordersExtends = ordersExtendMapper.findUidByOrderidPage(pageBean);
		     for (OrdersExtend ordersExtend : ordersExtends) {
			          // 根据orderID查找订单明细
						List<OrderitemsExtend> orderitemsExtends = orderitemsExtendMapper
								.findOrderitemsByOrderid(ordersExtend.getOrderid());
						for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
							ProductsExtend productsExtend = productsExtendMapper
									.findProductsById(orderitemsExtend.getPid());
							orderitemsExtend.setProductsExtend(productsExtend);
						}			
						ordersExtend.setUname(pageBean.getUsersName());
						// 通过orderid查找pid
						ordersExtend.setOrderitemsExtend(orderitemsExtends);
		      }		
		pageBean.setOrdersExtend(ordersExtends);
			// 封装总记录条数
		int totalRecordNum=ordersExtendMapper.findOrdersExtendCountByUid(pageBean);
		pageBean.setTotalRecordNum(totalRecordNum);
		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
        	
        }

	//根据商品的type查找商品
		public PageBean  findProductsByType (PageBean pageBean) throws Exception{

			// 根据页码和每页条数 计算开始索引
			int pNum = pageBean.getpNum();
			int start = (pNum - 1) * NUMPERPAGE;
			pageBean.setStart(start);
			pageBean.setNumperpage(12);
			// 调用DAO进行分页查询 --- 结果数据	
			List<ProductsExtend> productsExtends = productsExtendMapper.findProductsByType(pageBean);
			for (ProductsExtend productsExtend : productsExtends) {
				//查找图片
				List<ImagesExtend> imagesExtend1 = imagesExtendMapper.findImagesExtendByPid(productsExtend.getPid());
				//把图片集合放进商品里面
				productsExtend.setImagesExtend(imagesExtend1);
				//查找卖家。根据pid找到orders的集合，遍历集合找到uid,把用户 的头像的地址放到商品中
				 List<OrderitemsExtend>orderitemsExtend=orderitemsExtendMapper.findOrderidByPid(productsExtend.getPid());
				 for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
					
					int uid= ordersExtendMapper.findUIdByOrderid(orderitemsExtend2.getOrderid());
					UsersCustom usersCustom=usersCustomMapper.findUserById(uid);
					productsExtend.setAvator(usersCustom.getAvator());//用户的头像
					productsExtend.setUsername(usersCustom.getUsername());//用户的名字
				}
				
			}
		
			//把商品集合放进pagebean中
			pageBean.setProductsExtend(productsExtends);
			// 封装总记录条数
			int totalRecordNum = productsExtendMapper.findShangeChengCountByType(pageBean);
			pageBean.setTotalRecordNum(totalRecordNum);
			// 计算总页数
			int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
			pageBean.setTotalPageNum(totalpageNum);
			return pageBean;
	        	
	        }


	// 根据商品的条件进行查询
	public PageBean findByPageByName(PageBean pageBean) throws Exception {

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
        int totalRecordNum=0;
		// 调用DAO进行分页查询 --- 结果数据
    	if(pageBean.getConditionName().equals("name")){
    		List<ProductsExtend> productsExtend = productsExtendMapper.findByConditionByName(pageBean);
    		//通过商品pcid找到商品的分类
    		for (ProductsExtend productsExtend2 : productsExtend) {
    		String pcname=product_categoriesMapper.findPcnameByPcid(productsExtend2.getPcid());
    		productsExtend2.setPcname(pcname);
    		}
    		pageBean.setProductsExtend(productsExtend);
    		totalRecordNum = productsExtendMapper.findProductsCountByName(pageBean);
    		}else{
    			if(pageBean.getConditionName().equals("pcname")){
    				Product_categories product_categories=product_categoriesMapper.findProduct_categoriesByName(pageBean.getConditionValue());
    				pageBean.setPcid(product_categories.getPcid());
    				List<ProductsExtend> productsExtend =productsExtendMapper.findByConditionByPcid(pageBean);
    				for (ProductsExtend productsExtend2 : productsExtend) {
    					String pcname=product_categoriesMapper.findPcnameByPcid(productsExtend2.getPcid());
    					productsExtend2.setPcname(pcname);
    					}
    				pageBean.setProductsExtend(productsExtend);
    				totalRecordNum = productsExtendMapper.findProductsCountByPcid(pageBean);
    			}else{
    				if(pageBean.getConditionName().equals("type")){
    					List<ProductsExtend> productsExtend = productsExtendMapper.findByConditionByType(pageBean);
    					for (ProductsExtend productsExtend2 : productsExtend) {
    						String pcname=product_categoriesMapper.findPcnameByPcid(productsExtend2.getPcid());
    						productsExtend2.setPcname(pcname);
    						}
    					pageBean.setProductsExtend(productsExtend);
    					totalRecordNum = productsExtendMapper.findProductsCountByType(pageBean);
    				}
    			}
    		}
		// 封装总记录条数
		
		pageBean.setTotalRecordNum(totalRecordNum);

		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;

	}

	// 根据商品的名称查询
	public List<ProductsExtend> findTypePcnameByName(
			ProductsExtend productsExtend) throws Exception {
		return productsExtendMapper.findTypePcnameByName(productsExtend);
	}

	// 查询所有status=1，quantity=0的商品
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)
			throws Exception {

		return productsExtendMapper.findProductsRecord(productsExtend);

	}

	// 查询商品分类
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuery)
			throws Exception {
		return productsExtendMapper.findPcategories(productsQuery);
	}

	// 查询商品的type
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)
			throws Exception {
		return productsExtendMapper.findProductsType(productsExtend);
	}

	// 根据商品id删除商品
	@Override
	public void deleteProductsById(int id) throws Exception {

		productsExtendMapper.deleteProductsById(id);
	}

	// 修改商品
	public ProductsExtend updateProductsById(ProductsExtend productsExtend)
			throws Exception {
		productsExtendMapper.updateProductsById(productsExtend);
		ProductsExtend productsExtend1 = productsExtendMapper
				.findProductsById(productsExtend.getPid());
		return productsExtend1;
	}

	@Override
	// 根据status分查询商品总数
	public Integer findProductsCount(int status) throws Exception {
		Integer n = productsExtendMapper.findProductsCount(status);
		return n;
	}

	// 分页查询商品评论
	public PageBean findCommentByPage(PageBean pageBean) throws Exception {

		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// 封装当前页码
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		pageBean.setpNum(pNum);
		// 调用DAO进行分页查询 --- 结果数据
		List<Product_commentsExtend> product_commentsExtends = product_commentsMapper.findCommentByPage(pageBean);
		pageBean.setProduct_commentsExtend(product_commentsExtends);
		// 封装总记录条数
		int totalRecordNum = product_commentsMapper.countProduct_ommentsByPid(pageBean.getPid());
		pageBean.setTotalRecordNum(totalRecordNum);

		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	// 分页查询商品
	public PageBean findProductsByPage(PageBean pageBean) throws Exception {
		// 根据页码和每页条数 计算开始索引
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// 封装当前页码
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		pageBean.setpNum(pNum);
		// 调用DAO进行分页查询 --- 结果数据
		List<ProductsExtend> productsExtends = productsExtendMapper.findProductsByPage(pageBean);
		for (ProductsExtend productsExtend : productsExtends) {
			String pcname= product_categoriesMaper.findPcnameByPcid(productsExtend.getPcid());
			productsExtend.setPcname(pcname);
		}
		pageBean.setProductsExtend(productsExtends);
		// 封装总记录条数
		int totalRecordNum = productsExtendMapper.findProductsCount(pageBean.getStatus());
		pageBean.setTotalRecordNum(totalRecordNum);
		// 计算总页数
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	
	// 根据id查询商品
	public ProductsExtend findProductsById(int id) throws Exception {
		ProductsExtend productsExtend = productsExtendMapper
				.findProductsById(id);
		return productsExtend;

	}

	// 插入商品
	@Override
	public int insertProducts(ProductsExtend productsExtend, List<String> images)
			throws Exception {
		Product_categories product_categories1= product_categoriesMaper.findProduct_categoriesByName(productsExtend.getPcname());
		product_categories.setPcname(productsExtend.getPcname());
		if(product_categories1==null){
			product_categoriesMaper.insertPcategories(product_categories);
			productsExtend.setPcid(product_categories.getPcid());
		}else{
			productsExtend.setPcid(product_categories1.getPcid());
		}
		//将主图存到product表中
		productsExtend.setMainimages(images.get(0));
		long time=System.currentTimeMillis();
		productsExtend.setPcreated(time);
		productsExtendMapper.insertProducts(productsExtend);
		imagesExtend.setPid(productsExtend.getPid());
		 for(int i = 0;i < images.size(); i++){
			 imagesExtend.setUrl(images.get(i));
			 imagesExtendMapper.insertImages(imagesExtend);
	       } 
		return 1;
		/*Product_categories product_categories1= product_categoriesMaper.findProduct_categoriesByName(productsExtend.getPcname());
		product_categories.setPcname(productsExtend.getPcname());
	
		if(product_categories1==null){
			product_categoriesMaper.insertPcategories(product_categories);
			productsExtend.setPcid(product_categories.getPcid());
		}else{
			productsExtend.setPcid(product_categories1.getPcid());
		}
		//将主图存到product表中
		
		productsExtend.setMainimages(images.get(0));
		System.out.println("===============beforeinsert=============");
		//插入商品之前，先获得时间
		 long time=System.currentTimeMillis();
		 productsExtend.setPcreated(time);
		productsExtendMapper.insertProducts(productsExtend);
		System.out.println("---------insertproducts-----------------------------");
		imagesExtend.setPid(productsExtend.getPid());
		 for(int i = 0;i < images.size(); i++){
			 imagesExtend.setUrl(images.get(i));
			 System.out.println("=========imagesurl========="+imagesExtend.getUrl());
			 imagesExtendMapper.insertImages(imagesExtend);
	       } 
		return 1;*/
	}
	//根据条件来查询订单
	@Override
	public PageBean findOrdersByCondition(PageBean pageBean) throws Exception {
		// 根据页码和每页条数 计算开始索引
				int pNum = pageBean.getpNum();
				int start = (pNum - 1) * NUMPERPAGE;
				// 封装当前页码
				pageBean.setStart(start);
				pageBean.setNumperpage(PageBean.NUMPERPAGE);
		         int totalRecordNum=0;
		         List<OrdersExtend> ordersExtends  = new ArrayList<OrdersExtend>();
		         if(pageBean.getConditionName().equals("username")){
		        	UsersCustom usersCustom=usersCustomMapper.findUserByUserName(pageBean.getConditionValue());
		        	pageBean.setUid(usersCustom.getUid());
		        	ordersExtends = ordersExtendMapper.findUidByOrderidPage(pageBean);
		        	
		        	
		         }else{
		        	
		        	 if(pageBean.getConditionName().equals("orderid")){
		        		
		        		 pageBean.setOrderid(Integer.parseInt(pageBean.getConditionValue()));
		        		OrdersExtend ordersExtend = ordersExtendMapper.findOrdersByOrderid(Integer.parseInt(pageBean.getConditionValue()));
		        		ordersExtends.add(ordersExtend);
		        	 }else{
		        		 if(pageBean.getConditionName().equals("pcname")){
		        			 
		        			List<ProductsExtend> productsExtends= productsExtendMapper.findProductsByPcname(pageBean.getConditionValue());
		        			for (ProductsExtend productsExtend : productsExtends) {
		        				
		        				List<OrderitemsExtend> orderitemsExtends = orderitemsExtendMapper.findOrdersExtendByPid(productsExtend.getPid());
		        				if(orderitemsExtends.size()<=0){
		        					continue;
		        				}else{
			        			for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
			        				OrdersExtend ordersExtend =  ordersExtendMapper.findOrdersByOrderid(orderitemsExtend.getOrderid());
			        				ordersExtends.add(ordersExtend);
								}
		        			  }
							}
		        			 
		        		 }
		        	 }
		         }
		         for (OrdersExtend ordersExtend : ordersExtends) {
		 			UsersCustom usersCustom  = usersCustomMapper.findUserById(ordersExtend.getUid());
		 			ordersExtend.setUname(usersCustom.getUsername());
		 			ordersExtend.setPhone(usersCustom.getPhone());
		 			List<OrderitemsExtend> orderitemsExtends =  orderitemsExtendMapper.findOrderitemsByOrderid(ordersExtend.getOrderid());
		 			for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
		 				ProductsExtend productsExtend = productsExtendMapper
		 						.findProductsById(orderitemsExtend.getPid());
		 				orderitemsExtend.setProductsExtend(productsExtend);
		 			}
		 			totalRecordNum = orderitemsExtends.size()+totalRecordNum;
		 			ordersExtend.setOrderitemsExtend(orderitemsExtends);
		 		}
		 		pageBean.setOrdersExtends(ordersExtends);
		 		pageBean.setTotalRecordNum(totalRecordNum);
		 		// 计算总页数
		 		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		 		pageBean.setTotalPageNum(totalpageNum);
		 		return pageBean;
	}

	

}
