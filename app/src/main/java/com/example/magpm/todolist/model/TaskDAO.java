package com.example.magpm.todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO  {

    public static final String TASK_TABLE_NAME = "Task_table";
    private static final int VERSION_BDD  = 1;
    private static final String NOM_BDD = "ToDoListDataBase.db";
    private static final String TASK_COL_ID = "id";
    private static final int NUM_TASK_COL_ID = 0;
    private static final String TASK_COL_NOM = "nom";
    private static final int NUM_TASK_COL_NOM = 1;
    private static final String TASK_COL_DATE = "date";
    private static final int NUM_TASK_COL_DATE = 2;
    private static final String TASK_COL_REPETITION = "repetition";
    private static final int NUM_TASK_COL_REPETITION = 3;
    private static final String TASK_COL_EFFECTUE = "effectue";
    private static final int NUM_TASK_COL_EFFECTUE = 4;

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;


    public TaskDAO(Context context) {
        dbHelper = new SQLiteHelper(context, NOM_BDD,null, VERSION_BDD);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close(); // ou dbHelper
    }
    public SQLiteDatabase getBDD(){
        return database;
    }

    public long insertTask(Task task){
        ContentValues values = new ContentValues();
        values.put(TASK_COL_NOM, task.getNom());
        values.put(TASK_COL_DATE, task.getDate());
        values.put(TASK_COL_REPETITION, task.getRepetition());
        values.put(TASK_COL_EFFECTUE, task.getEffectue());
        return database.insert(TASK_TABLE_NAME,null, values);
    }

   public int updateTask(int id, Task task){
       ContentValues values = new ContentValues();
       String strFilter = "_id=" + id;

       values.put(TASK_COL_NOM, task.getNom());
       values.put(TASK_COL_DATE, task.getDate());
       values.put(TASK_COL_REPETITION, task.getRepetition());
       values.put(TASK_COL_EFFECTUE, task.getEffectue());
       return database.update(TASK_TABLE_NAME, values,TASK_COL_ID + "=" + id, null);
   }

   public int removeTask(int id){
        return database.delete(TASK_TABLE_NAME, TASK_COL_ID + " = " + id, null);
   }

   public Task getTaskWithNom(String nom){
        Cursor c = database.query(TASK_TABLE_NAME, new String[] {TASK_COL_ID, TASK_COL_NOM, TASK_COL_DATE, TASK_COL_REPETITION, TASK_COL_EFFECTUE},
                TASK_COL_NOM + " LIKE \"" + nom + "\"", null,null,null,null);

        return cursorToTask(c);
   }

    public Task getTaskWithID(String ID){
        Cursor c = database.query(TASK_TABLE_NAME, new String[] {TASK_COL_ID, TASK_COL_NOM, TASK_COL_DATE, TASK_COL_REPETITION, TASK_COL_EFFECTUE},
                TASK_COL_ID + " LIKE \"" + ID + "\"", null,null,null,null);

        return cursorToTask(c);
    }

    public ArrayList getAllID() {
        String selectQuery = "SELECT  * FROM " + TASK_TABLE_NAME;

        ArrayList list = new ArrayList<>();

            Cursor cursor = database.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Task task = new Task();
                        task.setId(Integer.parseInt(cursor.getString(0)));
                        list.add(task);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }
         return list;
    }

    public long getTaskCount() {
        long count = DatabaseUtils.queryNumEntries(database, TASK_TABLE_NAME);
        return count;
    }

    private Task cursorToId(Cursor c){

        c.moveToFirst();
        Task task = new Task();
        task.setId(c.getInt(NUM_TASK_COL_ID));
        task.setNom(c.getString(NUM_TASK_COL_NOM));
        task.setDate(c.getString(NUM_TASK_COL_DATE));
        task.setRepetition(c.getString(NUM_TASK_COL_REPETITION));
        task.setEffectue(c.getString(NUM_TASK_COL_EFFECTUE));

        return task;

    }


   private Task cursorToTask(Cursor c){
        if(c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        Task task = new Task();
        task.setId(c.getInt(NUM_TASK_COL_ID));
        task.setNom(c.getString(NUM_TASK_COL_NOM));
        task.setDate(c.getString(NUM_TASK_COL_DATE));
        task.setRepetition(c.getString(NUM_TASK_COL_REPETITION));
        task.setEffectue(c.getString(NUM_TASK_COL_EFFECTUE));

        c.close();
        return task;
   }

}
