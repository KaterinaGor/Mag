<%-- 
    Document   : showBuyProduct
    Created on : 10.05.2022, 13:22:35
    Author     : 37255
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупка</title>
    </head>
    <body>
        <h1>Оформляем покупку</h1>
        
        <form action="createHistory" method="POST">
            Список покупателей<br>
            <select name="clientId">
                <c:forEach var="client" items="${listClient}">
                    <option value="${client.id}">${client.firstname} ${client.lastname} </option>
                </c:forEach>
            </select>
                
                <br>
            
            Список товаров<br>
            <select name="productId">
                <c:forEach var="product" items="${listProduct}">
                    <option value="${product.id}">${product.name} ${product.quantity} </option>
                </c:forEach>
            </select>
                
                <hr>
            
            <input type="submit" value="Оплатить">
        </form>
    </body>
</html>
