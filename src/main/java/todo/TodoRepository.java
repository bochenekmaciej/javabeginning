package todo;

import com.todolist.maciek.HibernateUtil;
import java.util.List;

public class TodoRepository {

    List<ToDo> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from ToDo", ToDo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    ToDo toggleTodo(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var todo = session.get(ToDo.class, id);
        todo.setDone(!todo.isDone());
        session.update(todo);

        transaction.commit();
        session.close();
        return todo;
    }

    ToDo addTodo(ToDo newTodo){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;
    }

    public ToDo deleteTodo(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var todo = session.get(ToDo.class, id);
        session.delete(todo);

        transaction.commit();
        session.close();
        return todo;
    }
}
