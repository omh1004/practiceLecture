<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br />
<!-- 메모목록 -->


<table class="table">
  <tr>
    <th scope="col">번호</th>
    <th scope="col">메모</th>
    <th scope="col">날짜</th>
    <th scope="col">삭제</th>
  </tr>
  <tbody>
    <c:forEach var="memo" items="${memoList}" varStatus="vs">
      <tr>
        <td>${memo.memoNo}</td>
        <td>${memo.memo}</td>
        <td>${memo.memoDate}</td>
        <td><button class="btn btn-outline-warning" onclick="location.replace('${pageContext.request.contextPath}/memo/deleteMemo.do?id='+${memo.memoNo})">삭제</button></td>
      </tr>
    </c:forEach>
  </tbody>
  <style>
    div#memo-container{width:60%; margin:0 auto;}
  </style>
  <div id="memo-container">
    <form action="${pageContext.request.contextPath}/memo/addMemo.do" class="form-inline" method="post">
      <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
      <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
      <button class="btn btn-outline-success" type="submit" >저장</button>
    </form>
  </div>
</table>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

