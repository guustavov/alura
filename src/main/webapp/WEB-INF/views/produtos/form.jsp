<%--
  Created by IntelliJ IDEA.
  User: gustavo.vieira
  Date: 18/09/18
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Casa do Código</title>
</head>
<body>

    <form:form action="${s:mvcUrl('PC#gravar').build()}" method="post" commandName="produto" enctype="multipart/form-data">
        <div>
            <label>Titulo</label>
            <form:input path="titulo" />
            <form:errors path="titulo" />
        </div>
        <div>

            <label>Descrição</label>
            <form:textarea path="descricao" cols="20" rows="10"/>
            <form:errors path="descricao" />
        </div>
        <div>
            <label>Páginas</label>
            <form:input path="paginas" />
            <form:errors path="paginas" />
        </div>

        <div>
            <label>Data de Lançamento</label>
            <form:input path="dataLancamento"/>
            <form:errors path="dataLancamento"/>
        </div>

        <div>
            <label>Sumário</label>
            <input name="sumario" type="file">
        </div>

        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label>
                <form:input path="precos[${status.index}].valor" />
                <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
            </div>
        </c:forEach>

        <button type="submit">Cadastrar</button>

    </form:form>

</body>
</html>
