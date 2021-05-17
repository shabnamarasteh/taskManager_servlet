package taskManager.model.service;

import taskManager.model.entity.Task;
import taskManager.model.repository.TaskDA;

import java.util.List;

public class TaskService {
    private static TaskService ourInstance = new TaskService();
    private TaskService(){}
    public static  TaskService getInstance(){return ourInstance;}
    public void save(Task task) throws Exception{
        try(TaskDA taskDA = new TaskDA()){
            taskDA.insert(task);
        }
    }
    public void delete(Task task) throws Exception{
        try(TaskDA taskDA = new TaskDA()){
            taskDA.delete(task);
        }
    }
    public void update(Task task) throws Exception{
        try(TaskDA taskDA = new TaskDA()){
            taskDA.update(task);
        }
    }
    public List<Task> findAll() throws Exception{
        try(TaskDA taskDA = new TaskDA()){
            return taskDA.selectAll();
        }
    }
}
