package com.ace.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.others.CreateData;
import com.ace.utils.ThreadUtils;

import net.sf.json.JSONObject;
/*
 * Config.jsp中点击自动生成数据按钮后跳转至此servlet
 * 由于String i = request.getParameter("do")在增改摄像头时会出问题
 * 故分成两个servlet
 * */
public class ConfigData1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ThreadUtils t = new ThreadUtils();
	CreateData c = new CreateData();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("do");
		//找到自动添加数据的线程
		c = t.getCreateData();
		//判断是开始还是停止
		if(i.equals("1")) {
			c.setActive(false);
			try {
				//缓冲时间确保线程安全
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			c.setActive(true);
			try {
				//缓冲时间确保线程安全
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.start();
		}
		try {
			response.setContentType("text/html; charset=utf-8");
			//随意返回一个结果
			String a = "{\"do\":\"efg\"}"; 
			JSONObject json = JSONObject.fromObject(a);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
