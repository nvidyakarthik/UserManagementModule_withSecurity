<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
     
	function userRolesValidation() {
		
		var varUserRole = document.addUser.roles;
		for (var i = 0; i < varUserRole.length; i++) {
			if (varUserRole[i].checked)				
			break;
		}
		if (i == varUserRole.length)
			return alert("Please Select the User Role");
		
		addUser.submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New User</title>
</head>
<body>

<form name="addUser" action="${pageContext.request.contextPath}/secured/addUser" method="post" commandName="newUser">
		<table align="center">
			<tr>
				<td>Email Id: </td><td><input type="text" name="emailid"/></td>
			</tr>
			<tr>
				<td>First Name: </td><td><input type="text" name="firstname"/></td>
			</tr>
			<tr>
				<td>Last Name: </td><td><input type="text" name="lastname"/></td>
			</tr>
			<tr>
				<td>User Name: </td><td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>Roles : </td>
				<td><c:forEach items="${roleList}" var="role">
				<input type="checkbox" name="roles" value="${role.id}" />${role.roleName}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><input type="button" value="Add User" onclick="userRolesValidation()"/></td>
			</tr>	
		</table>
	</form>
</body>
</html>