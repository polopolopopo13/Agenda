<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Event" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String user_name = (String) request.getAttribute("userName"); %>
<% ArrayList<Event> user_events = (ArrayList<Event>) request.getAttribute("userEvents"); %>
<h2><%= user_name %>'s Agenda</h2>

<div>
	<h3>Add event</h3>
		<form action="#" method="Post" id="formAddEvent">
			<div>
				<label>Description</label>
				<input type="text" id="eventTitle" name="eventTitle" required>
			</div>
			<div>
				<label>Date</label>
				<input type="date" id="eventDate" name="eventDate" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
			</div>
			<input type="submit" value="Add to my agenda" name="addEvent">
		
		</form>
	</div>

<div>
	<table>
		<tr>
		
			<th>Date</th>
			<th>Event</th>
		</tr>
		<% for(Event event_ : user_events) { %>
		<tr>
					<td><%= event_.getTitle() %></td>
					<td><%= event_.getDate() %></td>
					<td><a href= "agenda?action=delete&reference=<%=event_.getReference()%>">Delete</a></td>
					<td><a href= "agenda?action=modify&reference=<%=event_.getReference()%>">Modify</a></td>
		</tr>
		<%}%>
		
	
	</table>
	
</div>


</body>
</html>