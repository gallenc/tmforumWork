<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="selectedPage" value="serviceProblemList" scope="request" />
<!-- start of serviceProblemList.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
	<form action="./serviceProblemList" method="post">
		<input type="hidden" name="action" value="deleteAll">
		<button class="btn" type="submit">clear all problems</button>
	</form>
	<H1>Current Service Problems</H1>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">id</th>
				<th scope="col">description</th>
				<th scope="col">priority</th>
				<th scope="col">correlationId</th>
				<th scope="col">timeRaised</th>
				<th scope="col">timeChanged</th>
				<th scope="col">href</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="problem" items="${problemList}">
				<tr>
					<td>${problem.id}</td>
					<td>${problem.description}</td>
					<td>${problem.priority}</td>
					<td>${problem.correlationId}</td>
					<td>${problem.timeRaised}</td>
					<td>${problem.timeChanged}</td>
					<td><a href="${problem.href}" target="_blank">raw json</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

</main>


<jsp:include page="footer.jsp" />