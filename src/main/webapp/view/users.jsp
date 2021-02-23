<%@ page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="eng">
<jsp:useBean id="user" class="model.User" scope="session"></jsp:useBean>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css"/>
    <title>Registra nuovo contatto</title>
    <%
        if ((User) session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }
    %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="index.jsp">Rubrica</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<table class="table mx-auto">
    <thead>
    <tr>
        <th scope ="col">Nome</th>
        <th scope ="col">Cognome</th>
        <th scope ="col">Email</th>
        <th scope ="col"> <a href="/Rubrica/caseController?action=add" class="btn">Aggiungi contatto</a> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="contatto" items="${user.rubrica}"> <!-- rubrica è la lista dei contatti-->
        <tr>
            <td>${contatto.nome}</td>
            <td>${contatto.cognome}</td>
            <td>${contatto.numero}</td>
            <td>
                <a href="/Rubrica/caseController?action=modify&idcontatto=${contatto.idcontatto}" class="btn">Modifica</a>
                <a href="/Rubrica/contattoController?action=delete&idcontatto=${contatto.idcontatto}" class="btn">Elimina</a>
            </td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
