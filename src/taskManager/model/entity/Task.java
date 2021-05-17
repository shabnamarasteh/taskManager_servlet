package taskManager.model.entity;

public class Task {
    private Long taskId;
    private String title;
    private String description;
    private Status status;

    public enum Status{
        todo, inprogress,finish,reject,approved
    }

    public Task() {
    }

    public Task(Long taskId, String title, String description, Status status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}