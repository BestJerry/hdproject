package com.hd.general;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verificationCode")
public class VerificationCode extends HttpServlet {
	
	// ��֤�볤��
    private static final int CODE_LEN = 4;
    //ͼƬ�߶�
    private static final int IMG_WIDTH = 80;
    //ͼƬ���
    private static final int IMG_HEIGHT = 40;
    
	public VerificationCode() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("image/jpeg");
		// ���ڻ���ͼƬ������ͼƬ�ĳ����ͼƬ���ͣ�RGB)
        BufferedImage bi = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // ��ȡ��ͼ����
        Graphics graphics = bi.getGraphics();
        graphics.setColor(new Color(238, 247, 254)); // ʹ��RGB���ñ�����ɫ
        graphics.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // ����������

        // ��֤������ʹ�õ����ַ�
        char[] codeChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        String captcha = ""; // ������ɵ���֤��
        Random random = new Random();
        for(int i = 0; i < CODE_LEN; i++) { // ѭ����ÿ����֤���ַ����Ƶ�ͼƬ��
            int index = random.nextInt(codeChar.length);
            // ���������֤����ɫ
            Color color = new Color(random.nextInt(150), random.nextInt(200), random.nextInt(255));
            graphics.setColor(color);
            // ��һ���ַ����Ƶ�ͼƬ�ϣ����ƶ�λ�ã�����x,y���꣩
            graphics.drawString(codeChar[index] + "", 10 + (i * 20), 20);
            captcha += codeChar[index];
        }
        // �����ɵ���֤��code����sessoin��
        req.getSession().setAttribute("verificationCode", captcha);
        
        // ͨ��ImageIO��ͼƬ���
        ImageIO.write(bi, "JPG", resp.getOutputStream());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
