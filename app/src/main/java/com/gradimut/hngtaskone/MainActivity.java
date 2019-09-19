package com.gradimut.hngtaskone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);


        Button logout = findViewById(R.id.log_out);
        final String name = sharedPreferences.getString("NAME","DEFAULT_NAME");
        final String email = sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");


        TextView welcomeText = findViewById(R.id.welcom_tv);
        TextView mail = findViewById(R.id.mail);
        if (name.equals("")) {
            welcomeText.setText("Welcome, Please click on ");
        } else {
            welcomeText.setText("Welcome " + name);
            mail.setText("Your Email : " + email);
        }


        // Log out current user
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("isLoggedIn",false).apply();
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
                finish();
            }
        });
    }

}
