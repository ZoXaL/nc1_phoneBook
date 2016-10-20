package com.netcracker.odmg.secondgroup.zoxal.task1;

import java.io.IOException;

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
	
	public PhoneBookController() throws SQLException{
		super();
		try{
			dataManager = new DataManager();
		}catch(SQLException cause) {
			throw new SQLException("Cannot connect to database", cause);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		
		List<String> names;
		try {
			names = dataManager.getNamesList();
		}catch(SQLException e) {
			//logging
			names = new LinkedList<>();
		}
		request.setAttribute("names", names);
		
		if(name==null) {			
			request.getRequestDispatcher("/jsp/phoneBook.jsp").forward(request, response);
			//response.getWriter().append("no name");
			//return;
		}else {
			String phoneNumber;
			try {
				phoneNumber = dataManager.getPhoneByName(name);
			}catch(SQLException e) {
				//logging
				phoneNumber = "";
			}
			request.setAttribute("phoneNumber", phoneNumber);
			request.getRequestDispatcher("/jsp/phoneBook.jsp").forward(request, response);
		}
	}
}
