<%-- 
    Document   : Menu
    Created on : Mar 5, 2025, 11:03:48 AM
    Author     : HA DUC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Header Section -->                        

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/Menu_header.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css">
    <link rel="apple-touch-icon" sizes="180x180" href="elements/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="elements/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="elements/favicon/favicon-16x16.png">
    <link rel="manifest" href="elements/favicon/site.webmanifest">
    <title>Menu Header</title>
</head>

<body class="container-fluid " style="padding-left: 0">
    <header class="header row" style="padding: 0">
        <div class="col-md-1"></div>
        <div class="col-md-2 logo">
            <a href="homepage.jsp"><img src="elements/logo/Logo-menu.png" alt="Home Logo"></a>
        </div>

        <div class="col-md-3">
            <!-- Empty column for spacing -->
        </div>

        <div class="col-md-5">
            <nav class="navigate">
                <a href="homepage.jsp">home</a>
                <a href="search.jsp">search</a>
                <a href="#about">about</a>
                <a href="login.jsp">account</a>
                <a href="cart.jsp">cart</a>
            </nav>
        </div>
        <div class="col-md-1"></div>
    </header>
</body>

</html>
