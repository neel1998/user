package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button go;
    ProgressBar load2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        load2=(ProgressBar)findViewById(R.id.load2);
        final EditText user=(EditText)findViewById(R.id.lun);
        final EditText pswd=(EditText)findViewById(R.id.lp);
        go=(Button)findViewById(R.id.loginbtn);
        load2.setVisibility(View.GONE);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            load2.setVisibility(View.VISIBLE);
                final String username=user.getText().toString();
                final String password=pswd.getText().toString();
                Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success){
                                Intent i=new Intent(LoginActivity.this,UserHome.class);
                                i.putExtra("name",jsonObject.getString("name"));
                                i.putExtra("id",jsonObject.getString("id"));
                                startActivity(i);
                                Toast.makeText(LoginActivity.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                                load2.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                load2.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            load2.setVisibility(View.GONE);
                        }
                    }
                };
                LoginRequest loginRequest=new LoginRequest(username,password,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }

        });
    }
}
