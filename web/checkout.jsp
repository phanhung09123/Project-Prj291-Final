<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Checkout Confirmation</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f8f8;
                margin: 0;
                padding: 0;
            }
            header, footer {
                background-color: #333;
                color: #fff;
                padding: 15px;
                text-align: center;
            }
            .container {
                max-width: 800px;
                margin: 40px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                text-align: center;
            }
            .container h2 {
                margin-bottom: 20px;
            }
            .total {
                font-size: 24px;
                margin: 20px 0;
            }
            .continue-link {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #28a745;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
            }
            .continue-link:hover {
                background-color: #218838;
            }
        </style>
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
