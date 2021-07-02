<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Event" %>
<%@ page import="calendar.Month" %>
<%@ page import="calendar.Week" %>
<%@ page import="calendar.Day" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" rel="stylesheet">
<title>myAgenda</title>
</head>
<body>

<% String user_name = (String) request.getAttribute("userName"); %>
<% Month month = (Month) request.getAttribute("month"); %>
<% ArrayList<Event> user_events = (ArrayList<Event>) request.getAttribute("userEvents"); %>

<h2><%= user_name %>'s Agenda</h2>

<nav class="month-nav">

		<div><a href="myAgenda?date=${month.previous}">&#9668;</a></div>

		<h2>${month.name} ${month.year}</h2>

		<div><a href="myAgenda?date=${month.next}">&#9654;</a></div>

</nav>

<div>
	<h3>${ selectedEvent != null ? "Modify event" : "Add event" }</h3>
	
	<form action='?addORmod=${ selectedEvent != null ? "modEvent" : "addEvent" }' method="POST">
			<div>
				<label>Description</label>
				<input type="text" id="eventTitle" name="eventTitle" value='${ selectedEvent != null ? selectedEvent.getDescription() : "" }' required>
			</div>
			<div>
				<label>Date</label>
				<input type="date" id="eventDate" name="eventDate" value='${ selectedEvent != null ? selectedEvent.getDate() : "" }'required pattern="[0-9]{4}/[0-9]{2}/[0-9]{2}">
			</div>
			<input type="submit" value='${ selectedEvent != null ? "Modify" : "Add" }'>
		
		</form>
	</div>

<div>

	<table class="calendar">
		<tr>

			<th>Monday</th>

			<th>Tuesday</th>

			<th>Wednesday</th>

			<th>Thursday</th>

			<th>Friday</th>

			<th>Saturday</th>

			<th>Sunday</th>

		</tr>
		 
		 <c:forEach items="${month.weeks}" var="week_">
		    <tr class="week">
		    <c:forEach items="${week_.days}" var="day_">
		    	<td class='${ day_.hasevent ? "yesevent" : "noevent" }'>${day_.day}</td>
			</c:forEach>
		</c:forEach>
	</table>


	<h3>Events incoming</h3>
	<table>
		<tr>
			<th scope="col">Date</th>
			<th scope="col">Event</th>
		</tr> 
		<% if(!user_events.isEmpty()){ %>
		<% for(Event event_ : user_events) { %>
		<% if(event_.isIncoming()){ %>
			<tr>
				<td><%= event_.getDate() %></td>
				<td><%= event_.getDescription()%></td>
				<td><a href= "?action=delete&reference=<%=event_.getId()%>">Delete</a></td>
				<td><a href= "?action=modify&reference=<%=event_.getId()%>">Modify</a></td>
			</tr>
		<%}}}%>
	</table>
	
	
	<h3>All events</h3>
	<table>
		<tr>
		
			<th scope="col">Date</th>
			<th scope="col">Event</th>
		</tr> 
		<% if(!user_events.isEmpty()){ %>
		<% for(Event event_ : user_events) { %>
			<tr>
				<td><%= event_.getDate() %></td>
				<td><%= event_.getDescription()%></td>
				<td><a href= "?action=delete&reference=<%=event_.getId()%>">Delete</a></td>
				<td><a href= "?action=modify&reference=<%=event_.getId()%>">Modify</a></td>
			</tr>
		<%}}%>
	</table>
	
</div>


</body>
</html>