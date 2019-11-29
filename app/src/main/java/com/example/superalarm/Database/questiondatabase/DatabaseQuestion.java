package com.example.superalarm.Database.questiondatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseQuestion extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;

    private static final String DATABASE_NAME = "Question";
    private static final String TABLE_NAME = "question_manager";
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    public DatabaseQuestion(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Và tạo lại.
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +QUESTION+" TEXT,"
                +ANSWER+" TEXT" + ")";
        db.execSQL(sql);


    }
   /* public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateContact(Q contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,contact.getName());
        values.put(PHONE_NUMBER,contact.getPhone());
        values.put(AVATAR,contact.getAvatar());
        db.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(contact.getID())});
        db.close();
    }*/

    public void addQuestion(Question question) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION,question.getQuestion());
        values.put(ANSWER,question.getAnswer());
        db.insert(TABLE_NAME,null,values);


    }
    public Question getContat(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.query(TABLE_NAME ,new String[]{ID,QUESTION,ANSWER},ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Question question = new Question(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return  question;
    }
    public ArrayList<Question> getAllQuestion(){
        ArrayList<Question> list = new ArrayList<Question>();
        String sql = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String question = cursor.getString(1);
                String answer = cursor.getString(2);
                Question question1 = new Question(id,question,answer);
                list.add(question1);
            }while (cursor.moveToNext());
        }
        return list;
    }
}

