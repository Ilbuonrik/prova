<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="view/css/style.css"/>

    <title>RUBRICA</title>
</head>
<body>
    <!--navbar-->
    <div class="container">
        <nav class="navbar navbar-expand-lg justify-content-center">
            <div class="d-flex w-60 order-0">
                <a class="navbar-brand ml-auto" href="index.jsp">
                    <h1>RUBRICA</h1>
                </a>
            </div>
        </nav>
    </div>

    <!-- registrazione e FORM-->
    <div class="sfondo">
    <div class="container text-center py-5">
        <a href="pageRegistration">Registrati</a>
    </div>
        <div class="container text-center py-3">
            <div class="row">
                <div class="col">
                    <form action="userController?action=login" method="post">
                        <label>Email: </label><input type="email" name="email" /><br>
                        <label>Password: </label><input type="password" name="password" /><br>
                        <input type="submit" value="login"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
