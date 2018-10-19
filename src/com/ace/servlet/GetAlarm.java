package com.ace.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.dao.AlarmDAO;
import com.ace.dao.impl.AlarmDAOImpl;
import com.ace.domain.Alarm;

import net.sf.json.JSONArray;

public class GetAlarm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AlarmDAO alarmDAO = new AlarmDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		List<Alarm> alarms = alarmDAO.getNew(id);
		response.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.fromObject(alarms);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		System.out.println("getAlarm...");
	}
}
