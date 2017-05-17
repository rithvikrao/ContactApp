package com.example.rithvik.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText editSearch;
    Button btnAddData;

    String[] multiField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhoneNumber = (EditText) findViewById(R.id.editText_phonenumber);
        editSearch = (EditText) findViewById(R.id.editText_search);

        multiField = new String[4];
        multiField[0] = "ID: ";
        multiField[1] = "Name: ";
        multiField[2] = "Age: ";
        multiField[3] = "Phone Number: ";
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

        editName.setText("");

        editAge.setText("");

        editPhoneNumber.setText("");

    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //Output message using log.d and Toast

            Log.d("MyContact", "No data found in database");
            Toast toast = Toast.makeText(getApplicationContext(), "No data found in database", Toast.LENGTH_SHORT);
            toast.show();

            return;

        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()) {
            for(int i=1; i<res.getColumnCount(); i++) {
                buffer.append(res.getColumnName(i) + ": " + res.getString(i) + "\n");
            }
            buffer.append("\n");
        }

        showMessage("Data", buffer.toString());         //  display message using showMessage


    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //AlertDialog.Builder method call
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void searchByName (View v) {

        String search = editSearch.getText().toString();

        Cursor res = myDb.getAllData();

        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferCopy = new StringBuffer();



        while(res.moveToNext()) {
            for (int i = 0; i < res.getCount(); i++) {
                if (res.getString(1).equals(search)) {
                    if(bufferCopy.indexOf(multiField[0] + res.getString(0) + "\n") == -1) {
                        for (int field = 0; field < 4; field++) {
                            if(field != 0) {buffer.append(multiField[field] + res.getString(field) + "\n");}
                            bufferCopy.append(multiField[field] + res.getString(field) + "\n");
                        }
                        buffer.append("\n");
                    }
                }
        }
        }

        if (buffer.toString().equals("")) {
            showMessage("Failure!", " ");

        } else {
            showMessage("Search Result:", buffer.toString());
        }

        editSearch.setText("");

    }
}
