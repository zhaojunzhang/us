package com.us.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.us.mapper.ImagesExtendMapper;
import com.us.mapper.Product_categoriesMapper;
import com.us.po.AdminsCustom;
import com.us.po.ImagesExtend;
import com.us.po.OrderitemsExtend;
import com.us.po.OrdersExtend;
import com.us.po.PageBean;
import com.us.po.Product_categories;
import com.us.po.Product_commentsExtend;
import com.us.po.ProductsExtend;
import com.us.po.ProductsQuery;
import com.us.po.UsersCustom;
import com.us.service.ImagesExtendService;
import com.us.service.ProductsExtendService;
import com.us.service.UsersService;
import com.us.utils.UploadUtils;

@Controller
public class ProductsExtendController {
	PageBean pageBean = new PageBean();
	ProductsExtend productsExtend = new ProductsExtend();
	ProductsQuery productsQuery = new ProductsQuery();
	@Autowired
	ProductsExtendService productsExtendService;
	@Autowired
	ImagesExtendService imagesExtendService;
	@Autowired
	UsersService usersService;
	@Autowired
	ImagesExtendMapper imagesExtendMapper;
	@Autowired
	Product_categoriesMapper product_categoriesMaper;
	ModelAndView modelAndView = new ModelAndView();

	// 查询所有status=1，quantity！=0的商品
	@RequestMapping("/findAllProducts")
	public ModelAndView findAllProducts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 接收从前台传来的状态码
		int status = Integer.parseInt(request.getParameter("status"));// 如果不是数字报错

