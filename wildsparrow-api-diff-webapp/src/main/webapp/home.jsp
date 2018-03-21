<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Wild Sparrow's API Diff tool</title>
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>
	<div align="center">
		<h2>Welcome to the Wild Sparrow's API Difference Online Tool</h2>
		<br>
		<br>
		<br>
		<h3>You just need the jars of two versions to find how your API's
			have changed</h3>
		<br>
		<br>

		<h3>Tool is available both online and offline, First Try online
			which have a max limit of 10 MB</h3>

	</div>
	<br>
	<br>
	<table align="center" border="1em">
		<tr align="center">

			<td><h3>&nbsp;&nbsp; WildSparrow API Difference Online Tool &nbsp;&nbsp;</h3></td>
			<td><h3>&nbsp;&nbsp; Download offline Tool &nbsp;&nbsp;</h3></td>

		</tr>
		<tr align="center">
			<td><a href="fileUploadForm">Online API Diff</a></td>
			<td><a href="download">Download</a></td>
		</tr>
	</table>
</body>
</html>