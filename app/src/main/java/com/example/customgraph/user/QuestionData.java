package com.example.customgraph.user;

/**
 * Created by Admin on 04-06-2018.
 */

public class QuestionData {
    private String mQuestion,mDate,mid;
    public QuestionData(String question,String date,String id){
        mQuestion=question;
        mDate=date;
        mid=id;
    }

    public String getQuestion(){return mQuestion;}
    public String getmDate(){return mDate;}
    public String getid(){return mid;}
}
