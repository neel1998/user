package com.example.customgraph.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllQuestions extends AppCompatActivity {


    String ans;
    Boolean canClick;
    ProgressBar load6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_questions);

        load6=(ProgressBar)findViewById(R.id.load6);
        load6.setVisibility(View.GONE);
        Button home=(Button)findViewById(R.id.home);
        Intent i=getIntent();
        ListView list=(ListView)findViewById(R.id.txt);
        final String prev_response=i.getStringExtra("response");
        final String name=i.getStringExtra("name");
        final String uid=i.getStringExtra("uid");
        ArrayList<QuestionData> questions=new ArrayList<>();
        try {
            final JSONArray jsonArray=new JSONArray(prev_response);
            for(int j=0;j<jsonArray.length();j++){
                JSONObject jsonObject=jsonArray.getJSONObject(j);
                String question=jsonObject.getString("question");
                String date=jsonObject.getString("date");
                String id=jsonObject.getString("question_id");
                questions.add(new QuestionData(question,date,id));
            }
            QuestionAdapter questionAdapter=new QuestionAdapter(AllQuestions.this,questions);
            list.setAdapter(questionAdapter);
            Log.d("",""+jsonArray);
        } catch (JSONException e) {
            Log.d("",""+e);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int position, long id) {

                load6.setVisibility(View.VISIBLE);
                final QuestionData current=(QuestionData) adapterView.getItemAtPosition(position);
                final String temp_id=current.getid();
                final String temp_que=current.getQuestion();
                final String temp_date=current.getmDate();
                Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("",""+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success) {
                                ans = jsonObject.getString("ans");
                                String tans=ans.replace("y","");
                                tans=tans.replace("n","");
                                Log.d("",""+ans);
                                String[] array=tans.split("\\+");
                                Log.d("",""+array);
                                int flag=0;
//                                if(ans!=null){
                                    for(String id: array){
                                        if(temp_id.equals(id)){
                                            flag=1;
                                            break;
                                        }
                                    }
                                    if(flag==0){
                                        canClick=true;
                                    }
                                    else{
                                        canClick=false;
                                    }
//                                }
//                                else
//                                {
//                                    canClick=true;
//                                }
                            }
                            else{

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(canClick)
                        {
                            Intent i=new Intent(AllQuestions.this,SingleQuestion.class);
                            i.putExtra("id",temp_id);
                            i.putExtra("question",temp_que);
                            i.putExtra("date",temp_date);
                            i.putExtra("response",prev_response);
                            i.putExtra("name",name);
                            i.putExtra("uid",uid);
                            i.putExtra("ans",ans);
                            load6.setVisibility(View.GONE);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(AllQuestions.this,"You can Answer a question only once",Toast.LENGTH_SHORT).show();
                            load6.setVisibility(View.GONE);
                        }
                    }

                };

                CheckRequest checkRequest=new CheckRequest(uid,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(AllQuestions.this);
                requestQueue.add(checkRequest);


        }});
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AllQuestions.this,UserHome.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
}
