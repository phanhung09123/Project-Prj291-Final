<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/detail_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css">
        <title>LCD Web Store</title>
        <link rel="apple-touch-icon" sizes="180x180" href="elements/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="elements/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="elements/favicon/favicon-16x16.png">
        <link rel="manifest" href="elements/favicon/site.webmanifest">
    </head>
    <meta charset="UTF-8">
    <title>Wine Details</title>
    
</head>
<body>
    <!-- Include the Menu/Header -->
    <jsp:include page="Menu.jsp"></jsp:include>

        <!-- Main Container: Sidebar + Detail Content -->
        <div class="container">
            <!-- Sidebar: Category Navigation (same as Home.jsp) -->
            <div class="sidebar">
                <h3>Categories</h3>
            <c:forEach items="${listC}" var="o">
                <li class="list-group-item text-white ${tag == o.cid ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/category?cid=${o.cid}">
                        ${o.cname}
                    </a>
                </li>
            </c:forEach>
        </div>

        <!-- Detail Content: Product Details -->
        <div class="detail-content">
            <h1>${detail.name}</h1>
            <p><strong>Price:</strong> <fmt:formatNumber value="${detail.price}" type="currency" /></p>
            <p><strong>Description:</strong> ${detail.description}</p>
            <p><strong>Country:</strong> ${detail.country}</p>
            <p><strong>Year:</strong> ${detail.year}</p>
        </div>
    </div>

    <!-- Back Link -->
    <div class="back-link">
        <a href="${pageContext.request.contextPath}/home">Back to Home</a>
    </div>

    <!-- Include the Footer -->
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
