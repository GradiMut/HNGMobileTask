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

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email_field= findViewById(R.id.emailInput); //
        final EditText password_field= findViewById(R.id.passwordInput); //

        Button logBtn = findViewById(R.id.loginBtn); //

        TextView regTV = findViewById(R.id.reg); //

        final SharedPreferences sharedPreferences = getSharedPreferences(
                "USER_CREDENTIALS",
                MODE_PRIVATE);
        final boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        final String required_email= sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
        final String required_password= sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");

        // check if user is logged in & redirect user to the main page

        if (isLoggedIn) {
            Intent main = new Intent(login.this, MainActivity.class);
            startActivity(main);
        }

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get text from the EditText
                String email= email_field.getText().toString();
                String password= password_field.getText().toString();

                /*
                * Check if user input exist (on sharedPreference)
                * if true, send user to the mainActivity
                * else add user to the sharedPreference
                * */

                if (email.equals(required_email) && password.equals(required_password)) {
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
                    Intent main = new Intent(login.this, MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(
                            login.this,
                            "User Not found So we decided to register you directly with this credential ",
                            Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("EMAIL", email);
                    editor.putString("PASSWORD", password);
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply();
                    Intent main = new Intent(login.this, MainActivity.class);
                    startActivity(main);

                }


            }
        });
    }
}
