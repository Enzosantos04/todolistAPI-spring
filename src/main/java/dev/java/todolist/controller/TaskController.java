package dev.java.todolist.controller;

import dev.java.todolist.dto.TaskDTO;
import dev.java.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> listAlltasks(){
        List<TaskDTO> tasks = taskService.listAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){
        TaskDTO newTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        if(taskService.getTaskById(id) != null){
            TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTaskById(@PathVariable Long id){
        if(taskService.getTaskById(id) != null){
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<TaskDTO> getTaskByTitle(@RequestParam String title){
        TaskDTO taskDTO =  taskService.getTaskByTitle(title);
        if(taskDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        if (taskService.getTaskById(id) != null){
            TaskDTO taskDTO = taskService.getTaskById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(taskDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<TaskDTO> setTaskStatus(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        if (taskService.getTaskById(id) != null) {
            TaskDTO setStatusTask = taskService.setTaskStatus(id, taskDTO);
            return ResponseEntity.status(HttpStatus.OK).body(setStatusTask);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
