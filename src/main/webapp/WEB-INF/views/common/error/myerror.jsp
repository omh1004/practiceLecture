<%--
  Created by IntelliJ IDEA.
  User: ohminhyun
  Date: 1/21/25
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ page import="com.bs.spring.common.error.MyException" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3><%=exception.getMessage()%></h3>
        <p><%=((MyException)exception).getDate()%></p>
</body>
</html>
