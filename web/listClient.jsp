<%-- 
    Document   : listClient
    Created on : 10.05.2022, 13:05:21
    Author     : 37255
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список всех клиентов</title>
    </head>
    <body>
        <h1>Покупатели</h1>
        
        <c:forEach var="client" items="${listClient}">
            <p>${client}</p>
        </c:forEach>
    </body>
</html>
