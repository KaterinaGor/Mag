<%-- 
    Document   : index
    Created on : 27.04.2022, 16:15:56
    Author     : 37255


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Веб-Магазин</title>
    </head>
    <body>
        <h1>Добро пожаловать!</h1>
        <a href="showAddProduct">Добавить товар</a><br>
        <a href="listProduct">Список товаров</a><br>
        <a href="showAddClient">Добавить покупателя</a><br>
        <a href="listClient">Список покупателей</a><br>
        <a href="showBuyProduct">Покупка</a><br>
        <a href="listHistory">Список всех сделок</a><br>
    </body>
</html>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
      <c:forEach var="product" items="${products}">
        <div class="card border-light mb-3" style="max-width: 20rem;">
            <div class="card-header">${product.name}</div>
            <div class="card-body">
              <p class="card-text">Welcome!</p>
            </div>
        </div>
      </c:forEach>
