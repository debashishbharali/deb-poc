<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Accounting Structure</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>


</head>


<body>
	<h2>${message}</h2>

	<form:form method="POST" action="/deb-poc-cache/deb/getAccountingStructureFromCache"
		modelAttribute="accountingStructure">
		<table>
			<tr>
				<td><form:label path="tenantId">tenantId</form:label></td>
				<td><form:input path="tenantId" /></td>
			</tr>
			<tr>
				<td><form:label path="productId">productId</form:label></td>
				<td><form:input path="productId" /></td>
			</tr>
			<tr>
				<td><form:label path="schemeId">schemeId</form:label></td>
				<td><form:input path="schemeId" /></td>
			</tr>
			<tr>
				<td><form:label path="currencyId">currencyId</form:label></td>
				<td><form:input path="currencyId" /></td>
			</tr>
			<tr>
				<td><form:label path="accountingClassificationMstId">accountingClassificationMstId</form:label></td>
				<td><form:input path="accountingClassificationMstId" /></td>
			</tr>
			<tr>
				<td><form:label path="transactionType">transactionType</form:label></td>
				<td><form:input path="transactionType" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>