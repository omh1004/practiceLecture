<%--
  Created by IntelliJ IDEA.
  User: ohminhyun
  Date: 1/16/25
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메세지</title>
</head>
<body>
  <script>
    alert("${msg}");
    location.replace("${pageContext.request.contextPath}${loc}")

  </script>
</body>
</html>
