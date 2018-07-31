package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button register,login;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        loading=(ProgressBar)findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
                loading.setVisibility(View.GONE);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                loading.setVisibility(View.GONE);
            }
        });
    }
}
