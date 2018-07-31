package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleQuestion extends AppCompatActivity {

    ProgressBar load5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);
        load5=(ProgressBar)findViewById(R.id.load5);
        load5.setVisibility(View.GONE);
        Intent i=getIntent();
        final String id=i.getStringExtra("id");
        final String question=i.getStringExtra("question");
        final String date=i.getStringExtra("date");
        final String prev_response=i.getStringExtra("response");
        final String name=i.getStringExtra("name");
        final String uid=i.getStringExtra("uid");
        final String ans=i.getStringExtra("ans");
        TextView hq=(TextView)findViewById(R.id.head);
        TextView hd=(TextView)findViewById(R.id.head_date);
        hq.setText(question);
        hd.setText(date);
        final Button yes=(Button) findViewById(R.id.yes);
        final Button no=(Button)findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load5.setVisibility(View.VISIBLE);
                final Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("",""+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(SingleQuestion.this,"Response Recorded",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SingleQuestion.this,AllQuestions.class);
                                i.putExtra("response",prev_response);
                                i.putExtra("name",name);
                                i.putExtra("uid",uid);
                                startActivity(i);
                                load5.setVisibility(View.GONE);
                            }
                            else{
                                load5.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            load5.setVisibility(View.GONE);
                        }
                    }
                };
                YesRequest yesRequest=new YesRequest(id,uid,ans,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(SingleQuestion.this);
                requestQueue.add(yesRequest);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load5.setVisibility(View.VISIBLE);
                final Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("",""+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(SingleQuestion.this,"Response Recorded",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SingleQuestion.this,AllQuestions.class);
                                i.putExtra("response",prev_response);
                                i.putExtra("name",name);
                                i.putExtra("uid",uid);
                                startActivity(i);
                                load5.setVisibility(View.GONE);
                            }
                            else{
                                load5.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            load5.setVisibility(View.GONE);
                        }
                    }
                };
                NoRequest noRequest=new NoRequest(id,uid,ans,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(SingleQuestion.this);
                requestQueue.add(noRequest);
            }
        });
    }
}
