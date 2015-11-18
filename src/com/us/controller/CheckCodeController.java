package com.us.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CheckCodeController")
public class CheckCodeController extends HttpServlet {

	public CheckCodeController() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		super.init();
	}

	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r = s + random.nextInt(e - s);
		int g = s + random.nextInt(e - s);
		int b = s + random.nextInt(e - s);
		return new Color(r, g, b);
	}

	@RequestMapping("/service")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���Ӧ��ͼƬ
		response.setContentType("image/jpeg");
		int width = 80;
		int height = 32;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB); // ����BufferedImage��Ķ���
		Graphics g = image.getGraphics(); // ����Graphics��Ķ���
		Graphics2D g2d = (Graphics2D) g; // ͨ��Graphics��Ķ��󴴽�һ��Graphics2D��Ķ���
		Random random = new Random(); // ʵ����һ��Random����
		Font mFont = new Font("��������", Font.BOLD, 20); // ͨ��Font��������
		g.setColor(getRandColor(200, 250)); // �ı�ͼ�εĵ�ǰ��ɫΪ������ɵ���ɫ
		g.fillRect(0, 0, width, height); // ����һ����ɫ����

		// ��һ������
		BasicStroke bs = new BasicStroke(0.8f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL); // ����һ��������ѡ��������ϸ�Ķ���
		g2d.setStroke(bs); // �ı������Ĵ�ϸ
		g.setColor(Color.DARK_GRAY); // ���õ�ǰ��ɫΪԤ������ɫ�е����ɫ
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		for (int j = 0; j < 3; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
		}
		g.drawPolyline(xPoints, yPoints, 3);
		// ���ɲ�����������֤����
		g.setFont(mFont);
		String sRand = "";
		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) {
				itmp = random.nextInt(26) + 65; // ����A~Z����ĸ
			} else {
				itmp = random.nextInt(10) + 48; // ����0~9������
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			/**** ����������ֲ���������תָ���Ƕ� **/
			// ��������תָ���Ƕ�
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((random.nextInt(50) < 25 ? -1 : 1) * random.nextInt(15) * 3.14 / 180, 15 * i + 10, 7);
			// ��������
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1.1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/************************/
			g.drawString(String.valueOf(ctmp), 15 * i + 5, height/2 + 5);

		}
		// �����ɵ���֤�뱣�浽Session��
		HttpSession session = request.getSession(true);
		session.setAttribute("checkcode_session", sRand);
		
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}
