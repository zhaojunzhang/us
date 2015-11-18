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
	// ��ѯ����status=1��quantity��=0����Ʒ
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception{
		return productsExtendMapper.findAllProducts(productsExtend);
		
	}
	
	
	//ͨ��orderid�����û�uid
			public int findUIdByOrderid(int orderid)throws Exception{
			return  ordersExtendMapper.findUIdByOrderid(orderid);
			}
	//1.����pid�ҵ�orderID��
		public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception{
			
			return orderitemsExtendMapper.findOrderidByPid(pid);
		}

	//��pid��ѯ��Ʒ���۵�����
		public int countProduct_ommentsByPid(int pid)throws Exception{
			
			return product_commentsMapper.countProduct_ommentsByPid(pid);			
		}
	//�鿴��Ʒ���������
		public int productsExtendViews(int pid)throws Exception{
			System.out.println("-pid--------------"+pid);
			productsExtendMapper.productsExtendView(pid);
		
			return 1;
			
		}
	//��������
		public int  insertProduct_comments(Product_commentsExtend product_commentsExtend ) throws Exception{
			//���pid,uid,created
			long time=System.currentTimeMillis();
			product_commentsExtend.setCreated(time);
			product_commentsMapper.insertProduct_comments(product_commentsExtend);
			return 1;		
		}
	//ͨ��orderitemsid����pid
		public int  findPidByOrderitemsid(int orderitemsid)throws Exception{
			
			return orderitemsExtendMapper.findPidByOrderitemsid(orderitemsid);
		}
	//ͨ����Ʒid�Ų�����Ʒ����
		public int findPcateByPid(int pid)throws Exception{
			
			return  productsExtendMapper.findPcateByPid(pid);
		}
	

	// ������Ʒ�����ҵ���Ʒid
	public List<ProductsExtend> findPidByName(String name) throws Exception {
		return productsExtendMapper.findPidByName(name);

	}

	// ������Ʒ�����ƽ��з�ҳ
	public PageBean findOrderitemsByPid(PageBean pageBean) throws Exception {

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		// ����DAO���з�ҳ��ѯ --- �������

		List<OrderitemsExtend> orderitemsExtend = orderitemsExtendMapper
				.findOrderitemsExtendByPid(pageBean);
		for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
			// ͨ��orderid�����û�id
			int uid = ordersExtendMapper.findUIdByOrderid(orderitemsExtend2
					.getOrderid());
			// ͨ��uid�����û���Ϣ
			UsersCustom usersCustom = usersCustomMapper.findUserById(uid);
			//
			// ͨ��orderitemsid����pid
			int pid = orderitemsExtendMapper
					.findPidByOrderitemsid(orderitemsExtend2.getOrderitemid());
			// ͨ��pid������Ʒ����Ϣ
			ProductsExtend productsExtend = productsExtendMapper
					.findProductsById(pid);
			orderitemsExtend2.setUsername(usersCustom.getUsername());// �û���
			orderitemsExtend2.setIp(usersCustom.getIp());// �û�IP��ַ
			orderitemsExtend2.setName(productsExtend.getName());// ����
			orderitemsExtend2.setSoldprice(productsExtend.getSoldprice());// �۸�
			orderitemsExtend2.setQuantity(productsExtend.getQuantity());// ����
			orderitemsExtend2.setType(productsExtend.getType());// ����
		}

		pageBean.setOrderitemsExtend(orderitemsExtend);
		// ��װ�ܼ�¼����
		int totalRecordNum = orderitemsExtendMapper.countOrdritemsByPid(pageBean);
		pageBean.setTotalRecordNum(totalRecordNum);

		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;

	}

	// ��ҳ��ѯ��Ʒ
	public PageBean findOrderitemsByOrderid(PageBean pageBean) throws Exception {

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// ��װ��ǰҳ��
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		pageBean.setpNum(pNum);
		// ����DAO���з�ҳ��ѯ --- �������
		List<OrderitemsExtend> orderitemsExtend = orderitemsExtendMapper
				.findOrderitemsExtendByPage(pageBean);
		for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
			// ͨ��orderid�����û�id
			int uid = ordersExtendMapper.findUIdByOrderid(orderitemsExtend2
					.getOrderid());
			// ͨ��uid�����û���Ϣ
			UsersCustom usersCustom = usersCustomMapper.findUserById(uid);
			// ͨ��orderitemsid����pid
			int pid = orderitemsExtendMapper
					.findPidByOrderitemsid(orderitemsExtend2.getOrderitemid());
			// ͨ��pid������Ʒ����Ϣ
			ProductsExtend productsExtend = productsExtendMapper
					.findProductsById(pid);
			orderitemsExtend2.setUsername(usersCustom.getUsername());// �û���
			orderitemsExtend2.setIp(usersCustom.getIp());// �û�IP��ַ
			orderitemsExtend2.setName(productsExtend.getName());// ����
			orderitemsExtend2.setSoldprice(productsExtend.getSoldprice());// �۸�
			orderitemsExtend2.setQuantity(productsExtend.getQuantity());// ����
			orderitemsExtend2.setType(productsExtend.getType());// ����
		}

		pageBean.setOrderitemsExtend(orderitemsExtend);
		// ��װ�ܼ�¼����
		int totalRecordNum = orderitemsExtendMapper.orderitemsCount();
		pageBean.setTotalRecordNum(totalRecordNum);

		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}

	// ����������Ʒ
	public void updateQuantity(ProductsExtend productsExtend) throws Exception {
		
		productsExtend.setQuantity(productsExtend.getQuantity() - 1);
		productsExtendMapper.updateQuantity(productsExtend);
	}

	// ��ѯ���ж���
	public PageBean findOrderitemsExtendByPage(PageBean pageBean)
			throws Exception {

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// ��װ��ǰҳ��
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
         int totalRecordNum=0;
		pageBean.setpNum(pNum);
		//��������ѯ���еĶ���
		List<OrdersExtend> ordersExtends = ordersExtendMapper.findAllOrdersExtend(pageBean);
		//����������ͨ��uid�����û�������û������ֻ���
		for (OrdersExtend ordersExtend : ordersExtends) {
			UsersCustom usersCustom  = usersCustomMapper.findUserById(ordersExtend.getUid());
			ordersExtend.setUname(usersCustom.getUsername());
			ordersExtend.setPhone(usersCustom.getPhone());
			//ͨ��orderID���Ҷ�����ϸ����ͨ���ҵ���Ʒ��
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
		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}

	// ɾ������
	public void deleteOrdersByOrderid(int orderid) throws Exception {
		ordersExtendMapper.deleteOrdersByOrderid(orderid);
	}

	
	// ͨ��orderid���Ҷ�����ϸ��
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)
			throws Exception {
		return orderitemsExtendMapper.findOrderitemsByOrderid(orderid);
	}

	// ����uid���Ҷ�����
	public List<OrdersExtend> findOrdersExtendByUid(int uid) throws Exception {
		return ordersExtendMapper.findOrdersExtendByUid(uid);
	}

	// �����û�id���ɶ���
	public int insertOrders(OrdersExtend ordersExtend) throws Exception {
		// �����û���idΪ3.
		long time = System.currentTimeMillis();
		ordersExtend.setCreated(time);
		return ordersExtendMapper.insertOrders(ordersExtend);

	}

	// ���ɶ�����ϸ
	public int insertOrderitems(int uid,int pid) throws Exception {

		// �����ɶ�����ϸ֮ǰ������orderid, pid.,����Ҫ������orders��by uid
		ordersExtend.setUid(uid);
		long time = System.currentTimeMillis();
		ordersExtend.setCreated(time);// ���ʱ��

		// �жϿ�����Ƿ����1.���С��1��������
		// ���ɶ��������¿��
		ProductsExtend productsExtend2 = productsExtendMapper
				.findProductsById(pid);
		if (productsExtend2.getQuantity()!=0) {
			ordersExtendMapper.insertOrders(ordersExtend);// ���붩��
			productsExtend2.setQuantity(productsExtend2.getQuantity() - 1);
			productsExtendMapper.updateQuantity(productsExtend2);//���¿��
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

	// ͨ����Ʒ���Ʋ�ѯ��Ʒ������
	public Integer findProductsCountByName(PageBean pageBean) throws Exception {
		Integer n = productsExtendMapper.findProductsCountByName(pageBean);
		return n;

	}
	//��ҳ��ѯ���˹����¼
	public PageBean  findPersonOrderitemsByPage (PageBean pageBean) throws Exception{

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(10);
		// ����DAO���з�ҳ��ѯ --- �������
		List<OrdersExtend> ordersExtends = ordersExtendMapper.findUidByOrderidPage(pageBean);
		     for (OrdersExtend ordersExtend : ordersExtends) {
			          // ����orderID���Ҷ�����ϸ
						List<OrderitemsExtend> orderitemsExtends = orderitemsExtendMapper
								.findOrderitemsByOrderid(ordersExtend.getOrderid());
						for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
							ProductsExtend productsExtend = productsExtendMapper
									.findProductsById(orderitemsExtend.getPid());
							orderitemsExtend.setProductsExtend(productsExtend);
						}			
						ordersExtend.setUname(pageBean.getUsersName());
						// ͨ��orderid����pid
						ordersExtend.setOrderitemsExtend(orderitemsExtends);
		      }		
		pageBean.setOrdersExtend(ordersExtends);
			// ��װ�ܼ�¼����
		int totalRecordNum=ordersExtendMapper.findOrdersExtendCountByUid(pageBean);
		pageBean.setTotalRecordNum(totalRecordNum);
		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
        	
        }

	//������Ʒ��type������Ʒ
		public PageBean  findProductsByType (PageBean pageBean) throws Exception{

			// ����ҳ���ÿҳ���� ���㿪ʼ����
			int pNum = pageBean.getpNum();
			int start = (pNum - 1) * NUMPERPAGE;
			pageBean.setStart(start);
			pageBean.setNumperpage(12);
			// ����DAO���з�ҳ��ѯ --- �������	
			List<ProductsExtend> productsExtends = productsExtendMapper.findProductsByType(pageBean);
			for (ProductsExtend productsExtend : productsExtends) {
				//����ͼƬ
				List<ImagesExtend> imagesExtend1 = imagesExtendMapper.findImagesExtendByPid(productsExtend.getPid());
				//��ͼƬ���ϷŽ���Ʒ����
				productsExtend.setImagesExtend(imagesExtend1);
				//�������ҡ�����pid�ҵ�orders�ļ��ϣ����������ҵ�uid,���û� ��ͷ��ĵ�ַ�ŵ���Ʒ��
				 List<OrderitemsExtend>orderitemsExtend=orderitemsExtendMapper.findOrderidByPid(productsExtend.getPid());
				 for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {
					
					int uid= ordersExtendMapper.findUIdByOrderid(orderitemsExtend2.getOrderid());
					UsersCustom usersCustom=usersCustomMapper.findUserById(uid);
					productsExtend.setAvator(usersCustom.getAvator());//�û���ͷ��
					productsExtend.setUsername(usersCustom.getUsername());//�û�������
				}
				
			}
		
			//����Ʒ���ϷŽ�pagebean��
			pageBean.setProductsExtend(productsExtends);
			// ��װ�ܼ�¼����
			int totalRecordNum = productsExtendMapper.findShangeChengCountByType(pageBean);
			pageBean.setTotalRecordNum(totalRecordNum);
			// ������ҳ��
			int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
			pageBean.setTotalPageNum(totalpageNum);
			return pageBean;
	        	
	        }


	// ������Ʒ���������в�ѯ
	public PageBean findByPageByName(PageBean pageBean) throws Exception {

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
        int totalRecordNum=0;
		// ����DAO���з�ҳ��ѯ --- �������
    	if(pageBean.getConditionName().equals("name")){
    		List<ProductsExtend> productsExtend = productsExtendMapper.findByConditionByName(pageBean);
    		//ͨ����Ʒpcid�ҵ���Ʒ�ķ���
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
		// ��װ�ܼ�¼����
		
		pageBean.setTotalRecordNum(totalRecordNum);

		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;

	}

	// ������Ʒ�����Ʋ�ѯ
	public List<ProductsExtend> findTypePcnameByName(
			ProductsExtend productsExtend) throws Exception {
		return productsExtendMapper.findTypePcnameByName(productsExtend);
	}

	// ��ѯ����status=1��quantity=0����Ʒ
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)
			throws Exception {

		return productsExtendMapper.findProductsRecord(productsExtend);

	}

	// ��ѯ��Ʒ����
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuery)
			throws Exception {
		return productsExtendMapper.findPcategories(productsQuery);
	}

	// ��ѯ��Ʒ��type
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)
			throws Exception {
		return productsExtendMapper.findProductsType(productsExtend);
	}

	// ������Ʒidɾ����Ʒ
	@Override
	public void deleteProductsById(int id) throws Exception {

		productsExtendMapper.deleteProductsById(id);
	}

	// �޸���Ʒ
	public ProductsExtend updateProductsById(ProductsExtend productsExtend)
			throws Exception {
		productsExtendMapper.updateProductsById(productsExtend);
		ProductsExtend productsExtend1 = productsExtendMapper
				.findProductsById(productsExtend.getPid());
		return productsExtend1;
	}

	@Override
	// ����status�ֲ�ѯ��Ʒ����
	public Integer findProductsCount(int status) throws Exception {
		Integer n = productsExtendMapper.findProductsCount(status);
		return n;
	}

	// ��ҳ��ѯ��Ʒ����
	public PageBean findCommentByPage(PageBean pageBean) throws Exception {

		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// ��װ��ǰҳ��
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);

		pageBean.setpNum(pNum);
		// ����DAO���з�ҳ��ѯ --- �������
		List<Product_commentsExtend> product_commentsExtends = product_commentsMapper.findCommentByPage(pageBean);
		pageBean.setProduct_commentsExtend(product_commentsExtends);
		// ��װ�ܼ�¼����
		int totalRecordNum = product_commentsMapper.countProduct_ommentsByPid(pageBean.getPid());
		pageBean.setTotalRecordNum(totalRecordNum);

		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)
				/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	// ��ҳ��ѯ��Ʒ
	public PageBean findProductsByPage(PageBean pageBean) throws Exception {
		// ����ҳ���ÿҳ���� ���㿪ʼ����
		int pNum = pageBean.getpNum();
		int start = (pNum - 1) * NUMPERPAGE;
		// ��װ��ǰҳ��
		pageBean.setStart(start);
		pageBean.setNumperpage(PageBean.NUMPERPAGE);
		pageBean.setpNum(pNum);
		// ����DAO���з�ҳ��ѯ --- �������
		List<ProductsExtend> productsExtends = productsExtendMapper.findProductsByPage(pageBean);
		for (ProductsExtend productsExtend : productsExtends) {
			String pcname= product_categoriesMaper.findPcnameByPcid(productsExtend.getPcid());
			productsExtend.setPcname(pcname);
		}
		pageBean.setProductsExtend(productsExtends);
		// ��װ�ܼ�¼����
		int totalRecordNum = productsExtendMapper.findProductsCount(pageBean.getStatus());
		pageBean.setTotalRecordNum(totalRecordNum);
		// ������ҳ��
		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		pageBean.setTotalPageNum(totalpageNum);
		return pageBean;
	}
	
	// ����id��ѯ��Ʒ
	public ProductsExtend findProductsById(int id) throws Exception {
		ProductsExtend productsExtend = productsExtendMapper
				.findProductsById(id);
		return productsExtend;

	}

	// ������Ʒ
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
		//����ͼ�浽product����
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
		//����ͼ�浽product����
		
		productsExtend.setMainimages(images.get(0));
		System.out.println("===============beforeinsert=============");
		//������Ʒ֮ǰ���Ȼ��ʱ��
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
	//������������ѯ����
	@Override
	public PageBean findOrdersByCondition(PageBean pageBean) throws Exception {
		// ����ҳ���ÿҳ���� ���㿪ʼ����
				int pNum = pageBean.getpNum();
				int start = (pNum - 1) * NUMPERPAGE;
				// ��װ��ǰҳ��
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
		 		// ������ҳ��
		 		int totalpageNum = (totalRecordNum + PageBean.NUMPERPAGE - 1)/ PageBean.NUMPERPAGE;
		 		pageBean.setTotalPageNum(totalpageNum);
		 		return pageBean;
	}

	

}
