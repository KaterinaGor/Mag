<%-- 
    Document   : listHistory
    Created on : 10.05.2022, 15:39:25
    Author     : 37255
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список всех сделок</title>
    </head>
    <body>
        <h1>Сделки купли-продажи</h1>
        
        <c:forEach var="history" items="${listHistory}">
            <p>${history}</p>
        </c:forEach>
    </body>
</html>