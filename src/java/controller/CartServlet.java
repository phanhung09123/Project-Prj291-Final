package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * CartServlet handles adding items to the shopping cart.
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        
        if (id != null && quantity != null) {
            // Read current cart data from cookie
            String cartData = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        cookie.setPath("/");
                        cartData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }
            
            // Update the cart data with the new item or updated quantity
            String newCartData = updateCartData(cartData, id, quantity);
            
            // Save updated cart data back to the cookie
            Cookie cartCookie = new Cookie("cart", URLEncoder.encode(newCartData, "UTF-8"));
            cartCookie.setPath("/");
            cartCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(cartCookie);
        }
        response.sendRedirect("cart.jsp");
    }

    /**
     * Updates the cart data stored in the cookie.
     * The cartData string is formatted as "id-quantity,id-quantity,...".
     *
     * @param cartData the current cart data
     * @param id the product id to add/update
     * @param quantity the quantity to add
     * @return the updated cart data string
     */
    private String updateCartData(String cartData, String id, String quantity) {
        StringBuilder newCart = new StringBuilder();
        boolean found = false;
        
        // If the cart is empty, return the new item
        if (cartData == null || cartData.trim().isEmpty()) {
            return id + "-" + quantity + ",";
        }
        
        String[] items = cartData.split(",");
        for (String item : items) {
            if (item.isEmpty()) continue;
            String[] parts = item.split("-");
            if (parts.length == 2) {
                String currentId = parts[0];
                int currentQuantity = Integer.parseInt(parts[1]);
                if (currentId.equals(id)) {
                    // Item exists, update the quantity
                    int newQuantity = currentQuantity + Integer.parseInt(quantity);
                    newCart.append(id).append("-").append(newQuantity).append(",");
                    found = true;
                } else {
                    // Keep the existing item unchanged
                    newCart.append(item).append(",");
                }
            }
        }
        
        // If the item was not found, add it as new
        if (!found) {
            newCart.append(id).append("-").append(quantity).append(",");
        }
        return newCart.toString();
    }

    // HttpServlet methods
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
        return "CartServlet handles updating shopping cart data.";
    }
}
