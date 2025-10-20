package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ✅ GET all tasks OR get by id (using ?id=123)
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(required = false) String id) {
        if (id == null) {
            return ResponseEntity.ok(taskService.getAllTasks());
        } else {
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    // ✅ PUT - create or update a task
    @PutMapping
    public ResponseEntity<?> createOrUpdateTask(@RequestBody Task task) {
        try {
            Task savedTask = taskService.saveTask(task);
            return ResponseEntity.ok(savedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ DELETE a task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ GET - find tasks by name
    @GetMapping("/search")
    public ResponseEntity<?> findTasksByName(@RequestParam String name) {
        List<Task> tasks = taskService.findTasksByName(name);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tasks);
        }
    }

    // ✅ PUT - execute task command by ID
    @PutMapping("/{id}/execute")
    public ResponseEntity<?> executeTask(@PathVariable String id) {
        try {
            Task updatedTask = taskService.executeTask(id);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
