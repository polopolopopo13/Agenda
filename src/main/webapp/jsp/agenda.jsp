<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>User's Agenda</h2>

<div>
	<h3>Add event</h3>
		<form action="#" method="Post" id="formAddEvent">
			<div>
				<label>Description</label>
				<input type="text" id="eventDescription" name="eventDescription">
			</div>
			<div>
				<label>Date</label>
				<input type="text" id="eventDate" name="eventDate">
			</div>
			<input type="submit" value="addEvent" name="addEvent">
		
		</form>
		
	</div>

<div>
	<table>
		<tr>
		
			<th>Date</th>
			<th>Event</th>
		</tr>
			<%-- <% ArrayList<Livre> all_books = (ArrayList<Livre>) request.getAttribute("library"); %>
		<% for(Livre book_ : all_books) { %>
		<tr>
					<td><%= book_.getReference() %></td>
					<td><%= book_.getTitle() %></td>
					<td><%= book_.getAuthor() %></td>
					<td><a href= "bibliotheque?action=delete&reference=<%=book_.getReference()%>">Supprimer</a></td>
					<td><a href= "bibliotheque?action=modify&reference=<%=book_.getReference()%>">Modifier</a></td>
		</tr>
		<%}%> --%>
		
	
	</table>
	
</div>


</body>
</html>