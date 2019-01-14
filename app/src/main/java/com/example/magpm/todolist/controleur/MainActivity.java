package com.example.magpm.todolist.controleur;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magpm.todolist.R;
import com.example.magpm.todolist.model.Task;
import com.example.magpm.todolist.model.TaskDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    MyAdaptater adapter;
    List<Task> mListTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        remplireAdaptater();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask(view);
            }
        });


        Intent i = getIntent();
        if(i.hasExtra("clickOnItem")){
            rafraichirMethod();
        }
    }

    public void onBackPressed(){
        finish();
    }

    public void rafraichirMethod(){
        mListTask.clear();
        remplireAdaptater();
        adapter.notifyDataSetChanged();

    }

    public void remplireAdaptater(){
        final TaskDAO taskDAO = new TaskDAO(MainActivity.this);
        taskDAO.open();


        long l = taskDAO.getTaskCount();
        int numberOfTaskInt = (int) l;

        mListView  = (ListView) findViewById(R.id.listViewMain);

        mListTask = new ArrayList<>();

        List<Task> allIdTask = taskDAO.getAllID();

        int nombreDeTask = allIdTask.size();
        for(int i = 0 ; i< allIdTask.size(); i++){
            Task taskFromBdd = taskDAO.getTaskWithID(String.valueOf(allIdTask.get(i).getId()));
            mListTask.add(taskFromBdd);
        }

       /* for(int i = 0; i<numberOfTaskInt ; i++){
            Task taskFromBdd = taskDAO.getTaskWithID(String.valueOf(i+1));
            mListTask.add(taskFromBdd);

        }*/


        adapter = new MyAdaptater(getApplicationContext(), mListTask);

        mListView.setAdapter(adapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int position, long id) {

                final Task selectedTask = mListTask.get(position);

                modifierTask(view, selectedTask);
                return true;
            }
        });



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Task selectedTask = mListTask.get(position);

                /*CheckBox checkBox = (CheckBox) view.findViewById (R.id.checkboxMain);

                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }
                else {
                    checkBox.setChecked(true);
                }

                if(checkBox.isChecked()) {
                    selectedTask.setEffectue("vrai");
                    taskDAO.updateTask(selectedTask.getId(),selectedTask);
                    //checkBox.setChecked(false);
                    rafraichirMethod();
                }
                else{
                    selectedTask.setEffectue("faux");
                    taskDAO.updateTask(selectedTask.getId(),selectedTask);
                    rafraichirMethod();
                    //checkBox.setChecked(true);
                }*/


                if(selectedTask.getEffectue().equals("vrai")){
                    selectedTask.setEffectue("faux");
                    taskDAO.updateTask(selectedTask.getId(),selectedTask);
                    //checkBox.setChecked(false);
                    rafraichirMethod();
                }
                else{
                    selectedTask.setEffectue("vrai");
                    taskDAO.updateTask(selectedTask.getId(),selectedTask);
                    //checkBox.setChecked(false);
                    rafraichirMethod();
                }




               // modifierTask(view, selectedTask);
            }
        });

    }




    public void newTask(View view){
        Intent intent = new Intent(this,  NewTaskActivity.class);
        intent.putExtra("ajoutKey", "ajout");
        startActivity(intent);
    }

    public void modifierTask(View view, Task selectedTask){
        Intent intent = new Intent(getApplicationContext(),  NewTaskActivity.class);
        intent.putExtra("clickOnItem", "50");
        intent.putExtra("selectedTaskNom", selectedTask.getNom());
        intent.putExtra("selectedTaskRepetition", selectedTask.getRepetition());
        intent.putExtra("selectedTaskDate", selectedTask.getDate());
        intent.putExtra("selectedTaskID", String.valueOf(selectedTask.getId()));
        intent.putExtra("selectedTaskEffectue", selectedTask.getEffectue());
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent (this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
