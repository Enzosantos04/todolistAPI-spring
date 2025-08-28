package dev.java.todolist.mapper;

import dev.java.todolist.dto.TaskStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusMapper {
    public TaskStatusDTO map(String status) {
        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
        taskStatusDTO.setStatus(status);
        return taskStatusDTO;
    }

    public String map(TaskStatusDTO taskStatusDTO) {
        return taskStatusDTO.getStatus();
    }
}
