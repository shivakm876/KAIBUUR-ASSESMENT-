package com.example.taskapi.service;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    // Find tasks by name
    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    // Create or update a task (with command validation)
    public Task saveTask(Task task) throws Exception {
        if (isCommandSafe(task.getCommand())) {
            return taskRepository.save(task);
        } else {
            throw new Exception("Unsafe or invalid command detected!");
        }
    }

    // Delete task by ID
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    // Execute a task command and save execution output
    // Execute a task command and save execution output
    public Task executeTask(String taskId) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            throw new Exception("Task not found");
        }

        Task task = optionalTask.get();
        String command = task.getCommand();

        if (!isCommandSafe(command)) {
            throw new Exception("Unsafe command!");
        }

        TaskExecution execution = new TaskExecution();
        execution.setStartTime(new Date());

        try {
            Process process;
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows: use cmd.exe /c
                process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});
            } else {
                // Linux/Mac: run command directly
                process = Runtime.getRuntime().exec(command);
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            execution.setEndTime(new Date());
            execution.setOutput(output.toString());
        } catch (Exception e) {
            execution.setEndTime(new Date());
            execution.setOutput("Error while executing command: " + e.getMessage());
        }

        task.getTaskExecutions().add(execution);
        return taskRepository.save(task);
    }
    
    // Basic command validation — allows only simple commands like echo, ls, pwd
    private boolean isCommandSafe(String command) {
        String lower = command.toLowerCase();
        return lower.startsWith("echo") || lower.equals("ls") || lower.equals("pwd");
    }
}
