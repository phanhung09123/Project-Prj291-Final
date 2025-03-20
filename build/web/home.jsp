<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Wine Shop Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/home_menu.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css">
    <title>LCD Web Store</title>
    <link rel="apple-touch-icon" sizes="180x180" href="elements/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="elements/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="elements/favicon/favicon-16x16.png">
    <link rel="manifest" href="elements/favicon/site.webmanifest">
</head>
<body>
    <!-- Include Header/Menu -->
    <jsp:include page="Menu.jsp"></jsp:include>
    
    <!-- Main Container: Sidebar + Main Content -->
    <div class="container">
        <!-- Sidebar: Category Tabs -->
        <div class="sidebar">
            <jsp:include page="Left.jsp"></jsp:include>
        </div>
        
        <!-- Main Content: Product Cards -->
        <div class="main">
            <h2 class="product-title" >products</h2>
            <div class="product-grid">
                <c:forEach var="o" items="${listP}">
                    <a href="detail?wine_id=${o.wine_id}" class="ouct-card">
                        <div>
                            <img src="${o.image_url}" alt="${o.name}" />
                            <h4 class="card-title show_txt">${o.name}</h4>
                            <p>
                                <fmt:formatNumber value="${o.price}" type="currency"/>
                            </p>
                            <form action="cart" method="post">
                                <!-- Assuming the wine id is available as detail.wine_id -->
                                <input type="hidden" name="id" value="${o.wine_id}" />
                                <input type="hidden" name="quantity" value="1" />
                                <button type="submit" class="add-to-cart">Add to Cart</button>
                            </form>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <!-- Include Footer -->
</body>
    <jsp:include page="Footer.jsp"></jsp:include>
</html>
