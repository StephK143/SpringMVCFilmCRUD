<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Film</title>
<link rel='stylesheet' href='stylesheet.css'>
</head>
<body>
	<a href="index.html"><img class="home"
		src="https://www.kindpng.com/picc/m/111-1110719_blue-home-page-icon-png-website-icon-home.png"
		title="Home Page" /></a>

	<br>
	<h1>Is this the film you want to delete:</h1>

	<c:choose>
		<c:when test="${! empty film}">
			<h2>Film Title: ${film.title}</h2>
			<table id="filmDetails">
				<thead>
					<tr>
						<th>Film Details</th>
						<th>Rental Details</th>
						<th>Actors In Film</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<ul style="list-style-type: none">
								<li class="wrap-text"><span>Description:</span>${film.description}</li><br>
								<li><span>Rating:</span> ${film.rating}</li><br>
								<li><span>Release Year:</span>${film.releaseYear}</li><br>
								<li><span>Language:</span>${film.language}</li><br>
								<li><span>Category:</span>${film.category}</li><br>
								<li><span>Features:</span>${film.features}</li>
							</ul>
						</td>
						<td>
							<ul style="list-style-type: none">
								<li><span><strong>Length:</strong></span> ${film.length}</li><br>
								<li><span><strong>Film ID:</strong></span> ${film.filmId}</li><br>
								<li><span><strong>Rental Rate:</strong></span>${film.rentalRate}</li><br>
								<li><span><strong>Duration:</strong></span>${film.duration}</li><br>
								<li><span><strong>Replacement Cost:</strong></span>${film.replaceCost}</li>
							</ul>
						</td>
						<td><c:forEach var="actor" items="${film.actors}">
								<ul style="list-style-type: none">
									<li><strong>${actor.firstName} ${actor.lastName}</strong></li>
								</ul>
							</c:forEach> <br></td>
					</tr>
				</tbody>
			</table>
		</c:when>
	</c:choose>
	<form action="DeleteFilm.do" method="post">
		<input type="hidden" value=${film.filmId } name="filmId" /> 
		<input class="danger" type="submit" value="Yes, delete this film" />
	</form>
</body>
</body>
</html>