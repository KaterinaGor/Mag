<%-- 
    Document   : showAddProduct
    Created on : 28.04.2022, 0:07:00
    Author     : 37255
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить товар</title>
    </head>
    <body>
        <h1>Создать товар</h1>
        
        <form action="createProduct" method="POST">
            Название товара<br>
            <input type="text" name="name"><br>
            
            Стоимость товара<br>
            <input type="text" name="price"><br>
            
            Количество товара<br>
            <input type="text" name="quantity"><br>
            
            <input type="submit" value="Создать">
        </form>
    </body>
</html>-->

 <div class="card border-light my-5" style="width: 30rem;">
    <div class="card-body">
        <form action="createBook" method="POST" enctype="multipart/form-data">
            <fieldset>
              <legend>Добавление товара</legend>
              
              <div class="form-group mb-3">
                <label for="caption">Название</label>
                <input type="text" class="form-control" id="caption" name="caption" aria-describedby="caption" placeholder="">
                <small id="caption" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
              </div>
                
              <div class="form-group mt-3">
                <label for="publishedYear">Количество</label>
                <input type="text" class="form-control" id="publishedYear" name="publishedYear" aria-describedby="publishedYear" placeholder="">
                <small id="publishedYear" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
              </div>
              
              <div class="form-group  mt-3">
                <label for="price">Стоимость</label>
                <input type="text" class="form-control" id="price" name="price" aria-describedby="price" placeholder="">
                <small id="price" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
              </div>

                <button type="submit" class="btn btn-primary mt-4">Добавить товар</button>
            </fieldset>
       </form>
    </div>
</div>
