<%@ include file="./tags.jsp"%>
<%@ page session="false"%>
<html>
<head>
	<title>Add New Category</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>
	<h2>Add a new Category</h2>
	<form:form action="./processNewCategoryForm" method="POST"
		commandName="category">
		<table>
			<tr>
				<td><fmt:message key="categoryName" /></td>
				<td><form:input path="categoryName" /> <form:errors path="categoryName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="<fmt:message key="addBtn" />">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>