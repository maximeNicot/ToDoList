package com.example.magpm.todolist.model;

public class Task {
    private int id;
    private String nom;
    private String date;
    private String repetition;
    private String effectue;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", repetition='" + repetition + '\'' +
                ", effectue='" + effectue + '\'' +
                '}';
    }

    public Task(){
        nom = "";
        date = "";
        repetition = "";
        effectue = "";

    }

    public Task(String nom, String date, String repetition, String effectue){
        this.nom = nom;
        this.date = date;
        this.repetition = repetition;
        this.effectue = effectue ;

    }
    public Task(String nom, String date, String repetition, String effectue, int id){
        this.nom = nom;
        this.date = date;
        this.repetition = repetition;
        this.effectue = effectue ;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEffectue() {
        return effectue;
    }

    public void setEffectue(String effectue) {
        this.effectue = effectue;
    }
}
