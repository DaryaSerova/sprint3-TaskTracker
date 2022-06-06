package ru.yandex.practicum.tracker.model;
import java.util.Objects;

public class Task {
    protected Integer id;
    protected String name;
    protected String details;
    protected TaskStatus status;

    public Task(Integer id, String name, String details, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
    }

    public Task(String name, String details) {
        this.name = name;
        this.details = details;
        status = TaskStatus.NEW;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (id != null) {
            hash = hash + id.hashCode();
        }
        hash = hash * 31;
        return hash;
    }

    @Override
    public String toString() {
        return "Номер задачи: " + id + " Название: " + name + '\n' +
                "Описание: " + details + '\n' +
                "Статус: " + status;
    }

}
