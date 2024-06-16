package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnModel {

    private Connection con;

    public ReturnModel() {
        con = DBConnection.getConnection();
    }

    public void returnBook(String bookID, String studentID, String returnDate, String elapsed, String fine) throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO returnbook (BookID, StudentID, returnDate, elapse, fine) VALUES (?, ?, ?, ?, ?)");
        pst.setString(1, bookID);
        pst.setString(2, studentID);
        pst.setString(3, returnDate);
        pst.setString(4, elapsed);
        pst.setString(5, fine);
        pst.executeUpdate();

        pst = con.prepareStatement("UPDATE books SET availability = 'Yes' WHERE BookNo = ?");
        pst.setString(1, bookID);
        pst.executeUpdate();

        pst = con.prepareStatement("DELETE FROM borrow WHERE BookID = ?");
        pst.setString(1, bookID);
        pst.executeUpdate();
    }

    public Vector<String> getAllBooks() throws SQLException {
        Vector<String> books = new Vector<>();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM books");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            books.add(rs.getString(1));
        }
        return books;
    }

    public Vector<String> getAllStudents() throws SQLException {
        Vector<String> students = new Vector<>();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM student");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            students.add(rs.getString(2));
        }
        return students;
    }

    public Vector<Vector<String>> loadReturnBookData() throws SQLException {
        Vector<Vector<String>> data = new Vector<>();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM returnbook");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Vector<String> row = new Vector<>();
            row.add(rs.getString("BookID"));
            row.add(rs.getString("StudentID"));
            row.add(rs.getString("returnDate"));
            row.add(rs.getString("elapse"));
            row.add(rs.getString("fine"));
            data.add(row);
        }
        return data;
    }
}
