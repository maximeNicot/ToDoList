package com.example.MAGP.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    private TaskDAO datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final String[] arrayChoixRepetition;
        Resources res = getResources();
        arrayChoixRepetition = res.getStringArray(R.array.arrayChoixRepetition); // on prends l'array dans arrays.xml


        final String[] arrayListesTaches;
        arrayListesTaches = res.getStringArray(R.array.arrayListesTaches);

        final EditText editTextDate = (EditText) findViewById(R.id.dateID);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DialogDate();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        final EditText editTextTime = (EditText) findViewById(R.id.timeID);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment TimePicker = new DialogTime();
                TimePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        final EditText editTextRepetition = (EditText) findViewById(R.id.repetitionID);
        editTextRepetition.setOnClickListener(new View.OnClickListener() {
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
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                        case 1 :
                                            choixRepetition = arrayChoixRepetition[1];
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                        case 2 :
                                            choixRepetition = arrayChoixRepetition[2];
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                        case 3 :
                                            choixRepetition = arrayChoixRepetition[3];
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                        case 4 :
                                            choixRepetition = arrayChoixRepetition[4];
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                        case 5 :
                                            choixRepetition = arrayChoixRepetition[5];
                                            editTextRepetition.setText(choixRepetition);
                                            break;
                                    }
                                }
                            });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        final EditText editTextListesTaches = (EditText) findViewById(R.id.listeID);
        editTextListesTaches.setOnClickListener(new View.OnClickListener() {
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
                                        editTextListesTaches.setText(choixListesTaches);
                                        break;
                                    case 1 :
                                        choixListesTaches = arrayListesTaches[1];
                                        editTextListesTaches.setText(choixListesTaches);
                                        break;
                                    case 2 :
                                        choixListesTaches = arrayListesTaches[2];
                                        editTextListesTaches.setText(choixListesTaches);
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
                EditText editTextNomTAche = (EditText) findViewById(R.id.tache_nomID);
                /**********BDD***************/
                final TaskDAO taskDAO = new TaskDAO(NewTaskActivity.this);
                taskDAO.open();
                String dateEtHeure = editTextDate.getText().toString();
                dateEtHeure = dateEtHeure + " " + editTextTime.getText().toString();

                Task taskEnregistrer = new Task(editTextNomTAche.getText().toString(),dateEtHeure,editTextRepetition.getText().toString(),"effectue");
                taskDAO.insertTask(taskEnregistrer);
                Task taskFromBdd = taskDAO.getTaskWithNom(taskEnregistrer.getNom());
                Toast.makeText(NewTaskActivity.this, taskFromBdd.toString(), Toast.LENGTH_LONG).show();
            }
        });



        /************!BDD*************/

/*      final TaskDAO taskDAO = new TaskDAO(NewTaskActivity.this);
        taskDAO.open();
        Task task = new Task("Nom", "DATEUH", "repet","effectue");
        taskDAO.insertTask(task);

        //Pour verifier si lajout a bien été fait
        Task taskFromBdd = taskDAO.getTaskWithNom(task.getNom());

        if(taskFromBdd != null){
            Toast.makeText(this, taskFromBdd.toString(), Toast.LENGTH_LONG).show();
            taskFromBdd.setNom("NOUVEAU NOM");
            taskDAO.updateTask(taskFromBdd.getId(), taskFromBdd);
        }
        //on supprime
        taskFromBdd = taskDAO.getTaskWithNom("NOUVEAU NOM");
        if(taskFromBdd != null){
            Toast.makeText(this, taskFromBdd.toString(), Toast.LENGTH_LONG).show();

            taskDAO.removeTask(taskFromBdd.getId());

        }

        taskFromBdd = taskDAO.getTaskWithNom("NOUVEAU NOM");
        if(taskFromBdd == null){
            Toast.makeText(this, "Ce task n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Ce task existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        taskDAO.close();
        /************!BDD*************/

        /**Bouton pour lancer l'activité google**/
        Button bGoogle = (Button) findViewById(R.id.lancerGoogleActivityID);
        bGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGoogleSignInActivity(view);
            }
        });


}


    public void newGoogleSignInActivity(View view){
        Intent intent = new Intent(this,  GoogleSignInActivity.class);
        startActivity(intent);
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
