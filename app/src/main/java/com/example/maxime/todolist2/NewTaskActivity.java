package com.example.maxime.todolist2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        /* 2t ab de string pour les listes de dialogs */

        final String[] arrayChoixRepetition;
        Resources res = getResources();
        arrayChoixRepetition = res.getStringArray(R.array.arrayChoixRepetition); // on prends l'array dans arrays.xml


        final String[] arrayListesTaches;
        arrayListesTaches = res.getStringArray(R.array.arrayListesTaches);

        EditText editTextDate = (EditText) findViewById(R.id.dateID);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DialogDate();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        EditText editTextTime = (EditText) findViewById(R.id.timeID);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment TimePicker = new DialogTime();
                TimePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        final Button buttonRepetition = (Button) findViewById(R.id.repetitionID);
        buttonRepetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewTaskActivity.this);
                    builder.setTitle("Tout les...")
                            .setItems(arrayChoixRepetition, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    // Delay de notification a changer selon le choix
                                    String choixRepetition = "";
                                    switch (which){
                                        case 0 :
                                            choixRepetition = arrayChoixRepetition[0];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                        case 1 :
                                            choixRepetition = arrayChoixRepetition[1];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                        case 2 :
                                            choixRepetition = arrayChoixRepetition[2];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                        case 3 :
                                            choixRepetition = arrayChoixRepetition[3];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                        case 4 :
                                            choixRepetition = arrayChoixRepetition[4];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                        case 5 :
                                            choixRepetition = arrayChoixRepetition[5];
                                            buttonRepetition.setText(choixRepetition);
                                            break;
                                    }
                                }
                            });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        final Button buttonListesTaches = (Button) findViewById(R.id.listeID);
        buttonListesTaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewTaskActivity.this);
                builder.setTitle("Quelle liste choisir?")
                        .setItems(arrayListesTaches, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delay de notification a changer selon le choix
                                String choixListesTaches = "";
                                switch (which){
                                    case 0 :
                                        choixListesTaches = arrayListesTaches[0];
                                        buttonListesTaches.setText(choixListesTaches);
                                        break;
                                    case 1 :
                                        choixListesTaches = arrayListesTaches[1];
                                        buttonListesTaches.setText(choixListesTaches);
                                        break;
                                    case 2 :
                                        choixListesTaches = arrayListesTaches[2];
                                        buttonListesTaches.setText(choixListesTaches);
                                        break;
                                }
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        final Button buttonEnvoyer = (Button) findViewById(R.id.newTaskSubmitID);
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Creer une task et la mettre dans une TaskList, BDD?*/
            }
        });
}


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.dateID);
        textView.setText(currentDateString);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.HOUR_OF_DAY, hour);
        c2.set(Calendar.MINUTE, minute);
        String currentTimeString = String.valueOf(timePicker.getCurrentHour()) + "h " + String.valueOf(timePicker.getCurrentMinute()) + "m";

        TextView textView = (TextView) findViewById(R.id.timeID);
        textView.setText(currentTimeString);
    }
}
