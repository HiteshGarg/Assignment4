<%@page import="com.nagarro.training.assignment4.dao.ImageDao"%>
<%@page import="org.apache.catalina.util.Base64"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.nagarro.training.assignment4.Constants.Constants"%>
<%@page import="com.nagarro.training.assignment4.pojo.UserImage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
			<form action="UpdateImage" method="post" id="form_edit"
				enctype="multipart/form-data">
				<img src="images/close.png" id="close" onclick="hidePopUp()" />
				<h2>Edit Name & Image</h2>
				<hr />
				<input type="hidden" value="" name="id" id="hidden_id"> <input
					type="text" name="name" id="name" /> <input type="file"
					name="update_image" id="file_edit" />
				<p id="error_msg"></p>
				<!-- <input id="submit" type="submit" name="submit" value="submit" /> -->
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
					<input type="file" name="image" id="upload_image"
						onchange="checkFileUploads(this)" required> <input
						type="submit" value="Upload"> <input type="reset"
						value="clear">
				</form>

			</div>

			<div id="error">
			<!-- Message from ImageUpload.java.... Prints about successful image upload or not... -->
				<c:if test="${not empty requestScope.repositoryMessages }">
					<c:out value="${requestScope.repositoryMessages }"></c:out> 
				</c:if>
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


					<c:set var="size" value="${0}" scope="page"></c:set>
					<c:set var="count" value="${0}" scope="page"></c:set>

					<c:forEach var="images" items="${requestScope.imageList }">
						<tr>
							<td>${count+1}</td>
							<td>${images.imageName }</td>
							<td><c:out value="${requestScope.imageLength[count]}"></c:out>
							</td>
							<td><img src="${requestScope.base64List[count]}"
								width="100px" height="auto"></td>

							<td><a onclick="javascript: showPopUp(${images.imageId})">
									<img width="25px" height="auto" alt="Edit Image"
									src="images/edit.jpg">
							</a> <a href="DeleteImage?id=${images.imageId}"> <img
									width="25px" height="auto" alt="Edit Image"
									src="images/delete.jpg"
									onclick="return confirm('Are you sure?')"></a></td>
						</tr>
						<c:set var="size"
							value="${size + requestScope.imageLength[count]}"></c:set>
						<c:set var="count" value="${count+1}"></c:set>

					</c:forEach>
					<p>Total Size -> ${size/1024} KB // ${size/1024/1024} MB</p>
				</table>
			</div>
		</div>
	</div>
</body>
</html>