package com.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthorEntityDao;
import com.dao.BookEntityDao;
import com.daoImpl.AuthorEntityDaoImpl;
import com.daoImpl.BookEntityDaoImpl;
import com.google.gson.Gson;
import com.model.AuthorEntity;
import com.model.BookEntity;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookEntityDao bookDao = new BookEntityDaoImpl();
	AuthorEntityDao authorDao = new AuthorEntityDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		Gson gson = new Gson();

		switch (action) {
		case "getAll":
			List<BookEntity> allBooks = bookDao.getAllBooks();

			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(allBooks));

			break;

		case "create":
			BookEntity book = new BookEntity();
			AuthorEntity author = authorDao.getAuthorByName(request.getParameter("author"));

			book.setTitle(request.getParameter("name"));
			book.setAuthor(author);

			bookDao.addBook(book);

			BookEntity b = bookDao.getBookByName(book.getTitle());

			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(b));

			break;
		case "update":
			Integer id = Integer.parseInt(request.getParameter("id"));

			book = bookDao.getBookById(id);
			book.setTitle(request.getParameter("name"));

			author = authorDao.getAuthorByName(request.getParameter("author"));

			book.setAuthor(author);

			bookDao.updateBook(book);

			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(book));

			break;

		case "delete":
			id = Integer.parseInt(request.getParameter("id"));

			book = bookDao.getBookById(id);
			bookDao.deleteBook(book);

			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(book));
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
