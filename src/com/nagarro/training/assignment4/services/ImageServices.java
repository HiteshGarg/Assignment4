package com.nagarro.training.assignment4.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.DAO.ImageHandler;
import com.nagarro.training.assignment4.DAO.UserDAO;
import com.nagarro.training.assignment4.POJO.UserDetails;
import com.nagarro.training.assignment4.POJO.UserImage;
import com.nagarro.training.assignment4.customException.NewCustomException;

public class ImageServices {

	public Boolean updateUserImage(HttpServletRequest request)
			throws NewCustomException {

		Boolean updated = false;
		Map<String, FileItem> formData = new HashMap<String, FileItem>();
		try {
			formData = FormDataHandler.getFilesList(request);

			Integer imageId = Integer.parseInt(formData.get("id").getString());

			Integer userid = (Integer) request.getSession().getAttribute(
					Constants.SESSION_USER_ID);
			for (Map.Entry<String, FileItem> entry : formData.entrySet()) {
				if (!entry.getValue().isFormField()) {

					Boolean validate = ImageServices.validateTotalImageSize(
							userid, entry.getValue().getSize(), imageId);
					if (!validate) {
						throw new NewCustomException(
								Constants.TOTAL_IMAGE_SIZE_EXCEEDS);
					}
				}
			}

			UserImage image = ImageHandler.getImageById(imageId);

			String name = formData.get("name").getString();
			if (name.length() > 0) {
				image.setImageName(name);
			}

			String imageName = formData.get("update_image").getName();
			byte[] imageByteArray = null;
			if (imageName != "") {
				InputStream inputStream = null;
				FileItem fileItem = null;

				fileItem = formData.get("update_image");
				inputStream = fileItem.getInputStream();

				imageByteArray = new byte[(int) fileItem.getSize()];
				inputStream.read(imageByteArray);
				image.setImage(imageByteArray);
				System.out.println("updating........");
				new UserHandler().updateTotalImageSize(userid,
						imageByteArray.length, imageId);
			}
			updated = new ImageHandler().updateImageInDB(image);
		} catch (IOException exc) {
			throw new NewCustomException(Constants.UNEXPECTED_ERROR);
		} catch (NewCustomException exception) {
			throw exception;
		}

		return updated;
	}

	public static boolean validateTotalImageSize(Integer userId,
			Long imageSize, Integer imageId) throws NewCustomException {
		Boolean validate = false;
		Long updateImageSize = (long) 0;

		try {
			UserDetails user = UserDAO.getUserById(userId);
			Long totalSize = user.getTotalSize();

			if (imageId > 0) {
				UserImage image = ImageHandler.getImageById(imageId);
				updateImageSize = (long) image.getImage().length;
			}
			totalSize = totalSize + imageSize - updateImageSize;

			if (totalSize < Constants.TOTAL_USER_UPLOAD_SIZE) {
				validate = true;
			}

		} catch (NewCustomException exception) {
			throw exception;
		}
		return validate;
	}
}