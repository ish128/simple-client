<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%//@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>QnA Main</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function(){ 
	$("#btnAddFile").on("click", function(){
		$("#divAttachFiles").append("<input type='file' name='attachFile'>"); 
	}); 
});
	
function fn_save(){  
	$("form[name='qnaFrm']").submit(); 
}

</script>
</head>
<body> 

<form:form modelAttribute="qnaForm" name="qnaFrm" method="post" action="${pageContext.request.contextPath}/qna" enctype="multipart/form-data" onsubmit="fn_save()">
	<section>
		<article>ID: 	<form:input path="userId"/><form:errors path="userId"/></article>		
		<article>TITLE: <form:input path="title"/></article>
		<article>CONTENT: <form:textarea path="content" cols="50" rows="5" /></article>  
		<article>성별 : <form:radiobuttons path="gender" items="${genderItem}" /></article>  
		<article>언어 : <form:checkboxes path="language" items="${languageItem}" /></article> 
		<article>과일 : <form:select path="fruit" items="${fruitItem}" multiple="true"/></article> 
		<article>FILES: 
			<div id="divAttachFiles"><input type="file" multiple="multiple" name='attachFile'/></div>
			<input type="button" id="btnAddFile" value="Add File"/>
		</article>  
	</section>
	<section>
		<input type="submit" value="Save Changes"/> 
	</section> 
</form:form>

</body>
</html>