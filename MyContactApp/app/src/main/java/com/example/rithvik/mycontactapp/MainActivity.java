package com.example.rithvik.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_name);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString());

        if(isInserted) {
            Log.d("MyContact", "Success inserting data");
            //Insert toast here...
            Toast toast = Toast.makeText(getApplicationContext(), "Success inserting data", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Log.d("MyContact", "Failure inserting data");
            //Insert toast here...
            Toast toast = Toast.makeText(getApplicationContext(), "Failure inserting data", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
