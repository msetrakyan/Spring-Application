<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.smartcode.web.service.comment.CommentService" %>
<%@ page import="com.smartcode.web.model.CommentEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartcode.web.model.UserEntity" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.smartcode.web.service.comment.impl.CommentServiceImpl" %>
<%@ page import="com.smartcode.web.repository.CommentRepository" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="javax.xml.stream.events.Comment" %>
<%@ page import="com.smartcode.web.service.user.UserService" %>
<%@ page import="org.springframework.context.ApplicationContext" %><%--
  Created by IntelliJ IDEA.
  User: msetrakyan
  Date: 21.09.23
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>

    <tr>
        <th>Comment table</th>
        <th>Description</th>
    </tr>
<%

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

    CommentService commentService = applicationContext.getBean(CommentService.class);

    UserService userService = applicationContext.getBean(UserService.class);

    String username = (String)request.getSession().getAttribute("username");

    UserEntity userEntity = userService.findByUsername(username);

    List<CommentEntity> list = commentService.getAll(userEntity);

    for(int i = 0; i < list.size(); i++) {
%>
<tr>
    <td>
        <%=list.get(i).getTitle()%>
    </td>
    <td>
        <%=list.get(i).getDescription()%>
    </td>
    <td>
        <form action="/users/comment" method="post"/>
            <input type="hidden" name="title" value="<%=list.get(i).getTitle()%>" />
            <input type="submit" value="delete">
        </form>

    </td>


</tr>

<%
    }
%>





</table>


<form action="/users/commentUtil" method="post"/>
<input type="text" name="title"><br>
<input type="text" name="description"><br>
<input type="submit" name="create">
</form>






</body>
</html>
