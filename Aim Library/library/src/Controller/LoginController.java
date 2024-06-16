package Controller;

import Model.LoginModel;
import Model.StudentModel;

public class LoginController {
    
    private LoginModel model;
    
    public LoginController() {
        model = new LoginModel();
    }
    
    public boolean authenticate(String username, String password) {
        return model.authenticate(username, password);
    }
}
