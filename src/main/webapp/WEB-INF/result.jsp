<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
	<a href="index.html"><input type="button"
		value="Take me back to the home page"></a>

	<c:choose>
		<c:when test="${! empty film}">
			<!-- Add additional fields to show ALL the goodies -->
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

			<form action="EditFilm.do" method="get">
				<input type="hidden" value=${film.filmId } name="filmId" /> <input
					type="submit" value="Edit Film" />

			</form>

			<form action="ConfirmDelete.do" method="get">
				<input type="hidden" value=${film.filmId } name="filmId" /> <input
					type="submit" value="Delete Film" />
			</form>

		</c:when>
		<c:otherwise>
			<p>${idMessage}</p>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${not empty filmKeyword}">
			<h2>Films that match your search:</h2>
			<br>
			<h3>${kwPrompt}</h3>
			<c:forEach var="film" items="${filmKeyword}">
				<ul style="list-style-type: none">
					<li><h3>
							<strong>Film Title:</strong> ${film.title}
						</h3></li>
					<li><h3>
							<strong>Description: </strong>${film.description}</h3></li>
				</ul>
				<form action="EditFilm.do" method="get">
					<input type="hidden" value=${film.filmId } name="filmId" /> <input
						type="submit" value="Edit Film" />

				</form>

				<form action="ConfirmDelete.do" method="get">
					<input type="hidden" value=${film.filmId } name="filmId" /> <input
						type="submit" value="Delete Film" />
				</form>
				<form action="GetFilm.do" method="get">
					<input type="hidden" value=${film.filmId } name="filmId" /> <input
						type="submit" value="List all film details" />
				</form>

			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>${kwMessage}</p>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${filmEdit == true}">
			<p>${editMessage}</p>
		</c:when>
		<c:otherwise>
			<p>${editFail}</p>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${filmDelete == true}">
			<p>${deleteMessage}</p>
		</c:when>
		<c:otherwise>
			<p>${deleteFail}</p>
		</c:otherwise>
	</c:choose>

</body>
</html>