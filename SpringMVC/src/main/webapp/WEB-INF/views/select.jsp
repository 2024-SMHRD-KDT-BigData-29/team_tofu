<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Forty by HTML5 UP</title>
		<meta charset="UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="resources/assets/css/main.css" />
	</head>
	<body style="text-align: center;">
		<!-- Wrapper -->
			<div id="wrapper">
				<!-- Menu -->
					<nav id="Update">	
						<table>
							<caption><h2>회원관리페이지</h2></caption>
							<tr>
								<td>No</td>
								<td>Email</td>
								<td>Tel</td>
								<td>Address</td>
								<td>Delete</td>							
							</tr>
							<!-- 2.모든 회원의 이메일(email),전화번호(tel),주소(address)를 출력하시오. -->
							<!-- 
								forEach태그: java의 for문과 같은 역할(반복문)
								var: 초기화 변수
								begin: 시작값
								end: 종료값
								step: 증감값
							 -->
							<%-- <c:forEach var="i" begin="1" end="5" step="1">
								Hello
							</c:forEach> --%>
							
							<!-- 
								var: 반복문 안에서 사용할 변수를 지정
								items: 배열 또는 리스트 변수를 저장
								varStatus: 상태용 변수 -> 인덱스 출력은 count 변수를 사용(1부터)
							 -->
							<c:forEach var="member" items="${list}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${member.email}</td>
									<td>${member.tel}</td>
									<td>${member.address}</td>
									<td><a href="memberDelete.do?email=${member.email}">삭제</a></td>
								</tr>
							</c:forEach>
							
							
						</table>
					</nav>		
					<a href="indexPage.do" class="button next scrolly">되돌아가기</a>	
			</div>
		<!-- Scripts -->
			<script src="resources/assets/js/jquery.min.js"></script>
			<script src="resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="resources/assets/js/jquery.scrollex.min.js"></script>
			<script src="resources/assets/js/skel.min.js"></script>
			<script src="resources/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="resources/assets/js/main.js"></script>
	</body>
</html>

