package com.example.superalarm.Database.questiondatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "QuestionModel";
    private static final String TABLE_NAME = "question_manager";
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private Context context;

    public QuestionDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
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
        String sql = "CREATE TABLE " + TABLE_NAME + "( "
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +QUESTION+" TEXT,"
                +ANSWER+" TEXT " + ")";
        db.execSQL(sql);
        Log.d("createTable" ,"done");


    }
    public void addQuestion(QuestionModel questionModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION, questionModel.getQuestion());
        values.put(ANSWER, questionModel.getAnswer());
        db.insert(TABLE_NAME,null,values);


    }
    public QuestionModel getQuestion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.query(TABLE_NAME ,new String[]{ID,QUESTION,ANSWER},ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        QuestionModel questionModel = new QuestionModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return questionModel;
    }
    public List<QuestionModel> getAllQuestion(){
        List<QuestionModel> list = new ArrayList<QuestionModel>();
        String sql = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String question = cursor.getString(1);
                String answer = cursor.getString(2);
                QuestionModel questionModel1 = new QuestionModel(id,question,answer);
                list.add(questionModel1);
            }while (cursor.moveToNext());
        }
        return list;
    }
}

