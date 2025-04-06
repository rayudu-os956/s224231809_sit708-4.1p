package com.example.myapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import java.util.List;
//
//public class TaskAdapter extends ArrayAdapter<Task> {
//    public TaskAdapter(Context context, List<Task> tasks) {
//        super(context, 0, tasks);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Task task = getItem(position);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
//        }
//        TextView title = convertView.findViewById(android.R.id.text1);
//        TextView date = convertView.findViewById(android.R.id.text2);
//        title.setText(task.title);
//        date.setText("Due: " + task.dueDate);
//        return convertView;
//    }
//}
//
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the task item at the current position
        Task task = getItem(position);

        // If no view exists, create a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        // Find the TextViews to populate
        TextView title = convertView.findViewById(android.R.id.text1);
        TextView dueDate = convertView.findViewById(android.R.id.text2);

        // Set the task details in the views
        title.setText(task.title);
        dueDate.setText("Due: " + task.dueDate);

        return convertView;
    }
}