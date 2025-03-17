/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Wines;
import model.Customers;
import model.Accounts;
import dal.DBContext;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author HA DUC
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Wines> getAllWines() {
        List<Wines> list = new ArrayList<>();
        String sql = "select * from Wines";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wines(
                        rs.getInt("wine_id"), // wine_id as int
                        rs.getString("name"), // name as String
                        rs.getInt("category_id"), // category_id as int
                        rs.getString("country"), // country as String
                        rs.getInt("year"), // year as int
                        rs.getDouble("price"), // price as double
                        rs.getInt("stock_quantity"), // stock_quantity as int
                        rs.getString("image_url"), // image_url as String
                        rs.getString("description"), // description as String
                        rs.getInt("supplier_id") // supplier_id as int
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Wines> getWinesByCID(String cid) {
        List<Wines> list = new ArrayList<>();
        String sql = "SELECT * FROM Wines WHERE category_id = ?;";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cid);  // Set the parameter BEFORE executing the query.
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wines(
                        rs.getInt("wine_id"), // wine_id as int
                        rs.getString("name"), // name as String
                        rs.getInt("category_id"), // category_id as int
                        rs.getString("country"), // country as String
                        rs.getInt("year"), // year as int
                        rs.getDouble("price"), // price as double
                        rs.getInt("stock_quantity"), // stock_quantity as int
                        rs.getString("image_url"), // image_url as String
                        rs.getString("description"), // description as String
                        rs.getInt("supplier_id") // supplier_id as int
                ));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Wines getWinesByID(String id) {
        Wines wine = null;
        String sql = "SELECT wine_id, name, category_id, country, year, price, stock_quantity, image_url, description, supplier_id "
                + "FROM Wines WHERE wine_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            // Convert the String id to an int before setting the parameter
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            if (rs.next()) {
                wine = new Wines(
                        rs.getInt("wine_id"),
                        rs.getString("name"),
                        rs.getInt("category_id"),
                        rs.getString("country"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("image_url"),
                        rs.getString("description"),
                        rs.getInt("supplier_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException ne) {
            System.err.println("The provided wine_id is not a valid number: " + id);
            ne.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wine;
    }

    public List<Wines> getWinesByName(String txtSearch) {
        List<Wines> list = new ArrayList<>();
        String sql = "select * from Wines wi where wi.name like ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");  // Set the parameter BEFORE executing the query.
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wines(
                        rs.getInt("wine_id"), // wine_id as int
                        rs.getString("name"), // name as String
                        rs.getInt("category_id"), // category_id as int
                        rs.getString("country"), // country as String
                        rs.getInt("year"), // year as int
                        rs.getDouble("price"), // price as double
                        rs.getInt("stock_quantity"), // stock_quantity as int
                        rs.getString("image_url"), // image_url as String
                        rs.getString("description"), // description as String
                        rs.getInt("supplier_id") // supplier_id as int
                ));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int createOrder(Integer customerId, double totalPrice) {
        int orderId = -1;
        String sql = "INSERT INTO Orders (customer_id, total_price) VALUES (?, ?)";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (customerId != null) {
                ps.setInt(1, customerId);
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            ps.setDouble(2, totalPrice);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void addOrderDetail(int orderId, Integer customerId, int wineId, int quantity, double unitPrice) {
        String sql = "INSERT INTO OrderDetails (order_id, customer_id, wine_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            if (customerId != null) {
                ps.setInt(2, customerId);
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            ps.setInt(3, wineId);
            ps.setInt(4, quantity);
            ps.setDouble(5, unitPrice);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wines getLast() {
        String sql = "select top 1 * from Wines order by wine_id desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Wines(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10));
            }
        } catch (SQLException e) {

        }

        return null;
    }
    
    

    //Added by Hung Cuong
    public List<Wines> getWinesBySellID(int sid) {
        List<Wines> list = new ArrayList<>();
        String sql = "SELECT * FROM Wines WHERE wines_id = ?;";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, sid);  // Set the parameter BEFORE executing the query.
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wines(
                        rs.getInt("wine_id"), // wine_id as int
                        rs.getString("name"), // name as String
                        rs.getInt("category_id"), // category_id as int
                        rs.getString("country"), // country as String
                        rs.getInt("year"), // year as int
                        rs.getDouble("price"), // price as double
                        rs.getInt("stock_quantity"), // stock_quantity as int
                        rs.getString("image_url"), // image_url as String
                        rs.getString("description"), // description as String
                        rs.getInt("sid") // sell_id as int
                ));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteWines(int wid) {
        String query = "delete from Wines\n"
                + "where wine_id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, wid);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void insertWines(int wine_id, String name, String type, String country, int year, double price, String image_url, String description, int category_id, int sid) {
        String query = "INSERT INTO Wines \n"
                + "(wine_id, name, type, country, year, price, image_url, description, category_id, sid)\n"
                + "values (?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, wine_id);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, country);
            ps.setInt(5, year);
            ps.setDouble(6, price);
            ps.setString(7, image_url);
            ps.setString(8, description);
            ps.setInt(9, category_id);
            ps.setInt(10, sid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editWines(String name, String type, String country, int year, int price, String image_url, String description, int category_id, int wid) {
        String query = "update Wines\n"
                + "set [name] = ?,\n"
                + "[type],\n"
                + "[country],\n"
                + "[year],\n"
                + "[price],\n"
                + "image_url,\n"
                + "[description],\n"
                + "category_id\n"
                + "where wines_id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, country);
            ps.setInt(4, year);
            ps.setInt(4, price);
            ps.setString(5, image_url);
            ps.setString(5, description);
            ps.setInt(6, category_id);
            ps.setInt(7, wid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
//Added by HungCuong
    

    public Accounts login(String username, String password) {
        String query = "select * from Accounts\n"
                + "where email = ?\n"
                + "and password_hash = ?";
        try {
            System.out.println("Connecting to database for login...");
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println("Executing query: " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Login successful for username: " + username);
                return new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5));
            }
            System.out.println("Login failed for username: " + username);
        } catch (SQLException e) {
            System.out.println("SQL Exception during login: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Handle closing errors
                ex.printStackTrace();
            }
        }
        return null;
    }

    public Accounts checkAccountExists(String username) {
        String query = "select * from Accounts\n"
                + "where email = ?";
        try {
            System.out.println("Checking if account exists for username: " + username);
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Account found for username: " + username);
                return new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5));
            }
            System.out.println("No account found for username: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Handle closing errors
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void register(String username, String password, String telephone) {
        String Accquery = "INSERT INTO Accounts (email, password_hash, phone, created_at)\n"
                + "VALUES (?, ?, ?, GETDATE());";
        String Cusquery = "INSERT INTO Customers (email, password_hash, phone, created_at)\n"
                + "VALUES (?, ?, ?, GETDATE());";
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            System.out.println("Connecting to database...");
            conn = DBContext.getConnection();
            ps1 = conn.prepareStatement(Accquery);
            ps2 = conn.prepareStatement(Cusquery);
            ps1.setString(1, username);
            ps1.setString(2, password);
            ps1.setString(3, telephone);
            ps2.setString(1, username);
            ps2.setString(2, password);
            ps2.setString(3, telephone);
            System.out.println("Executing query: " + ps);
            ps1.executeUpdate();
            ps2.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps1 != null) {
                    ps1.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Handle closing errors
                ex.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//        DAO dao = new DAO();
//        List<Wines> wineList = dao.getWinesByCID("1");
//        for (Wines wines : wineList) {
//            System.out.println(wines);
//        }
//    }
}
