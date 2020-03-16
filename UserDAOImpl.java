package com.cg.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.cg.JPAUtility.JPAUtility;
import com.cg.dto.User;

public class UserDAOImpl implements IUserDAO{

	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	
	@Override
	public int addCustomer(User user) {
		factory = JPAUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	
		transaction.begin();
		
		try {
			manager.persist(user);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}
		return user.getUserId();
	}

}
