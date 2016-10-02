package com.example.sujeet.assignment;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
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

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Store_external extends AppCompatActivity {
    private EditText editTextFileName;
    String nam = Environment.getExternalStorageDirectory().getPath()+"/";
    private EditText editTextData;
    private Button saveButton;
    private Button readButton;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_store_external);
        editTextFileName = (EditText) findViewById(R.id.editText11);
        editTextData = (EditText) findViewById(R.id.editText22);
        saveButton = (Button) findViewById(R.id.write_external);
        readButton = (Button) findViewById(R.id.read_external);
        tv = (TextView) findViewById(R.id.textViewl);
        //Performing action on save button
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String filename = editTextFileName.getText().toString();
                String data = "Doctor Name: " + filename + "\n\nAppointment Details:   " + editTextData.getText().toString() + "\n\n";

                FileOutputStream fos;
                try {
                    File myFile = new File(nam   + filename + ".txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new

                            FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();
                    editTextFileName.setText("");
                    editTextData.setText("");
                    Toast.makeText(getApplicationContext(), filename + "  saved", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

        //Performing action on Read Button
        readButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String filename = editTextFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "";
                String aBuffer = "";
                try {
                    File myFile = new File(nam  + filename + ".txt");
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
               // tv.setText(aBuffer);
                Toast.makeText(getBaseContext(), aBuffer+"\nfile read", Toast.LENGTH_SHORT).show();


            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

            switch (item.getItemId()) {
                case R.id.item11:
                    nam = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/";
                    return true;

                case R.id.item12:
                    nam = getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/";
                    return true;

                case R.id.item13:
                    nam = Environment.getExternalStorageDirectory().getPath() + "/";
                    return true;


                default:
                    return super.onOptionsItemSelected(item);
            }

    }
}