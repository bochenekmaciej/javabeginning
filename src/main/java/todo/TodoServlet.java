package todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.HelloServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToDo", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private TodoRepository repository;
    private ObjectMapper mapper;

    /**
     * Servlet container needs it
     */
    @SuppressWarnings("unused")
    public TodoServlet(){
        this(new TodoRepository(), new ObjectMapper());
    }

    TodoServlet(TodoRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());

        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getPathInfo();
        try {
           var todo = repository.toggleTodo(Integer.valueOf(command.substring(1)));
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), todo);
        } catch (NumberFormatException e) {
            logger.warn("Chuj ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       var newTodo = mapper.readValue(req.getInputStream(), ToDo.class);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.addTodo(newTodo));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String deleteId = req.getPathInfo();
        var delete = repository.deleteTodo(Integer.valueOf(deleteId.substring(1)));
        mapper.writeValue(resp.getOutputStream(), delete);
    }
}
