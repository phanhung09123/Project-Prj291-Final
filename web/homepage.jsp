<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/homepagestyle.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css">
    <title>LCD Web Store</title>
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
        
        <div class="col-md-4">

        </div>

        <nav class="col-md-6 navigate">
            <a href="${pageContext.request.contextPath}/search">search</a>
            <a href="${pageContext.request.contextPath}/home"">explore</a>
            <a href="#">about</a> <!-- Update link to jump to about section -->
            <a href="login.jsp">account</a>
            <a href="cart.jsp">cart</a>
        </nav>
    </header>
    
    <main>
        <section class="poster-slider">
            <div class="poster">
                <img src="elements/banner/banner-ctkm-champagne.jpg" alt="Poster 1">
            </div>
            <div class="poster">
                <img src="elements/banner/banner-vang-hong-8-3.jpg" alt="Poster 2">
            </div>
            <!-- Add more posters as needed -->
        </section>

        <section id="about" class="about row"> <!-- Add id to about section -->
            <div class="col-md-6">
                <h2>HIGH QUALITY</h2>
                <p>we provide the finest, authentic liquor</p>
                <p>imported from respected provider</p>
                
            </div>
            <div class="col-md-6">
                <h2>VAST VARIETY</h2>
                <p>suitable for everyone's liking </p>
                <p>over 200 brands to choose from</p>
                
            </div>
            <button class="start_explore">
                <a href="home">start exploring</a>
            </button>
        </section>

        <section>
            <div class="footer row">
            <div class="col-md-4 title_contact">
                <h3>CONTACT US</h3>
            </div>

            <div class="col-md-4 contact_info">
                <p>Address: 123 Main Street, Ha Noi</p>
                <p>Phone: 0123456789</p>
            </div>
            </div>
        </section>

        
    </main>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.poster-slider').slick({
                infinite: true,
                slidesToShow: 1,
                slidesToScroll: 1,
                autoplay: true,
                autoplaySpeed: 1500,
                prevArrow: '<button type="button" class="slick-prev"><</button>',
                nextArrow: '<button type="button" class="slick-next">></button>',
            });
        });
    </script>
</body>
</html>
