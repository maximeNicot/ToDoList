package com.example.magpm.todolist.controleur;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.magpm.todolist.model.Task;
import com.example.magpm.todolist.model.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        final TaskDAO taskDAO = new TaskDAO(WidgetService.this);
        taskDAO.open();


        long l = taskDAO.getTaskCount();
        int numberOfTaskInt = (int) l;

        List<Task> mListTask;
        mListTask = new ArrayList<>();

        List<Task> allIdTask = taskDAO.getAllID();

        for(int i = 0 ; i< allIdTask.size(); i++){
            Task taskFromBdd = taskDAO.getTaskWithID(String.valueOf(allIdTask.get(i).getId()));
            mListTask.add(taskFromBdd);
        }


        return new WidgetFactory(this.getApplicationContext(), intent, (ArrayList) mListTask);
    }
}
