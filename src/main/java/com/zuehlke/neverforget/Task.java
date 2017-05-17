package com.zuehlke.neverforget;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @NotNull
    @Size(min = 1)
    private String name;

    @Size(max = 300)
    private String description;

    private LocalDateTime dueDatetime;

    @NotNull
    private boolean wholeDay;

    private boolean checked;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "category_task_table",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Category category;


    protected Task() {
    }

    public Task(String name, String description, LocalDateTime dueDatetime, boolean wholeDay) {
        this.name = name;
        this.description = description;
        this.dueDatetime = dueDatetime;
        this.wholeDay = wholeDay;
        this.checked = false;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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

    public LocalDateTime getDueDatetime() {
        return dueDatetime;
    }

    public void setDueDatetime(LocalDateTime dueDatetime) {
        this.dueDatetime = dueDatetime;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDatetime=" + dueDatetime +
                ", wholeDay=" + wholeDay +
                ", checked=" + checked +
                ", category=" + category +
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
        return dueDatetime != null ? dueDatetime.equals(task.dueDatetime) : task.dueDatetime == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dueDatetime != null ? dueDatetime.hashCode() : 0);
        result = 31 * result + (wholeDay ? 1 : 0);
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }
}
