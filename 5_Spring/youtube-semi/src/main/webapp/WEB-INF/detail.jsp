<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YouTube</title>
<link rel="icon" href="https://www.youtube.com/s/desktop/ae4ecf92/img/favicon_144x144.png" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/detail.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="detail">
		<div class="video-detail">
			<video src="${video.videoUrl }" controls></video>
			<h2>${video.videoTitle }</h2>
			<div class="video-detail-desc">
				<div class="detail-desc-left">
					<img src="${video.channel.channelImg }">
					<div class="channel-desc">
						<h3>${video.channel.channelName }</h3>
						<p>구독자 0명</p>
					</div>
					<button>구독</button>
				</div>
				<button><i class="fa-regular fa-thumbs-up"></i>좋아요</button>
			</div>
			<div class="video-detail-info">
				${video.videoDesc }
			</div>
		</div>
		<div class="video-list">
			<c:forEach items="${list }" var="video">
				<a href="/${video.videoCode }" class="vedio-list-card">
					<img src="${video.videoImg }">
					<div class="video-list-desc">
						<h4>${video.videoTitle }</h4>
						<p>${video.channel.channelName }</p>
						<p class="video-meta" data-video-date="${video.videoDate }">
							조회수 ${video.videoCount }회 ㆍ <span class="video-date"></span>
						</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</main>
	<script src="${pageContext.request.contextPath }/js/time.js"></script>
</body>
</html>