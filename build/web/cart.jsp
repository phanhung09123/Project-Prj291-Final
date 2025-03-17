<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie, java.net.URLDecoder, java.net.URLEncoder, java.util.ArrayList, java.util.List" %>
<%@ page import="dal.DAO, model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Your Shopping Cart</title>
        <style>
            /* Global Styles */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            header, footer {
                background-color: #333;
                color: #fff;
                padding: 15px;
                text-align: center;
            }
            /* Container for sidebar + main content */
            .container {
                display: flex;
                padding: 20px;
            }
            .sidebar {
                width: 200px;
                background-color: #f4f4f4;
                padding: 15px;
            }
            .main {
                flex: 1;
                padding: 15px;
            }
            /* Cart Table */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 15px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f4f4f4;
            }
            /* Cart Action Buttons */
            form button {
                margin: 0 5px;
            }
            a {
                text-decoration: none;
                color: #0066cc;
            }
            /* Checkout and Continue Shopping Links Container */
            .cart-actions {
                margin-top: 20px;
                text-align: center;
            }
            .cart-actions a {
                display: inline-block;
                margin: 0 15px;
                padding: 10px 20px;
                background-color: #28a745;
                color: #fff;
                border-radius: 5px;
            }
            .cart-actions a:hover {
                background-color: #218838;
            }

        </style>
    </head>
    <body>
        <div>
            <!-- Include Header/Menu -->
            <jsp:include page="Menu.jsp"></jsp:include>



                <!-- Main Content: Cart -->
                <div class="main">
                    <h2>Your Shopping Cart</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            // Instantiate the DAO.
                            DAO dao = new DAO();
                            // Retrieve the cart cookie.
                            Cookie[] cookies = request.getCookies();
                            String cartData = "";
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if ("cart".equals(cookie.getName())) {
                                        cartData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                                        break;
                                    }
                                }
                            }
                        
                            double totalPrice = 0;
                            List<String> cartItems = new ArrayList<>();
                        
                            if (cartData != null && !cartData.trim().isEmpty()) {
                                // Expected cartData format: "productId-quantity,productId-quantity,..."
                                String[] items = cartData.split(",");
                                for (String item : items) {
                                    if (item != null && !item.trim().isEmpty()) {
                                        // Split the item on hyphen.
                                        String[] parts = item.split("-");
                                        if (parts.length == 2) {
                                            try {
                                                int productId = Integer.parseInt(parts[0].trim());
                                                int quantity = Integer.parseInt(parts[1].trim());
                                            
                                                // Retrieve product from database.
                                                Product p = dao.getProduct(productId);
                                                if (p != null) {
                                                    // Set the product's quantity (if needed for display).
                                                    p.setQuantity(quantity);
                                                    // Save item for cookie update.
                                                    cartItems.add(productId + "-" + quantity);
                        %>
                        <tr>
                            <td><%= p.getName() %></td>
                            <td><%= p.getPrice() %> $</td>
                            <td>
                                <form action="updateCart" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= productId %>" />
                                    <button type="submit" name="action" value="decrease">-</button>
                                    <span><%= quantity %></span>
                                    <button type="submit" name="action" value="increase">+</button>
                                </form>
                            </td>
                            <td>
                                <%= p.getPrice().multiply(new java.math.BigDecimal(quantity)) %> $
                            </td>
                            <td>
                                <a href="removefromcart?id=<%= productId %>">Remove</a>
                            </td>
                        </tr>
                        <%
                                                    totalPrice += p.getPrice().doubleValue() * quantity;
                                                }
                                            } catch (NumberFormatException nfe) {
                                                System.err.println("Error parsing cart item: " + item);
                                            }
                                        } else {
                                            System.err.println("Invalid cart item format: " + item);
                                        }
                                    }
                                }
                            }
                            // Update the cart cookie with current valid items.
                            String updatedCartData = String.join(",", cartItems);
                            Cookie updatedCart = new Cookie("cart", URLEncoder.encode(updatedCartData, "UTF-8"));
                            updatedCart.setMaxAge(60 * 60 * 24 * 7); // 7 days.
                            updatedCart.setPath("/");
                            response.addCookie(updatedCart);
                        %>
                    </tbody>
                </table>
                <h4>Total: <%= String.format("%.2f", totalPrice) %> $</h4>
                <form action="${pageContext.request.contextPath}/checkout" method="post">
                    <button type="submit">Proceed to Checkout</button>
                </form>
                <p><button><a class="continue-shopping" href="${pageContext.request.contextPath}/home">Continue Shopping</a></button></p>
            </div>


            <!-- Include Footer -->
            <jsp:include page="Footer.jsp"></jsp:include>
        </div>
    </body>
</html>
