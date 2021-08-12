package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.DBConnection.DBConnection;
import com.dao.AuthorEntityDao;
import com.model.AuthorEntity;

public class AuthorEntityDaoImpl implements AuthorEntityDao {
	public Integer addAuthor(AuthorEntity author) {
		Integer roll = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(author);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	@SuppressWarnings("unchecked")
	public List<AuthorEntity> getAuthors() {
		List<AuthorEntity> list = new ArrayList<>();
		try {
			
			SessionFactory factory = DBConnection.getFactory();
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			list = session.createQuery("From AuthorEntity").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Integer deleteAuthor(AuthorEntity author) {
		Integer roll = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.remove(author);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	public AuthorEntity getAuthorById(int id) {
		AuthorEntity a = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			a = session.get(AuthorEntity.class, id);

			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public Integer updateAuthor(AuthorEntity author) {
		Integer roll = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.update(author);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	@Override
	public AuthorEntity getAuthorByName(String name) {
		AuthorEntity author = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("From AuthorEntity a where a.authorName=:n");
			query.setParameter("n", name);
			author = (AuthorEntity)query.getSingleResult();
			
			transaction.commit();
			System.out.println("Successfully fetched.");
			// factory.close();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return author;
	}

}
