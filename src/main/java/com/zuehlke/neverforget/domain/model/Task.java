package com.zuehlke.neverforget.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Task extends BaseEntity {

    @NotNull
    @Size(min = 1)
    private String name;

    @Size(max = 300)
    private String description;

    private LocalDate dueDate;

    private LocalTime dueTime;

    @NotNull
    private boolean wholeDay;

    @NotNull
    private boolean checked;

    @ManyToOne
    private Category category;

    @ManyToOne //XXX don't allow circular references!!!!
    private Task parent;

    @NotNull
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    private User owner;

    protected Task() {
        this.checked = false;
    }

    public Task(String name, String description, LocalDate dueDate, LocalTime dueTime, boolean wholeDay, User owner) {
        this();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.wholeDay = wholeDay;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isWholeDay() {
        return wholeDay;
    }

    public void setWholeDay(boolean wholeDay) {
        this.wholeDay = wholeDay;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", dueTime=" + dueTime +
                ", wholeDay=" + wholeDay +
                ", checked=" + checked +
                ", category=" + category +
                ", parent={" + (parent == null ? null : parent.getId() + "," + parent.getName()) + "}" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (wholeDay != task.wholeDay) return false;
        if (checked != task.checked) return false;
        if (!name.equals(task.name)) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (dueDate != null ? !dueDate.equals(task.dueDate) : task.dueDate != null) return false;
        if (dueTime != null ? !dueTime.equals(task.dueTime) : task.dueTime != null) return false;
        return category != null ? category.equals(task.category) : task.category == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (dueTime != null ? dueTime.hashCode() : 0);
        result = 31 * result + (wholeDay ? 1 : 0);
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

}
