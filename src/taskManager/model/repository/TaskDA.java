package taskManager.model.repository;

import taskManager.common.JDBCProvider;
import taskManager.model.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDA implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TaskDA() throws Exception{
        connection = JDBCProvider.getConnection();
        connection.setAutoCommit(false);
    }

    public void insert(Task task) throws Exception{
        preparedStatement = connection.prepareStatement("select task_seq.nextval tid from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        task.setTaskId(resultSet.getLong("tid"));
        preparedStatement = connection.prepareStatement("insert into task (taskId,title,description,status) values (?,?,?,?)");
        preparedStatement.setLong(1, resultSet.getLong("tid"));
        preparedStatement.setString(2, task.getTitle());
        preparedStatement.setString(3, task.getDescription());
        preparedStatement.setString(4, task.getStatus());
        preparedStatement.executeUpdate();
    }

    public void update(Task task) throws Exception {
        preparedStatement = connection.prepareStatement("update task set title=?, description=?, status=? where taskId=?");
        preparedStatement.setLong(4, task.getTaskId());
        preparedStatement.setString(3, task.getStatus());
        preparedStatement.setString(2, task.getDescription());
        preparedStatement.setString(1, task.getTitle());
        preparedStatement.executeUpdate();
    }

    public void delete(Task task) throws Exception {
        preparedStatement = connection.prepareStatement("delete task where taskId=?");
        preparedStatement.setLong(1, task.getTaskId());
        preparedStatement.executeUpdate();
    }

    public List<Task> selectAll() throws Exception {
        preparedStatement = connection.prepareStatement("select * from task");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Task> taskList = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getLong("taskId"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setStatus(Task.Status.valueOf(resultSet.getString("status")));
            taskList.add(task);

        }
        return taskList;
    }
    @Override
    public void close() throws Exception {
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
