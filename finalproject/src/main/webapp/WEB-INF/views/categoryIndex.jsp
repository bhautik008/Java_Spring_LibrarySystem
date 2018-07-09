<%@ include file="./tags.jsp"%>
<%@ page session="false"%>
<html>
<head>
<title>Category Index</title>
</head>
<body>
	<a href="${context}/">Home Page</a> | <a href="${context}/newCategoryDataForm">Add new Category</a>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<th>Category ID</th>
			<th>Name</th>
			<th>Books</th>
		</tr>
		<c:forEach items="${categories}" var="cat">
			<tr>
				<td>${cat.categoryId}</td>
				<td>${cat.categoryName}</td>
				<td><a href="${context}/bookCategoryIndex?id=${cat.categoryId}">Books</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>