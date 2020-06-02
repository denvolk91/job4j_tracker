package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void findAllItem(Input input, Tracker tracker) {
        System.out.println("=== Отображение всех заявок ===");
        Item[] item = tracker.findAll();
        if (item.length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("Список на " + item.length + " заявок(ки)");
            for (int i = 0; i < item.length; i++) {
                System.out.println(item[i]);
            }
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Замена заявки в старую ячейку ===");
        String id = input.askStr("ВВедите номер заявки которую хотите заменить: ");
        String newName = input.askStr("Ведите новое имя заявки: ");
        Item newItem = new Item(newName);
        if (tracker.replace(id, newItem)) {
            System.out.println("Успешная замена");
        } else {
            System.out.println("Ошибка! Замена имя заявки которую хотите изменить не найдено.");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Удалите заявку ===");
        String nameId = input.askStr("Введите имя заявки которую хотите удалить: ");
        if (tracker.delete(nameId)) {
            System.out.print("Успешное удаление");
        } else {
            System.out.print("Ошибка. Неверное имя заявки");
        }
    }

    public static void findByIdItem(Input input, Tracker tracker) {
        System.out.println("=== Поиск заявки по номеру ===");
        String id = input.askStr("Введите номер заявки: ");
        Item rsl = tracker.findById(id);
        if (rsl == null) {
            System.out.println("Неверный номер заявки");
        } else {
            System.out.println(rsl);
        }
    }

    public static void findByNameItem(Input input, Tracker tracker) {
        System.out.println("=== Поиск списка заявок по совпавшим именам ===");
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
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.findAllItem(input, tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findByIdItem(input, tracker);
            } else if (select == 5) {
                StartUI.findByNameItem(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit item.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by Id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit Program.");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
