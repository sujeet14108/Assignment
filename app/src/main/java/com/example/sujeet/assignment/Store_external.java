package com.example.sujeet.assignment;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Store_external extends AppCompatActivity {
    private EditText editTextFileName;
    private EditText editTextData;
    private Button saveButton;
    private Button readButton;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_external);
        editTextFileName=(EditText)findViewById(R.id.editText11);
        editTextData=(EditText)findViewById(R.id.editText22);
        saveButton=(Button)findViewById(R.id.write_external);
        readButton=(Button)findViewById(R.id.read_external);
        tv=(TextView)findViewById(R.id.textViewl);
        //Performing action on save button
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                String data="Doctor Name: " +filename+"\n\nAppointment Details:   "+editTextData.getText().toString()+"\n\n";

                FileOutputStream fos;
                try {
                    File myFile = new File("/sdcard/"+filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new

                            FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();
editTextFileName.setText("");
                    editTextData.setText("");
                    tv.setText("");
                    Toast.makeText(getApplicationContext(),filename + "  saved",Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

            }

        });

        //Performing action on Read Button
        readButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "";
                String aBuffer = "";
                try {
                    File myFile = new File("/sdcard/"+filename);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));

                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv.setText(aBuffer);
                Toast.makeText(getBaseContext(),"file read", Toast.LENGTH_SHORT).show();


            }

        });
    }


}
