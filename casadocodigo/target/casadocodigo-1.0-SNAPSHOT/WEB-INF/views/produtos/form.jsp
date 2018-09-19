<%--
  Created by IntelliJ IDEA.
  User: gustavo.vieira
  Date: 18/09/18
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Casa do Código</title>
</head>
<body>

    <form action="/produtos" method="post">
        <div>
            <label>Titulo
                <input type="text" name="titulo">
            </label>
        </div>
        <div>
            <label>Descrição
                <textarea name="descricao" id="" cols="20" rows="10"></textarea>
            </label>
        </div>
        <div>
            <label>Páginas
                <input type="number" name="paginas">
            </label>
        </div>

        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}
                    <input type="text" name="precos[${status.index}].valor" />
                </label>
                <input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}" />
            </div>
        </c:forEach>

        <button type="submit">Cadastrar</button>

    </form>

</body>
</html>
