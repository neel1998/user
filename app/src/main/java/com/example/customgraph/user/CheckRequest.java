package com.example.customgraph.user;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 08-06-2018.
 */

public class CheckRequest extends StringRequest{
    private static final String REQUEST_URL="https://neeltrivedi.000webhostapp.com/check.php";
    private Map<String,String> params;
    public CheckRequest(String id, Response.Listener<String> listener){
        super(Request.Method.POST,REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("id",id);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
