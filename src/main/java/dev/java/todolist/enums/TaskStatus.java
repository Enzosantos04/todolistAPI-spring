package dev.java.todolist.enums;

public enum TaskStatus {
    PENDING("pending"),
    IN_PROGRESS("in_Progress"),
    COMPLETED("completed");
    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
