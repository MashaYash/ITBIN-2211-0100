package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowModel {

    private Connection con;
    private PreparedStatement pst;

    public BorrowModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aimlibrary", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrowBook(String bookID, String studentID, String fee, String date, String dueDate) throws SQLException {
        try {
            pst = con.prepareStatement("INSERT INTO borrow(BookID, StudentID, fee, Date, DueDate) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, bookID);
            pst.setString(2, studentID);
            pst.setString(3, fee);
            pst.setString(4, date);
            pst.setString(5, dueDate);
            pst.executeUpdate();

            pst = con.prepareStatement("UPDATE books SET Availability = 'No' WHERE BookNo = ?");
            pst.setString(1, bookID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Vector<String> getAllBooks() throws SQLException {
        Vector<String> books = new Vector<>();
        try {
            pst = con.prepareStatement("SELECT * FROM books");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                books.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return books;
    }

    public Vector<String> getAllStudents() throws SQLException {
        Vector<String> students = new Vector<>();
        try {
            pst = con.prepareStatement("SELECT * FROM student");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                students.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return students;
    }

    public String getBookAvailability(String bookID) throws SQLException {
        try {
            pst = con.prepareStatement("SELECT Availability FROM books WHERE BookNo = ?");
            pst.setString(1, bookID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(1).trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return null;
    }

    public String getStudentName(String studentID) throws SQLException {
        try {
            pst = con.prepareStatement("SELECT Sname FROM student WHERE StudentID = ?");
            pst.setString(1, studentID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(1).trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return null;
    }
}
