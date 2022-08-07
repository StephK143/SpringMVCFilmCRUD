<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Film</title>
</head>
<body>
					<a href="index.html"><input type="button" value="Take me back to the home page"></a>

<br>
  <h1>Is this the film you want to delete: </h1>      
  
  <!-- Check on how to mandate fields in update and add -->
  	<c:choose>
		<c:when test="${! empty film}">    <!-- Add additional fields to show ALL the goodies -->
				<h2>Film Title: ${film.title}</h2>
			<ul>
				<li>Film ID: ${film.filmId}</li>
				<li>Description: ${film.description}</li>
				<li>Release Year: ${film.releaseYear}</li>
				<li>Language: ${film.language}</li>
				<li>Duration: ${film.duration}</li>
				<li>Rental Rate: ${film.rentalRate}</li>
				<li>Length: ${film.length}</li>
				<li>Replacement Cost: ${film.replaceCost}</li>
				<li>Rating: ${film.rating}</li>
				<li>Features: ${film.features}</li>
				<li>Category: ${film.category}</li>
			</ul>
				<h2>Actors in this film:</h2>
				<c:forEach var="actor" items="${film.actors}">
							<ul style="list-style-type: none">
								<li><strong>${actor.firstName} ${actor.lastName}</strong></li>
							</ul>
			</c:forEach>
			<br>
			</c:when>
		
			</c:choose>
  	<form action="DeleteFilm.do" method="post">
		 <input type="hidden" value=${film.filmId } name="filmId"/>
		 <input class="danger" type="submit" value="Yes, delete this film"/>
	</form>
  </body>
</body>
</html>