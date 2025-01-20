<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/" var="root"/> <%--컨텍스트 루트 생성해 root라는 변수에 저장 하겠다 --%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <style>


    </style>
    <title></title>


</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>


<div class="container">
    <div class="card card-body">

            <div class="form-group">
                <label for="board_writer_name">글쓴이</label>
                <input type="text" value="${readContent.content_writer_name}" disabled="disabled"
                       class="form-control" id="board_writer_name" name="board_writer_name"/>
            </div>

            <div class="form-group">
                <label for="board_date">작성날짜</label>
                <input type="text" value="${readContent.content_date}" disabled="disabled"
                       class="form-control" id="board_date" name="board_date"/>
            </div>

            <div class="form-group">
                <label for="board_subject">제목</label>
                <input type="text" value="${readContent.content_subject}" disabled="disabled"
                       class="form-control" id="board_subject" name="board_subject"/>

            </div>

            <div class="form-group">
                <label for="board_text">내용</label>
                <textarea disabled="disabled"  rows="10"
                          class="form-control" id="board_text" name="board_text">${readContent.content_text}</textarea>
            </div>

            <div class="form-group">
                <div class="text-right">
                    <a class="btn btn-primary" href="${root}board/main?board_info_idx=${board_info_idx}
                    &page=${page}">목록</a>

                    <%--로그인 한 사람이 본인글을 쓰고 제목 클릭하면 목록, 수정,  삭제가 다 뜨지만
                    본인글이 아니라면 목록만 뜬다.--%>

                    <c:if test="${loginBean.user_idx==readContent.content_writer_idx}">
                    <a class="btn btn-primary" href="${root}board/modify?board_info_idx=${board_info_idx}
                    &content_idx=${content_idx}&page=${page}">수정</a>
                    <a class="btn btn-primary" href="${root}board/delete?board_info_idx=${board_info_idx}
                    &content_idx=${content_idx}&page=${page}">삭제</a>
                    </c:if>
                </div>
            </div>
    </div>
</div>


<c:import url="/WEB-INF/views/include/bottom_menu.jsp"/>

</body>
</html>