package ru.yandex.practicum.tracker;

import ru.yandex.practicum.tracker.model.Epic;
import ru.yandex.practicum.tracker.model.Subtask;
import ru.yandex.practicum.tracker.model.Task;
import ru.yandex.practicum.tracker.model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = taskManager.addTask(new Task("Покупки", "Купить хлеб"));

        Task task2 = taskManager.addTask(new Task("Чтение", "Прочитать Война и Мир"));

        Epic epic1 = taskManager.addEpic(new Epic("Переезд", "Собрать вещи"));

        Subtask subtask1 =
                taskManager.addSubtask(new Subtask(epic1.getId(), "Кухня", "Собрать посуду"));

        Subtask subtask2 =
                taskManager.addSubtask(new Subtask(epic1.getId(), "Ванная комната", "Собрать зубные щетки"));

        Epic epic2 = taskManager.addEpic(new Epic("Покупки", "Новые щетки в новую ванную комнату"));

        Subtask subtask3 =
                taskManager.addSubtask(new Subtask(epic2.getId(), "Новая ванная", "Купить новые щетки"));

        taskManager.printTaskInfo();

        Task newTask1 = new Task(task1.getId(), "Покупки", "Хлеб купил", TaskStatus.DONE);

        taskManager.updateTask(newTask1);

        Task newTask2 = new Task(task2.getId(), "Чтение", "Книга прочитана", TaskStatus.DONE);

        taskManager.updateTask(newTask2);

        Subtask newSubtask1 = new Subtask(subtask1.getId(), "Кухня", "Посуду собрал",
                TaskStatus.DONE, epic1.getId());

        taskManager.updateTask(newSubtask1);

        Subtask newSubtask2 = new Subtask(subtask2.getId(), "Ванная комната", "Куплю новые щетки",
                TaskStatus.DONE, epic1.getId());

        taskManager.updateTask(newSubtask2);

        Subtask newSubtask3 = new Subtask(subtask3.getId(), "Новая ванная", "Купил новые щетки",
                TaskStatus.DONE, epic2.getId());

        taskManager.updateTask(newSubtask3);

        taskManager.printTaskInfo();

        taskManager.removeTask(task2.getId());

        taskManager.removeTask(epic2.getId());

        taskManager.printTaskInfo();

        System.out.println(taskManager.getTasks());
    }
}
