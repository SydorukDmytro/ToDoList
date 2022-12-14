package sydoruk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sydoruk.domain.Todo;
import sydoruk.domain.plainObjects.TodoPojo;
import sydoruk.services.interfaces.ITodoService;

import java.util.List;

@RestController
public class TodoController {

    private final ITodoService todoService;

    @Autowired
    public TodoController (ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/creatingtodo")
    public ResponseEntity<TodoPojo> createTodo(@PathVariable Long userId, @RequestBody Todo todo) {
        TodoPojo result = todoService.createTodo(todo, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> getTodo (@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/todos")
    public ResponseEntity<List<TodoPojo>> getAllTodo (@PathVariable Long userId) {
        return new ResponseEntity<>(todoService.getAllTodo(userId), HttpStatus.OK);
    }

    @PutMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> updateTodo (@RequestBody Todo source, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateTodo(source, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.OK);
    }

}
