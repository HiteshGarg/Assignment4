<%@page import="org.apache.catalina.util.Base64"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.nagarro.training.assignment4.DAO.ImageHandler"%>
<%@page import="com.nagarro.training.assignment4.Constants.Constants"%>
<%@page import="com.nagarro.training.assignment4.POJO.UserImage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Management Utility</title>

<link href="css/imageRepository.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/customJs.js"></script>
</head>

<body>
	<div id="abc">
		<!-- Popup div starts here -->
		<div id="popupEdit">

			<!-- contact us form -->
			<form action="UpdateImage" method="post" id="form_edit">
				<img src="images/close.png" id="close" onclick="hidePopUp()" />
				<h2>Edit Name & Image</h2>
				<hr />
				<input type="hidden" value="" name="id" id="hidden_id"> <input type="text"
					name="name" id="name" /> <input type="file" name="image" id="file_edit" />
			<p id="error_msg"></p>
				<a id="submit" onclick="validateInput()">Update</a> 
				
			</form>
		</div>
		<!-- Popup div ends here -->
	</div>
	<div>
		<div id="header">
			<p>Image Management Utility</p>
		</div>
		<div id="content">
			<div id="upload">
				<p>Please select an image file to upload(Max size 1 Mb)</p>

				<a href="Logout">Logout</a>
				<form action="ImageUpload" enctype="multipart/form-data"
					method="post">
					<input type="file" name="image" required> <input
						type="submit" value="Upload"> <input type="reset"
						value="clear">
				</form>

			</div>

			<div id="image_display">

				<table>
					<tr>
						<th>S. No.</th>
						<th>Name</th>
						<th>Size</th>
						<th>Preview</th>
						<th>Actions</th>
					</tr>
					<%
						List<UserImage> imageList = ImageHandler
								.listUserImages((Integer) session
										.getAttribute(Constants.SESSION_USER_ID));
						Iterator<UserImage> iter = imageList.iterator();
						Integer size = 0;
						Integer count = 0;
						while (iter.hasNext()) {
							count++;
							UserImage image = (UserImage) iter.next();
							size = size + image.getImage().length;

							String url = "data:image/png;base64,"
									+ Base64.encode(image.getImage());
					%>
					<tr>
						<td>
							<%
								out.print(count);
							%>
						</td>
						<td>
							<%
								out.print(image.getImageName());
							%>
						</td>
						<td>
							<%
								out.print(image.getImage().length / 1024 + " KB");
							%>
						</td>
						<td><img alt="<%out.print(image.getImageName());%>"
							src=<%out.print(url);%> width="100px" height="auto"></td>

						<td><a onclick="javascript: showPopUp(<%out.print(image.getImageId());%>)"> <img
								width="25px" height="auto" alt="Edit Image"
								src="images/edit.jpg">
						</a> <a href="DeleteImage?id=<%=image.getImageId()%>"> <img
								width="25px" height="auto" alt="Edit Image"
								src="images/delete.jpg"></td>
						</a>
					</tr>
					<%
						}

						out.println("Total size : " + (size / (1024 * 1024)) + "MB");
					%>
				</table>
			</div>
		</div>
	</div>
	</div>
	<%
		//Message from ImageUpload.java.... Prints about successful image upload or not...

		if (null != request
				.getAttribute(Constants.IMAGE_REPOSITORY_MESSAGES)) {
			out.println(request
					.getAttribute(Constants.IMAGE_REPOSITORY_MESSAGES));

			session.removeAttribute(Constants.IMAGE_REPOSITORY_MESSAGES);
		}
	%>




</body>
</html>