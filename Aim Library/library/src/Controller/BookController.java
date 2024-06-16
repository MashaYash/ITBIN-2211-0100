package Controller;

import Model.BookModel;
import View.BookManagement;
import javax.swing.JOptionPane;

public class BookController {

    private final BookManagement view;
    private final BookModel model;

    public BookController(BookManagement view) {
        this.view = view;
        this.model = new BookModel();
    }

    public void addBook(String bookNo, String author, String category, String availability) {
        model.addBook(bookNo, author, category, availability);
        view.table_update();
        JOptionPane.showMessageDialog(view, "Successfully Saved");
    }

    public void editBook(String bookNo, String author, String category, String availability) {
        model.editBook(bookNo, author, category, availability);
        view.table_update();
        JOptionPane.showMessageDialog(view, "Record Updated");
    }

    public void deleteBook(String bookNo) {
        model.deleteBook(bookNo);
        view.table_update();
        JOptionPane.showMessageDialog(view, "Record Deleted");
    }

    public void updateTable() {
        view.setTableData(model.getAllBooks());
    }

    public String getNextBookNo() {
        return model.getNextBookNo();
    }

}
