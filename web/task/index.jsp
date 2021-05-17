<%@ page import="taskManager.model.entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>task</title>
    <meta name="viewport" class="glyphicon-text-width, initial-scale=1">
    <link rel="stylesheet" href="/assets/bootstrap.min.css">
    <script src="/assets/jquery.min.js"></script>
    <script src="/assets/bootstrap.min.js"></script>
</head>
<body>
<div class="col-lg-12">

    <form action="/task/save.do" method="post">
        <div class="form-group">
            <label for="title">title</label>
            <input type="text" class="form-control" name="title" id="title"/>
        </div>
        <div class="form-group">
            <label for="status">status</label>
            <select class="form-control" name="status" id="status">
                <option value="todo">todo</option>
                <option value="inprogress">inprogress</option>
                <option value="finish">finish</option>
                <option value="reject">reject</option>
                <option value="approved">approved</option>
            </select>
        </div>
        <div class="form-group">
            <label for="description">description</label>
            <textarea name="description" id="description"></textarea>
        </div>
        <input type="submit" class="btn btn-default" value="SAVE"/>
    </form>
</div>
<div class="col-lg-12">
    <table class="table table-responsive">
        <tr>
            <td>title</td>
            <td>status</td>
            <td>description</td>
            <td></td>
            <td></td>
        </tr>
        <%
            List<Task> taskList = (List<Task>) request.getAttribute("list");
            if(taskList != null){
            for (Task task : taskList) {
        %>
        <form action="/task/update.do" method="POST">
            <tr>
                <td><input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
                    <input type="text" class="form-control" name="title" value="<%=task.getTitle()%>" readonly>
                </td>
                <td><select class="form-control" name="status">
                    <option <%
                        if (task.getStatus().equals("todo")) {
                    %> selected
                            <% } %>
                       value="todo">todo</option>
                    <option <%
                        if (task.getStatus().equals("inprogress")) {
                    %> selected
                            <% } %>
                       value="inprogress">inprogress</option>
                    <option <%
                        if (task.getStatus().equals("finish")) {
                    %> selected
                            <% } %>
                       value="finish">finish</option>
                    <option <%
                        if (task.getStatus().equals("reject")) {
                    %> selected
                            <% } %>
                       value="reject">reject</option>
                    <option <%
                        if (task.getStatus().equals("approved")) {
                    %> selected
                            <% } %>
                       value="approved">approved</option>
                </select>
                </td>
                <td><textarea name="description"><%=task.getDescription()%></textarea></td>
                <td><input type="submit" class="btn-success" value="update"></td>
                <td><input type="button" class="btn-danger" value="delete" onclick="deleteTask(<%=task.getTaskId()%>">
                </td>
            </tr>
        </form>
        <%
                }
            }
        %>
    </table>
    <script>
        function deleteTask(id) {
            window.location = "/task/delete.do?id=" + id;
        }
    </script>
</div>
</body>
</html>
