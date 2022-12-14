package sydoruk.services.interfaces;

import sydoruk.domain.Todo;
import sydoruk.domain.User;
import sydoruk.domain.plainObjects.TodoPojo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo(Todo todo, Long userId);
    TodoPojo getTodo(Long id);
    List<TodoPojo> getAllTodo(Long id);
    TodoPojo updateTodo(Todo todo, Long id);
    String deleteTodo(Long id);
}
