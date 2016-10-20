package com.netcracker.odmg.secondgroup.zoxal.task1;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;

import com.phonebook.DataManager;


public class PhoneBookController extends HttpServlet {
	private DataManager dataManager;
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		String dbPath = getServletConfig().getInitParameter("DB_PATH");
		String userLogin = getServletConfig().getInitParameter("DB_USER_LOGIN");
		String userPassword = getServletConfig().getInitParameter("DB_USER_PASSWORD");
		try{			
			dataManager = new DataManager(dbPath,userLogin,userPassword);
		}catch(SQLException cause) {
			throw new ServletException("Cannot connect to database", cause);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String personName = request.getParameter("personName");		
		List<String> names;
		
		try {
			names = dataManager.getNamesList();
		} catch (SQLException e) {
			//logging
			names = new LinkedList<>();
		}
		request.setAttribute("names", names);
		
		if (personName == null) {			
			request.getRequestDispatcher("/jsp/phoneBook.jsp").forward(request, response);
		} else {
			String phoneNumber; 
			
			try {
				phoneNumber = dataManager.getPhoneByName(personName);
			} catch (SQLException e) {
				//logging
				phoneNumber = "";
			}
			request.setAttribute("personName", personName);
			request.setAttribute("phoneNumber", phoneNumber);
			request.getRequestDispatcher("/jsp/phoneBook.jsp").forward(request, response);
		}
	}
}
