## Это репозиторий проекта "Трекер задач"
### Это только часть бэкенда трекера
  
**В данном коде реализовано:**  
1. Создание задачи, подзачи, эпика.  
2. Получение списка всех задач.
3. Обновление задачи, подзачи, эпика.
4. Удаление всех задач. 
5. Получение по идентификатору.
6. Удаление по идентификатору.
7. Получение списка всех подзадач определённого эпика.
8. Обновление статуса выполнения.

Функций реализованы в классе TaskManager.
Класс Task - родитель. Subtask и Epic - наследники.
Такая структура дает возможность не только изменять свойство 
сразу у всех видов задач, но и отдельно у каждой. 

Задачи всех типов хранятся в HashMap tasks. Это удобно для поиска элемента.

Тесты кода реализованы в классе Main.

TaskStatus хранит в себе статусы для задач.

------