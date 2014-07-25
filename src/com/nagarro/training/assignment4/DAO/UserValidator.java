package com.nagarro.training.assignment4.DAO;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nagarro.training.assignment4.POJO.UserDetails;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.utilities.HibernateUtil;



public class UserValidator {

	public static Integer validateUser(String username, String password) throws NewCustomException{
		
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Integer userId = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("password", password));
//			String hql = "from UserDetails as UD where UD.username = ':username' and UD.password = ':password'";
//			Query query = session.createQuery(hql);
//			query.setString("username", username);
//			query.setString("password", password);
			List<UserDetails> user = criteria.list();
			if(0 != user.size()){
				userId = user.get(0).getId();
			}
			tx.commit();
			return userId;
		}catch (HibernateException exception) {
			exception.printStackTrace();
			throw new NewCustomException("Error in validating the user");
		}
	}
}
