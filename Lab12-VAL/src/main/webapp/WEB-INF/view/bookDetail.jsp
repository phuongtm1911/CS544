<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${msg} a Book</title>
</head>

<body>
	<form:form modelAttribute="book" action="../books" method="post">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<form:hidden path="id"/>
		<table>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" cssClass="error"/> </td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><form:input path="ISBN" cssClass="error"/> </td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><form:input path="author" cssClass="error"/> </td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" cssClass="error"/> </td>
			</tr>
			<tr>
				<td>Published date:</td>
				<td><form:input type="date" path="publishedDate" cssClass="error"/> </td>
			</tr>
		</table>
		<input type="submit" />
	</form:form>
	<c:if test="${msg == 'Update'}">
	<form action="delete?id=${book.id}" method="post">
		<button type="submit">Delete</button>
	</form>
	</c:if>
</body>

</html>
