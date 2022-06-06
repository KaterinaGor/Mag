<%-- 
    Document   : showLogin
    Created on : 16.05.2022, 14:33:04
    Author     : 37255
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="card border-light my-5" style="width: 30rem;">
    <div class="card-body">
        <form action="login" method="POST">
            <fieldset>
              <legend>Авторизация</legend>
              <div class="form-group mb-3">
                <label for="login">Логин</label>
                <input type="text" class="form-control" id="login" name="login" aria-describedby="login" placeholder="">
                <small id="login" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
              </div>
              
              <div class="form-group mb-3">
                <labelpassword">Пароль</label>
                <input type="password" class="form-control" id="password" name="password" aria-describedby="password" placeholder="">
                <small id="password" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
              </div>
              
              <button type="submit" class="btn btn-primary mt-4">Войти</button>
              
              <p class="text-info w-100 text-center my-3">Нет логина? <a href="showRegistration">Зарегистрироваться</a></p>
            </fieldset>
       </form>
    </div>
</div>
