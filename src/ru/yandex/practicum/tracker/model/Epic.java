package ru.yandex.practicum.tracker.model;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> subtaskIds;

    public Epic(String name, String details) {
        super(name, details);
        this.subtaskIds = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    @Override
    public String toString() {
        return "Номер эпика: " + id + " Название: " + name + '\n' +
                "Описание: " + details + '\n' +
                "Статус: " + status + '\n' +
                "Количество подзадач: " + subtaskIds.size();
    }
}
