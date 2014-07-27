package com.nagarro.training.assignment4.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.nagarro.training.assignment4.POJO.UserDetails;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.utilities.HibernateUtil;

public class UserDAO {

	@SuppressWarnings("unchecked")
	public static Integer validateUser(String username, String password)
			throws NewCustomException {

		SessionFactory factory = HibernateUtil.createSessionFactory();
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
			throw new NewCustomException("Error in validating the user");
		}
	}

	public static UserDetails getUserById(Integer userId)
			throws NewCustomException {

		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		UserDetails user = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			user = (UserDetails) session.get(UserDetails.class, userId);
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			throw new NewCustomException(
					"Error in Communicatiing with database");
		}
		return user;
	}

	public static void updateUser(UserDetails user) throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			throw new NewCustomException(
					"Error in updating user");
		}
	}
}
