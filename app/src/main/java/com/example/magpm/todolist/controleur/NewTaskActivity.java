package com.example.magpm.todolist.controleur;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.appwidget.AppWidgetManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.magpm.todolist.R;
import com.example.magpm.todolist.model.Task;
import com.example.magpm.todolist.model.TaskDAO;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    private TaskDAO datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        final String[] arrayChoixRepetition;
        Resources res = getResources();
        arrayChoixRepetition = res.getStringArray(R.array.arrayChoixRepetition); // on prends l'array dans arrays.xml

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
                DialogFragment timePicker = new DialogTime();
                timePicker.show(getSupportFragmentManager(), "time picker");
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
                                    }
                                }
                            });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        final Button buttonEnvoyer = (Button) findViewById(R.id.newTaskSubmitID);
        buttonEnvoyer.setVisibility(View.VISIBLE);
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNomTache = (EditText) findViewById(R.id.tache_nomID);
                if (editTextDate.getText().toString().matches("") || editTextTime.getText().toString().matches("") ||
                        editTextNomTache.getText().toString().matches("") || editTextRepetition.toString().matches("")) {
                    Toast.makeText(NewTaskActivity.this, "Veuillez remplir tout les champs de saisie", Toast.LENGTH_LONG).show();
                }
                else {
                    /**********BDD***************/
                    final TaskDAO taskDAO = new TaskDAO(NewTaskActivity.this);
                    taskDAO.open();
                    String dateEtHeure = editTextDate.getText().toString();
                    dateEtHeure = dateEtHeure + " " + editTextTime.getText().toString();

                    Task taskEnregistrer = new Task(editTextNomTache.getText().toString(), dateEtHeure, editTextRepetition.getText().toString(), "faux");
                    taskDAO.insertTask(taskEnregistrer);
                    Task taskFromBdd = taskDAO.getTaskWithNom(taskEnregistrer.getNom());
                    Toast.makeText(NewTaskActivity.this, taskFromBdd.toString(), Toast.LENGTH_LONG).show();
                    /************!BDD*************/

                    /*******NOTIFICATION*******/
                    Calendar calendar = Calendar.getInstance();
                    String[] separatedDate = editTextDate.getText().toString().split("/");
                    String[] separatedTime = editTextTime.getText().toString().split("/");
                    calendar.set(Calendar.YEAR, Integer.parseInt(separatedDate[0]));
                    calendar.set(Calendar.MONTH, Integer.parseInt(separatedDate[1]));
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(separatedDate[2]));
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(separatedTime[0]));// heure a laquelle la notification aura lieu
                    calendar.set(Calendar.MINUTE, Integer.parseInt(separatedTime[1]));// minute a laquelle la notification aura lieu

                    Random rand = new Random();
                    int requestCodeRandom = rand.nextInt(2000);
                    Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
                    intent.putExtra("KEY_NOM_TACHE", editTextNomTache.getText().toString());
                    intent.putExtra("KEY_REQUEST_CODE", requestCodeRandom);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCodeRandom, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


                    if (editTextRepetition.getText().toString().equals("Une fois par jour")) {
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);// ici interval
                    }
                    if (editTextRepetition.getText().toString().equals("Une fois par heure")) {
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);// ici interval
                    }
                    if (editTextRepetition.getText().toString().equals("Pas de repetition")) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        }
                    }
                }
            }
        });

        /**Bouton pour lancer l'activité google**/
        Button bGoogle = (Button) findViewById(R.id.lancerGoogleActivityID);
        bGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGoogleSignInActivity(view);
            }
        });


        Button buttonEmail = (Button) findViewById(R.id.sendEmailButton);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        /**Reception des partages */
        Intent intent = getIntent();


        if(intent.hasExtra("clickOnItem")) { // ici activity lancé depuis un click d'un item pour le modifier
            editTextRepetition.setText(intent.getStringExtra("selectedTaskRepetition"));
            EditText tacheNomEditText = (EditText) findViewById(R.id.tache_nomID);
            tacheNomEditText.setText(intent.getStringExtra("selectedTaskNom"));
            final String effectue = intent.getStringExtra("selectedTaskEffectue");
            String date = intent.getStringExtra("selectedTaskDate");
            String parts[] = date.split(" ");
            editTextDate.setText(parts[0]);
            editTextTime.setText(parts[1]);

            final TaskDAO taskDAO = new TaskDAO(NewTaskActivity.this);
            taskDAO.open();

            final int selectedTaskID = Integer.parseInt(intent.getStringExtra("selectedTaskID"));



            Button buttonModifier = (Button) findViewById(R.id.modifierButton);
            buttonModifier.setVisibility(View.VISIBLE);
            buttonModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editTextNom = findViewById(R.id.tache_nomID);
                    EditText editTextDate = findViewById(R.id.dateID);
                    EditText editTextTime = findViewById(R.id.timeID);
                    EditText editTextRepetition = findViewById(R.id.repetitionID);
                    Task modifierTask = new Task(editTextNom.getText().toString(),editTextDate.getText().toString() + " " + editTextTime.getText().toString(), editTextRepetition.getText().toString(),effectue,selectedTaskID);
                    //final Task modifierTask = new Task(tacheNomEditText.getText().toString(),date,editTextRepetition.getText().toString(),effectue,selectedTaskID);

                    taskDAO.updateTask(selectedTaskID,modifierTask);
                    Toast.makeText(NewTaskActivity.this, "Tâche modifié", Toast.LENGTH_LONG).show();

                }
            });
            Button buttonSupprimer = (Button) findViewById(R.id.suprimerButton);
            buttonModifier.setVisibility(View.VISIBLE);
            buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskDAO.removeTask(selectedTaskID);
                    Toast.makeText(NewTaskActivity.this, "Tâche supprimé", Toast.LENGTH_LONG).show();
                }
            });
        }
        //Pour rendre invisible les buttons inutiles
        if(intent.hasExtra("ajoutKey")){
            Button buttonModifier = (Button) findViewById(R.id.modifierButton);
            Button buttonSupprimer = (Button) findViewById(R.id.suprimerButton);
            buttonModifier.setVisibility(View.INVISIBLE);
            buttonSupprimer.setVisibility(View.INVISIBLE);
        }else{
            buttonEnvoyer.setVisibility(View.INVISIBLE);
        }

        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);

            }
        }

}


    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
        intent.putExtra("rafraichirKey", "rafraichirValue");
        startActivity(intent);
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            EditText tacheNomEditText = (EditText) findViewById(R.id.tache_nomID);
            EditText dateEditText = (EditText) findViewById(R.id.dateID);
            EditText timeEditText = (EditText) findViewById(R.id.timeID);
            EditText repetitionEditText = (EditText) findViewById(R.id.repetitionID);

            String[] parts = sharedText.split("\\.");


            if(parts[0].equals("Partage de tâche")){
                String[] partsDateTime = parts[2].split("\\ ");
                String[] partsRepetition = parts[3].split("\\:");
                tacheNomEditText.setText(parts[1].substring(1));
                dateEditText.setText(partsDateTime[4]);
                timeEditText.setText(partsDateTime[6].replace('h', '/' ).replace('m' , ' '));
                repetitionEditText.setText(partsRepetition[1].substring(1));

            }
            else{
                Toast.makeText(NewTaskActivity.this, "La tâche n'est pas dans le bon format", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String newLine = System.getProperty("line.separator");

        EditText tacheNomEditText = (EditText) findViewById(R.id.tache_nomID);
        EditText dateEditText = (EditText) findViewById(R.id.dateID);
        EditText timeEditText = (EditText) findViewById(R.id.timeID);
        String[] parts = timeEditText.getText().toString().split("/");
        EditText repetitionEditText = (EditText) findViewById(R.id.repetitionID);

        if (tacheNomEditText.getText().toString().matches("") || dateEditText.getText().toString().matches("") ||
                timeEditText.getText().toString().matches("") || repetitionEditText.toString().matches("")) {
            Toast.makeText(NewTaskActivity.this, "Veuillez remplir tout les champs de saisie", Toast.LENGTH_LONG).show();
        }
        else{

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Partage de tâche."+ newLine + tacheNomEditText.getText().toString() + "." + newLine + "A effectuer avant le " + dateEditText.getText().toString() + " à " + parts[0] + "h" + parts[1] +"m." + newLine +
                    "Repetition des notifications : " + repetitionEditText.getText().toString() + ".");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(NewTaskActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void newGoogleSignInActivity(View view){
        Intent intent = new Intent(this,  GoogleSignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month) ;
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String currentDateString = String.valueOf(c.get(Calendar.YEAR)) + "/" +  String.valueOf(c.get(Calendar.MONTH ))  + "/" +String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        TextView textView = (TextView) findViewById(R.id.dateID);
        textView.setText(currentDateString);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.HOUR_OF_DAY, hour);
        c2.set(Calendar.MINUTE, minute);
        String currentTimeString = String.valueOf(timePicker.getCurrentHour()) + "/" + String.valueOf(timePicker.getCurrentMinute());

        TextView textView = (TextView) findViewById(R.id.timeID);
        textView.setText(currentTimeString);
    }
}
