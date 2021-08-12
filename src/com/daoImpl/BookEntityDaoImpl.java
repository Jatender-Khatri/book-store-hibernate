package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.DBConnection.DBConnection;
import com.dao.BookEntityDao;
import com.model.BookEntity;

public class BookEntityDaoImpl implements BookEntityDao{

	@Override
	public Integer addBook(BookEntity book) {
		Integer result = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			session.save(book);
			result = 1;

			transaction.commit();
			System.out.println("Successfully added.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return result;
	}

	@Override
	public Integer updateBook(BookEntity book) {
		Integer result = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			session.update(book);
			result = 1;

			transaction.commit();
			System.out.println("Successfully updated.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return result;
	}

	@Override
	public Integer deleteBook(BookEntity book) {
		Integer result = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			session.remove(book);
			result = 1;

			transaction.commit();
			System.out.println("Successfully deleted.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return result;
	}

	@Override
	public BookEntity getBookById(Integer id) {
		BookEntity book = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			book = session.get(BookEntity.class, id);
			
			transaction.commit();
			System.out.println("Successfully fetched.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return book;
	}

	@Override
	public List<BookEntity> getAllBooks() {
		List<BookEntity> books = new ArrayList<BookEntity>();
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			books = session.createQuery("From BookEntity").list();
			
			transaction.commit();
			System.out.println("Successfully fetched.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return books;
	}

	@Override
	public BookEntity getBookByName(String book) {
		BookEntity books = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("From BookEntity b where b.title=:n");
			query.setParameter("n", book);
			
			books = (BookEntity) query.getSingleResult();
			
			transaction.commit();
			System.out.println("Successfully fetched.");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return books;
	}

}
