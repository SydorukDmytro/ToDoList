package sydoruk.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "important")
    private Boolean important;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany
    @JoinTable(name = "todo_tag",
            joinColumns = @JoinColumn(name = "TODO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"))
    private Set<Tag> tagList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        setUser(user, false);
    }

    public void setUser(User user, boolean otherSideHasBeenSet) {
        this.user = user;
        if(otherSideHasBeenSet) {
            return;
        }
        user.addTodo(this, true);
    }

    public void removeUser(User user) {
        removeUser(user, false);
    }

    public void removeUser(User user, boolean otherSideHasBeenSet) {
        this.user = null;
        if(otherSideHasBeenSet) {
            return;
        }
        user.removeTodo(this, true);
    }

    public Set<Tag> getTagList() {
        return tagList;
    }

    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean otherSideHasBeenSet) {
        this.getTagList().add(tag);
        if(otherSideHasBeenSet) {
            return;
        }
        tag.addTodo(this, true);
    }

    public void removeTag(Tag tag) {
        removeTag(tag, false);
    }

    public void removeTag(Tag tag, boolean otherSideHasBeenSet) {
        this.getTagList().remove(tag);
        if(otherSideHasBeenSet) {
            return;
        }
        tag.removeTodo(this, true);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", important=" + important +
                ", priotity=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return id.equals(todo.id) &&
                name.equals(todo.name) &&
                Objects.equals(comment, todo.comment) &&
                Objects.equals(startDate, todo.startDate) &&
                Objects.equals(endDate, todo.endDate) &&
                Objects.equals(important, todo.important) &&
                priority == todo.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comment, startDate, endDate, important, priority);
    }
}