package com.pepoc.androidnewtechnique.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pepoc.androidnewtechnique.log.LogManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yangchen on 2016/5/13.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, "test.db", null, 1);
        LogManager.i("--------------DBOpenHelper--------------");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogManager.i("--------------onCreate--------------");
        db.execSQL("CREATE TABLE person (uid integer primary key autoincrement, name varchar(20), age integer, sex integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("age", person.getAge());
        values.put("sex", person.getSex());
        db.insert("person", null, values);
        db.close();
    }

    public Person findPersonById(int uid) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("person", null, "uid=?", new String[]{"1"}, null, null, null);
        Person person = null;
        if (cursor.moveToNext()) {
            person = new Person();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int sex = cursor.getInt(cursor.getColumnIndex("sex"));
            person.setUid(uid);
            person.setName(name);
            person.setAge(age);
            person.setSex(sex);
        }
        cursor.close();
        return person;
    }

    public List<Person> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        List<Person> personList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Person person = new Person();
            personList.add(person);
            int uid = cursor.getInt(cursor.getColumnIndex("uid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int sex = cursor.getInt(cursor.getColumnIndex("sex"));
            person.setUid(uid);
            person.setName(name);
            person.setAge(age);
            person.setSex(sex);
        }
        cursor.close();
        return personList;
    }
}
