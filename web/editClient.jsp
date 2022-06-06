<%-- 
    Document   : editClient
    Created on : 16.05.2022, 14:19:54
    Author     : 37255
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="updateUser" method="POST">
                    <fieldset>
                      <legend>Редактирование данных пользователя</legend>
                      
                      <div class="form-group mb-3">
                        <label for="firstname">Имя</label>
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="text" class="form-control" id="firstname" name="firstname" aria-describedby="firstname" placeholder=""  value="${user.client.firstname}">
                        <small id="firstname" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                      
                      <div class="form-group mb-3">
                        <label for="lastname">Фамилия</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" aria-describedby="lastname" placeholder=""   value="${user.client.lastname}">
                        <small id="lastname" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mb-3">
                        <label for="phone">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone" aria-describedby="phone" placeholder=""  value="${user.client.phone}">
                        <small id="phone" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mb-3">
                        <label for="money">Кошелек</label>
                        <input type="text" class="form-control" id="money" name="money" aria-describedby="money" placeholder=""  value="${user.client.money}">
                        <small id="money" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mb-3">
                        <label for="login">Логин</label>
                        <input type="text" class="form-control" readonly id="login" name="login" aria-describedby="login" placeholder="" value="${user.login}">
                        <input type="hidden"  id="id" name="id">
                        <small id="login" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mb-3">
                        <label for="password1">Пароль</label>
                        <input type="text" class="form-control" id="password1" name="password1" aria-describedby="password" placeholder="">
                        <small id="password1" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        
                      <div class="form-group mb-3">
                        <label for="password2">Повторите пароль</label>
                        <input type="text" class="form-control" id="password2" name="password2" aria-describedby="password" placeholder="">
                        <small id="password2" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <button type="submit" class="btn btn-primary mt-4">Изменить данные</button>
                    </fieldset>
               </form>
            </div>
        </div>
