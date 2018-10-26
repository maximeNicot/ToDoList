package com.example.maxime.todolist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.Contacts.SettingsColumns.KEY;

public class TaskDAO  {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {dbHelper.TASK_COL_ID,dbHelper.TASK_COL_NOM ,dbHelper.TASK_COL_DATE, dbHelper.TASK_COL_REPETITION, dbHelper.TASK_COL_EFFECTUE};


    public TaskDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Task cursorToTask(Cursor cursor) {
        Task newTask = new Task();
        newTask.setId(cursor.getLong(0));
        newTask.setNom(cursor.getString(1));
        return newTask;
    }

    public Task createTask(String nom){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TASK_COL_NOM, nom);
        long insertID = database.insert(SQLiteHelper.TASK_COL_NOM, null, values);
        Cursor cursor = database.query(SQLiteHelper.TASK_COL_NOM,allColumns,SQLiteHelper.TASK_COL_ID + " = " + insertID, null,null,null,null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }
    public void deleteTask (Task task){
        long id = task.getId();
        System.out.println("Task deleted with id: " + id);
        database.delete(SQLiteHelper.TASK_TABLE_NAME, SQLiteHelper.TASK_COL_ID
                + " = " + id, null);
    }

    public Task getFirstTask(){
        Cursor cursor = database.query(SQLiteHelper.TASK_TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        Task task =  cursorToTask(cursor);
        cursor.close();
        return task;
    }
}
