<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="headerCut.jsp" />
	<c:if test="${!empty contentPage}">
		<jsp:include page="${contentPage}"/>
	</c:if>
	<c:import url="footer.jsp"></c:import>

</body>
</html>