package com.example.maxime.todolist2;

public class Task {
    private String nom;
    private String dateAnnee;
    private String dateMois;
    private String dateJour;
    private String dateHeure;
    private String dateMinute;
    private boolean effectue; // vrai = la tache est fini
    private String repetition;


    public Task(){
        nom = "";
        dateAnnee = "";
        dateMois = "";
        dateJour = "";
        dateHeure = "";
        dateMinute = "";
        effectue = false;
        repetition = "";
    }

    public Task(String nom, String dateAnnee, String dateMois, String dateJour, String dateHeure, String dateMinute, String repetition){
        this.nom = nom;
        this.dateAnnee = dateAnnee;
        this.dateMois = dateMois;
        this.dateJour = dateJour;
        this.dateHeure = dateHeure;
        this.dateMinute = dateMinute;
        effectue = false;
        this.repetition = repetition;
    }

    public void taskTerminer(){
        effectue = true;
    }
}
