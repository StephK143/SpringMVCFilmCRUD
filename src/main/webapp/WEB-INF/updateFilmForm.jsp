<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title class="head">Edit Film</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link rel='stylesheet' href='stylesheet.css'>
</head>
<body>
<br>
	<a href="index.html"><img class="home" src="https://www.kindpng.com/picc/m/111-1110719_blue-home-page-icon-png-website-icon-home.png"  title="Home Page"/></a>
 
<table>
	<tr>
	<td width=100%>
	  <h1>Edit film:</h1>      
	  
	 <div class="forms">
	  
	    <form action="UpdateFilm.do" method="POST">
	   
	      <label for="filmId">Film ID:</label>
	      <input  type="text"  name="filmId" value="${film.filmId}">
	      <br>
	      <label for="title">Title:</label>
	      <input type="text" value="${film.title}" name="title">
	      <br>
	      <label for="description">Description:</label>
	      <input type="text" name="description" value="${film.description}">
	      <br>
	      <label for="releaseYear">Year film was released:</label>
	      <input type="text" name="releaseYear" value="${film.releaseYear}">
	      <br>
	      <label for="duration">Duration of rental:</label>
	      <input type="number" name="duration" value="${film.duration}">
	      <br>
	      <label for="rentalRate">Rental Rate:</label>
	      <input type="text" name="rentalRate" value="${film.rentalRate}">
	      <br>
	      <label for="length">Length:</label>
	      <input type="text" name="length" value="${film.length}">
	      <br>
	      <label for="replaceCost">Replacement Cost:</label>
	      <input type="text" name="replaceCost" value="${film.replaceCost}">
	      <br>
	      <label for="rating">Rating:</label>
	      <input type="text" name="rating" value="${film.rating}">
	      <br>
	      <label for="category">Film Category:</label>
	      <input type="text" name="category" value="${film.category}">
	      <br><br><br>
	      <input type="submit" value="Update Film">
	    </form>
	 </div>
	    <br>
	    <br>
	      </tr>
    </table>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</body>
</html>