package com.Servlet;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBConnection.DatabaseConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection con = (Connection) DatabaseConnection.getConnection();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Email : " + email);
		System.out.println("password : " + password);

		try {
			Integer flag = 1;
			Statement s = con.createStatement();
			ResultSet set = s.executeQuery("select * from users");

			while (set.next()) {
				if ((set.getString(2).equals(email) && set.getString(3).equals(password))
						|| (set.getString(4).equals(email) && set.getString(3).equals(password))) {
					flag = 0;
					break;
				}
			}
			if (flag == 0) {

				response.sendRedirect("home.jsp");
			} else {
				printWriter.write("<h1>Incorrect Username or Password</h1>");
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
