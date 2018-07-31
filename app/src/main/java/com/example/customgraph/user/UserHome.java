package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class UserHome extends AppCompatActivity {

    TextView textView;
    ProgressBar load4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        load4=(ProgressBar) findViewById(R.id.load4);
        load4.setVisibility(View.GONE);
        textView=(TextView)findViewById(R.id.text);
        Intent i=getIntent();
        final String name=i.getStringExtra("name");
        final String uid=i.getStringExtra("id");
        textView.setText(name);
        Button sq=(Button) findViewById(R.id.sq);
        sq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load4.setVisibility(View.VISIBLE);
                final Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent i=new Intent(UserHome.this,AllQuestions.class);
                        i.putExtra("response",response);
                        i.putExtra("name",name);
                        i.putExtra("uid",uid);

                        startActivity(i);
                    load4.setVisibility(View.GONE);
                    }
                };
                FetchQuestionsRequest fetchQuestionsRequest=new FetchQuestionsRequest(listener);
                RequestQueue requestQueue= Volley.newRequestQueue(UserHome.this);
                requestQueue.add(fetchQuestionsRequest);
            }
        });
        Button lo=(Button) findViewById(R.id.logout);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserHome.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(UserHome.this,"Logged Out Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
