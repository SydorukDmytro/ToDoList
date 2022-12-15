package sydoruk.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private Set<Todo> todoList = new HashSet<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Todo> getTodoList() {
        return todoList;
    }

    public void addTodo(Todo todo) {
        addTodo(todo, false);
    }

    public void addTodo(Todo todo, boolean otherSideHasBeenSet) {
        this.getTodoList().add(todo);
        if(otherSideHasBeenSet) {
            return;
        }
        todo.addTag(this, true);
    }

    public void removeTodo(Todo todo) {
        removeTodo(todo, false);
    }

    public void removeTodo(Todo todo, boolean otherSideHasBeenSet) {
        this.getTodoList().remove(todo);
        if(otherSideHasBeenSet) {
            return;
        }
        todo.removeTag(this, true);
    }
}