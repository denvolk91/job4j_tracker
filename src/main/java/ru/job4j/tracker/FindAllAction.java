package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    private final Output out;

    public FindAllAction(Output out) {
        this.out = out;
    }
    @Override
    public String name() {
        return "=== Отображение всех заявок ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] item = tracker.findAll();
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
