package com.example.guest999.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Main3Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, RatingBar.OnRatingBarChangeListener {

    EditText edname, edpass;
    Button bsubmit;
    RatingBar ratingBar;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    CheckBox checkBox1, checkBox2;
    RadioGroup radioGroup;
    Bundle b;
    TextView t;
    int def;
    String rating = null, s = null, g = null, c = null, name = null, pass = null, r = null;
    ContentValues cv;

    String str = "CREATE TABLE IF NOT EXISTS DEV(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT,EDU TEXT,SKILL TEXT,RAT TEXT)";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        edname = (EditText) findViewById(R.id.uname);
        edpass = (EditText) findViewById(R.id.upass);

        t=(TextView)findViewById(R.id.idd);

        bsubmit = (Button) findViewById(R.id.button);
        bsubmit.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radiog);

        ratingBar = (RatingBar) findViewById(R.id.rat);
        ratingBar.setOnRatingBarChangeListener(this);

        checkBox1 = (CheckBox) findViewById(R.id.cha);
        checkBox2 = (CheckBox) findViewById(R.id.chp);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(Main3Activity.this, R.array.Sp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        b = getIntent().getExtras();
        String abc = b.getString("key");
        t.setText(abc);
        def=Integer.parseInt(abc);


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

            cv = new ContentValues();
            cv.put("NAME", name);
            cv.put("PASS", pass);
            cv.put("GENDER", g);
            cv.put("EDU", s);
            cv.put("SKILL", c);
            cv.put("RAT", r);
            db.update("DEV",cv,"id"+"="+def,null);
            Toast.makeText(this, cv+"", Toast.LENGTH_LONG).show();

            Intent i=new Intent(Main3Activity.this,Main2Activity.class);
            startActivity(i);


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv=(TextView)view;
        String st1=tv.getText().toString();
        if(st1.equalsIgnoreCase("BE"))
        {
            s="BE";
        }
        if(st1.equalsIgnoreCase("BCA"))
        {
            s="BCA";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
// TODO Auto-generated method stub
        Float f=v;
        rating=f.toString();
        Toast.makeText(this, rating, Toast.LENGTH_LONG).show();
    }
}
