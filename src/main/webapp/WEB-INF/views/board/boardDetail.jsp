<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div id="board-container">
  <input type="text" class="form-control" placeholder="Á¦¸ñ" name="boardTitle" id="boardTitle"  required>
  <input type="text" class="form-control" name="boardWriter"  readonly required>

  <button type="button"
          class="btn btn-outline-success btn-block"
          onclick="">
  </button>


  <textarea class="form-control" name="boardContent" placeholder="³»¿ë" required></textarea>
</div>

<style>
  div#board-container{width:400px; margin:0 auto; text-align:center;}
  div#board-container input,div#board-container button{margin-bottom:15px;}
  div#board-container label.custom-file-label{text-align:left;}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>