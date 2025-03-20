package controller;
import dal.DAO;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderItem;
import model.Product;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();

        // Retrieve the cart cookie (format: "wineId-quantity,wineId-quantity,...")
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

        // If the cart is empty, redirect back to cart.jsp.
        if (cartData == null || cartData.trim().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        // Retrieve customer ID from session (if logged in) or remain null for guest checkout.
        Integer customerId = (Integer) request.getSession().getAttribute("customerId");

        // Parse the cart data into a list of OrderItem objects and compute the total price using double.
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;
        String[] items = cartData.split(",");
        for (String item : items) {
            if (item != null && !item.trim().isEmpty()) {
                // Each item is expected to be in the format "wineId-quantity"
                String[] parts = item.split("-");
                if (parts.length == 2) {
                    try {
                        int wineId = Integer.parseInt(parts[0].trim());
                        int quantity = Integer.parseInt(parts[1].trim());

                        // Retrieve product details from the database.
                        Product p = dao.getProduct(wineId);
                        if (p != null && quantity > 0) {
                            double unitPrice = p.getPrice().doubleValue();
                            totalPrice += unitPrice * quantity;

                            // Add this item to our order items list.
                            orderItems.add(new OrderItem(wineId, quantity, unitPrice));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid cart item format: " + item);
                    }
                } else {
                    System.err.println("Invalid cart item format: " + item);
                }
            }
        }

        // Create a new order in the Orders table.
        int orderId = dao.createOrder(customerId, totalPrice);

        // Insert each order item into the OrderDetails table (including customerId).
        for (OrderItem oi : orderItems) {
            //dao.addOrderDetail(orderId, customerId, oi.getWineId(), oi.getQuantity(), oi.getUnitPrice());
            dao.addOrderDetail(orderId, customerId, oi.getWineId(), oi.getQuantity(), oi.getUnitPrice());
        }
        
        

        // Clear the cart cookie.
        Cookie clearCart = new Cookie("cart", "");
        clearCart.setMaxAge(0);
        clearCart.setPath("/");
        response.addCookie(clearCart);

        // Redirect to an order confirmation page.
        request.setAttribute("orderId", orderId);
        request.setAttribute("totalPrice", totalPrice);  // totalPrice as a double
        response.sendRedirect(request.getContextPath() + "/checkout.jsp?orderId=" + orderId + "&totalPrice=" + totalPrice);

    }
}
