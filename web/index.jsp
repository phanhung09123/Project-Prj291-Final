<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Store</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #46626D;
            }
            .navbar {
                background-color: #0078D4;
            }
            .navbar a, .navbar button {
                color: white !important;
            }
            .sidebar {
                background-color: #5A7D8C;
                padding: 15px;
                border-radius: 10px;
            }
            .product-card {
                background-color: white;
                padding: 15px;
                border-radius: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>

        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="image/logo.jpg" alt="Logo" height="30"></a>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item"><a class="nav-link" href="#">Red</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Sparkling</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">White</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Rosé</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Dessert</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Fortified</a></li>

                    </ul>
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search">
                        <button class="btn btn-light">Search</button>
                    </form>
                    <a class="nav-link" href="#">Login</a>
                    <a class="nav-link" href="#">Register</a>
                </div>
            </div>
        </nav>

        <!-- Sidebar & Product List -->
        <div class="container mt-4">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-2 sidebar">
                    <h5 class="text-white">LIST OF PRODUCTS</h5>
                    <ul class="list-unstyled text-white">
                        <li><a href="#" class="text-white">Red</a></li>
                        <li><a href="#" class="text-white">Sparkling</a></li>
                        <li><a href="#" class="text-white">White</a></li>
                        <li><a href="#" class="text-white">Rosé</a></li>
                        <li><a href="#" class="text-white">Dessert</a></li>
                        <li><a href="#" class="text-white">Fortified</a></li>
                    </ul>
                </div>

                <!-- Product Cards -->
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Château Margaux.jpg" class="img-fluid">
                                <h5>Château Margaux</h5>
                                <p>Price: 19,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Silver Oak Cabernet.jpg" class="img-fluid">
                                <h5>Silver Oak Cabernet</h5>
                                <p>Price: 16,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Moët & Chandon Brut.jpg" class="img-fluid">
                                <h5>Moët & Chandon Brut</h5>
                                <p>Price: 13,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Moët & Chandon Brut.jpg" class="img-fluid">
                                <h5>Penfolds Grange</h5>
                                <p>Price: 13,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Moët & Chandon Brut.jpg" class="img-fluid">
                                <h5>Cloudy Bay Sauvignon Blanc</h5>
                                <p>Price: 13,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Moët & Chandon Brut.jpg" class="img-fluid">
                                <h5>Beringer Private Reserve Chardonnay</h5>
                                <p>Price: 13,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="product-card">
                                <img src="image/Moët & Chandon Brut.jpg" class="img-fluid">
                                <h5>Whispering Angel Rosé</h5>
                                <p>Price: 13,000.0 USD</p>
                                <button class="btn btn-primary">Detail</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
