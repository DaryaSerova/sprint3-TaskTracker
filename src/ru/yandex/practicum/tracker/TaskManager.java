package ru.yandex.practicum.tracker;

import ru.yandex.practicum.tracker.model.Epic;
import ru.yandex.practicum.tracker.model.Subtask;
import ru.yandex.practicum.tracker.model.Task;
import ru.yandex.practicum.tracker.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private Integer numberTask = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();

    private Integer getAndIncrement() {
        return numberTask++;
    }

    public Task addTask(Task task) {
        Integer id = getAndIncrement();
        task.setId(id);
        tasks.put(id, task);
        return task;

    }

    public Epic addEpic(Epic epic) {
        Integer id = getAndIncrement();
        epic.setId(id);
        epic.setStatus(getEpicStatus(epic));
        tasks.put(id, epic);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask) {
        Integer epicId = subtask.getEpicId();
        Task task = tasks.get(epicId);
        if (task == null || !(task instanceof Epic)) {
            return null;
        }

        Integer id = getAndIncrement();
        subtask.setId(id);
        tasks.put(id, subtask);

        Epic epic = (Epic) task;
        ArrayList<Integer> epicSubtaskIds = epic.getSubtaskIds();
        epicSubtaskIds.add(id);
        epic.setStatus(getEpicStatus(epic));

        return subtask;
    }

    private TaskStatus getEpicStatus(Epic epic) { // Расчет статуса Эпика
        if (epic == null || epic.getSubtaskIds() == null || epic.getSubtaskIds().isEmpty()) {
            return TaskStatus.NEW;
        }

        boolean isAllNew = true;
        boolean isAllDone = true;

        for (Integer subtaskId : epic.getSubtaskIds()) {
            Task subtask = tasks.get(subtaskId);
            TaskStatus status = subtask.getStatus();
            if (status != TaskStatus.DONE) {
                isAllDone = false;
            }
            if (status != TaskStatus.NEW) {
                isAllNew = false;
            }
        }
        if (isAllDone) {
            return TaskStatus.DONE;
        }
        if (isAllNew) {
            return TaskStatus.NEW;
        }
        return TaskStatus.IN_PROGRESS;
    }

    public Task updateTask(Task task) { /* Обновление. Новая версия объекта с верным идентификатором
                                         передаётся в виде параметра.*/
        if (task == null) {
            return null;
        }
        if (task instanceof Subtask) {
            Subtask subtask = (Subtask) task;
            tasks.put(task.getId(), task);
            Integer epicId = subtask.getEpicId();
            Epic epic = (Epic) tasks.get(epicId);
            epic.setStatus(getEpicStatus(epic));
            return subtask;
        }
        if (task instanceof Epic) {
            Epic epic = (Epic) task;
            epic.setStatus(getEpicStatus(epic));
            tasks.put(epic.getId(), epic);
            return epic;
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public ArrayList<Task> getTasks() {  // Получение списка всех задач.
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {  // Удаление всех задач.
        tasks.clear();
    }

    public void removeTask(Integer id) {  //Удаление по идентификатору.
        Task task = tasks.get(id);
        if (task == null) {
            return;
        }
        if (task instanceof Subtask) {
            Subtask subtask = (Subtask) task;
            Integer epicId = subtask.getEpicId();
            Epic epicForSubtask = (Epic) tasks.get(epicId);
            tasks.remove(id);
            epicForSubtask.setStatus(getEpicStatus(epicForSubtask));
            return;
        }
        if (!(task instanceof Epic)) {
            tasks.remove(id);
            return;
        }
        Epic epic = (Epic) task;
        for (Integer subtaskIds : epic.getSubtaskIds()) {
            tasks.remove(subtaskIds);
        }
        tasks.remove(id);
    }

    public Task getTask(Integer id) { // Получение по идентификатору.
        return tasks.get(id);
    }

    public ArrayList<Subtask> getSubtasks(Integer epicId) { //Получение списка всех подзадач определённого эпика.
        Task value = tasks.get(epicId);
        if (value == null || !(value instanceof Epic)) {
            return null;
        }
        Epic epic = (Epic) value;
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasksList.add((Subtask) tasks.get(subtaskId));
        }
        return subtasksList;
    }

    public void printTaskInfo() {
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }
}
