package com.example.customgraph.user;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 03-06-2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String REQUEST_URL="https://neeltrivedi.000webhostapp.com/register.php";
    private Map<String,String> params;
    public RegisterRequest(String name, String username, Integer age, String password, Response.Listener<String> listener){
        super(Method.POST,REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("age",age+"");
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
