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

public class Register_page extends AppCompatActivity {

    Button Login;
    EditText passwordinput, emailinput , repassinput;
    DatabaseHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        Login = findViewById(R.id.reg);
        TextView back = findViewById(R.id.back);
        emailinput = findViewById(R.id.emailinput);
        passwordinput = findViewById(R.id.passwordinput);
        repassinput = findViewById(R.id.repassinput);
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
                Intent intent = new Intent(Register_page.this,MainActivity.class);
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
            String loginrepass = repassinput.getText().toString();

            if(loginpassword.length()==0){
                Toast.makeText(Register_page.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
            }
            else {
                if (loginpassword.equals(loginrepass)) {
                    Toast.makeText(this, "mailID and password validated", Toast.LENGTH_SHORT).show();

                    Boolean checkuseremail = DatabaseHelper.checkmail(loginemail);

                    if (checkuseremail == false) {
                        Boolean insert = Db.addContact(loginemail, loginpassword);

                        if (insert == true){
                            Toast.makeText(Register_page.this,"Signup Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Register_page.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Register_page.this,"User already existed, Please login", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Confirm password and password should be same", Toast.LENGTH_SHORT).show();
                }
            }

            return true;
        }
        else {
            Toast.makeText(this,"Enter Valid Email Id !",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    
}