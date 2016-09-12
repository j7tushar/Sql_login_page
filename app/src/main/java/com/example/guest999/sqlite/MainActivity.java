package com.example.guest999.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, RatingBar.OnRatingBarChangeListener {
    EditText edname, edpass;
    Button bsubmit;
    RatingBar ratingBar;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    CheckBox checkBox1, checkBox2;
    RadioGroup radioGroup;

    String rating = null, s = null, g = null, c = null, name = null, pass = null, r = null;

    SQLiteDatabase db;
    ContentValues cv;

    String str = "CREATE TABLE IF NOT EXISTS DEV(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PASS TEXT,GENDER TEXT,EDU TEXT,SKILL TEXT,RAT TEXT)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = (EditText) findViewById(R.id.uname);
        edpass = (EditText) findViewById(R.id.upass);

        bsubmit = (Button) findViewById(R.id.button);
        bsubmit.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radiog);

        ratingBar = (RatingBar) findViewById(R.id.rat);
        ratingBar.setOnRatingBarChangeListener(this);

        checkBox1 = (CheckBox) findViewById(R.id.cha);
        checkBox2 = (CheckBox) findViewById(R.id.chp);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.Sp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        db = openOrCreateDatabase("shiv", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL(str);

    }

    @Override
    public void onClick(View view) {

        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(id);
        String string = rb.getText().toString();
        if (string.equalsIgnoreCase("MALE")) {
            g = "MALE";
        }
        if (string.equalsIgnoreCase("FEMALE")) {
            g = "FEMALE";
        }

        if (checkBox1.isChecked()) {
            c = "Android";
        }
        if (checkBox2.isChecked()) {
            c = "Php";
        }
        if (bsubmit == view) {
            name = edname.getText().toString();
            pass = edpass.getText().toString();
            r = rating;

            cv=new ContentValues();
            cv.put("NAME",name);
            cv.put("PASS",pass);
            cv.put("GENDER",g);
            cv.put("EDU",s);
            cv.put("SKILL",c);
            cv.put("RAT",r);

            db.insert("DEV",null,cv);

            Toast.makeText(this,cv+"",Toast.LENGTH_LONG).show();

            Log.e("cv",cv+"");

            Intent i=new Intent(this,Main2Activity.class);
            startActivity(i);


        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        Float f = v;
        rating = f.toString();
        Toast.makeText(MainActivity.this, rating, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv = (TextView) view;
        String st = tv.getText().toString();
        if (st.equalsIgnoreCase("BE")) {
            s = "BE";
        }
        if (st.equalsIgnoreCase("BCA")) {
            s = "BCA";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
