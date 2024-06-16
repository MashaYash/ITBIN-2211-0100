package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    
    private Connection conn;
    
    public LoginModel() {
        conn = DBConnection.getConnection();
    }
    
    public boolean authenticate(String username, String password) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM student WHERE Sname = ? AND Password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // If there's at least one row, authentication is successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
