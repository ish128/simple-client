<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%//@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA Main</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function(){
	
});
	
function fn_save(){  
	$("form[name='qnaFrm']").submit(); 
}
</script>
</head>
<body> 

<form:form modelAttribute="qna" name="qnaFrm" method="post" action="${pageContext.request.contextPath}/qna" onsubmit="fn_save()">
	<section>
		<article>ID: 	<form:input path="userId"/><form:errors path="userId"/></article>		
		<article>TITLE: <form:input path="title"/></article>
		<article>CONTENT: <form:textarea path="content" cols="50" rows="5" /></article> 
	</section>
	<section>
		<input type="submit" value="Save Changes"/> 
	</section> 
</form:form>

</body>
</html>