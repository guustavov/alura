<%--
  Created by IntelliJ IDEA.
  User: gustavo.vieira
  Date: 27/09/18
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:url value="/" var="contextPath" />

    <header id="layout-header">
        <div class="clearfix container">
            <a href="${contextPath}" id="logo"> </a>
            <div id="header-content">
                <nav id="main-nav">
                    <ul class="clearfix">
                        <li><a href="${s:mvcUrl('CC#itens').build()}" rel="nofollow">Carrinho (${carrinho.quantidade})</a></li>
                        <li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre Nós</a></li>
                        <li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas Frequentes</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <nav class="categories-nav">
        <ul class="container">
            <li class="category"><a href="http://www.casadocodigo.com.br">Home</a></li>
            <li class="category"><a href="/collections/livros-de-agile"> Agile </a></li>
            <li class="category"><a href="/collections/livros-de-front-end"> Front End </a></li>
            <li class="category"><a href="/collections/livros-de-games"> Games </a></li>
            <li class="category"><a href="/collections/livros-de-java"> Java </a></li>
            <li class="category"><a href="/collections/livros-de-mobile"> Mobile </a></li>
            <li class="category"><a href="/collections/livros-desenvolvimento-web"> Web </a></li>
            <li class="category"><a href="/collections/outros"> Outros </a></li>
        </ul>
    </nav>
