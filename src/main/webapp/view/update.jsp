<%@ page import="model.User" %>
<%@ page import="model.Contatto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<jsp:useBean id="user" class="model.User" scope="session"></jsp:useBean>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css"/>
    <head>
        <title>Modifica Contatto</title>
    </head>
    <body>
    <%
        if ((User) session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }

        Contatto contatto = new Contatto();
        contatto.setIdcontatto((Integer)session.getAttribute("idcontatto"));

    %>

    //////////////////// <form action="contattoController?action=update&idcontatto=<%=contatto.getIdcontatto()%>" method="post">
    <label>Nome: </label><input type="text" name="nome"/>
    <label>Cognome: </label><input type="text" name="cognome"/>
    <label>Numero: </label><input type="number" name="numero"/>
    <input type="submit" value="registra"/>
    </form>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>