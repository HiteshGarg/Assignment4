/**
 * 
 */
package com.nagarro.training.assignment4.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.pojo.UserImage;
import com.nagarro.training.assignment4.utilities.HibernateUtil;

/**
 * @author hiteshgarg
 * 
 */
public class ImageDaoImpl {

	/**
	 * 
	 * @param imageList
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean uploadUserImage(UserImage image) throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Boolean uploaded = false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(image);
			tx.commit();
			session.close();
			uploaded = true;

		} catch (HibernateException e) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
		return uploaded;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws NewCustomException
	 */
	@SuppressWarnings("unchecked")
	public static List<UserImage> listUserImages(Integer userId)
			throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		List<UserImage> imageList = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hql = "from UserImage where userId = :userId";
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			imageList = query.list();
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
		return imageList;
	}

	/**
	 * 
	 * @param imageId
	 * @return
	 * @throws NewCustomException
	 */
	public Integer removeimagefromDB(Integer imageId) throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Integer count = 0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session
					.createQuery("Delete from UserImage where id = :id");
			query.setInteger("id", imageId);
			count = query.executeUpdate();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
		return count;
	}

	/**
	 * 
	 * @param image
	 * @return
	 * @throws NewCustomException 
	 */
	public Boolean updateImageInDB(UserImage image) throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Boolean success = false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.update(image);
			success = true;
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		}
		return success;
	}

	/**
	 * 
	 * @param imageId
	 * @return
	 * @throws NewCustomException 
	 */
	@SuppressWarnings("unchecked")
	public static UserImage getImageById(Integer imageId) throws NewCustomException {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		UserImage image = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hql = "from UserImage where imageId = :Id";
			Query query = session.createQuery(hql);
			query.setInteger("Id", imageId);
			List<UserImage> imageList = query.list();
			if (imageList.size() != 0) {
				image = imageList.get(0);
			}
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			throw new NewCustomException(Constants.ERROR_CONTACTING_SERVER);
		} 
		return image;
	}

}
