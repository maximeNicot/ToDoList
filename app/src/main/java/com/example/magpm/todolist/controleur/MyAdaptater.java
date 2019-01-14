package com.example.magpm.todolist.controleur;

import android.content.Context;

import android.graphics.Color;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.magpm.todolist.R;
import com.example.magpm.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;


public class MyAdaptater extends BaseAdapter {

    private Context mContext;
    private List<Task> mTaskList;

    public MyAdaptater( Context mContext, List<Task> mTaskList) {
        this.mContext = mContext;
        this.mTaskList = mTaskList;

    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int i) {
        return mTaskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item,null);


        TextView text1 = (TextView) v.findViewById(R.id.text1);
        TextView text2 = (TextView) v.findViewById(R.id.text2);
        TextView text3 = (TextView) v.findViewById(R.id.text3);

        text1.setText(mTaskList.get(position).getNom());
        String[] parts = mTaskList.get(position).getDate().split(" ");
        text2.setText(parts[0]);

        //CheckBox checkBox = v.findViewById(R.id.checkboxMain);

        if(mTaskList.get(position).getEffectue().equals("vrai")){
           // checkBox.setChecked(true);
            text1.setTextColor(Color.GREEN);
            text2.setVisibility(View.INVISIBLE);
            text3.setVisibility(View.INVISIBLE);
        }
        if(mTaskList.get(position).getEffectue().equals("faux")){
            //checkBox.setChecked(false);
            text1.setTextColor(Color.RED);
            text2.setVisibility(View.VISIBLE);
            text3.setVisibility(View.VISIBLE);
        }







        String[] partsTime = parts[1].split("/");

        text3.setText(partsTime[0] + "h" + partsTime[1] +"m");

        v.setTag(mTaskList.get(position).getId());

        return v;
    }

}