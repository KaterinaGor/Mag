<%-- 
    Document   : editProduct
    Created on : 16.05.2022, 14:17:27
    Author     : 37255
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="updateBook" method="POST" multiple>
                    <fieldset>
                      <legend>Редактирование данных товара</legend>
                      
                      <div class="form-group mb-3">
                        <label for="name">Название</label>
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="name" placeholder="" value="${product.name}">
                        <small id="name" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mt-3">
                        <label for="quantity">Количество</label>
                        <input type="text" class="form-control" id="quantity" name="quantity" aria-describedby="quantity" placeholder="" value="${product.quantity}">
                        <small id="quantity" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                      
                        <div class="form-group  mt-3">
                        <label for="price">Цена</label>
                        <input type="text" class="form-control" id="price" name="price" aria-describedby="price" placeholder="" value="${product.price}">
                        <small id="price" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>


                        <button type="submit" class="btn btn-primary mt-4">Изменить данные</button>
                    </fieldset>
               </form>
            </div>
        </div>