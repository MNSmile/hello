package com.pw.web.servlet;

import com.pw.util.CodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/validateCode")
public class ValidateCodeServlet extends HttpServlet {
    private List<String> words = new ArrayList<>();//读取所有的验证码放到此集合中

    /**
     * servlet初始化方法，只会执行一次
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //获取ServletContext 应用上下文
        ServletContext context = config.getServletContext();
        //获取项目中指定资源的真实路径
        String path = context.getRealPath("/WEB-INF/words.txt");
        System.out.println(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
            String line = null;
            while((line = br.readLine())!=null){
                words.add(line);
            }
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用验证码工具类得到验证码和验证码所生成的图片数据
        Map<String, Object> map = CodeUtil.code(120,30,30,words);
        //告知客户端浏览器,写回来的数据是图片
        resp.setContentType("image/jpeg");
        //取出生成的验证码
        String code = (String)map.get("code");
        //把验证码放到Session中
        req.getSession().setAttribute("code",code);
        //以字节输出流的方式输出到浏览器
        ServletOutputStream out = resp.getOutputStream();//获取字节输出流
        //借助ImageIO工具把图片数据转换成二进制的图片,并用字节输出流把二进制数据输出到客户端
        ImageIO.write((RenderedImage) map.get("codePic"),"jpeg",out);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
