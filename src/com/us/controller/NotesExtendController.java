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

	
	// ��ҳ��ѯ����findByPageNotes
	@RequestMapping("/findByPageNotes")
	public ModelAndView findByPageNotes(HttpServletRequest request)
			throws Exception {
		// ��ÿͻ����ύҳ��
		
		String pNumStr = request.getParameter("pNum");
		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���
		// ��ҳ�봫�ݵ�service��
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
	
		pageBean = notesService.findByPageNotes(pageBean);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/leave/messages");
		return modelAndView;
	}
	// ��ҳ��ѯ����findByPageNotes
	@RequestMapping("/findByPageNotesName")
	public ModelAndView findByPageNotesName(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		// ��ÿͻ����ύ�û���
        String username=request.getParameter("username");
        //���Ǵ���get������
        if(request.getMethod().equalsIgnoreCase("Get")){
            username = new String(username.getBytes("iso-8859-1"), "utf-8");        	
        }
    	//ͨ��username����uid
        System.out.println("-----------------"+username);
		UsersCustom usersCustom =usersService.findUserByUserName(username);
		System.out.println("======�û�û��==="+usersCustom);
        //�ж��û����Ƿ�Ϊ��
        if(usersCustom!=null&&!username.equals("")){
        	System.out.println("0000000000000000000000000000");
    		String pNumStr = request.getParameter("pNum");
    		int pNum = Integer.parseInt(pNumStr);// ����������ֱ���
    	
    		// ��ҳ�봫�ݵ�service��
    		PageBean pageBean = new PageBean();
    		pageBean.setpNum(pNum);
    		pageBean.setName(username);
    		pageBean.setUid(usersCustom.getUid());
    		pageBean = notesService.findByPageNotesName(pageBean);
    		modelAndView.addObject("pageBean", pageBean);
    		modelAndView.setViewName("/leave/messagesName");
    		return modelAndView;
                 	
        }else{
        	request.setAttribute("msg", "�������û�������û�������");
			request.getRequestDispatcher("/error.jsp").forward(request, response);  
        }
        
        return null;
	}
	// ����ɾ������
	@RequestMapping("/delBatchNotes")
	public ModelAndView delBatchNotes(HttpServletRequest request,
			NotesExtend notesExtend) throws Exception {

		// ��ö�����
		String[] ids = request.getParameterValues("ids");
		Integer[] ids1 = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ids1[i] = Integer.parseInt(ids[i]);
		}

		if (ids1 != null) {
			// ���ݵ�service��
			for (int id : ids1) {
				notesService.deleteNotes(id);
			}
		}
		modelAndView.addObject("notesExtend1", notesExtend);
		modelAndView.setViewName("/leave/delSuccess");
		return modelAndView;

	}

	// ��������
	@RequestMapping("/insertNotes")
	public ModelAndView insertNotes(NotesExtend notesExtend,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		UsersCustom usersCustom = (UsersCustom) request.getSession().getAttribute("usersCustom");
		//����û�δ��
		if(usersCustom==null){
			
			request.setAttribute("msg", "����û�е�¼�����¼��������");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}else{
					  notesExtend.setUid(usersCustom.getUid());
					// ��������
					 notesService.insertNotes(notesExtend);
					//�����ݿ��в�������
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
	

	// ��ѯ���е�����
	@RequestMapping("/findAllNotesWeb")
	public ModelAndView findAllNotesWeb(NotesExtend notesExtend)
			throws Exception {

		List<NotesExtend> notesExtend1 = notesService.findAllNotesWeb();
		//��������id�ҵ��û�id
		for (NotesExtend notesExtend2 : notesExtend1) {
		int uid=notesService.findUidByNoteid(notesExtend2.getNoteid());
		//����uid����user
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

	// ��ѯ���е�����
	@RequestMapping("/findAllNotes")
	public ModelAndView findAllNotes(NotesExtend notesExtend) throws Exception {

		List<NotesExtend> notesExtend1 = notesService.findAllNotes();
		modelAndView.addObject("notesExtend1", notesExtend1);
		modelAndView.setViewName("/leave/messages");
		return modelAndView;
	}

	// ����idɾ������
	@RequestMapping("/deleteNotes")
	public ModelAndView deleteNotes(NotesExtend notesExtend) throws Exception {
		int id = notesExtend.getNoteid();
		notesService.deleteNotes(id);
		modelAndView.addObject("notesExtend1", notesExtend);
		modelAndView.setViewName("/leave/delSuccess");
		return modelAndView;

	}
}
