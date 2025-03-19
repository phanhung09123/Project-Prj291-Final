<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/checkout_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css">
        <title>LCD Web Store</title>
        <link rel="apple-touch-icon" sizes="180x180" href="elements/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="elements/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="elements/favicon/favicon-16x16.png">
        <link rel="manifest" href="elements/favicon/site.webmanifest">
        <meta charset="UTF-8">
        <title>Checkout Confirmation</title>
    </head>
    <body>
        <!-- Include Header/Menu -->
        <jsp:include page="Menu.jsp" />

        <div class="container">
            <h2>Thank You for Your Purchase!</h2>
            <p>Your Order ID: <strong>${param.orderId}</strong></p>
            <p class="total">Your Total Bill is: <strong>$${param.totalPrice}</strong></p>
            <p>We appreciate your business!</p>
            <a class="continue-link" href="${pageContext.request.contextPath}/home">Continue Shopping</a>
        </div>

        <!-- Include Footer -->
        <jsp:include page="Footer.jsp" />
    </body>
</html>
