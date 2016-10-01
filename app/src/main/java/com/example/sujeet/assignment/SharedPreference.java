package com.example.sujeet.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreference extends AppCompatActivity {
    private EditText name;
    private EditText mail;
    private EditText age;
    private static final String MyPREFERENCES = "profile" ;
    private static final String Name = "name";
    private static final String Age = "age";
    private static final String Email = "email";
    private SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("chu");
        setContentView(R.layout.activity_shared_preference);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        mail=(EditText)findViewById(R.id.email);
        //  Button b1=(Button)findViewById(R.id.update);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            mail.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Age)) {
            age.setText(sharedpreferences.getString(Age, ""));

        }



    }
    public void onlick(View v) {


        //Log.d(TAG, Integer.toString(y)+"and"+Integer.toString(r));

        switch (v.getId()) {
            case R.id.update:

                String  n=name.getText().toString();
                String m=mail.getText().toString();
                String a=age.getText().toString();
                System.out.println(n+"  "+m+"  "+a);

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Age, a);
                editor.putString(Email, m);
                editor.apply();
                Toast.makeText(this,"Updated", Toast.LENGTH_LONG).show();

        }
    }
}
