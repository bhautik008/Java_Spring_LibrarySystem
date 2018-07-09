<%@ include file="./tags.jsp"%>
<%@ page session="false"%>
<html>
<head>
<title>Book Index</title>
</head>
<body>
	<a href="${context}/">Home Page</a> | <a href="${context}/newBookDataForm">Add new Book</a>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<th>Book ID</th>
			<th>Title</th>
			<th>Content</th>
			<th>Category Name</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.bookId}</td>
				<td>${book.title}</td>
				<td>${book.content}</td>
				<td>${book.categoryName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>