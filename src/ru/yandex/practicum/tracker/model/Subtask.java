package ru.yandex.practicum.tracker.model;

public class Subtask extends Task {

    protected Integer epicId;

    public Subtask(Integer id, String name, String details, TaskStatus status, Integer epicId) {
        super(id, name, details, status);
        this.epicId = epicId;
    }

    public Subtask(String name, String details, Integer epicId) {
        super(name, details);
        this.epicId = epicId;
    }

    public Subtask(Integer epicId, String name, String details) {
        super(name, details);
        this.epicId = epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Номер подзадачи: " + id + " Название: " + name + '\n' +
                "Описание: " + details + '\n' +
                "Статус: " + status + '\n' +
                "Номер эпика: " + epicId;
    }
}
