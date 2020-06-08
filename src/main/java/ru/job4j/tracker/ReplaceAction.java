package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }
    @Override
    public String name() {
        return "=== Замена заявки в старую ячейку ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("ВВедите номер заявки которую хотите заменить: ");
        String newName = input.askStr("Ведите новое имя заявки: ");
        Item newItem = new Item(newName);
        if (tracker.replace(id, newItem)) {
            out.println("Успешная замена");
        } else {
            out.println("Ошибка! Замена имя заявки которую хотите изменить не найдено.");
        }
        return true;
    }
}
