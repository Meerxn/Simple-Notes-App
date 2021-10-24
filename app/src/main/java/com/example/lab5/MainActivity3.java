package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    TextView  noteVal;
    int nodeid = -1;
    DBHelper  db;

    public void clickedHere(View view){
    noteVal = (TextView) findViewById(R.id.noteText);
    String content = noteVal.getText().toString();

    Context context = getApplicationContext();
    SQLiteDatabase sqLite = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

    SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5",Context.MODE_PRIVATE);
    db = new DBHelper(sqLite);

    String str = sharedPreferences.getString("username","");
    String username = str;
    String title;
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    String date = dateFormat.format(new Date());
    if (nodeid == -1){
        title = "NOTE_" + (MainActivity2.notes.size() +1);
        db.saveNotes(username,title,content,date);

    }
    else{
        title = "NOTE_" + (nodeid + 1);
        db.updateNote(title,date,content,username);
    }
        Intent intent = new Intent(this,MainActivity2.class);
        //intent.putExtra("message",username);
        startActivity(intent);




}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        noteVal = (TextView) findViewById(R.id.noteText);
        Intent intent = getIntent();

        nodeid= intent.getIntExtra("nodeid",-1);
        if(nodeid != -1){
            Note note = MainActivity2.notes.get(nodeid);
            String noteContent = note.getContent();
            noteVal.setText(noteContent);
        }






    }
}