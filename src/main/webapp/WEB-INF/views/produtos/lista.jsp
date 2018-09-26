<%--
  Created by IntelliJ IDEA.
  User: gustavo.vieira
  Date: 19/09/18
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Casa do Código</title>
</head>
<body>
    <h1>Lista de livros</h1>

    <div>${sucesso}</div>
    <div>${falha}</div>

    <table>
        <tr>
            <td>Titulo</td>
            <td>Descrição</td>
            <td>Páginas</td>
        </tr>
        <c:forEach items="${produtos}" var="produto">
            <tr>
                <td><a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.titulo}</a></td>
                <td>${produto.descricao}</td>
                <td>${produto.paginas}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
