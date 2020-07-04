<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "selectedPage" value = "swaggerUI" scope="request" />
<!-- start of about.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <H1>Interface Swagger Test Page</H1>
    <a href="./swagger-ui/" target="_blank">Open swagger UI page in new tab</a><BR>
    <a href="./tmf-api/serviceProblemManagement/v3/swagger.json" target="_blank">Open Generated Open API Json Spec</a>
    
    <iframe height="700" width="100%" src="./swagger-ui/" title="Interface Swagger Test Page">
</iframe>
</main>

<jsp:include page="footer.jsp" />
