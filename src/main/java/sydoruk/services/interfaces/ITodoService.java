package sydoruk.services.interfaces;

import sydoruk.domain.Todo;
import sydoruk.domain.User;
import sydoruk.domain.plainObjects.TodoPojo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo(Todo todo, Long userId);
    TodoPojo getTodo(Long id, Long userId);
    List<TodoPojo> getAllTodo(Long id);
    TodoPojo updateTodo(Todo todo, Long id, Long userId);
    String deleteTodo(Long id, Long userId);
}
