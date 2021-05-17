package taskManager.controller;


import taskManager.model.entity.Task;
import taskManager.model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTaskController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Task task = new Task();
            task.setTaskId(Long.parseLong(req.getParameter("taskId")));
            TaskService.getInstance().delete(task);
                resp.sendRedirect("/task/findAll.do");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(707);
        }
    }
}
