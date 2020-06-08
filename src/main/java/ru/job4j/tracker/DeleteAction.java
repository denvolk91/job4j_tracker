package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }
    @Override
    public String name() {
        return "=== Удалите заявку ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String nameId = input.askStr("Введите имя заявки которую хотите удалить: ");
        if (tracker.delete(nameId)) {
            System.out.println("Успешное удаление");
        } else {
            System.out.println("Ошибка. Неверное имя заявки");
        }
        return true;
    }
}
