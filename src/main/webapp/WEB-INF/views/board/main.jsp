<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/" var="root"/> <%--컨텍스트 루트 생성해 root라는 변수에 저장 하겠다 --%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
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
    <h5 class="card-title">${board_info_name}</h5>
    <table class="table table-hover">
        <thead>
            <tr>
                <th class="d-md-table-cell">글번호</th>
                <th class="d-md-table-cell w-50">제목</th>
                <th class="d-md-table-cell">글쓴이</th>
                <th class="d-md-table-cell">작성날짜</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${contentList}">
            <tr>
                <td class="d-md-table-cell">${obj.content_idx}</td>
                <td class="d-md-table-cell w-50">
                    <a href="${root}board/read?board_info_idx=${board_info_idx}&content_idx=${obj.content_idx}&page=${page}">
                            ${obj.content_subject}</a></td>
                <td class="d-md-table-cell">${obj.content_writer_name}</td>
                <td class="d-md-table-cell">${obj.content_date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-right">
        <a href="${root}board/write?board_info_idx=${board_info_idx}" class="btn btn-primary">글쓰기</a>
    </div>
    <div class="d-none d-md-block">
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${pBean.prePage <= 0 }">
                    <li class="page-item disabled"><a href="#"
                                                      class="page-link">이전</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a
                            href="${root }board/main?board_info_idx=${board_info_idx}&page=${pBean.prePage}"
                            class="page-link">이전</a></li>
                </c:otherwise>
            </c:choose>


            <c:forEach var='idx' begin="${pBean.min }" end='${pBean.max }'>
                <c:choose>
                    <c:when test="${idx == pBean.currentPage }">
                        <li class="page-item active"><a
                                href="${root }board/main?board_info_idx=${board_info_idx}&page=${idx}"
                                class="page-link">${idx }</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a
                                href="${root }board/main?board_info_idx=${board_info_idx}&page=${idx}"
                                class="page-link">${idx }</a></li>
                    </c:otherwise>
                </c:choose>

            </c:forEach>

            <c:choose>
                <c:when test="${pBean.max >= pBean.pageCnt}">
                    <li class="page-item disabled"><a href="#"
                                                      class="page-link">다음</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a
                            href="${root }board/main?board_info_idx=${board_info_idx}&page=${pBean.nextPage}"
                            class="page-link">다음</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>
</div>



<c:import url="/WEB-INF/views/include/bottom_menu.jsp"/>
</body>
</html>