package com.example.sandy.newlogin.Presenter;

import com.example.sandy.newlogin.Model.User;
import com.example.sandy.newlogin.View.ILoginView;

/**
 * Created by sandy on 20-Nov-18.
 */

public class Loginpresenter implements ILoginpresenter {

    ILoginView loginView;
    public Loginpresenter(ILoginView loginView)
    {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {

        User user = new User(email,password);
        boolean isloginstatus = user.validate();
        if (isloginstatus)
        {
            loginView.onLoginResult("Login Success");
        }
        else
        {
            loginView.onLoginResult("Login Fail");
        }

    }
}
