package com.example.MAGP.todolist;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private String nom;
    private List<Task> taskArrayList = new ArrayList<Task>();


    public TaskList(){
        nom = "";
        taskArrayList = null;
    }

    public TaskList(String nom){
        this.nom = nom;
        taskArrayList = null;
    }

    public void addTask(Task task){
        taskArrayList.add(task);
    }

    public boolean deleteTask(Task task){
        for(int i = 0; i<taskArrayList.size(); i++){
            if(taskArrayList.get(i).equals(task)){
                taskArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

}
