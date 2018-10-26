package com.example.maxime.todolist2;

public class Task {
    private long id;
    private String nom;
    private String date;
    private String repetition;
    private boolean effectue; // vrai = la tache est fini



    public Task(){
        id = 0;
        nom = "";
        date = "";
        repetition = "";
        effectue = false;

    }

    public Task(String nom){
        //this.id = 0;
        this.nom = nom;
        this.date = "";
        this.repetition = "";
        effectue = false;

    }
    public Task(String id ,String nom, String date, String repetition){
        this.id = 0;
        this.nom = nom;
        this.date = date;
        this.repetition = repetition;
        effectue = false;

    }

    public void taskTerminer(){
        effectue = true;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public boolean isEffectue() {
        return effectue;
    }

    public void setEffectue(boolean effectue) {
        this.effectue = effectue;
    }
}
