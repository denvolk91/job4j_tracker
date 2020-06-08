package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private final Output out;

    public FindByIdAction(Output out) {
        this.out = out;
    }
    @Override
    public String name() {
        return "=== Поиск заявки по номеру ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Введите номер заявки: ");
        Item rsl = tracker.findById(id);
        if (rsl == null) {
            System.out.println("Неверный номер заявки");
        } else {
            System.out.println(rsl);
        }
        return true;
    }
}
