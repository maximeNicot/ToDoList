package com.example.MAGP.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoListDataBase.db";
    public static final String TASK_TABLE_NAME = "Task_table";

    public static final String TASK_COL_ID = "id";
    public static final String TASK_COL_NOM = "nom";
    public static final String TASK_COL_DATE = "date"; /*("YYYY-MM-DD HH:MM:SS.SSS").*/
    public static final String TASK_COL_REPETITION = "repetition";
    public static final String TASK_COL_EFFECTUE = "effectue";
    public static final String TASK_CREATE_TABLE =   "CREATE TABLE " + TASK_TABLE_NAME  + "(" +
            TASK_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
            TASK_COL_NOM + " TEXT, " +
            TASK_COL_DATE + " TEXT, " +
            TASK_COL_REPETITION + " TEXT, "+
            TASK_COL_EFFECTUE + " TEXT"+ ");";

    public SQLiteHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,databaseName,factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TASK_TABLE_NAME + ";");
        onCreate(db);
    }

}
