<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/" var="root"/> <%--컨텍스트 루트 생성해 root라는 변수에 저장 하겠다 --%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery-3.3.1/jquery.min.js"></script>
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
        <c:if test="${fail==true}">
            <div class="alert alert-danger">
                <h2>로그인 실패</h2>
                <p>아이디랑 비밀번호 확인하세요</p>
            </div>
        </c:if>
        <form:form action="${root}user/login_pro" method="post" modelAttribute="loginProBean">
        <div class="form-group">
            <form:label path="user_id">아이디</form:label>
            <form:input type="text" path="user_id" class="form-control"/>
            <form:errors path="user_id" style="color:red"/>
        </div>
            <div class="form-group">
                <form:label path="user_pw">비밀번호</form:label>
                <form:input type="password" path="user_pw" class="form-control"/>
                <form:errors path="user_pw" style="color:red"/>
            </div>
            <div class="form-group">
                <div class="text-right">
                    <form:button type="submit" class="btn btn-primary">로그인</form:button>
                    <a href="${root}user/join">회원가입</a>
                </div>
            </div>

        </form:form>
    </div>
</div>
<c:import url="/WEB-INF/views/include/bottom_menu.jsp"/>
</body>
</html>