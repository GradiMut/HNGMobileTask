package com.gradimut.hngtaskone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name_field = findViewById(R.id.usernameInput);
        final EditText email_field = findViewById(R.id.regEmailInput);
        final EditText password_field = findViewById(R.id.regPasswordInput);
        final EditText confirm_password_field = findViewById(R.id.confirmPasswordInput);
        Button registerbutton = findViewById(R.id.regBtn);
        TextView login = findViewById(R.id.log);


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_field.getText().toString();
                String email_id = email_field.getText().toString();
                String password_1 = password_field.getText().toString();
                String password_2 = confirm_password_field.getText().toString();

                if(password_1.equals(password_2))
                {
                    // code to register the user like an entry in the database
                    SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("NAME",name);
                    editor.putString("EMAIL",email_id);
                    editor.putString("PASSWORD",password_1);
                    editor.putBoolean("ISLOGGEDIN",true);
                    editor.apply();

                    // Start a new activity
                    Intent main = new Intent(register.this, MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(register.this,"Passwords don't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
