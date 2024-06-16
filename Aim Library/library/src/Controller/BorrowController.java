package Controller;

import Model.BorrowModel;
import View.Borrow;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BorrowController {
    private final Borrow view;
    private final BorrowModel model;

    public BorrowController(Borrow view) {
        this.view = view;
        this.model = new BorrowModel();
    }

    public void borrowBook() {
        try {
            String bookID = view.getComboBookID().getSelectedItem().toString();
            String studentID = view.getComboBoxStudent().getSelectedItem().toString();
            String fee = view.getTxtfee().getText();
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            String date = Date_Format.format(view.getTxtdate().getDate());
            String dueDate = Date_Format.format(view.getTxtdue().getDate());

            model.borrowBook(bookID, studentID, fee, date, dueDate);

            JOptionPane.showMessageDialog(view, "Successfully Saved");
            
            view.getTxtSname().setText("");
            view.getTxtfee().setText("");

        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void comboBookIDActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String bookID = view.getComboBookID().getSelectedItem().toString();
            String availability = model.getBookAvailability(bookID);
            view.getTextAval().setText(availability);

            if ("Yes".equals(availability)) {
                view.getTxtSname().setEnabled(true);
                view.getTxtfee().setEnabled(true);
                view.getTxtdate().setEnabled(true);
                view.getTxtdue().setEnabled(true);
            } else {
                view.getTxtSname().setEnabled(false);
                view.getTxtfee().setEnabled(false);
                view.getTxtdate().setEnabled(false);
                view.getTxtdue().setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ComboBoxStudentActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String studentID = view.getComboBoxStudent().getSelectedItem().toString();
            String studentName = model.getStudentName(studentID);
            view.getTxtSname().setText(studentName);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadBooksAndStudents() {
        try {
            view.setBooks(model.getAllBooks());
            view.setStudents(model.getAllStudents());
        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
