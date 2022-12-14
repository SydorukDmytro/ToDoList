package sydoruk.utils;

import org.springframework.stereotype.Component;
import sydoruk.domain.Tag;
import sydoruk.domain.Todo;
import sydoruk.domain.User;
import sydoruk.domain.plainObjects.TagPojo;
import sydoruk.domain.plainObjects.TodoPojo;
import sydoruk.domain.plainObjects.UserPojo;

import java.util.stream.Collectors;

@Component
public class Converter {

    public UserPojo userToPojo(User source) {
        UserPojo result = new UserPojo();

        result.setId(source.getId());
        result.setEmail(source.getEmail());
        result.setPassword(source.getPassword());

        result.setTodoList(source.getTodoList().stream().map(todo -> todoToPojo(todo)).collect(Collectors.toSet()));

        return result;
    }

    public TodoPojo todoToPojo(Todo source) {

        TodoPojo result = new TodoPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setComment(source.getComment());
        result.setStartDate(source.getStartDate());
        result.setEndDate(source.getEndDate());
        result.setImportant(source.getImportant());
        result.setPriority(source.getPriority());
        result.setUserId(source.getUser().getId());

        result.setTagList(source.getTagList().stream().map(tag -> tagToPojo(tag)).collect(Collectors.toSet()));

        return result;
    }

    public TagPojo tagToPojo (Tag source) {
        TagPojo result = new TagPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setTodoListIds(source.getTodoList().stream().map(todo -> todo.getId()).collect(Collectors.toSet()));

        return result;
    }

}
