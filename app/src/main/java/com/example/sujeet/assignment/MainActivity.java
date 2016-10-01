package com.example.sujeet.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void click(View v) {


        //Log.d(TAG, Integer.toString(y)+"and"+Integer.toString(r));

        switch (v.getId()) {
            case R.id.Profile:
                Intent intent = new Intent(this, SharedPreference.class);
                this.startActivity(intent);
                break;
            case  R.id.Record:
                Intent intent1 = new Intent(this, Record_SQlite.class);
                startActivity(intent1);
                break;
            case  R.id.Review:
                Intent intent2 = new Intent(this, Store_internal.class);
                startActivity(intent2);
                break;

            case  R.id.Appointment:
                Intent intent3 = new Intent(this, Store_external.class);
                startActivity(intent3);
                break;



        }
    }


}
