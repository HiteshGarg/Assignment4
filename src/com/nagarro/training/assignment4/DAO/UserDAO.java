package com.nagarro.training.assignment4.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.pojo.UserDetails;
import com.nagarro.training.assignment4.utilities.HibernateUtil;

public class UserDao {

	/**
	 * validates user on login using the username and password
	 * @param username
	 * @param password
	 * @return
	 * @throws NewCustomException
	 */
	@SuppressWarnings("unchecked")
	public static Integer validateUser(String username, String password)
			throws NewCustomException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = null;
		Integer userId = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("password", password));
			List<UserDetails> user = criteria.list();
			if (0 != user.size()) {
				userId = user.get(0).getId();
			}
			session.getTransaction().commit();
			return userId;
		} catch (HibernateException exception) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
	}

	/**
	 * Returns user object by using the Id of user
	 * @param userId
	 * @return
	 * @throws NewCustomException
	 */
	public static UserDetails getUserById(Integer userId)
			throws NewCustomException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = null;
		UserDetails user = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			user = (UserDetails) session.get(UserDetails.class, userId);
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
		return user;
	}

	/**
	 * Updates the user
	 * @param user
	 * @throws NewCustomException
	 */
	public void updateUser(UserDetails user) throws NewCustomException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
	}
}
