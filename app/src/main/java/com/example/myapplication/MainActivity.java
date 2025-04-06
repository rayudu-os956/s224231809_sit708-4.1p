package com.example.myapplication;

//import android.content.Intent;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ListView taskListView;
//    private Button addTaskButton;
//    private TaskDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        taskListView = findViewById(R.id.taskListView);
//        addTaskButton = findViewById(R.id.addTaskButton);
//        db = TaskDatabase.getInstance(this);
//
//        loadTasks();
//
//        addTaskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
//
//    private void loadTasks() {
//        List<Task> tasks = db.taskDao().getAllTasks();
//        TaskAdapter adapter = new TaskAdapter(this, tasks);
//        taskListView.setAdapter(adapter);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadTasks();
//    }
//}
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView taskListView;
    private Button addTaskButton;
    private TaskDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListView = findViewById(R.id.taskListView);
        addTaskButton = findViewById(R.id.addTaskButton);
        db = TaskDatabase.getInstance(this);

        loadTasks();

        // Open AddEditTaskActivity to add a new task
        addTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });

        // When a task is clicked, open the AddEditTaskActivity to edit the task
        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = (Task) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            intent.putExtra("TASK_TITLE", task.title); // Pass the task title to edit
            startActivity(intent);
        });
    }

    // Load tasks from database and display in ListView
    private void loadTasks() {
        List<Task> tasks = db.taskDao().getAllTasksSortedByDueDate();
        TaskAdapter adapter = new TaskAdapter(this, tasks);
        taskListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();  // Reload tasks when coming back from Add/Edit task
    }
}