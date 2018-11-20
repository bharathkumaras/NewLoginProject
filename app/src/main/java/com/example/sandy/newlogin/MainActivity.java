package com.example.sandy.newlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sandy.newlogin.Presenter.ILoginpresenter;
import com.example.sandy.newlogin.Presenter.Loginpresenter;
import com.example.sandy.newlogin.View.ILoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ILoginView {

    EditText email,password;
    Button send;


    private FirebaseAuth mAuth;

    String gMail = null;
    String gPassword = null;
    ILoginpresenter loginpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        send = findViewById(R.id.send);

        mAuth = FirebaseAuth.getInstance();
        loginpresenter = new Loginpresenter((ILoginView) MainActivity.this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             loginpresenter.onLogin(email.getText().toString(),password.getText().toString());
                if (password.getText().toString().length()>6)
                {
                    gMail = email.getText().toString();
                    gPassword = password.getText().toString();
                    startsignin();
                }

            }
        });
    }

    public void startsignin()
    {

        mAuth.signInWithEmailAndPassword(gMail,gPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "signinproblem", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent n = new Intent(MainActivity.this,Welcome.class);
                    startActivity(n);
                }

            }
        });
    }

    @Override
    public void onLoginResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
