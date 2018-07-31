package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    ProgressBar load3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        load3=(ProgressBar) findViewById(R.id.load3);
        load3.setVisibility(View.GONE);
        final EditText ename=(EditText)findViewById(R.id.name);
        final EditText eusername=(EditText)findViewById(R.id.username);
        final EditText eage=(EditText)findViewById(R.id.age);
        final EditText epassword=(EditText)findViewById(R.id.password);
        final Button register=(Button)findViewById(R.id.registerForm);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load3.setVisibility(View.VISIBLE);
                final String name=ename.getText().toString();
                final String username=eusername.getText().toString();
                final Integer age=Integer.parseInt(eage.getText().toString());
                final String password=epassword.getText().toString();
                final Response.Listener<String> responseListener=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.d("respose",""+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            boolean taken=jsonObject.getBoolean("taken");
                            if(success)
                            {
                                if(taken){
                                    Toast.makeText(RegisterActivity.this, "Username already taken...", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Registerd Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(i);
                                }
                                    load3.setVisibility(View.GONE);

                            }
                            else
                            {
                                Log.d("","unsuccessful response");
                                Toast.makeText(RegisterActivity.this,"Registerd Unsccessfully",Toast.LENGTH_SHORT).show();
                                load3.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            Log.d("",""+e);
                            Toast.makeText(RegisterActivity.this,"Registerd Unsccessfully",Toast.LENGTH_SHORT).show();
                            load3.setVisibility(View.GONE);
                        }

                    }
                };

                RegisterRequest registerRequest=new RegisterRequest(name,username,age,password,responseListener);
                RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }
}
