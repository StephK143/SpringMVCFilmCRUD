<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Film</title>
</head>
<body>
<table>
<tr>
<td width=100%>
  <h3>Edit film:</h3>      
  
  <!-- Check on how to mandate fields in update and add -->
  
  
    <form action="UpdateFilm.do" method="POST">
      <label for="filmId">Film ID:</label>
      <input type="text" name="filmId"/>
      <br>
      <label for="title">Title:</label>
      <input type="text" name="title"/>
      <br>
      <label for="description">Description:</label>
      <input type="text" name="description">
      <br>
      <label for="releaseYear">Year film was released:</label>
      <input type="text" name="releaseYear">
      <br>
      <label for="duration">Duration of rental:</label>
      <input type="number" name="duration">
      <br>
      <label for="rentalRate">Rental Rate:</label>
      <input type="text" name="rentalRate">
      <br>
      <label for="length">Length:</label>
      <input type="text" name="length">
      <br>
      <label for="replaceCost">Replacement Cost:</label>
      <input type="text" name="replaceCost">
      <br>
      <label for="rating">Rating:</label>
      <input type="text" name="rating">
      <br>
      <label for="category">Film Category:</label>
      <input type="text" name="category">
      <br>
      <input type="submit" value="Edit Film">
    </form>
<!--    </td>
    <td width=80%>
    <form action="DeleteFilm.do" method="POST">
		<a href="updateFilmForm.html"><input type="button" value="Edit/Delete"></a>
      <input type="text" name="title" value="${film.title}"/>
    </form>
    </td> -->
      </tr>
 
    </table>
  </body>
</body>
</html>