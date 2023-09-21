<%--
  Created by IntelliJ IDEA.
  User: msetrakyan
  Date: 21.09.23
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>


<form action="/users/login" method="post">

        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>


        <input type="submit" value="login">
    </form>








</body>
</html>
