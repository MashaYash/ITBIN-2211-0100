package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentModel {

    private Connection con;

    public StudentModel() {
        con = DBConnection.getConnection();
    }

    public void addStudent(String id, String name, String address, String phone, String password) throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO student (StudentID, Sname, Address, PhoneNumber, Password) VALUES (?, ?, ?, ?,?)");
        pst.setString(1, id);
        pst.setString(2, name);
        pst.setString(3, address);
        pst.setString(4, phone);
        pst.setString(5, password);
        pst.executeUpdate();
    }

    public void updateStudent(String id, String name, String address, String phone) throws SQLException {
        PreparedStatement pst = con.prepareStatement("UPDATE student SET Sname = ?, Address = ?, PhoneNumber = ? WHERE StudentID = ?");
        pst.setString(1, name);
        pst.setString(2, address);
        pst.setString(3, phone);
        pst.setString(4, id);
        pst.executeUpdate();
    }

    public void deleteStudent(String id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("DELETE FROM student WHERE StudentID = ?");
        pst.setString(1, id);
        pst.executeUpdate();
    }

    public Vector<Vector<String>> getAllStudents() throws SQLException {
        Vector<Vector<String>> data = new Vector<>();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM student");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Vector<String> row = new Vector<>();
            row.add(rs.getString("StudentID"));
            row.add(rs.getString("Sname"));
            row.add(rs.getString("Address"));
            row.add(rs.getString("PhoneNumber"));
            data.add(row);
        }
        return data;
    }

    public String getNextStudentID() throws SQLException {
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(StudentID) FROM student");
        rs.next();
        String maxID = rs.getString("MAX(StudentID)");
        if (maxID == null) {
            return "S0001";
        } else {
            long id = Long.parseLong(maxID.substring(2)) + 1;
            return "S0" + String.format("%03d", id);
        }
    }
}
