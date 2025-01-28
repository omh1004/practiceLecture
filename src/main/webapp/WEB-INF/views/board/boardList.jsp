<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id="board-container" class="container">

  <p>총 ${totalContents }건의 게시물이 있습니다.</p>
  <c:if test="${loginMember!=null}">
    <button class="btn btn-success" onclick="location
            .assign('${pageContext.request.contextPath}/board/boardform.do');">
      작성하기
    </button>
  </c:if>
  <table id="tbl-board" class="table table-striped table-hover">
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>첨부파일</th>
      <th>조회수</th>
    </tr>
    <c:forEach var="board" items="${boardList}">
      <tr>
        <td>${board.boardNo}</td>
        <td>
          <a href="${pageContext.request.contextPath}/board/boardview.do?no=${board.boardNo}">
              ${board.boardTitle}</a>
        </td>
        <td>${board.boardWriter}</td>
        <td>${board.boardDate}</td>
        <c:forEach var="file" items="${board.files}">
          <td>${file.originalFileName}</td>
        </c:forEach>
        <td>${board.boardReadCount}</td>
      </tr>
    </c:forEach>
  </table>
  <div id="pageBar">
    <c:out value="${pageBar}" escapeXml="false"/>
  </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>