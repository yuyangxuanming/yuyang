package com.example.demo4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 生成验证码图片
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics g = getGraphics(image);
        //生成验证码,调用getNumber方法
        String number = getNumber(5);
        //将验证码保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("vcode", number);
        //将验证码写入图片
        g.drawString(number, 5, 20);
        //将验证码图片输出到页面
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpeg", os);
        os.close();
    }

    private static Graphics getGraphics(BufferedImage image) {
        Graphics g = image.getGraphics();
        Random rd = new Random();
        //设置画笔颜色
        g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
        //填充背景色
        g.fillRect(0, 0, 100, 30);
        //绘制8条干扰线
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
            g.drawLine(rd.nextInt(100), rd.nextInt(30), rd.nextInt(100), rd.nextInt(30));
        }
        //设置字体
        g.setFont(new Font(null,Font.BOLD+Font.ITALIC, 20));
        return g;
    }

    public String getNumber(int size){
        String str="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        String number="";
        Random r=new Random();
        for(int i=0;i<size;i++){
            number+=str.charAt(r.nextInt(str.length()));
        }
        return number;
    }
}
