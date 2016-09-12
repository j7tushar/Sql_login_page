package com.example.guest999.sqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;
    Cursor c;
    ContentValues cv;
    String name = null, pass = null, gen = null, edu = null, skill = null, rat = null;
    ListView listView;
    ArrayList<HashMap<String, String>> list = null;
    AlertDialog.Builder alert;

    private static String tag_id = "ID";
    private static String tag_name = "NAME";
    private static String tag_pass = "PASS";
    private static String tag_g = "GENDER";
    private static String tag_e = "EDU";
    private static String tag_s = "SKILL";
    private static String tag_r = "RAT";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.lv);
        list = new ArrayList<HashMap<String, String>>();

        alert=new AlertDialog.Builder(this);

        db = openOrCreateDatabase("shiv", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        c = db.rawQuery("select * from DEV", null);
        c.moveToFirst();
        do {

            int id = c.getInt(c.getColumnIndex("ID"));
            name = c.getString(c.getColumnIndex("NAME"));
            pass = c.getString(c.getColumnIndex("PASS"));
            gen = c.getString(c.getColumnIndex("GENDER"));
            edu = c.getString(c.getColumnIndex("EDU"));
            skill = c.getString(c.getColumnIndex("SKILL"));
            rat = c.getString(c.getColumnIndex("RAT"));

            HashMap<String, String> map = new HashMap<String, String>();

            map.put(tag_id, id + "");
            map.put(tag_name, name);
            map.put(tag_pass, pass);
            map.put(tag_g, gen);
            map.put(tag_e, edu);
            map.put(tag_s, skill);
            map.put(tag_r, rat);

            list.add(map);
        }
        while (c.moveToNext());
        c.close();
        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.raw, new String[]{tag_id, tag_name, tag_pass, tag_e, tag_g, tag_r, tag_s}, new int[]{R.id.mno, R.id.mname, R.id.mpass, R.id.medu, R.id.mgen, R.id.mrat, R.id.mskill});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        alert.setTitle("HELLLOOO");
        alert.setMessage("HMMMMMMMMMMMMM");

        String string =((TextView)view.findViewById(R.id.mno)).getText().toString();
        final int id=Integer.parseInt(string);
        alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cv=new ContentValues();
                cv.put("ID",id+"");
                db.delete("DEV","id="+id,null);

                Intent intent= new Intent(Main2Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("key",id+"");
                startActivity(intent);
            }
        });
        alert.show();
        Toast.makeText(this,id+"",Toast.LENGTH_LONG).show();
    }
}
