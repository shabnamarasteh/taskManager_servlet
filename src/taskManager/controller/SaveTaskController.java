package taskManager.controller;


import taskManager.model.entity.Task;
import taskManager.model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveTaskController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Task task = new Task();
            task.setStatus(Task.Status.valueOf(req.getParameter("status")));
            task.setTitle(req.getParameter("title"));
            task.setDescription(req.getParameter("description"));
                TaskService.getInstance().save(task);
                resp.sendRedirect("/task/findAll.do");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(707);
        }
    }
}