		// 查找所有送的商品
		productsExtend.setStatus(status);
		productsExtend.setType("闲互送");
		List<ProductsExtend> productsList = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsList) {
			// 遍历商品，根据商品的ID找到图片
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// 将图片集合放进商品中。
			productsExtend.setImagesExtend(imagesExtend);
			//
			// 查找卖家。根据pid找到orders的集合，遍历集合找到uid,把用户 的头像的地址放到商品中
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// 用户的头像
				productsExtend.setUsername(usersCustom.getUsername());// 用户的名字
				System.out.println("------------useraname---------"
						+ productsExtend.getUsername());
			}

		}

		// 查找所有换的商品
		productsExtend.setType("闲互换");
		List<ProductsExtend> productsExchange = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsExchange) {
			// 遍历商品，根据商品的ID找到图片
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// 将图片集合放进商品中。
			productsExtend.setImagesExtend(imagesExtend);
			//
			// 查找卖家。根据pid找到orders的集合，遍历集合找到uid,把用户 的头像的地址放到商品中
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// 用户的头像
				productsExtend.setUsername(usersCustom.getUsername());// 用户的名字
				System.out.println("------------useraname---------"
						+ productsExtend.getUsername());
			}

		}

		// 查找所有换的商品
		productsExtend.setType("聚便宜");
		List<ProductsExtend> productsCheap = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsCheap) {
			// 遍历商品，根据商品的ID找到图片
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// 将图片集合放进商品中。
			productsExtend.setImagesExtend(imagesExtend);
			//
			// 查找卖家。根据pid找到orders的集合，遍历集合找到uid,把用户 的头像的地址放到商品中
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// 用户的头像
				productsExtend.setUsername(usersCustom.getUsername());// 用户的名字
				System.out.println("------------useraname---------"
						+ productsExtend.getUsername());
			}

		}
		modelAndView.addObject("productsList", productsList);
		modelAndView.addObject("productsExchange", productsExchange);
		modelAndView.addObject("productsCheap", productsCheap);

		modelAndView.setViewName("/products/testindex");
		return modelAndView;
	}

	// 插入商品评论
	@RequestMapping("/insertComments")
	public String insertComments(HttpServletRequest request,
			HttpServletResponse response,
			Product_commentsExtend product_commentsExtend)
			throws ServletException, IOException {

		int uid = 3;
		int pid = Integer.parseInt(request.getParameter("pid"));// 如果不是数字报错
		product_commentsExtend.setUid(uid);

		try {
			productsExtendService
					.insertProduct_comments(product_commentsExtend);
		} catch (Exception e) {
			request.setAttribute("msg", "评论出错，请重新评论");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		modelAndView.setViewName("/products/productsDetailComment");
		return "redirect:/findCommentByPage.action?pNum=1&pid=" + pid;

	}

	// 根据商品名称分页查询
	@RequestMapping("/findOrderitemsByPidWeb")
	public ModelAndView findOrderitemsByPidWeb(HttpServletRequest request)
			throws Exception {
		// 获得客户端提交页码
		String name = request.getParameter("name");
		// 将页码传递到service层
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// 根据商品名找到pid
		List<ProductsExtend> productsExtend = productsExtendService
				.findPidByName(name);
		for (ProductsExtend productsExtend2 : productsExtend) {
			pageBean.setPid(productsExtend2.getPid());
			pageBean = productsExtendService.findOrderitemsByPid(pageBean);
		}

		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/products/orderitemsPageListWeb");
		return modelAndView;

	}

	// 根据商品名称分页查询findOrderitemsByPid
	@RequestMapping("/findOrderitemsByPid")
	public ModelAndView findOrderitemsByPid(HttpServletRequest request)
			throws Exception {
		// 获得客户端提交页码
		String name = request.getParameter("name");
		// 将页码传递到service层
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// 根据商品名找到pid
		List<ProductsExtend> productsExtend = productsExtendService
				.findPidByName(name);
		for (ProductsExtend productsExtend2 : productsExtend) {
			pageBean.setPid(productsExtend2.getPid());
			pageBean = productsExtendService.findOrderitemsByPid(pageBean);
		}

		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/products/orderitemsPageList");
		return modelAndView;

	}

	// 通过用户名查找订单明细
	@RequestMapping("/findOrderitemsByUsersname")
	public ModelAndView findOrderitemsByUsersname(HttpServletRequest request)
			throws Exception {
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错

		// 将页码传递到service层
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// 获得用户名
		String username = request.getParameter("username");
		usersService.findUserByUserName(username);
		// 找到用户，根据用户的id查找orderid,再通过查找orderitemsid
		pageBean = productsExtendService.findOrderitemsExtendByPage(pageBean);

		modelAndView.addObject("pageBean", pageBean);

		modelAndView.setViewName("/products/orderitemsPageList");
		return modelAndView;

	}

	// 查询所有订单
	@RequestMapping("/findOrderitemsExtendByPage")
	public ModelAndView findOrderitemsExtendByPage(HttpServletRequest request)
			throws Exception {
		// 获得客户端提交页码

		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错

		// 将页码传递到service层
		pageBean.setpNum(pNum);

		PageBean pageBean1 = productsExtendService
				.findOrderitemsExtendByPage(pageBean);
		modelAndView.addObject("pageBean", pageBean1);

		modelAndView.setViewName("/products/orderitemsPageList");
		return modelAndView;
	}

	// 查询商品的type
	@RequestMapping("/findOrderitemsExtendByPageWeb")
	public ModelAndView findOrderitemsExtendByPageWeb(HttpServletRequest request)
			throws Exception {
		// 获得客户端提交页码

		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错

		// 将页码传递到service层
		pageBean.setpNum(pNum);

		pageBean = productsExtendService.findOrderitemsExtendByPage(pageBean);
		modelAndView.addObject("pageBean", pageBean);

		modelAndView.setViewName("/products/orderitemsPageListWeb");
		return modelAndView;
	}

	// 删除订单
	@RequestMapping("/deleteOrdersByOrderid")
	public String deleteOrdersByOrderid(HttpServletRequest request)
			throws Exception {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		int orderid1 = Integer.parseInt(request.getParameter("orderid"));
		productsExtendService.deleteOrdersByOrderid(orderid1);
		if (usersCustom != null) {
			return "redirect:/findPersonOrderitems.action";
		} else {
			return "redirect:/findOrderitemsExtendByPage.action?pNum=1";
		}

	}

	// 分页查询个人购买记录
	@RequestMapping("findPersonOrderitemsByPage")
	public ModelAndView findPersonOrderitemsByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// 要判断用户是否登录，登录的用户才能够查看
		// 通过session获得用户的uid
		// 通过用户id查找订单
		if (usersCustom == null) {
			request.setAttribute("msg", "您还没有登录，请登录后查看");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			
			// 将页码传递到service层
			int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
			pageBean.setpNum(pNum);
			pageBean.setUid(usersCustom.getUid());
			pageBean.setUsersName(usersCustom.getUsername());
			try {
				// 根据用户名找到该用户的所以订单。
				pageBean = productsExtendService.findPersonOrderitemsByPage(pageBean);
			} catch (Exception e) {
				request.setAttribute("msg", "获取信息失败");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/personOrderitems");
			return modelAndView;

		}

		return modelAndView;
	}

	// 个人购买记录
	@RequestMapping("/findPersonOrderitems")
	public ModelAndView findPersonOrderitems(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// 要判断用户是否登录，登录的用户才能够查看
		// 通过session获得用户的uid
		// 通过用户id查找订单
		if (usersCustom == null) {
			request.setAttribute("msg", "您还没有登录，请登录后查看");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			System.out.println("===================uid==========="
					+ usersCustom.getUid());
			List<OrdersExtend> ordersExtends = productsExtendService
					.findOrdersExtendByUid(usersCustom.getUid());
			for (OrdersExtend ordersExtend : ordersExtends) {
				// 根据orderID查找订单明细
				List<OrderitemsExtend> orderitemsExtends = productsExtendService
						.findOrderitemsByOrderid(ordersExtend.getOrderid());
				for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
					ProductsExtend productsExtend = productsExtendService
							.findProductsById(orderitemsExtend.getPid());
					orderitemsExtend.setProductsExtend(productsExtend);
				}
				ordersExtend.setUname(usersCustom.getUsername());
				// 通过orderid查找pid
				ordersExtend.setOrderitemsExtend(orderitemsExtends);
			}

			modelAndView.addObject("ordersExtend2", ordersExtends);
		}
		System.out.println("=======================================");
		modelAndView.setViewName("/products/personOrderitems");
		return modelAndView;

	}

	// 生成订单
	@RequestMapping("/insertOrderitemsSuccess")
	public String insertOrderitemsSuccess(OrderitemsExtend orderitemsExtend,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession().getAttribute("usersCustom");
		int num = 0;
		if (usersCustom == null) {
			request.setAttribute("msg", "您还没有登录，请登录后购买");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			int pid = Integer.parseInt(request.getParameter("pid"));
			List<OrdersExtend> ordersExtends;
			ProductsExtend productsExtend1 = null;
			try {
				/*
				 * 1.限制抢购的数量 2.根据uid找到该用户的所有的订单，如果用户没有订单，则可以购买，
				 * 若有订单，则判断用户同一个商品的订单是否超过限制的数量。 超过则不能购买，不超则可以抢购。
				 */

				ordersExtends = productsExtendService
						.findOrdersExtendByUid(usersCustom.getUid());
				if (ordersExtends.size() > 0) {
					for (OrdersExtend ordersExtend : ordersExtends) {
						List<OrderitemsExtend> orderitemsExtends;
						try {
							orderitemsExtends = productsExtendService
									.findOrderitemsByOrderid(ordersExtend
											.getOrderid());
							for (OrderitemsExtend orderitemsExtend2 : orderitemsExtends) {
								/*
								 * 判断抢购的商品的pid是否跟订单条目的pid一样。
								 */
								if (pid == orderitemsExtend2.getPid()) {
									num++;
								}
							}
							productsExtend1 = productsExtendService
									.findProductsById(pid);

						} catch (Exception e) {
							request.setAttribute("msg", "抢购商品失败1");
							request.getRequestDispatcher("/error.jsp").forward(
									request, response);
						}
					}
				}
				// 限制购买的数量
				if (productsExtend1.getLimit() == num) {
					request.setAttribute("msg", "您购买的数量已经达到上限，请见谅");
					request.getRequestDispatcher("/Notice.jsp").forward(
							request, response);
				} else {
					productsExtendService.insertOrderitems(
							usersCustom.getUid(), pid);
					request.getRequestDispatcher("/success.jsp").forward(
							request, response);
				}
			} catch (Exception e) {
				request.setAttribute("msg", "抢购商品失败2");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}

		}
		return null;
	}

	// 按条件查询订单
	@RequestMapping("/findOrdersByCondition")
	public ModelAndView findOrdersByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		// 将页码传递到service层
		int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
		pageBean.setpNum(pNum);
		pageBean.setConditionName(conditionName);
		pageBean.setConditionValue(conditionValue);
		PageBean pageBean1;
		try {
			pageBean1 = productsExtendService.findOrdersByCondition(pageBean);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/products/orderitemsPageListWeb");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;

	}

	// 按照条件查询商品
	@RequestMapping("/findByPageByName")
	public ModelAndView findByPageByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得客户端提交页码
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		// 将页码传递到service层
		int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
		int status = Integer.parseInt(request.getParameter("status"));// 如果不是数字报错
		pageBean.setpNum(pNum);
		pageBean.setStatus(status);
		pageBean.setConditionName(conditionName);
		pageBean.setConditionValue(conditionValue);
		try {
			pageBean = productsExtendService.findByPageByName(pageBean);
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/products/page_listName");
		return modelAndView;

	}

	// 根据商品的分类分页查询商品
	@RequestMapping("/findShangchengByType")
	public ModelAndView findShangchengByType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得页面提交的type
		String type = request.getParameter("type");
		String type1 = new String(type.getBytes("iso-8859-1"), "utf-8");
		productsExtend.setType(type1);
		// 将页码传递到service层
		int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
		int status = Integer.parseInt(request.getParameter("status"));// 如果不是数字报错
		pageBean.setpNum(pNum);
		pageBean.setStatus(status);
		pageBean.setType(type1);
		try {
			pageBean = productsExtendService.findProductsByType(pageBean);
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		modelAndView.addObject("pageBean", pageBean);
		if (productsExtend.getType().equals("闲互送")) {

			modelAndView.setViewName("/products/testgive");
		} else {

			if (productsExtend.getType().equals("闲互换")) {
				modelAndView.setViewName("/products/testExchange");
			} else {
				modelAndView.setViewName("/products/testCheap");
			}
		}
		return modelAndView;

	}

	// 根据商品的名称查询
	@RequestMapping("/findTypePcnameByName")
	public ModelAndView findTypePcnameByName(HttpServletRequest request,
			ProductsExtend productsExtend2) throws Exception {
		String name = productsExtend2.getName();
		String type1 = request.getParameter("type");
		String type = new String(type1.getBytes("iso-8859-1"), "utf-8");
		productsExtend2.setType(type);
		List<ProductsExtend> productName = productsExtendService
				.findTypePcnameByName(productsExtend2);
		for (ProductsExtend productsExtend : productName) {
			// 通过商品的pid查找图片 商品：图片=1：多
			List<ImagesExtend> imagesExtend1 = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// 遍历图片，把图片的地址复制到商品的地址中
			for (ImagesExtend imagesExtend : imagesExtend1) {
				productsExtend.setUrl(imagesExtend.getUrl());
			}
		}

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productsList1", productName);
		if (productsExtend2.getType().equals("闲互送")) {
			modelAndView.setViewName("/products/testgive");
		} else {

			if (productsExtend2.getType().equals("闲互换")) {
				modelAndView.setViewName("/products/testExchange");
			} else {

				modelAndView.setViewName("/products/testCheap");
			}
		}
		return modelAndView;

	}

	// 查询商品分类
	@RequestMapping("/findPcategories")
	public ModelAndView findPcategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pcname = request.getParameter("pcname");
		String pcname1 = new String(pcname.getBytes("iso-8859-1"), "utf-8");
		String type = request.getParameter("type");
		String type1 = new String(type.getBytes("iso-8859-1"), "utf-8");
		productsQuery.setPcname(pcname1);
		productsQuery.setType(type1);
		List<ProductsExtend> productsExtend;
		try {
			productsExtend = productsExtendService
					.findPcategories(productsQuery);
			modelAndView.addObject("productsList1", productsExtend);
			if (productsQuery.getType().equals("闲互送")) {
				modelAndView.setViewName("/products/testgive");
			} else {

				if (productsQuery.getType().equals("闲互换")) {
					modelAndView.setViewName("/products/testExchange");
				} else {

					modelAndView.setViewName("/products/testCheap");
				}
			}
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 查询商品的type
	@RequestMapping("/findProductsType")
	public ModelAndView findProductsType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String type1 = new String(type.getBytes("iso-8859-1"), "utf-8");
		if (type1.equals("give")) {
			type1 = "闲互送";
		}
		productsExtend.setType(type1);
		List<ProductsExtend> productsExtend1;
		try {
			productsExtend1 = productsExtendService
					.findProductsType(productsExtend);
			for (ProductsExtend productsExtend2 : productsExtend1) {
				// 通过商品的pid查找图片 商品：图片=1：多
				List<ImagesExtend> imagesExtend1 = imagesExtendService
						.findImagesExtendByPid(productsExtend2.getPid());
				// 遍历图片，把图片的地址复制到商品的地址中
				for (ImagesExtend imagesExtend : imagesExtend1) {
					productsExtend2.setUrl(imagesExtend.getUrl());
				}
			}
			// 返回ModelAndView
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("productsList1", productsExtend1);
			if (productsExtend.getType().equals("闲互送")) {
				modelAndView.setViewName("/products/testgive");
			} else {

				if (productsExtend.getType().equals("闲互换")) {
					modelAndView.setViewName("/products/testExchange");
				} else {

					modelAndView.setViewName("/products/testCheap");
				}
			}
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 根据商品id删除商品
	@RequestMapping("/deleteProductsById")
	public ModelAndView deleteProductsById(HttpServletRequest request,
			ProductsExtend productsExtend, HttpServletResponse response)
			throws ServletException, IOException {
		int pid = productsExtend.getPid();
		try {
			productsExtendService.deleteProductsById(pid);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("productsList", productsExtend);
			modelAndView.setViewName("/products/deleteSuccess");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "删除商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 修改商品信息
	@RequestMapping("/updateProducts")
	public ModelAndView updateProducts(HttpServletRequest request,
			ProductsExtend productsExtend, HttpServletResponse response)
			throws ServletException, IOException {

		ProductsExtend productsExtend1;
		try {

			// 更新
			productsExtend1 = productsExtendService
					.updateProductsById(productsExtend);
			modelAndView.addObject("productsExtend1", productsExtend1);

			request.setAttribute("msg", "修改商品成功");
			modelAndView.setViewName("/products/page_list");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "修改商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 根据id查询商品
	@RequestMapping("/findProductsById")
	public ModelAndView findProductsById(ProductsExtend productsExtend,
			HttpServletRequest request) throws Exception {

		ProductsExtend productsExtend1 = productsExtendService
				.findProductsById(productsExtend.getPid());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productsExtend1", productsExtend1);
		modelAndView.setViewName("/products/update");
		return modelAndView;
	}

	// 根据商品pid查询图片
	@RequestMapping("/findImagesExtendByPid")
	public ModelAndView findImagesExtendByPid(ProductsExtend productsExtend,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pid = productsExtend.getPid();
		List<ImagesExtend> imagesExtend;
		try {
			imagesExtend = imagesExtendService.findImagesExtendByPid(pid);
			modelAndView.addObject("imagesExtend", imagesExtend);
			modelAndView.setViewName("/products/img");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 分页查询商品评论
	@RequestMapping("/findCommentByPage")
	public ModelAndView findCommentByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得客户端提交页码
		int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
		int pid = Integer.parseInt(request.getParameter("pid"));// 如果不是数字报错
		// 将页码传递到service层
		pageBean.setpNum(pNum);
		pageBean.setPid(pid);
		try {
			pageBean = productsExtendService.findCommentByPage(pageBean);
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/productsDetailComment");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 分页查询
	@RequestMapping("/findProductsByPage")
	public ModelAndView findProductsByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得客户端提交页码
		int pNum = Integer.parseInt(request.getParameter("pNum"));// 如果不是数字报错
		int status1 = Integer.parseInt(request.getParameter("status"));// 如果不是数字报错
		// 保存status
		request.getSession().setAttribute("status", status1);

		// 将页码传递到service层
		pageBean.setpNum(pNum);
		pageBean.setStatus(status1);
		try {
			pageBean = productsExtendService.findProductsByPage(pageBean);
			modelAndView.addObject("status", status1);
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/page_list");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 插入商品
	@RequestMapping("/insertProducts")
	public ModelAndView insertProducts(HttpServletRequest request,
			HttpServletResponse response, ProductsExtend productsExtend)

	throws ServletException, IOException {
		int status = Integer.parseInt(request.getParameter("status"));
		if (status == 1) {
			productsExtend.setStatus(status);
		}
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		System.out.println("=============usersCustom=======" + usersCustom);
		if (usersCustom == null) {
			// 跳转到登录页面
			request.setAttribute("msg", "您还没有登录，请登录后挂货");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);

		} else {
			String[] authorities = usersCustom.getAuthority().split(",");
			if (authorities[1].equals("0")) {
				request.setAttribute("msg", "权限不足");
				modelAndView.addObject("usersCustom", usersCustom);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			}

			List<String> images = new ArrayList<String>();
			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							System.out.println(myFileName);
							// 重命名上传后的文件名
							String originalFilename = file
									.getOriginalFilename();
							// 上传图片
							if (file != null && originalFilename != null
									&& originalFilename.length() > 0) {

								// 唯一UUID 随机文件名,MultipartFile avator
								// 新的图片名称
								String uuidname = UUID.randomUUID()
										+ originalFilename
												.substring(originalFilename
														.lastIndexOf("."));
								// 分散目录生成
								String randomDir = UploadUtils
										.generateRandomDir(uuidname);
								// 创建随机目录
								File dir = new File(request
										.getSession()
										.getServletContext()
										.getRealPath(
												"/uploadProductsImage"
														+ randomDir));
								dir.mkdirs();
								// 文件上传
								InputStream in;
								try {
									in = new BufferedInputStream(
											file.getInputStream());
									OutputStream out = new BufferedOutputStream(
											new FileOutputStream(new File(dir,
													uuidname)));
									int b;
									while ((b = in.read()) != -1) {
										out.write(b);
									}
									out.close();
									in.close();
									images.add("/uploadProductsImage"
											+ randomDir + "/" + uuidname);
								} catch (IOException e) {
									throw new RuntimeException(
											" Controller-----文章照片按流写入错误");
								}
							}
						}
					}
				}
			}

			if (productsExtend.getQuantity() == 0) {
				request.setAttribute("msg", "您上传的数量为0，请您重新操作");
				request.getRequestDispatcher("/Notice.jsp").forward(request,
						response);
			} else {

				try {
					System.out
							.println("-------------------------------------------------------");
					productsExtendService
							.insertProducts(productsExtend, images);
					request.setAttribute("msg", "您上传成功");
					if (status == 1) {
						request.getRequestDispatcher("/adminguahuo.jsp")
								.forward(request, response);
					} else {
						request.getRequestDispatcher("/guahuo.jsp").forward(
								request, response);
					}
				} catch (Exception e) {
					request.setAttribute("msg", "您上传失败，请重新上传");
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}

			}
		}
		return null;

	}

	// admin查看商品详细信息
	@RequestMapping("/productsDetail")
	public ModelAndView productsDetail(ProductsExtend productsExtend)
			throws Exception {

		int pid = productsExtend.getPid();
		// 更新浏览次数
		productsExtendService.productsExtendViews(pid);
		productsExtend = productsExtendService.findProductsById(pid);
		String pcname = product_categoriesMaper.findPcnameByPcid(productsExtend
				.getPcid());
		// 图片
		List<ImagesExtend> imagesExtend = imagesExtendService
				.findImagesExtendByPid(pid);
		productsExtend.setImagesExtend(imagesExtend);
		productsExtend.setPcname(pcname);
		// 评论次数
		int count = productsExtendService.countProduct_ommentsByPid(pid);
		productsExtend.setCommentcount(count);
		modelAndView.addObject("productsExtend", productsExtend);
		modelAndView.setViewName("/products/productsDetail");
		return modelAndView;
	}
}
