package dev.java.todolist.service;

import dev.java.todolist.dto.TaskDTO;
import dev.java.todolist.dto.TaskStatusDTO;
import dev.java.todolist.entity.Task;
import dev.java.todolist.mapper.TaskMapper;
import dev.java.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    public List<TaskDTO> listAllTask(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = taskMapper.map(taskDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.map(savedTask);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO){
        Optional<Task> existingTask = taskRepository.findById(id);
        if(existingTask.isPresent()){
            Task updatedTask =  taskMapper.map(taskDTO);
            updatedTask.setId(id);
            Task savedTask = taskRepository.save(updatedTask);
            return taskMapper.map(savedTask);
        }
        return null;
    }

    public TaskDTO getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.map(taskMapper::map).orElse(null);
    }

    public TaskDTO getTaskByTitle(String title){
        Task task = taskRepository.findTaskByTitle(title);
        if(task != null){
            return taskMapper.map(task);
        }
        return null;
    }

    public TaskDTO setTaskStatus(Long id, TaskStatusDTO taskStatusDTO){
        Optional<Task> existingTask = taskRepository.findById(id);
        if(existingTask.isPresent()){
            Task task = existingTask.get();
            task.setStatus(taskStatusDTO.getStatus());
            Task savedTask = taskRepository.save(task);
            return taskMapper.map(savedTask);
        }
        return null;
    }
}
