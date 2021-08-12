package com.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.DBConnection.DBConnection;
import com.dao.UserEntityDao;
import com.model.UserEntity;

public class UserEntityDaoImpl implements UserEntityDao {

	@Override
	public Integer addUser(UserEntity u) {
		Integer roll = null;
		try {
			SessionFactory factory = DBConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(u);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}
}
