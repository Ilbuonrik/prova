<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="eng">
<jsp:useBean id="user" class="model.User" scope="session"></jsp:useBean>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='css/style.css'/>">
    <head>
        <title>Home</title>
    </head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="home.jsp">Rubrica</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<h1>Benvenuto ${user.nome} ${user.cognome}</h1>
<a href="/Rubrica/userController?action=logout">Logout</a>
<c:forEach var="ruolo" items="${user.ruoli}">
    <c:if test="${ruolo.idruolo == 1}">
        <a class="btn btn-outline-success" href="/Rubrica/userController?action=users">ADMIN</a>
    </c:if>
</c:forEach>

<table class="table mx-auto">
    <thead>
    <tr>
        <th scope ="col">Nome</th>
        <th scope ="col">Cognome</th>
        <th scope ="col">Numero di telefono</th>
        <th scope ="col">Azioni</th>
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value='js/script.js'/>"></script>
</body>
</html>
