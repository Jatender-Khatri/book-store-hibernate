package com.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AuthorEntity;
import com.dao.AuthorEntityDao;
import com.daoImpl.AuthorEntityDaoImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AuthorServlet
 */
@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuthorEntityDao dao = new AuthorEntityDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		System.out.println("Action : " + action);

		Integer result = null;
		AuthorEntity author = null;
		Gson gson = new Gson();
		String jsonlist = "";
		String listData = "";

		switch (action) {
		case "create":
			String authorName = request.getParameter("name").toString();
			Integer publications = Integer.parseInt(request.getParameter("publications"));

			System.out.println("Name : " + authorName + ", publications : " + publications);

			author = new AuthorEntity();
			author.setAuthorName(authorName);
			author.setNoOfPublication(publications);

			result = dao.addAuthor(author);

			AuthorEntity a = dao.getAuthorByName(author.getAuthorName());

			response.setContentType("application/json");

			response.getWriter().print(gson.toJson(a));

			break;

		case "getAll":

			List<AuthorEntity> allAuthors = dao.getAuthors();

			jsonlist = gson.toJson(allAuthors);

			response.setContentType("application/json");
			response.getWriter().print(jsonlist);
			break;

		case "update":
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			publications = Integer.parseInt(request.getParameter("publications"));

			System.out.println("ID : " + id);
			author = dao.getAuthorById(id);

			if (author != null) {
				author.setAuthorName(name);
				author.setNoOfPublication(publications);
				dao.updateAuthor(author);
			}

			listData = gson.toJson(author);

			response.setContentType("application/json");
			response.getWriter().print(listData);

			break;

		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
				
			System.out.println("Id = "+id);
			author = dao.getAuthorById(id);

			if (author != null) {
				dao.deleteAuthor(author);

			}

			listData = gson.toJson(author);

			response.setContentType("application/json");
			response.getWriter().print(listData);

			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
