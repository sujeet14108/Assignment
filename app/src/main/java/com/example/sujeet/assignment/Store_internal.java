package com.example.sujeet.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.R.attr.data;


public class Store_internal extends AppCompatActivity {
Button b1,b2;
    EditText ed1,w;
    TextView tv;
    String data;
    private String file = "review";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_internal);
        b1=(Button)findViewById(R.id.write_internal);
        b2=(Button)findViewById(R.id.read_internal);
        w=(EditText)findViewById(R.id.editText1);
        ed1=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textViewm);
       // file=w.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data="Doctor: "+ w.getText().toString() +"\n\n"+"Review:   "+ed1.getText().toString()+"\n\n";

                try {
                    if(!w.getText().toString().equals(""))
                        file=w.getText().toString();
                    FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);

                    fOut.write(data.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                }

                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(!w.getText().toString().equals(""))
                        file=w.getText().toString();
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp="";

                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    tv.setText(temp);
                    Toast.makeText(getBaseContext(),"file read", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }
            }
        });
    }






}
