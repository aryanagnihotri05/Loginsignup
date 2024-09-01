package com.example.nukhba_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    Button Login;
    EditText passwordinput;
    EditText emailinput;
    DatabaseHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        Login = findViewById(R.id.reg);
        TextView back = findViewById(R.id.back);
        emailinput = findViewById(R.id.emailinput);
        passwordinput = findViewById(R.id.passwordinput);
        Db = new DatabaseHelper(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailValidator(emailinput);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean emailValidator(EditText emailinput){
        String emailtoText = emailinput.getText().toString();

        if (!emailtoText.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(emailtoText).matches()){
//            Toast.makeText(this,"Email Verified !",Toast.LENGTH_SHORT).show();
            String loginemail = emailinput.getText().toString();
            String loginpassword = passwordinput.getText().toString();

            if(loginpassword.length()==0){
                Toast.makeText(LoginPage.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "mailID and password validated" , Toast.LENGTH_SHORT).show();
                Db.addContact(loginemail,loginpassword);
                    }

            return true;
        }
        else {
            Toast.makeText(this,"Enter Valid Email Id !",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}