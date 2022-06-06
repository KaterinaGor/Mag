<%-- 
    Document   : showAddClient
    Created on : 10.05.2022, 12:46:31
    Author     : 37255
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить клиента</title>
    </head>
    <body>
        <h1>Создать клиента</h1>
        
        <form action="createClient" method="POST">
            Имя клиента<br>
            <input type="text" name="firstname"><br>
            
            Фамилия<br>
            <input type="text" name="lastname"><br>
            
            Телефон<br>
            <input type="text" name="phone"><br>
            
            Денег в кошельке<br>
            <input type="text" name="money"><br>
            
            <input type="submit" value="Создать">
        </form>
    </body>
</html>
