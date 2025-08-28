package dev.java.todolist.repository;

import dev.java.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByTitle(String title);
}
