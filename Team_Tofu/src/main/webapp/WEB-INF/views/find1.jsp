<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FIND</title>
        <link rel="stylesheet" href="resources/css/find.css">
    </head>
    <body>
        <div class="find-container">
            <div class="find-header">
                <form action="main">
                    <button id="back-button">←</button>
                </form>
                <h2>FIND</h2>
            </div>
            <div class="find-content">
                <div class="room">
                    <div class="room-author">
                        <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                        <span class="author-nickname">닉네임1</span>
                    </div>
                    <h3>함께 그림 작업 하실 분</h3>
                    <p>2/7</p>
                    <button class="join-button">참여하기</button>
                </div>
                <div class="room">
                    <div class="room-author">
                        <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                        <span class="author-nickname">닉네임2</span>
                    </div>
                    <h3>함께 밴드 하실 분</h3>
                    <p>2/5</p>
                    <button class="join-button">참여하기</button>
                </div>
                <div class="room">
                    <div class="room-author">
                        <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                        <span class="author-nickname">닉네임3</span>
                    </div>
                    <h3>유튜브 편집 하실 분</h3>
                    <p>0/1</p>
                    <button class="join-button">참여하기</button>
                </div>
                <div class="room">
                    <div class="room-author">
                        <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                        <span class="author-nickname">닉네임4</span>
                    </div>
                    <h3>공모전 함께 참가하실 분</h3>
                    <p>2/3</p>
                    <button class="join-button">참여하기</button>
                </div>
            </div>
    
            <!-- 참여방 만들기 버튼을 위한 별도의 div -->
            <div class="create-room-section">
               <form method="get" action="find_write">
                   <button id="create-room-button">참여방 만들기</button>
                </form>
            </div>
        </div>
        <script src="resources/css/find.js"></script>
    </body>
    </html>