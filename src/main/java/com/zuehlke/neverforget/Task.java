package com.zuehlke.neverforget;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by urzy on 15.05.2017.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    private String name;
    private String description;
    private LocalDateTime dueDatetime;
    private boolean wholeDay;
    private boolean checked;

    protected  Task() {}

    public Task(String name, String description, LocalDateTime dueDatetime, boolean wholeDay) {
        this.name = name;
        this.description = description;
        this.dueDatetime = dueDatetime;
        this.wholeDay = wholeDay;
        this.checked = false;
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

}
