package com.gradimut.hngtaskone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);

        Button logout = findViewById(R.id.log_out);


        // Log out current user
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("isLoggedIn",false).apply();
                startActivity(login);
                finish();
            }
        });
    }


    /*
     * Whe application activity start, check if user is logged in
     * */

    @Override
    protected void onStart() {
        super.onStart();

        final SharedPreferences sharedPreferences = getSharedPreferences(
                "USER_CREDENTIALS",
                MODE_PRIVATE);
        final boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        Intent main = new Intent(MainActivity.this, login.class);
        startActivity(main);
    }
}
