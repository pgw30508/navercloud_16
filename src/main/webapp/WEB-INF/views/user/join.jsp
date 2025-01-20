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
        <form:form action="${root}user/join_pro" method="post" modelAttribute="joinBean">
            <form:hidden path="existId"/>
            <div class="form-group">
                <form:label path="user_name">이름</form:label>
                <form:input type="text" path="user_name" class="form-control"/>
                <form:errors path="user_name" style="color:red"/>
            </div>

            <div class="form-group">
                <form:label path="user_id">아이디</form:label><br>
                <div class="input-group-append">
                    <form:input type="text" path="user_id" class="form-control" onkeypress="resetUser()"/>
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2"
                    onclick="chexistId()">중복확인</button>
                </div>
                <form:errors path="user_id" style="color:red"/>
            </div>

            <div class="form-group">
                <form:label path="user_pw">비밀번호</form:label>
                <form:password path="user_pw" class="form-control"/>
                <form:errors path="user_pw" style="color:red"/>
            </div>

            <div class="form-group">
                <form:label path="user_pw2">비밀번호 확인</form:label>
                <form:password path="user_pw2" class="form-control"/>
                <form:errors path="user_pw2" style="color:red"/>
            </div>

            <div class="form-group">
                <div class="text-right">
                    <form:button type="submit" class="btn btn-primary">회원가입</form:button>
                </div>
            </div>
        </form:form>
    </div>
</div>


<c:import url="/WEB-INF/views/include/bottom_menu.jsp"/>

<script>
    function chexistId() {
        let user_id=$("#user_id").val(); //입력한 아이디 가져옴
        if(user_id.length===0) {
            alert('아이디 입력해주세요');
            return;
        }

        $.ajax({
            url:'${root}user/chexistId/'+user_id,
            type:'get',
            dataType:'text',
            success:function(result){
                if(result.trim()==="true") {
                    alert('사용할 수 있는 아이디입니다');
                    $("#existId").val('true');
                }
                else {
                    alert('사용할 수 없는 아이디입니다');
                    $("#existId").val('false');
                }
            }
        });

    }
        function resetUser() {
            $("#existId").val('false');
    }
</script>
</body>
</html>