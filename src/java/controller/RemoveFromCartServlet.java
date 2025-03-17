package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/removefromcart"})
public class RemoveFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve the product id parameter from the request.
        String productIdStr = request.getParameter("id");
        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);
            String cartData = "";
            // Read the existing cart data from the cookie.
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        cartData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }

            // Rebuild the cart string without the product being removed.
            StringBuilder newCartData = new StringBuilder();
            String[] items = cartData.split(",");
            for (String item : items) {
                if (!item.isEmpty()) {
                    // Each item is in the format "id-quantity"
                    String[] parts = item.split("-");
                    if (parts.length == 2) {
                        int id = Integer.parseInt(parts[0]);
                        // Append items that are not the one to remove.
                        if (id != productId) {
                            newCartData.append(id).append("-").append(parts[1]).append(",");
                        }
                    }
                }
            }
            
            // Update the cart cookie with the new cart data.
            Cookie updatedCart = new Cookie("cart", URLEncoder.encode(newCartData.toString(), "UTF-8"));
            updatedCart.setMaxAge(60 * 60 * 24 * 7); // 7 days
            updatedCart.setPath("/");
            response.addCookie(updatedCart);
        }
        // Redirect back to the cart page.
        response.sendRedirect("cart.jsp");
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
        return "Servlet that removes a product from the shopping cart.";
    }
}
