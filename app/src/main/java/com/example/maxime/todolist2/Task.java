package com.example.maxime.todolist2;

public class Task {
    private int id;
    private String nom;
    private String date;
    private String repetition;
    private String effectue; // vrai = la tache est fini


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

   /* public Task(String nom){
        //this.id = 0;
        this.nom = nom;
        this.date = "";
        this.repetition = "";
        effectue = false;

    }*/
    public Task(String nom, String date, String repetition, String effectue){
        this.nom = nom;
        this.date = date;
        this.repetition = repetition;
        this.effectue = effectue ;

    }

   /* public void taskTerminer(){
        effectue = true;
    }*/


    /*public String toString() {
        return "Task{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", repetition='" + repetition + '\'' +
                ", effectue=" + effectue +
                '}';
    }*/

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
