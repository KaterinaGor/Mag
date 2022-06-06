<%-- 
    Document   : listProduct
    Created on : 28.04.2022, 0:24:34
    Author     : 37255
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список товаров в магазине</title>
    </head>
    <body>
        <h1>В наличии имеются следующие товары:</h1>
        
        <c:forEach var="product" items="${listProduct}">
            <p>${product}</p>
        </c:forEach>
        
    </body>
</html>-->

<c:forEach var="product" items="${product}">
        <div class="card border-light mb-3" style="max-width: 20rem;">
            <div class="card-header">${product.name}</div>
            <img src="insertFile/${product.cover}" style="max-height: 25rem;" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">${product.quantity}</p>
                <a class="card-body" href="buyProduct?productId=${product.id}">Купить</a>
                <c:if test="${role eq 'MANAGER' or role eq 'ADMINISTRATOR'}">
                    <a class="card-body" href="editProduct?productId=${product.id}">Редактировать</a>
                </c:if>
              <p class="card-text"></p>
            </div>
        </div>
      </c:forEach>