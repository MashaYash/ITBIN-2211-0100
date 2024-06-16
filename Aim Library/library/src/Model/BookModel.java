package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookModel {

    private Connection con;
    private PreparedStatement insert;

    public BookModel() {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aimlibrary", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBook(String bookNo, String author, String category, String availability) {
        try {
            insert = con.prepareStatement("INSERT INTO books (BookNo, Author, Category, Availability) VALUES (?, ?, ?, ?)");
            insert.setString(1, bookNo);
            insert.setString(2, author);
            insert.setString(3, category);
            insert.setString(4, availability);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editBook(String bookNo, String author, String category, String availability) {
        try {
            insert = con.prepareStatement("UPDATE books SET Author = ?, Category = ?, Availability = ? WHERE BookNo = ?");
            insert.setString(1, author);
            insert.setString(2, category);
            insert.setString(3, availability);
            insert.setString(4, bookNo);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteBook(String bookNo) {
        try {
            insert = con.prepareStatement("DELETE FROM books WHERE BookNo = ?");
            insert.setString(1, bookNo);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Vector<String>> getAllBooks() {
        Vector<Vector<String>> data = new Vector<>();
        try {
                        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("BookNo"));
                row.add(rs.getString("Author"));
                row.add(rs.getString("Category"));
                row.add(rs.getString("Availability"));
                data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public String getNextBookNo() {
        String nextBookNo = "B0001";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(BookNo) FROM books");
            if (rs.next() && rs.getString(1) != null) {
                long id = Long.parseLong(rs.getString(1).substring(2));
                id++;
                nextBookNo = "B0" + String.format("%03d", id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextBookNo;
    }
}

