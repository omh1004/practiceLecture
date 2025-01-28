<%--
  Created by IntelliJ IDEA.
  User: ohminhyun
  Date: 1/21/25
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>에러페이지</title>
</head>
<body>
    <h3>runtime에러가 발생</h3>
    <p>관리자에게 문의하세요!</p>
    <script>
      setTimeout(()=>{
        location.replace("${pageContext.request.contextPath}");
      },1000);
    </script>
</body>
</html>
