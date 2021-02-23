<%@ page import="model.User"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Ruolo" %>
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
    <title>Admin</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/home.jsp">Rubrica</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<h1>Benvenuto ${user.nome} ${user.cognome}</h1>
<a href="/Rubrica/userController?action=logout">Logout</a>
<c:forEach var="ruolo" items="${user.ruoli}">
    <c:if test="${ruolo.idruolo == 2}">
        <a class="btn btn-outline-success" href="/Rubrica/caseController?action=backToHome">Utente</a>
    </c:if>
</c:forEach>


<table class="table mx-auto">
    <thead>
    <tr>
        <th scope ="col">Nome</th>
        <th scope ="col">Cognome</th>
        <th scope ="col">Email</th>
        <th scope ="col">Ruoli</th>
        <th scope ="col"> <a href="/Rubrica/caseController?action=add" class="btn">Aggiungi admin</a> </th>
    </tr>
    </thead>
    <tbody>
    <%
        if (session != null && session.getAttribute("users") != null){
            for (User us : (List<User>)session.getAttribute("users")){
    %>
        <tr>
            <td><%=us.getNome()%></td>
            <td><%=us.getCognome()%></td>
            <td><%=us.getEmail()%></td>
            <td>
                <div class="btn-group dropright">
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        RUOLI
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <%
                            Ruolo r = null;
                            for(int i =0; i < us.getRuoli().size(); i++){
                                r = us.getRuoli().get(i);
                                System.out.println(r.getDescrizione());
                        %>
                        <a class="dropdown-item"><%=r.getDescrizione()%></a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </td>
            <td>
                <a href="/Rubrica/caseController?action=updateUser&id=<%=us.getId()%>>" class="btn">Modifica</a>
                <a href="/Rubrica/userController?action=deleteUsers&id=<%=us.getId()%>" class="btn">Elimina</a>
            </td>
            <td></td>
        </tr>
    <%
        }
    %>

    </tbody>
    <%
        }
    %>
</table>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>