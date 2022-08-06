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
  <c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.title}</li>
        <li>${film.description}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
  
  		<c:choose>
			<c:when test="${not empty filmKeyword}">
				<h2>Films that match your search:</h2>
				<c:forEach var="film" items="${filmKeyword}">
					<table>
						<tr>
							<td align=left>
				<!-- 			<h2 align=left><a href="presidentInfo?termNumber=${prez.termNumber}">${prez.firstName} ${prez.middleName} ${prez.lastName}</a></h2> 
								<img src="images/${prez.termNumber}.jpg" width="100%" height="auto" /> -->
							</td>
							<td align=left width=80%>
								<ul style="list-style-type: none">
									<li><h3><strong>Film Title:</strong> ${film.title}</h3></li>
									<li><h3><strong>Description: </strong>${film.description}</h3></li>
				<!-- 					<li><h3><strong>Reason For Leaving Office: </strong>${prez.whyLeftOffice}</h3></li> -->
								</ul>
							</td>
						</tr>
					</table>
				</c:forEach>
			</c:when>
		</c:choose>
  
</body>
</html>