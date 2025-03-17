package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String productId = request.getParameter("id");
            String action = request.getParameter("action");
            if (productId != null && action != null) {
                // Read current cart data from cookie
                String cartData = "";
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("cart".equals(cookie.getName())) {
                            cartData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                        }
                    }
                }
                // Split items (each in format "id-quantity")
                String[] items = cartData.split(",");
                StringBuilder newCartData = new StringBuilder();
                boolean productExist = false;

                for (String item : items) {
                    if (!item.isEmpty()) {
                        // Split on hyphen ("-") instead of comma
                        String[] parts = item.split("-");
                        if (parts.length == 2) {
                            int id = Integer.parseInt(parts[0]);
                            int quantity = Integer.parseInt(parts[1]);
                            if (id == Integer.parseInt(productId)) {
                                if ("increase".equals(action)) {
                                    quantity++;
                                } else if ("decrease".equals(action) && quantity > 1) {
                                    quantity--;
                                }
                                productExist = true;
                            }
                            newCartData.append(id).append("-").append(quantity).append(",");
                        }
                    }
                }
                // If product was not found and action is "increase", add it as new
                if (!productExist && "increase".equals(action)) {
                    newCartData.append(productId).append("-1,");
                }
                // Remove the old cart cookie
                Cookie oldCart = new Cookie("cart", "");
                oldCart.setMaxAge(0);
                oldCart.setPath("/");
                response.addCookie(oldCart);

                // Create and add the updated cart cookie
                Cookie updatedCart = new Cookie("cart", URLEncoder.encode(newCartData.toString(), "UTF-8"));
                updatedCart.setMaxAge(60 * 60 * 24 * 7); // 7 days
                updatedCart.setPath("/");
                response.addCookie(updatedCart);

                System.out.println("Updated cart data: " + newCartData.toString()); // Debug
            }
            response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "UpdateCartServlet updates the quantity of cart items.";
    }
}
