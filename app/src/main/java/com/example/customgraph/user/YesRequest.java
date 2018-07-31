package com.example.customgraph.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 04-06-2018.
 */

public class YesRequest extends StringRequest{
    private static final String REQUEST_URL="https://neeltrivedi.000webhostapp.com/yes.php";
    private Map<String,String> params;
    public YesRequest(String id,String uid,String ans, Response.Listener<String> listener){
        super(Request.Method.POST,REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("id",id);
        params.put("uid",uid);
        String newans=ans+"+"+id+"y";
        params.put("ans",newans);
        Log.d("",""+ans+"+"+id);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
