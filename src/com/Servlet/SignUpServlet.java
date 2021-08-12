package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserEntityDao;
import com.daoImpl.UserEntityDaoImpl;
import com.model.UserEntity;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserEntity e = new UserEntity();
    UserEntityDao dao = new UserEntityDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Email" + email);
		System.out.println("Username : " + username);
		System.out.println("Password :" + password);
		e.setEmail(email);
		e.setPassword(password);
		e.setUsername(username);
		dao.addUser(e);
		System.out.println("Added Successfully");
		response.sendRedirect("LoginSignUp.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
