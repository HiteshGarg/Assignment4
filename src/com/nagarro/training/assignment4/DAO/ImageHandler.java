/**
 * 
 */
package com.nagarro.training.assignment4.DAO;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.POJO.UserImage;
import com.nagarro.training.assignment4.utilities.HibernateUtil;

/**
 * @author hiteshgarg
 * 
 */
public class ImageHandler {

	public Boolean uploadImageList(List<UserImage> imageList) {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Boolean uploaded = false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			for(UserImage userImage : imageList){
				session.saveOrUpdate(userImage);
			}
			
			tx.commit();
			session.close();
			uploaded = true;
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return uploaded;
	}
	
	public static List<UserImage> listUserImages(Integer userId){
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		List<UserImage> imageList= null;
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
			// TODO: handle exception
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return imageList;
	}

	public Integer removeimagefromDB(Integer imageId) {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Integer count = 0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("Delete from UserImage where id = :id");
			query.setInteger("id", imageId);
			count = query.executeUpdate();
			tx.commit();
			session.close();
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public Integer updateImageInDB(Integer imageId, String name) {
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		Integer count = 0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			UserImage image = new UserImage();
			image.setImageId(imageId);
			image.setImageName(name);
			session.update(image);
			tx.commit();
			session.close();
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public static UserImage getImageById(Integer imageId){
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		UserImage image= null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hql = "from UserImage where imageId = :Id";
			Query query = session.createQuery(hql);
			query.setInteger("Id", imageId);
			List<UserImage> imageList = query.list();
			if(imageList.size()!=0){
				image = imageList.get(0);
			}
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}

}
