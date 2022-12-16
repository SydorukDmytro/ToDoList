package sydoruk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import sydoruk.domain.Todo;
import sydoruk.domain.plainObjects.TodoPojo;
import sydoruk.exceptions.CustomEmptyDataException;
import sydoruk.services.interfaces.ITodoService;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TodoController {

    private final ITodoService todoService;

    @Autowired
    public TodoController (ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/todo")
    public ResponseEntity<TodoPojo> createTodo(@PathVariable Long userId, @RequestBody Todo todo) {
        TodoPojo result = todoService.createTodo(todo, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> getTodo (@PathVariable Long userId,
                                             @PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodo(id, userId), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/todos")
    public ResponseEntity<List<TodoPojo>> getAllTodo (@PathVariable Long userId) {
        return new ResponseEntity<>(todoService.getAllTodo(userId), HttpStatus.OK);
    }

    @PutMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> updateTodo (@RequestBody Todo source,
                                                @PathVariable Long userId,
                                                @PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateTodo(source, id, userId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long userId,
                                             @PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id, userId), HttpStatus.OK);
    }

    /**
     * Exception handling
     */

    @ExceptionHandler
    public ResponseEntity<String>  onMissingTodoName(DataIntegrityViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": Name of todo is obligatory");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodoId(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " :No such todo was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUser(EmptyResultDataAccessException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no one todo was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass()) + exception.getSQLState() +
                exception.getLocalizedMessage() +
                ": something went wrong with todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler(CustomEmptyDataException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass())
                + " "
                + exception.getCause()
                + " "
                + exception.getLocalizedMessage());
    }
}