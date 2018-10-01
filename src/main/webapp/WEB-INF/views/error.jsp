<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tags:pageTemplate titulo="Produto não encontrado - Casa do Código">

	<section id="index-section" class="container middle">

		<h2>Exception disparada no sistema! CORRA!</h2>


	<!--
		Mensagem: ${exception.message}
		<c:forEach items="${exception.stackTrace}" var="stack">
			${stack}
		</c:forEach>
	-->

	</section>

</tags:pageTemplate>