package com.example.myapplication;

//import  android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class AddEditTaskActivity extends AppCompatActivity {
//
//    private EditText titleInput, descriptionInput, dueDateInput;
//    private Button saveButton;
//    private TaskDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_edit_task);
//
//        titleInput = findViewById(R.id.titleInput);
//        descriptionInput = findViewById(R.id.descriptionInput);
//        dueDateInput = findViewById(R.id.dueDateInput);
//        saveButton = findViewById(R.id.saveButton);
//        db = TaskDatabase.getInstance(this);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleInput.getText().toString();
//                String description = descriptionInput.getText().toString();
//                String dueDate = dueDateInput.getText().toString();
//
//                if (title.isEmpty() || dueDate.isEmpty()) {
//                    Toast.makeText(AddEditTaskActivity.this, "Title and Due Date required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Task task = new Task(title, description, dueDate);
//                db.taskDao().insertTask(task);
//                finish();
//            }
//        });
//    }
//}
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class AddEditTaskActivity extends AppCompatActivity {
//
//    private EditText titleInput, descriptionInput, dueDateInput;
//    private Button saveButton, deleteButton, backButton;
//    private TaskDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_edit_task);
//
//        titleInput = findViewById(R.id.titleInput);
//        descriptionInput = findViewById(R.id.descriptionInput);
//        dueDateInput = findViewById(R.id.dueDateInput);
//        saveButton = findViewById(R.id.saveButton);
//        deleteButton = findViewById(R.id.deleteButton);
//        backButton = findViewById(R.id.backButton);
//        db = TaskDatabase.getInstance(this);
//
//        // Save Task Button Click Listener
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleInput.getText().toString();
//                String description = descriptionInput.getText().toString();
//                String dueDate = dueDateInput.getText().toString();
//
//                if (title.isEmpty() || dueDate.isEmpty()) {
//                    Toast.makeText(AddEditTaskActivity.this, "Title and Due Date required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Task task = new Task(title, description, dueDate);
//                db.taskDao().insertTask(task);
//                finish();
//            }
//        });
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleInput.getText().toString();
//                String description = descriptionInput.getText().toString();
//                String dueDate = dueDateInput.getText().toString();
//
//                if (title.isEmpty() || dueDate.isEmpty()) {
//                    Toast.makeText(AddEditTaskActivity.this, "Title and Due Date required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Assuming task is being updated, you would get the task by title or ID
//                Task taskToUpdate = db.taskDao().getTaskByTitle(title);
//                if (taskToUpdate != null) {
//                    // Update the task with new details
//                    taskToUpdate.description = description;
//                    taskToUpdate.dueDate = dueDate;
//                    db.taskDao().updateTask(taskToUpdate);
//                    Toast.makeText(AddEditTaskActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
//                } else {
//                    // If task doesn't exist, insert a new one
//                    Task newTask = new Task(title, description, dueDate);
//                    db.taskDao().insertTask(newTask);
//                    Toast.makeText(AddEditTaskActivity.this, "New task added", Toast.LENGTH_SHORT).show();
//                }
//                finish(); // Finish activity and go back
//            }
//        });
//
//
//        // Delete Task Button Click Listener
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleInput.getText().toString();
//
//                if (title.isEmpty()) {
//                    Toast.makeText(AddEditTaskActivity.this, "Title is required to delete", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Task taskToDelete = db.taskDao().getTaskByTitle(title);
//                if (taskToDelete != null) {
//                    db.taskDao().deleteTask(taskToDelete);
//                    Toast.makeText(AddEditTaskActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
//                    finish();  // Go back after deleting
//                } else {
//                    Toast.makeText(AddEditTaskActivity.this, "Task not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // Back Button Click Listener (Navigate to MainActivity)
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddEditTaskActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();  // Finish this activity
//            }
//        });
//    }
//}
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput, dueDateInput;
    private Button saveButton, deleteButton, backButton;
    private TaskDatabase db;
    private Task taskToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        dueDateInput = findViewById(R.id.dueDateInput);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);

        db = TaskDatabase.getInstance(this);

        // Get the task title passed from MainActivity (if editing an existing task)
        Intent intent = getIntent();
        String taskTitle = intent.getStringExtra("TASK_TITLE");

        if (taskTitle != null) {
            // If a task title is passed, load the task from the database
            taskToEdit = db.taskDao().getTaskByTitle(taskTitle);

            if (taskToEdit != null) {
                titleInput.setText(taskToEdit.title);
                descriptionInput.setText(taskToEdit.description);
                dueDateInput.setText(taskToEdit.dueDate);

                // Change the save button to indicate updating
                saveButton.setText("Update Task");

                // Show delete button if editing an existing task
                deleteButton.setVisibility(View.VISIBLE);
            }
        }

        // Save or update the task
        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String dueDate = dueDateInput.getText().toString();

            // Validate title and due date are not empty
            if (title.isEmpty()) {
                titleInput.setError("Title is required.");
                return;
            }
            if (dueDate.isEmpty()) {
                dueDateInput.setError("Due Date is required.");
                return;
            }

            // Validate the due date format (e.g., yyyy-mm-dd)
            if (!dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                dueDateInput.setError("Invalid date format. Please use yyyy-mm-dd.");
                return;
            }

            if (taskToEdit != null) {
                // If editing, update the existing task
                taskToEdit.description = description;
                taskToEdit.dueDate = dueDate;
                db.taskDao().updateTask(taskToEdit);
                Toast.makeText(AddEditTaskActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
            } else {
                // If new task, insert it into the database
                Task newTask = new Task(title, description, dueDate);
                db.taskDao().insertTask(newTask);
                Toast.makeText(AddEditTaskActivity.this, "New task added", Toast.LENGTH_SHORT).show();
            }

            finish();  // Close the activity and return to the previous screen
        });

        // Delete the task if editing
        deleteButton.setOnClickListener(v -> {
            if (taskToEdit != null) {
                db.taskDao().deleteTask(taskToEdit);
                Toast.makeText(AddEditTaskActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
            }
            finish();  // Close the activity and return to the previous screen
        });

        // Go back to MainActivity without saving
        backButton.setOnClickListener(v -> finish());
    }
}
