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

	// ��ѯ����status=1��quantity��=0����Ʒ
	@RequestMapping("/findAllProducts")
	public ModelAndView findAllProducts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���մ�ǰ̨������״̬��
		int status = Integer.parseInt(request.getParameter("status"));// ����������ֱ���

		// ���������͵���Ʒ
		productsExtend.setStatus(status);
		productsExtend.setType("�л���");
		List<ProductsExtend> productsList = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsList) {
			// ������Ʒ��������Ʒ��ID�ҵ�ͼƬ
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// ��ͼƬ���ϷŽ���Ʒ�С�
			productsExtend.setImagesExtend(imagesExtend);
			//
			// �������ҡ�����pid�ҵ�orders�ļ��ϣ����������ҵ�uid,���û� ��ͷ��ĵ�ַ�ŵ���Ʒ��
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// �û���ͷ��
				productsExtend.setUsername(usersCustom.getUsername());// �û�������
				System.out.println("------------useraname---------"
						+ productsExtend.getUsername());
			}

		}

		// �������л�����Ʒ
		productsExtend.setType("�л���");
		List<ProductsExtend> productsExchange = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsExchange) {
			// ������Ʒ��������Ʒ��ID�ҵ�ͼƬ
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// ��ͼƬ���ϷŽ���Ʒ�С�
			productsExtend.setImagesExtend(imagesExtend);
			//
			// �������ҡ�����pid�ҵ�orders�ļ��ϣ����������ҵ�uid,���û� ��ͷ��ĵ�ַ�ŵ���Ʒ��
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// �û���ͷ��
				productsExtend.setUsername(usersCustom.getUsername());// �û�������
				System.out.println("------------useraname---------"
						+ productsExtend.getUsername());
			}

		}

		// �������л�����Ʒ
		productsExtend.setType("�۱���");
		List<ProductsExtend> productsCheap = productsExtendService
				.findAllProducts(productsExtend);
		for (ProductsExtend productsExtend : productsCheap) {
			// ������Ʒ��������Ʒ��ID�ҵ�ͼƬ
			List<ImagesExtend> imagesExtend = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// ��ͼƬ���ϷŽ���Ʒ�С�
			productsExtend.setImagesExtend(imagesExtend);
			//
			// �������ҡ�����pid�ҵ�orders�ļ��ϣ����������ҵ�uid,���û� ��ͷ��ĵ�ַ�ŵ���Ʒ��
			List<OrderitemsExtend> orderitemsExtend = productsExtendService
					.findOrderidByPid(productsExtend.getPid());
			for (OrderitemsExtend orderitemsExtend2 : orderitemsExtend) {

				int uid = productsExtendService
						.findUIdByOrderid(orderitemsExtend2.getOrderid());
				UsersCustom usersCustom = usersService.findUserById(uid);
				productsExtend.setAvator(usersCustom.getAvator());// �û���ͷ��
				productsExtend.setUsername(usersCustom.getUsername());// �û�������
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

	// ������Ʒ����
	@RequestMapping("/insertComments")
	public String insertComments(HttpServletRequest request,
			HttpServletResponse response,
			Product_commentsExtend product_commentsExtend)
			throws ServletException, IOException {

		int uid = 3;
		int pid = Integer.parseInt(request.getParameter("pid"));// ����������ֱ���
		product_commentsExtend.setUid(uid);

		try {
			productsExtendService
					.insertProduct_comments(product_commentsExtend);
		} catch (Exception e) {
			request.setAttribute("msg", "���۳�������������");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		modelAndView.setViewName("/products/productsDetailComment");
		return "redirect:/findCommentByPage.action?pNum=1&pid=" + pid;

	}

	// ������Ʒ���Ʒ�ҳ��ѯ
	@RequestMapping("/findOrderitemsByPidWeb")
	public ModelAndView findOrderitemsByPidWeb(HttpServletRequest request)
			throws Exception {
		// ��ÿͻ����ύҳ��
		String name = request.getParameter("name");
		// ��ҳ�봫�ݵ�service��
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// ������Ʒ���ҵ�pid
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

	// ������Ʒ���Ʒ�ҳ��ѯfindOrderitemsByPid
	@RequestMapping("/findOrderitemsByPid")
	public ModelAndView findOrderitemsByPid(HttpServletRequest request)
			throws Exception {
		// ��ÿͻ����ύҳ��
		String name = request.getParameter("name");
		// ��ҳ�봫�ݵ�service��
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// ������Ʒ���ҵ�pid
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

	// ͨ���û������Ҷ�����ϸ
	@RequestMapping("/findOrderitemsByUsersname")
	public ModelAndView findOrderitemsByUsersname(HttpServletRequest request)
			throws Exception {
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���

		// ��ҳ�봫�ݵ�service��
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		// ����û���
		String username = request.getParameter("username");
		usersService.findUserByUserName(username);
		// �ҵ��û��������û���id����orderid,��ͨ������orderitemsid
		pageBean = productsExtendService.findOrderitemsExtendByPage(pageBean);

		modelAndView.addObject("pageBean", pageBean);

		modelAndView.setViewName("/products/orderitemsPageList");
		return modelAndView;

	}

	// ��ѯ���ж���
	@RequestMapping("/findOrderitemsExtendByPage")
	public ModelAndView findOrderitemsExtendByPage(HttpServletRequest request)
			throws Exception {
		// ��ÿͻ����ύҳ��

		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���

		// ��ҳ�봫�ݵ�service��
		pageBean.setpNum(pNum);

		PageBean pageBean1 = productsExtendService
				.findOrderitemsExtendByPage(pageBean);
		modelAndView.addObject("pageBean", pageBean1);

		modelAndView.setViewName("/products/orderitemsPageList");
		return modelAndView;
	}

	// ��ѯ��Ʒ��type
	@RequestMapping("/findOrderitemsExtendByPageWeb")
	public ModelAndView findOrderitemsExtendByPageWeb(HttpServletRequest request)
			throws Exception {
		// ��ÿͻ����ύҳ��

		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���

		// ��ҳ�봫�ݵ�service��
		pageBean.setpNum(pNum);

		pageBean = productsExtendService.findOrderitemsExtendByPage(pageBean);
		modelAndView.addObject("pageBean", pageBean);

		modelAndView.setViewName("/products/orderitemsPageListWeb");
		return modelAndView;
	}

	// ɾ������
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

	// ��ҳ��ѯ���˹����¼
	@RequestMapping("findPersonOrderitemsByPage")
	public ModelAndView findPersonOrderitemsByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// Ҫ�ж��û��Ƿ��¼����¼���û����ܹ��鿴
		// ͨ��session����û���uid
		// ͨ���û�id���Ҷ���
		if (usersCustom == null) {
			request.setAttribute("msg", "����û�е�¼�����¼��鿴");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			
			// ��ҳ�봫�ݵ�service��
			int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
			pageBean.setpNum(pNum);
			pageBean.setUid(usersCustom.getUid());
			pageBean.setUsersName(usersCustom.getUsername());
			try {
				// �����û����ҵ����û������Զ�����
				pageBean = productsExtendService.findPersonOrderitemsByPage(pageBean);
			} catch (Exception e) {
				request.setAttribute("msg", "��ȡ��Ϣʧ��");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/personOrderitems");
			return modelAndView;

		}

		return modelAndView;
	}

	// ���˹����¼
	@RequestMapping("/findPersonOrderitems")
	public ModelAndView findPersonOrderitems(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// Ҫ�ж��û��Ƿ��¼����¼���û����ܹ��鿴
		// ͨ��session����û���uid
		// ͨ���û�id���Ҷ���
		if (usersCustom == null) {
			request.setAttribute("msg", "����û�е�¼�����¼��鿴");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			System.out.println("===================uid==========="
					+ usersCustom.getUid());
			List<OrdersExtend> ordersExtends = productsExtendService
					.findOrdersExtendByUid(usersCustom.getUid());
			for (OrdersExtend ordersExtend : ordersExtends) {
				// ����orderID���Ҷ�����ϸ
				List<OrderitemsExtend> orderitemsExtends = productsExtendService
						.findOrderitemsByOrderid(ordersExtend.getOrderid());
				for (OrderitemsExtend orderitemsExtend : orderitemsExtends) {
					ProductsExtend productsExtend = productsExtendService
							.findProductsById(orderitemsExtend.getPid());
					orderitemsExtend.setProductsExtend(productsExtend);
				}
				ordersExtend.setUname(usersCustom.getUsername());
				// ͨ��orderid����pid
				ordersExtend.setOrderitemsExtend(orderitemsExtends);
			}

			modelAndView.addObject("ordersExtend2", ordersExtends);
		}
		System.out.println("=======================================");
		modelAndView.setViewName("/products/personOrderitems");
		return modelAndView;

	}

	// ���ɶ���
	@RequestMapping("/insertOrderitemsSuccess")
	public String insertOrderitemsSuccess(OrderitemsExtend orderitemsExtend,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession().getAttribute("usersCustom");
		int num = 0;
		if (usersCustom == null) {
			request.setAttribute("msg", "����û�е�¼�����¼����");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			int pid = Integer.parseInt(request.getParameter("pid"));
			List<OrdersExtend> ordersExtends;
			ProductsExtend productsExtend1 = null;
			try {
				/*
				 * 1.�������������� 2.����uid�ҵ����û������еĶ���������û�û�ж���������Թ���
				 * ���ж��������ж��û�ͬһ����Ʒ�Ķ����Ƿ񳬹����Ƶ������� �������ܹ��򣬲��������������
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
								 * �ж���������Ʒ��pid�Ƿ��������Ŀ��pidһ����
								 */
								if (pid == orderitemsExtend2.getPid()) {
									num++;
								}
							}
							productsExtend1 = productsExtendService
									.findProductsById(pid);

						} catch (Exception e) {
							request.setAttribute("msg", "������Ʒʧ��1");
							request.getRequestDispatcher("/error.jsp").forward(
									request, response);
						}
					}
				}
				// ���ƹ��������
				if (productsExtend1.getLimit() == num) {
					request.setAttribute("msg", "������������Ѿ��ﵽ���ޣ������");
					request.getRequestDispatcher("/Notice.jsp").forward(
							request, response);
				} else {
					productsExtendService.insertOrderitems(
							usersCustom.getUid(), pid);
					request.getRequestDispatcher("/success.jsp").forward(
							request, response);
				}
			} catch (Exception e) {
				request.setAttribute("msg", "������Ʒʧ��2");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}

		}
		return null;
	}

	// ��������ѯ����
	@RequestMapping("/findOrdersByCondition")
	public ModelAndView findOrdersByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		// ��ҳ�봫�ݵ�service��
		int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
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
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;

	}

	// ����������ѯ��Ʒ
	@RequestMapping("/findByPageByName")
	public ModelAndView findByPageByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ÿͻ����ύҳ��
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		// ��ҳ�봫�ݵ�service��
		int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
		int status = Integer.parseInt(request.getParameter("status"));// ����������ֱ���
		pageBean.setpNum(pNum);
		pageBean.setStatus(status);
		pageBean.setConditionName(conditionName);
		pageBean.setConditionValue(conditionValue);
		try {
			pageBean = productsExtendService.findByPageByName(pageBean);
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/products/page_listName");
		return modelAndView;

	}

	// ������Ʒ�ķ����ҳ��ѯ��Ʒ
	@RequestMapping("/findShangchengByType")
	public ModelAndView findShangchengByType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���ҳ���ύ��type
		String type = request.getParameter("type");
		String type1 = new String(type.getBytes("iso-8859-1"), "utf-8");
		productsExtend.setType(type1);
		// ��ҳ�봫�ݵ�service��
		int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
		int status = Integer.parseInt(request.getParameter("status"));// ����������ֱ���
		pageBean.setpNum(pNum);
		pageBean.setStatus(status);
		pageBean.setType(type1);
		try {
			pageBean = productsExtendService.findProductsByType(pageBean);
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		modelAndView.addObject("pageBean", pageBean);
		if (productsExtend.getType().equals("�л���")) {

			modelAndView.setViewName("/products/testgive");
		} else {

			if (productsExtend.getType().equals("�л���")) {
				modelAndView.setViewName("/products/testExchange");
			} else {
				modelAndView.setViewName("/products/testCheap");
			}
		}
		return modelAndView;

	}

	// ������Ʒ�����Ʋ�ѯ
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
			// ͨ����Ʒ��pid����ͼƬ ��Ʒ��ͼƬ=1����
			List<ImagesExtend> imagesExtend1 = imagesExtendService
					.findImagesExtendByPid(productsExtend.getPid());
			// ����ͼƬ����ͼƬ�ĵ�ַ���Ƶ���Ʒ�ĵ�ַ��
			for (ImagesExtend imagesExtend : imagesExtend1) {
				productsExtend.setUrl(imagesExtend.getUrl());
			}
		}

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productsList1", productName);
		if (productsExtend2.getType().equals("�л���")) {
			modelAndView.setViewName("/products/testgive");
		} else {

			if (productsExtend2.getType().equals("�л���")) {
				modelAndView.setViewName("/products/testExchange");
			} else {

				modelAndView.setViewName("/products/testCheap");
			}
		}
		return modelAndView;

	}

	// ��ѯ��Ʒ����
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
			if (productsQuery.getType().equals("�л���")) {
				modelAndView.setViewName("/products/testgive");
			} else {

				if (productsQuery.getType().equals("�л���")) {
					modelAndView.setViewName("/products/testExchange");
				} else {

					modelAndView.setViewName("/products/testCheap");
				}
			}
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ��ѯ��Ʒ��type
	@RequestMapping("/findProductsType")
	public ModelAndView findProductsType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String type1 = new String(type.getBytes("iso-8859-1"), "utf-8");
		if (type1.equals("give")) {
			type1 = "�л���";
		}
		productsExtend.setType(type1);
		List<ProductsExtend> productsExtend1;
		try {
			productsExtend1 = productsExtendService
					.findProductsType(productsExtend);
			for (ProductsExtend productsExtend2 : productsExtend1) {
				// ͨ����Ʒ��pid����ͼƬ ��Ʒ��ͼƬ=1����
				List<ImagesExtend> imagesExtend1 = imagesExtendService
						.findImagesExtendByPid(productsExtend2.getPid());
				// ����ͼƬ����ͼƬ�ĵ�ַ���Ƶ���Ʒ�ĵ�ַ��
				for (ImagesExtend imagesExtend : imagesExtend1) {
					productsExtend2.setUrl(imagesExtend.getUrl());
				}
			}
			// ����ModelAndView
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("productsList1", productsExtend1);
			if (productsExtend.getType().equals("�л���")) {
				modelAndView.setViewName("/products/testgive");
			} else {

				if (productsExtend.getType().equals("�л���")) {
					modelAndView.setViewName("/products/testExchange");
				} else {

					modelAndView.setViewName("/products/testCheap");
				}
			}
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣ");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ������Ʒidɾ����Ʒ
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
			request.setAttribute("msg", "ɾ����Ʒʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// �޸���Ʒ��Ϣ
	@RequestMapping("/updateProducts")
	public ModelAndView updateProducts(HttpServletRequest request,
			ProductsExtend productsExtend, HttpServletResponse response)
			throws ServletException, IOException {

		ProductsExtend productsExtend1;
		try {

			// ����
			productsExtend1 = productsExtendService
					.updateProductsById(productsExtend);
			modelAndView.addObject("productsExtend1", productsExtend1);

			request.setAttribute("msg", "�޸���Ʒ�ɹ�");
			modelAndView.setViewName("/products/page_list");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "�޸���Ʒʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ����id��ѯ��Ʒ
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

	// ������Ʒpid��ѯͼƬ
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
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ��ҳ��ѯ��Ʒ����
	@RequestMapping("/findCommentByPage")
	public ModelAndView findCommentByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ÿͻ����ύҳ��
		int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
		int pid = Integer.parseInt(request.getParameter("pid"));// ����������ֱ���
		// ��ҳ�봫�ݵ�service��
		pageBean.setpNum(pNum);
		pageBean.setPid(pid);
		try {
			pageBean = productsExtendService.findCommentByPage(pageBean);
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/productsDetailComment");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ��ҳ��ѯ
	@RequestMapping("/findProductsByPage")
	public ModelAndView findProductsByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ÿͻ����ύҳ��
		int pNum = Integer.parseInt(request.getParameter("pNum"));// ����������ֱ���
		int status1 = Integer.parseInt(request.getParameter("status"));// ����������ֱ���
		// ����status
		request.getSession().setAttribute("status", status1);

		// ��ҳ�봫�ݵ�service��
		pageBean.setpNum(pNum);
		pageBean.setStatus(status1);
		try {
			pageBean = productsExtendService.findProductsByPage(pageBean);
			modelAndView.addObject("status", status1);
			modelAndView.addObject("pageBean", pageBean);
			modelAndView.setViewName("/products/page_list");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ������Ʒ
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
			// ��ת����¼ҳ��
			request.setAttribute("msg", "����û�е�¼�����¼��һ�");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);

		} else {
			String[] authorities = usersCustom.getAuthority().split(",");
			if (authorities[1].equals("0")) {
				request.setAttribute("msg", "Ȩ�޲���");
				modelAndView.addObject("usersCustom", usersCustom);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			}

			List<String> images = new ArrayList<String>();
			// ����һ��ͨ�õĶಿ�ֽ�����
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// �ж� request �Ƿ����ļ��ϴ�,���ಿ������
			if (multipartResolver.isMultipart(request)) {
				// ת���ɶಿ��request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// ȡ��request�е������ļ���
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// ��¼�ϴ�������ʼʱ��ʱ�䣬���������ϴ�ʱ��
					int pre = (int) System.currentTimeMillis();
					// ȡ���ϴ��ļ�
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// ȡ�õ�ǰ�ϴ��ļ����ļ�����
						String myFileName = file.getOriginalFilename();
						// ������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������
						if (myFileName.trim() != "") {
							System.out.println(myFileName);
							// �������ϴ�����ļ���
							String originalFilename = file
									.getOriginalFilename();
							// �ϴ�ͼƬ
							if (file != null && originalFilename != null
									&& originalFilename.length() > 0) {

								// ΨһUUID ����ļ���,MultipartFile avator
								// �µ�ͼƬ����
								String uuidname = UUID.randomUUID()
										+ originalFilename
												.substring(originalFilename
														.lastIndexOf("."));
								// ��ɢĿ¼����
								String randomDir = UploadUtils
										.generateRandomDir(uuidname);
								// �������Ŀ¼
								File dir = new File(request
										.getSession()
										.getServletContext()
										.getRealPath(
												"/uploadProductsImage"
														+ randomDir));
								dir.mkdirs();
								// �ļ��ϴ�
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
											" Controller-----������Ƭ����д�����");
								}
							}
						}
					}
				}
			}

			if (productsExtend.getQuantity() == 0) {
				request.setAttribute("msg", "���ϴ�������Ϊ0���������²���");
				request.getRequestDispatcher("/Notice.jsp").forward(request,
						response);
			} else {

				try {
					System.out
							.println("-------------------------------------------------------");
					productsExtendService
							.insertProducts(productsExtend, images);
					request.setAttribute("msg", "���ϴ��ɹ�");
					if (status == 1) {
						request.getRequestDispatcher("/adminguahuo.jsp")
								.forward(request, response);
					} else {
						request.getRequestDispatcher("/guahuo.jsp").forward(
								request, response);
					}
				} catch (Exception e) {
					request.setAttribute("msg", "���ϴ�ʧ�ܣ��������ϴ�");
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}

			}
		}
		return null;

	}

	// admin�鿴��Ʒ��ϸ��Ϣ
	@RequestMapping("/productsDetail")
	public ModelAndView productsDetail(ProductsExtend productsExtend)
			throws Exception {

		int pid = productsExtend.getPid();
		// �����������
		productsExtendService.productsExtendViews(pid);
		productsExtend = productsExtendService.findProductsById(pid);
		String pcname = product_categoriesMaper.findPcnameByPcid(productsExtend
				.getPcid());
		// ͼƬ
		List<ImagesExtend> imagesExtend = imagesExtendService
				.findImagesExtendByPid(pid);
		productsExtend.setImagesExtend(imagesExtend);
		productsExtend.setPcname(pcname);
		// ���۴���
		int count = productsExtendService.countProduct_ommentsByPid(pid);
		productsExtend.setCommentcount(count);
		modelAndView.addObject("productsExtend", productsExtend);
		modelAndView.setViewName("/products/productsDetail");
		return modelAndView;
	}
}
