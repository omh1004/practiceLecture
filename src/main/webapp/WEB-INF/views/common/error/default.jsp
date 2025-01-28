<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ page import="com.bs.spring.common.error.MyException" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<%=exception.getMessage()%>
</body>
</html>
