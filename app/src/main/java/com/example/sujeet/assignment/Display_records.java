package com.example.sujeet.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Display_records extends AppCompatActivity {

    private DBHelper mydb;

    private TextView disease;
    private TextView doctor;
    private TextView medication;

    private int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);
        disease = (TextView) findViewById(R.id.Disease);
        doctor = (TextView) findViewById(R.id.Doctor);
        medication = (TextView) findViewById(R.id.Medication);


        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String dis = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_DISEASE));
                String doc = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_DOCTOR));
                String med = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_MEDICATION));


                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.savedata);
                b.setVisibility(View.INVISIBLE);

                disease.setText((CharSequence) dis);
                disease.setFocusable(false);
                disease.setClickable(false);

                doctor.setText((CharSequence) doc);
                doctor.setFocusable(false);
                doctor.setClickable(false);

                medication.setText((CharSequence) med);
                medication.setFocusable(false);
                medication.setClickable(false);


            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.display_contact, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button) findViewById(R.id.savedata);
                b.setVisibility(View.VISIBLE);
                disease.setEnabled(true);
                disease.setFocusableInTouchMode(true);
                disease.setClickable(true);

                doctor.setEnabled(true);
                doctor.setFocusableInTouchMode(true);
                doctor.setClickable(true);

                medication.setEnabled(true);
                medication.setFocusableInTouchMode(true);
                medication.setClickable(true);


                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

                if (Value > 0) {

                    if (mydb.updateContact(id_To_Update, disease.getText().toString(), doctor.getText().toString(), medication.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (mydb.insertContact(disease.getText().toString(), doctor.getText().toString(), medication.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

        }
    }
}