<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert</title>
</head>
<body>
    <script type="text/javascript">
        // 컨트롤러에서 전달된 메시지를 alert로 출력
        alert("${message}");
        // 지정된 URL로 리다이렉션
        window.location.href = "${redirectUrl}";
    </script>
</body>
</html>