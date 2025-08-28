package dev.java.todolist.mapper;

import dev.java.todolist.dto.TaskDTO;
import dev.java.todolist.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task map(TaskDTO taskDTO) {
        Task newTask = new Task();
        newTask.setId(taskDTO.getId());
        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setStatus(taskDTO.getStatus());
        return newTask;


    }

    public TaskDTO map(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }
}
