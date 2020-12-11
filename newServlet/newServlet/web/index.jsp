<%@ page import="ru.appline.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.10.2020
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите ID пользователя (0 - для вывода всего списка пользователей)
  <br/>
  Доступно: <%
    Model model = Model.getInstance();

    out.print(model.getFromList().size());
  %>

  <form method="get" action="get">
    <label>
      <input type="text" name="id"><br/>
    </label>
    <button type="submit">Поиск</button>

    <a href="addUser.html">Создать нового пользователя</a>
  </form>

  </body>
</html>
