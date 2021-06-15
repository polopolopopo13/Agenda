<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	
	<h1>Welcome to Agenda</h1>
	
	<div>
	<h3>Register</h3>
		<form action="#" method="Post" id="form_register">
			<div>
				<label>Username</label>
				<input type="text" id="user" name="user">
			</div>
			<div>
				<label>Password</label>
				<input type="text" id="password" name="password">
			</div>
			<input type="submit" value="Sign up" name="signUp">
		
		</form>
		
	</div>

	<div>
	<h3>Identification</h3>
		<form action="#" method="Post" id="form_register">
			<div>
				<label>Username</label>
				<input type="text" id="user" name="user">
			</div>
			<div>
				<label>Password</label>
				<input type="text" id="password" name="password">
			</div>
			<input type="submit" value="Sign in" name="signIn">
		
		</form>
				
	</div>

<form action=""></form>

<div>
	<table>
		<tr>
		
			<th>Users</th>
		</tr>
			<% ArrayList<User> all_users = (ArrayList<User>) request.getAttribute("allUsers"); %>
		<% for(User user_ : all_users) { %>
		<tr>
					<td><%= user_.getUserName() %></td>
					
					<td><a href= "">Contact</a></td>
		</tr>
		<%}%>
		
	
	</table>
	
</div>


</body>
</html>