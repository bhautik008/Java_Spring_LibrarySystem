<%@ include file="./tags.jsp"%>
<%@ page session="false"%>
<html>
<head>
	<title>Add New Book</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>
	<h2>Add a new Book</h2>
	<form:form action="./processNewBookForm" method="POST"
		commandName="book">
		<table>
			<tr>
				<td><fmt:message key="title" /></td>
				<td><form:input path="title" /> <form:errors path="title" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="content" /></td>
				<td><form:textarea path="content"></form:textarea> <form:errors path="content" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="categoryId" /></td>
				<td><form:select path="categoryId">
						<c:forEach items="${categories}" var="cat">
							<form:option value="${cat.categoryId}">${cat.categoryName}</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="categoryId" cssClass="error" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="<fmt:message key="addBtn" />">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>