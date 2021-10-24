package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
public void clickFunction(View view){
    EditText userNameField  = (EditText) findViewById(R.id.userName);
    String str = userNameField.getText().toString();
    SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);
    sharedPreferences.edit().putString("username",str).apply();
    goToActivity2(str);
}
    public void goToActivity2(String s){
        //Log.i("info", "button pressed");
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);

//        EditText myTextField = findViewById(R.id.myTextField);
//        Toast.makeText(MainActivity.this,myTextField.getText().toString(),Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5",Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            String str = sharedPreferences.getString(usernameKey,"");
            goToActivity2(str);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }
}