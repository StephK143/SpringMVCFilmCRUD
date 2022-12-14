<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movie Maven Film Catalog: Films</title>
<link rel='stylesheet' href='stylesheet.css'>
</head>
<body>
	<a href="index.html"><img class="home"
		src="https://www.kindpng.com/picc/m/111-1110719_blue-home-page-icon-png-website-icon-home.png"
		align="right" width=5% title="Home Page" /></a>

	<c:choose>
		<c:when test="${! empty film}">
			<h2 class="head">Film Title: ${film.title}</h2>
			<table id="filmDetails">
				<thead class="head2">
					<tr>
						<th>Film Details</th>
						<th>Rental Details</th>
						<th>Actors In Film</th>
					</tr>
				</thead>
				<tbody id="list-film-table">
					<tr>
						<td>
							<ul style="list-style-type: none">
								<li class="wrap-text"><span><strong>Description:</strong></span>
									${film.description}</li>
								<br>
								<li><span><strong>Rating:</strong></span> ${film.rating}</li>
								<br>
								<li><span><strong>Release Year:</strong></span>
									${film.releaseYear}</li>
								<br>
								<li><span><strong>Language:</strong></span>
									${film.language}</li>
								<br>
								<li><span><strong>Category:</strong></span>
									${film.category}</li>
								<br>
								<li><span><strong>Features:</strong></span>
									${film.features}</li>
							</ul>
						</td>
						<td>
							<ul style="list-style-type: none">
								<li><span><strong>Length:</strong></span> ${film.length}</li>
								<br>
								<li><span><strong>Film ID:</strong></span> ${film.filmId}</li>
								<br>
								<li><span><strong>Rental Rate:</strong></span>
									${film.rentalRate}</li>
								<br>
								<li><span><strong>Duration:</strong></span>
									${film.duration} days</li>
								<br>
								<li><span><strong>Replacement Cost:</strong></span>
									${film.replaceCost}</li>
							</ul>
						</td>
						<td><c:forEach var="actor" items="${film.actors}">
								<ul style="list-style-type: none">
									<li><strong>${actor.firstName} ${actor.lastName}</strong></li>
								</ul>
							</c:forEach></td>
					</tr>
					<br>
					<tr>
						<br>
						<td>
							<form action="EditFilm.do" method="get">
								<input type="hidden" value=${film.filmId } name="filmId" /> <input
									class="button3" type="submit" value="Edit Film" />
							</form>
						</td>
						<td>
							<form action="ConfirmDelete.do" method="get">
								<input type="hidden" value=${film.filmId } name="filmId" /> <input
									class="danger" type="submit" value="Delete Film" />
							</form>
						</td>
						<td>
							<form action="Inventory.do" method="get">
								<input type="hidden" value=${film.filmId } name="filmId" /> <input
									class="button3" type="submit"
									value="Show Inventory" />
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h1>${idMessage}</h1>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${not empty filmKeyword}">
			<h1 class="head">Films that match your search:</h1>
			<br>
			<table class="keywordTable" id="kwTable">
				<thead>
					<tr>
						<th>Film Title</th>
						<th>Description</th>
						<th>Release Year</th>
						<th>Language</th>
						<th>Rental Rate</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="film" items="${filmKeyword}">
						<tr>
							<td><strong>${film.title}</strong></td>
							<td class="wrap-text"><strong>${film.description}</strong></td>
							<td><strong>${film.releaseYear}</strong></td>
							<td><strong>${film.language}</strong></td>
							<td><strong>${film.rentalRate}</strong></td>
							<td>
								<form action="EditFilm.do" method="get">
									<input type="hidden" value=${film.filmId } name="filmId" /> <input
										class="button3" type="submit" value="Edit Film" />
								</form>

							</td>
							<td>
								<form action="ConfirmDelete.do" method="get">
									<input class="button3" type="hidden" value=${film.filmId }
										name="filmId" /> <input class="danger" type="submit"
										value="Delete Film" />
								</form>

							</td>
							<td>
								<form action="GetFilm.do" method="get">
									<input type="hidden" value=${film.filmId } name="filmId" /> <input
										class="button3" type="submit" value="Film details" />
								</form>

							</td>
						</tr>
					</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h1>${kwMessage}</h1>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${filmEdit == true}">
			<h3>${editMessage}</h3>
		</c:when>
		<c:otherwise>
			<h3>${editFail}</h3>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${filmDelete == true}">
			<h3>${deleteMessage}</h3>
		</c:when>
		<c:otherwise>
			<h3>${deleteFail}</h3>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${! empty actor}">
			<h2 class="head">Actor Added:</h2>
			<ul>
				<li>Actor Name: ${actor.firstName} ${actor.lastName}</li>
				<li>Actor ID: ${actor.id}</li>
			</ul>
			<br>
		</c:when>
		<c:otherwise>
			<h3>${badActor}</h3>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${! empty inventory}">
			<h2 class="head">Copies Available in Store</h2>
			<table id="invTable">
				<thead>
					<tr class="head2">
						<th>Title</th>
						<th>Inventory ID</th>
						<th>Condition</th>
						<th>Store Address</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="copy" items="${inventory}">
						<tr>
							<td><strong>${copy.filmTitle}</strong></td>
							<td><strong>${copy.invId}</strong></td>
							<td><strong>${copy.condition}</strong></td>
							<td><strong>${copy.address}</strong></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
	</c:choose>

</body>
</html>