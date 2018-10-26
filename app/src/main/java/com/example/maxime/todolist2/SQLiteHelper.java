package com.example.maxime.todolist2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoListDataBase.db";
    public static final String TASK_TABLE_NAME = "Task_table";

    public static final String TASK_COL_ID = "id";
    public static final String TASK_COL_NOM = "nom";
    public static final String TASK_COL_DATE = "date"; /*("YYYY-MM-DD HH:MM:SS.SSS").*/
    public static final String TASK_COL_REPETITION = "repetition";
    public static final String TASK_COL_EFFECTUE = "effectue";
    /*class intern a enlver*/
    public static class Constants implements BaseColumns {
        public static final String DATABASE_NAME = "ToDoListDataBase.db";
        public static final String TASK_TABLE_NAME = "Task_table";
        public static final String TASK_COL_ID = "id";
        public static final String TASK_COL_NOM = "nom";
        public static final String TASK_COL_DATE = "date"; /*("YYYY-MM-DD HH:MM:SS.SSS").*/
        public static final String TASK_COL_REPETITION = "repetition";
        public static final String TASK_COL_EFFECTUE = "effectue";

    }

    public static final String TASK_CREATE_TABLE =   "CREATE TABLE " + Constants.TASK_TABLE_NAME + " (" +
            Constants.TASK_COL_ID + " integer primary key autoincrement, " +
            Constants.TASK_COL_EFFECTUE + "INTEGER , "+
            Constants.TASK_COL_NOM + " TEXT, " +
            Constants.TASK_COL_DATE + " TEXT, " +
            Constants.TASK_COL_REPETITION + " TEXT) ";

    public SQLiteHelper(Context pContext, String databaseName, Context context, int version) {
        super(context,Constants.DATABASE_NAME,null, 1);
    }
    public SQLiteHelper(Context pContext) {
        super(pContext,Constants.DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TASK_TABLE_NAME);
        onCreate(db);
    }
}
