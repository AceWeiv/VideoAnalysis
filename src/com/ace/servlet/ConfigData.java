package com.ace.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ace.dao.ConfigDAO;
import com.ace.dao.impl.ConfigDAOImpl;
import com.ace.domain.Config;
import com.ace.others.CreateData;
import com.ace.utils.ThreadUtils;

/*
 * Config.jsp的servlet
 * 跳转时执行此servlet
 * */
public class ConfigData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ThreadUtils t = new ThreadUtils();
	ConfigDAO configDAO = new ConfigDAOImpl();
	CreateData c = new CreateData();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Config> cons = configDAO.getAll();
		//判断自动插入线程是否存在，存在则找到那条线程，否则新建自动插入线程
		String i = "0";
		c = t.getCreateData();
		if(c.isActive()) {
			i = "1";
		}
		request.setAttribute("cons", cons);
		request.setAttribute("ifDatasource", i);
		request.getRequestDispatcher("/ConfigData.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
}
