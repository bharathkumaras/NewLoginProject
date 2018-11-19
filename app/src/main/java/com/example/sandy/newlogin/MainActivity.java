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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button send;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;

    String gMail = null;
    String gPassword = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        send = findViewById(R.id.send);

        mAuth = FirebaseAuth.getInstance();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validatelogin();


            }
        });
    }

    public void Validatelogin()
    {
        String sEmail =  email.getText().toString();
        String sPassword = password.getText().toString();

        if (TextUtils.isEmpty(sEmail) || TextUtils.isEmpty(sPassword))
        {
            Toast.makeText(MainActivity.this, "Fields Not Empty", Toast.LENGTH_SHORT).show();
        }
       else
        {
            if (sEmail.matches(emailPattern))
            {
                if (sPassword.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else

                {
                    if (sPassword.length()>5)
                    {
                        gMail = sEmail;
                        gPassword = sPassword;
                        startsignin();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password Must Be greater Than 5 Letters", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else
            {
                Toast.makeText(MainActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void startsignin()
    {


            mAuth.signInWithEmailAndPassword(gMail, gPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
}
