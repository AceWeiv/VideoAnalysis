package com.ace.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.others.VideoAnalysis;
import com.ace.utils.ThreadUtils;

import net.sf.json.JSONObject;

public class ConfigData2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThreadUtils t = new ThreadUtils();
	private VideoAnalysis v = new VideoAnalysis();
       
    public ConfigData2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received...");
		v = t.getVideoAnalysis();
		v.setMes("1,3,1,3,5,2,30");
		v.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
