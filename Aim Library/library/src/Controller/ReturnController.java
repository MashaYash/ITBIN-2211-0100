package Controller;

import Model.ReturnModel;
import View.Return;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ReturnController {

    private final ReturnModel model;

    public ReturnController() {
        model = new ReturnModel();
    }

    public void returnBook(Return returnView, JComboBox<String> ComboBoxBook, JComboBox<String> ComboBoxStudentID, javax.swing.JTextField txtelp, javax.swing.JTextField txtfine, com.toedter.calendar.JDateChooser txtdue) {
        try {
            String bookID = ComboBoxBook.getSelectedItem().toString();
            String studentID = ComboBoxStudentID.getSelectedItem().toString();

            String returnDate = new SimpleDateFormat("yyyy-MM-dd").format(txtdue.getDate());

            String elapsed = txtelp.getText();
            String fine = txtfine.getText();

            model.returnBook(bookID, studentID, returnDate, elapsed, fine);

            JOptionPane.showMessageDialog(null, "Update Saved");
            returnView.table_update();

        } catch (SQLException ex) {
            Logger.getLogger(ReturnController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(returnView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadBooksAndStudents(Return returnView) {
        try {
            returnView.setBooks(model.getAllBooks());
            returnView.setStudents(model.getAllStudents());
        } catch (SQLException ex) {
            Logger.getLogger(ReturnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Vector<String>> loadReturnBookData() throws SQLException {
        return model.loadReturnBookData();
    }
}
