package com.example.rithvik.mycontactapp;

import android.database.Cursor;
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
    EditText editAge;
    EditText editPhoneNumber;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhoneNumber = (EditText) findViewById(R.id.editText_phonenumber);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAge.getText().toString(),editPhoneNumber.getText().toString());

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

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //Output message using log.d and Toast
            return;

        }

        StringBuffer buffer = new StringBuffer();
        //set up a loop with Cursor (res) using the moveToNext method
        //  append each COL to the buffer
        //  display message using showMessage
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String s) {
        //AlertDialog.Builder method call
    }
}
