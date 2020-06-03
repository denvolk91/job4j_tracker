package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Удалите заявку ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String nameId = input.askStr("Введите имя заявки которую хотите удалить: ");
        if (tracker.delete(nameId)) {
            System.out.print("Успешное удаление");
        } else {
            System.out.print("Ошибка. Неверное имя заявки");
        }
        return true;
    }
}
