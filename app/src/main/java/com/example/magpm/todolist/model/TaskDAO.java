package com.example.magpm.todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TaskDAO  {

    public static final String TASK_TABLE_NAME = "Task_table";
    private static final int VERSION_BDD  = 1;
    private static final String NOM_BDD = "ToDoListDataBase.db";
    private static final String TASK_COL_ID = "id";
    private static final int NUM_TASK_COL_ID = 0;
    private static final String TASK_COL_NOM = "nom";
    private static final int NUM_TASK_COL_NOM = 1;
    private static final String TASK_COL_DATE = "date"; /*("YYYY-MM-DD HH:MM:SS.SSS").*/
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
       values.put(TASK_COL_NOM, task.getNom());
       values.put(TASK_COL_DATE, task.getDate());
       values.put(TASK_COL_REPETITION, task.getRepetition());
       values.put(TASK_COL_EFFECTUE, task.getEffectue());
       return database.update(TASK_TABLE_NAME, values,TASK_COL_ID + " = " + id, null);
   }

   public int removeTask(int id){
        return database.delete(TASK_TABLE_NAME, TASK_COL_ID + " = " + id, null);
   }

   public Task getTaskWithNom(String nom){
        Cursor c = database.query(TASK_TABLE_NAME, new String[] {TASK_COL_ID, TASK_COL_NOM, TASK_COL_DATE, TASK_COL_REPETITION, TASK_COL_EFFECTUE},
                TASK_COL_NOM + " LIKE \"" + nom + "\"", null,null,null,null);

        return cursorToTask(c);
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

/*
    public Task createTask(String nom, String date, String repetition, String effectue){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TASK_COL_NOM, nom);
        values.put(SQLiteHelper.TASK_COL_DATE, date);
        values.put(SQLiteHelper.TASK_COL_REPETITION, repetition);
        values.put(SQLiteHelper.TASK_COL_EFFECTUE, effectue);
        long insertID = database.insert(SQLiteHelper.TASK_TABLE_NAME, null, values);
        Cursor cursor = database.query(SQLiteHelper.TASK_TABLE_NAME,allColumns,SQLiteHelper.TASK_COL_ID + " = " + insertID, null,null,null,null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }
    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setNom(cursor.getString(1));
        task.setDate(cursor.getString(2));
        task.setRepetition(cursor.getString(3));
        task.setEffectue(cursor.getString(4));
        return task;
    }

    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<Task>();

        Cursor cursor = database.query(SQLiteHelper.TASK_TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasksList.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasksList;
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
    }*/
}
