<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/write.css" />
</head>
<body>
	<div class="write-wrapper">
        <h1>작성 페이지</h1>
        <form method="post" id="write-form" action="write" enctype="multipart/form-data">
	            <!-- 제목 입력란 -->
	            <input type="text" name="feed_title" placeholder="제목" class="form-input" required />
	
	            <!-- 내용 입력란 -->
	            <textarea name="feed_content" placeholder="나누고 싶은 이야기나 공유하고 싶은 것들을 이야기 해보세요" class="form-textarea"></textarea>
	
	            <!-- 파일 업로드 섹션 -->
	            <div id="file-upload-section">
	                 <!-- 미리보기 컨테이너 -->
	                 <div class="preview-container"></div>
	
	                <div class="file-upload">
	                    <input type="file" name="feed_file" class="file-input">
	                </div>
	            </div>
	
	            <!-- 파일 추가 버튼 -->
	            <input type="file" name="feed_file" id="add-file-btn" class="add-file-btn">파일 추가</input>
	
	            <!-- 태그 입력란 -->
	            <input type="text" name="hash_tag" placeholder="테그를 추가 해주세요 (예: #태그1 #태그2)" class="form-input">
	            
	            <!-- 작성 버튼 -->
	           <button type="submit" class="submit-btn">작성 완료</button>
           </form>
    </div>
    <script src="resources/css/write.js"></script>
</body>
</html>