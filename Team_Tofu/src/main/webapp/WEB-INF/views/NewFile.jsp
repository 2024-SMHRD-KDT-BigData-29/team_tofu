<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
!-- Q17. 게시글 목록 조회 기능 -->
			<!-- Q18. 게시글 목록 세부페이지 기능(제목을 클릭하면 세부페이지 BoardDetail.jsp로 이동)-->
			<div id="board">
				<table id = "list">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>시간</td>
					</tr>
					
					<c:forEach var="b" items="${list }" varStatus="s">
						<tr>
							<td>${s.count }</td>
							<td><a href="boardDetail?idx=${b.idx }">${b.title }</a></td>
							<td>${b.writer }</td>
							<td>${b.b_date }</td>
							
							<c:if test="${loginMember.email eq 'admin' }">
								<td><a href="deleteBoard/${b.idx }">삭제</a></td>
								<%-- <td><a href="deleteBoard?idx=${b.idx }">삭제</a></td> --%>
							</c:if>
							
						</tr>
					</c:forEach>
				
				</table>
				
				<a href="indexPage.do"><button id="writer">홈으로가기</button></a>
				<a href="goBoardWrite"><button id="writer">작성하러가기</button></a>
			</div>
</body>
</html>