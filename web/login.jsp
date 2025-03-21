<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style_1.css">
        <link rel="stylesheet" href="css/style_login.css">
        <title>Login - LCD Web Store</title>
        <link rel="apple-touch-icon" sizes="180x180" href="elements/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="elements/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="elements/favicon/favicon-16x16.png">
        <link rel="manifest" href="elements/favicon/site.webmanifest">
    </head>

    <body class="container-fluid">
        <header class="row">
            <div class="col-md-2 logo">
                <a href="homepage.jsp"><img src="elements/logo/home_logo.png" alt="Home Logo"></a> <!-- Wrap logo in a link -->
            </div>
            <div class="col-md-4"></div>
            <nav class="col-md-6 navigate">
                <a href="HomePage.html">home</a>
                <a href="#">search</a>
                <a href="#">explore</a>
                <a href="Login.html">account</a>
                <a href="#">cart</a>
            </nav>
        </header>

        <main class="row justify-content-center">
            <div class="col-md-4">
                <h2 class="text-center">log in.</h2>
                <h2 class="text-center2">${mess}</h2>
                <form id="loginForm" action="login" method="POST">
                    <div class="mb-3">
                        <input type="email" class="form-control" placeholder="Email Address" name="user" required>
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" placeholder="Password" name="pass" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">log in</button>
                </form>
                <div class="flex-container">
                    <p class="text-center1 mt-3">Don't have an account?</p>
                    <a href="register.jsp" class="btn btn-link log-btn">register here</a>
                </div>
                <div class="flex-container">
                    <a href="https://accounts.google.com/o/oauth2/auth?
                       &client_id=709737879497-ga296lhh44fac2ti4n07qdpjb56cpjko.apps.googleusercontent.com                       
                       &redirect_uri=http://localhost:9999/wines/login
                       &response_type=code
                       &scope=email%20profile%20openid" class="btn btn-link log-btn">Login with Google</a>
                </div>
            </div>
        </main>


        <footer class="footer row">
            <div class="col-md-4 title_contact">
                <h3>CONTACT US</h3>
            </div>
            <div class="col-md-4 contact_info">
                <p>Address: 123 Main Street, Ha Noi</p>
                <p>Phone: 0123456789</p>
            </div>
        </footer>
    </body>

</html>