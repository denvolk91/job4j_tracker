package ru.job4j.tracker;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "=== Конец программы ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
