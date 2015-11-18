package com.us.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.us.mapper.UsersCustomMapper;
import com.us.po.NotesExtend;
import com.us.po.PageBean;
import com.us.po.UsersCustom;
import com.us.service.NotesExtendService;
import com.us.service.UsersService;

@Controller
public class NotesExtendController {
	@Autowired
	UsersService usersService;
	@Autowired
	NotesExtendService notesService;
	ModelAndView modelAndView = new ModelAndView();

	
	// 分页查询留言findByPageNotes
	@RequestMapping("/findByPageNotes")
	public ModelAndView findByPageNotes(HttpServletRequest request)
			throws Exception {
		// 获得客户端提交页码
		
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错
		// 将页码传递到service层
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
	
		pageBean = notesService.findByPageNotes(pageBean);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/leave/messages");
		return modelAndView;
	}
	// 分页查询留言findByPageNotes
	@RequestMapping("/findByPageNotesName")
	public ModelAndView findByPageNotesName(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		// 获得客户端提交用户名
        String username=request.getParameter("username");
        //这是处理get方法的
        if(request.getMethod().equalsIgnoreCase("Get")){
            username = new String(username.getBytes("iso-8859-1"), "utf-8");        	
        }
    	//通过username查找uid
        System.out.println("-----------------"+username);
		UsersCustom usersCustom =usersService.findUserByUserName(username);
		System.out.println("======用户没有==="+usersCustom);
        //判断用户名是否为空
        if(usersCustom!=null&&!username.equals("")){
        	System.out.println("0000000000000000000000000000");
    		String pNumStr = request.getParameter("pNum");
    		int pNum = Integer.parseInt(pNumStr);// 如果不是数字报错
    	
    		// 将页码传递到service层
    		PageBean pageBean = new PageBean();
    		pageBean.setpNum(pNum);
    		pageBean.setName(username);
    		pageBean.setUid(usersCustom.getUid());
    		pageBean = notesService.findByPageNotesName(pageBean);
    		modelAndView.addObject("pageBean", pageBean);
    		modelAndView.setViewName("/leave/messagesName");
    		return modelAndView;
                 	
        }else{
        	request.setAttribute("msg", "请输入用户名或该用户不存在");
			request.getRequestDispatcher("/error.jsp").forward(request, response);  
        }
        
        return null;
	}
	// 批量删除留言
	@RequestMapping("/delBatchNotes")
	public ModelAndView delBatchNotes(HttpServletRequest request,
			NotesExtend notesExtend) throws Exception {

		// 获得多个编号
		String[] ids = request.getParameterValues("ids");
		Integer[] ids1 = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ids1[i] = Integer.parseInt(ids[i]);
		}

		if (ids1 != null) {
			// 传递到service层
			for (int id : ids1) {
				notesService.deleteNotes(id);
			}
		}
		modelAndView.addObject("notesExtend1", notesExtend);
		modelAndView.setViewName("/leave/delSuccess");
		return modelAndView;

	}

	// 插入留言
	@RequestMapping("/insertNotes")
	public ModelAndView insertNotes(NotesExtend notesExtend,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		UsersCustom usersCustom = (UsersCustom) request.getSession().getAttribute("usersCustom");
		//如果用户未空
		if(usersCustom==null){
			
			request.setAttribute("msg", "您还没有登录，请登录后再留言");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}else{
					  notesExtend.setUid(usersCustom.getUid());
					// 插入留言
					 notesService.insertNotes(notesExtend);
					//从数据库中查找留言
					List<NotesExtend> notesExtend1 = notesService.findAllNotesWeb();
					for (NotesExtend notesExtend2 : notesExtend1) {
							UsersCustom usersCustom1 = usersService.findUserById(notesExtend2
								.getUid());
							notesExtend2.setUsername(usersCustom1.getUsername());
							notesExtend2.setAvator(usersCustom1.getAvator());
						}
						
						modelAndView.addObject("notesExtend1", notesExtend1);
						modelAndView.setViewName("/leave/messagesWeb");
				 
		   }
		
				return modelAndView;

	}
	

	// 查询所有的留言
	@RequestMapping("/findAllNotesWeb")
	public ModelAndView findAllNotesWeb(NotesExtend notesExtend)
			throws Exception {

		List<NotesExtend> notesExtend1 = notesService.findAllNotesWeb();
		//根据留言id找到用户id
		for (NotesExtend notesExtend2 : notesExtend1) {
		int uid=notesService.findUidByNoteid(notesExtend2.getNoteid());
		//根据uid查找user
		System.out.println("=================="+uid);
		UsersCustom usersCustom =usersService.findUserById(uid);
		notesExtend2.setUsername(usersCustom.getUsername());
		notesExtend2.setAvator(usersCustom.getAvator());
		System.out.println(notesExtend2.getAvator()+"===============");
		}
		modelAndView.addObject("notesExtend1", notesExtend1);
		modelAndView.setViewName("/leave/messagesWeb");
		return modelAndView;
	}

	// 查询所有的留言
	@RequestMapping("/findAllNotes")
	public ModelAndView findAllNotes(NotesExtend notesExtend) throws Exception {

		List<NotesExtend> notesExtend1 = notesService.findAllNotes();
		modelAndView.addObject("notesExtend1", notesExtend1);
		modelAndView.setViewName("/leave/messages");
		return modelAndView;
	}

	// 根据id删除留言
	@RequestMapping("/deleteNotes")
	public ModelAndView deleteNotes(NotesExtend notesExtend) throws Exception {
		int id = notesExtend.getNoteid();
		notesService.deleteNotes(id);
		modelAndView.addObject("notesExtend1", notesExtend);
		modelAndView.setViewName("/leave/delSuccess");
		return modelAndView;

	}
}
