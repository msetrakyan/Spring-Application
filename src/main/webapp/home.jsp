<%--
  Created by IntelliJ IDEA.
  User: msetrakyan
  Date: 21.09.23
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/users/logout" method="get">
    <input type="submit" value="logout">
</form>



<%
    if(request.getSession().getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<h1>Welcome dear <%=request.getSession().getAttribute("username")%> </h1>


<button onclick="window.location.pathname='comment.jsp';">
    Comment Section
</button>




<%

%>


</body>
</html>
