<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div id="board-container">
  <input type="text" class="form-control" value="${board.boardTitle}"
         placeholder="제목" name="boardTitle" id="boardTitle"  required>
  <input type="text" class="form-control" name="boardWriter"
         value="${board.boardWriter}"
         readonly required>
  <c:if test="${not empty board.files}">
    <c:forEach var="file" items="${board.files}">
      <button type="button"
              class="btn btn-outline-success btn-block"
              onclick="fn_filedown('${file.originalFileName}','${file.renamedFileName}');">
          ${file.originalFileName}
      </button>
    </c:forEach>
  </c:if>

  <textarea class="form-control" name="boardContent"
            placeholder="내용" required>${board.boardContent}</textarea>
</div>
<script>
  const fn_filedown=(oriname,rename)=>{
    location.assign(`${pageContext.request.contextPath}/board/filedown.do?oriname=\${oriname}&rename=\${rename}`);
  }


</script>
<style>
  div#board-container{width:400px; margin:0 auto; text-align:center;}
  div#board-container input,div#board-container button{margin-bottom:15px;}
  div#board-container label.custom-file-label{text-align:left;}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

