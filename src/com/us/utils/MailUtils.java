
package com.us.utils;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.us.po.UsersCustom;





/**
 * �����ʼ� ���߷���
 * 
 * @author seawind
 * 
 */
public class MailUtils {
	
 
	// �����ʼ�
	public static void sendMail(Message message, Session session)
			throws Exception {
		Transport transport = session.getTransport();
		transport.connect("13029802829", "13895895490");
		transport.sendMessage(message, message.getAllRecipients());
	}

	// �����ʼ�
	public static Message generateMessage(Session session,UsersCustom usersCustom)
			throws Exception {
		
		MimeMessage message = new MimeMessage(session);
		// �ʼ�ͷ
		message.setFrom(new InternetAddress("13029802829@sina.cn"));// ������
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(usersCustom
				.getEmail())); // �ռ���
		message.setSubject("US�̳�");
		// �ʼ�ͷ
		message
				.setContent(
						"<h2>"
								+ usersCustom.getUsername()
								+ "��ȷ��Ҫ�һ����룬������Ǳ��˲����벻Ҫ���</h2><h3>��</h3><a href='http://localhost/us1/findPasswordByEmail.jsp?uid="
								+ usersCustom.getUid()
                                +"'>http://localhost/rollcall/active?activecode=" 
                                +"</a>",
						"text/html;charset=utf-8");
		return message;
	}

	// �����Ự
	public static Session createSession() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "smtp.sina.cn");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties);
		return session;
	}
}
