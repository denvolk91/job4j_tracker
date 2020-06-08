package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }
    @Override
    public String name() {
        return "=== Поиск списка заявок по совпавшим именам ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Введите имя для получения списка: ");
        Item[] item = tracker.findByName(name);
        if (item.length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("Список на " + item.length + " заявок(ки)");
            for (int i = 0; i < item.length; i++) {
                System.out.println(item[i]);
            }
        }
        return true;
    }
}
