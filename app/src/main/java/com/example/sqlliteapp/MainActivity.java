package com.example.sqlliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqlliteapp.DbHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    Button _btnInsert, _btnDelete, _btnUpdate, _btnShow;
    EditText _IDText, _nameText, _addressText, _phoneNumberText, _emailText;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnInsert=(Button)findViewById(R.id.btnInsert);
        _btnDelete=(Button)findViewById(R.id.btnDelete);
        _btnUpdate=(Button)findViewById(R.id.btnUpdate);
        _btnShow=(Button)findViewById(R.id.btnShow);

        _IDText=(EditText)findViewById(R.id.IDText);
        _nameText=(EditText)findViewById(R.id.nameText);
        _addressText=(EditText)findViewById(R.id.addressText);
        _phoneNumberText=(EditText)findViewById(R.id.phoneText);
        _emailText=(EditText)findViewById(R.id.emailText);

        openHelper=new DbHelper(this);


        _btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String name =_nameText.getText().toString();
              String address =_addressText.getText().toString();
              String phone_number =_phoneNumberText.getText().toString();
              String email =_emailText.getText().toString();
              db=openHelper.getWritableDatabase();
              insertData(name, address, phone_number, email);
              Toast.makeText(getApplicationContext(), "INSERT SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });

        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String id = _IDText.getText().toString();
                deleteData(id);
                Toast.makeText(getApplicationContext(), "DELETED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =_nameText.getText().toString();
                String address =_addressText.getText().toString();
                String phone_number =_phoneNumberText.getText().toString();
                String email =_emailText.getText().toString();
                db=openHelper.getWritableDatabase();
                updateData(name, address, phone_number, email);
                Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void insertData(String name, String address, String phone_number, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.COLUMNS_2, name);
        contentValues.put(DbHelper.COLUMNS_3, address);
        contentValues.put(DbHelper.COLUMNS_4, phone_number);
        contentValues.put(DbHelper.COLUMNS_5, email);
        long id = db.insert(TABLE_NAME, null, contentValues);
    }

    public boolean deleteData(String id){
        return db.delete(TABLE_NAME, DbHelper.COLUMNS_1 + "=?", new String[]{id})>0;
    }

    public boolean updateData(String name, String address, String phone_number, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.COLUMNS_2, name);
        contentValues.put(DbHelper.COLUMNS_3, address);
        contentValues.put(DbHelper.COLUMNS_4, phone_number);
        contentValues.put(DbHelper.COLUMNS_5, email);
        String id = _IDText.getText().toString();
        return db.update(TABLE_NAME, contentValues, DbHelper.COLUMNS_1 + "=?", new String[]{id})>0;
    }

}
