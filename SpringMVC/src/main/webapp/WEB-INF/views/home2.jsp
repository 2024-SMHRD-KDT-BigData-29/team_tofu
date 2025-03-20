<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home2 페이지</h1>
	
	<!-- EL문법 -->
	${requestScope.msg}<br>
	${msg}<br>
	${5}<br>
	${5.6}<br>
	${"msg"}<br>
	${true}<br>
	${5 > 3}<br>
	${empty msg}<br>
	
</body>
</html>