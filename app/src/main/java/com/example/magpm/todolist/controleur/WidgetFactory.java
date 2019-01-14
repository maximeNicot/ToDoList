package com.example.magpm.todolist.controleur;

import android.app.LauncherActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.example.magpm.todolist.R;
import com.example.magpm.todolist.model.SQLiteHelper;
import com.example.magpm.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
public class WidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String[] items={"lorem", "ipsum"};
    private Context ctxt=null;
    private ArrayList mListTask;
    private int appWidgetId;

    public WidgetFactory(Context ctxt, Intent intent, ArrayList mListTask) {
        this.ctxt=ctxt;
        this.mListTask = mListTask;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
    public RemoteViews getViewAt(int position) {
        RemoteViews row=new RemoteViews(ctxt.getPackageName(),
                R.layout.item_widget);


        Task selectedTask = (Task) mListTask.get(position);
        row.setTextViewText(R.id.textWidget1, selectedTask.getNom());



        if(selectedTask.getEffectue().equals("vrai")){
            row.setTextColor(R.id.textWidget1, Color.GREEN);
        }
        else{
            String[] parts = selectedTask.getDate().split(" ");
            //text2.setText(parts[0]);

            String[] partsTime = parts[1].split("/");
            row.setTextViewText(R.id.textWidget2, parts[0]);
            row.setTextViewText(R.id.textWidget3, partsTime[0] + "h" + partsTime[1] + "m");
            row.setTextColor(R.id.textWidget1, Color.RED);

        }


        Intent i=new Intent();
        Bundle extras=new Bundle();

        extras.putString(AppWidget.EXTRA_WORD, selectedTask.getNom());

        i.putExtras(extras);


        row.setOnClickFillInIntent(R.id.textWidget1, i);
        row.setOnClickFillInIntent(R.id.textWidget2, i);
        row.setOnClickFillInIntent(R.id.textWidget3, i);

        return(row);
    }

    @Override
    public int getCount() {
        //return(items.length);
        return(mListTask.size());
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }
}