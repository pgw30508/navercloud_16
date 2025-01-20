<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/" var="root"/> <%--컨텍스트 루트 생성해 root라는 변수에 저장 하겠다 --%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<script>
    alert('게시글이 수정되엇습니다');
    location.href="${root}board/read?board_info_idx=${modifyBean.content_board_idx}&content_idx=${modifyBean.content_idx}&page=1";
</script>