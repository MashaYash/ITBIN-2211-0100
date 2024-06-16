package Controller;

import Model.StudentModel;
import View.Login;
import View.Student;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class StudentController {
    
    private Student view;
    private StudentModel model;

    public StudentController(Student view) {
        this.view = view;
        this.model = new StudentModel();
    }
    
    public void addStudent(String id, String name, String address, String phone, String password) {
        try {
            model.addStudent(id, name, address, phone, password);
            JOptionPane.showMessageDialog(view, "Successfully Saved");
            view.clearFields(); 
            tableUpdate();
            new Login().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(String id, String name, String address, String phone) {
        try {
            model.updateStudent(id, name, address, phone);
            JOptionPane.showMessageDialog(view, "Successfully Updated");
            view.clearFields();
            tableUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(String id) {
        try {
            model.deleteStudent(id);
            JOptionPane.showMessageDialog(view, "Successfully Deleted");
            view.clearFields();
            tableUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void autoID() {
        try {
            String nextID = model.getNextStudentID();
            view.setStudentID(nextID);
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tableUpdate() {
        try {
            view.updateTable(model.getAllStudents());
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
