package com.example.gohasu.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.gohasu.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> family = new ArrayList<>();
        family.add("Mai");
        family.add("Joshua");
        family.add("Vi An");

        try {
            String serialized = ObjectSerializer.serialize(family);
            sharedPreferences.edit().putString("family", serialized).apply();

            Log.i("family", serialized);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> newFamily = new ArrayList<>();
        try {
            newFamily = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("family", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("new family", newFamily.toString());

//        sharedPreferences.edit().putString("username", "gosuvn").apply();  // saving the data in sharedpreferences
//
//        String username = sharedPreferences.getString("username", "");  // extract data from sharedpreferences
//
//        Log.i("This is the username", username);
    }
}
