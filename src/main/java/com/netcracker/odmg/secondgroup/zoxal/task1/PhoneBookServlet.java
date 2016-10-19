package com.netcracker.odmg.secondgroup.zoxal.task1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import com.phonebook.DataManager;


public class PhoneBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "---";
		try {
			DataManager manager = new DataManager();
			data = manager.getPhoneByName("Mum");
		}catch(SQLException e) {
			data = "***";
		}
		response.getWriter().append("Mum: ").append(data);
	}


}
