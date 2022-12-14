package sydoruk.domain.plainObjects;

import java.util.HashSet;
import java.util.Set;

public class TagPojo {

    private Long id;
    private String name;
    private Set<Long> todoListIds = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getTodoListIds() {
        return todoListIds;
    }

    public void setTodoListIds(Set<Long> todoListIds) {
        this.todoListIds = todoListIds;
    }
}

