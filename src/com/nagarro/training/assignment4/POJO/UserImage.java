/**
 * 
 */
package com.nagarro.training.assignment4.POJO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.catalina.User;

import com.mysql.jdbc.Blob;

/**
 * @author hiteshgarg
 *
 */
@Entity

public class UserImage {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int imageId;
	
	private String imageName;
	
	@Lob
	private byte[] image;
	
	@ManyToOne(targetEntity=UserDetails.class)
	@JoinColumn(name="userId", referencedColumnName="id")
	private UserDetails userId;

	public UserImage() {}
	
	public UserImage(String name, byte[] image, UserDetails id) {
		imageName = name;
		this.image = image;
		userId = id;
	}
	
	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the userId
	 */
	public UserDetails getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}

}
