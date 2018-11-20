package com.example.sandy.newlogin.Model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by sandy on 20-Nov-18.
 */

public class User implements IUser{

    String email,password;

    public User(String email,String password)
    {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getemail() {

        return email;

    }

    @Override
    public String getpassword() {
        return password;

    }

    @Override
    public boolean validate() {

        return !TextUtils.isEmpty(getemail()) && Patterns.EMAIL_ADDRESS.matcher(getemail()).matches()
                && getpassword().length()>6;
    }
}
