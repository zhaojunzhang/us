
package com.us.utils;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.us.po.UsersCustom;





/**
 * 发送邮件 工具方法
 * 
 * @author seawind
 * 
 */
public class MailUtils {
	
 
	// 发送邮件
	public static void sendMail(Message message, Session session)
			throws Exception {
		Transport transport = session.getTransport();
		transport.connect("13029802829", "13895895490");
		transport.sendMessage(message, message.getAllRecipients());
	}

	// 生成邮件
	public static Message generateMessage(Session session,UsersCustom usersCustom)
			throws Exception {
		
		MimeMessage message = new MimeMessage(session);
		// 邮件头
		message.setFrom(new InternetAddress("13029802829@sina.cn"));// 发件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(usersCustom
				.getEmail())); // 收件人
		message.setSubject("US商城");
		// 邮件头
		message
				.setContent(
						"<h2>"
								+ usersCustom.getUsername()
								+ "您确定要找回密码，如果不是本人操作请不要点击</h2><h3>：</h3><a href='http://localhost/us1/findPasswordByEmail.jsp?uid="
								+ usersCustom.getUid()
                                +"'>http://localhost/rollcall/active?activecode=" 
                                +"</a>",
						"text/html;charset=utf-8");
		return message;
	}

	// 创建会话
	public static Session createSession() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "smtp.sina.cn");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties);
		return session;
	}
}
