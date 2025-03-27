<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>find_write</title>
<link rel="stylesheet" href="resources/css/find_write.css">
</head>
<body>
    <div class="write-wrapper">
        <h1>TOFU</h1>
        <form method="post" id="write-form" action="find_write">
            <!-- 제목 입력란 -->
            <input type="text" name="cw_title" placeholder="제목" class="form-input">
            <!-- 협업 소개란-->
             <input type="textarea" name="cw_intro" placeholder="무엇을 위한 그룹인지를 설명해주세요" class="form-input">

            <!-- 내용 입력란 -->
            <textarea name="cw_content" placeholder="나누고 싶은 이야기나 공유하고 싶은 것들을 이야기 해보세요" class="form-textarea"></textarea>

            <!-- 파일 첨부 버튼 -->
              <!-- 파일 업로드 섹션 -->
              <div id="file-upload-section">
                <!-- 미리보기 컨테이너 -->
                <div class="preview-container"></div>

               <div class="file-upload">
                   <input type="file" name="cw_img" class="file-input">
               </div>
           </div>

            <button type="button" id="add-file-btn" class="add-file-btn">파일 추가</button>

            <!-- 협원 인원 선택란-->
            <input type="text" name="cw_limit" class="form-input" placeholder="인원을 입력해주세요">
            <!-- 태그 입력란 -->
            <input type="text" name="hash_tag" placeholder="테그를 추가 해주세요 (예: #태그1 #태그2)" class="form-input">

            <!-- 작성 버튼 -->
            <button type="submit" class="submit-btn">작성 완료</button>
        </form>
    </div>
    <script src="resources/css/find_write.js"></script>
</body>
</html>