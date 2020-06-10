package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                "0. Create" + System.lineSeparator() +
                "1. === Конец программы ===" + System.lineSeparator() +
                "=== Create a new Item ===" + System.lineSeparator() +
                "Menu." + System.lineSeparator() +
                "0. Create" + System.lineSeparator() +
                "1. === Конец программы ===" + System.lineSeparator()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Конец программы ===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Replaced item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", item.getId(), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Замена заявки в старую ячейку ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator() +
                        "Успешная замена" + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. === Замена заявки в старую ячейку ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator()
                ));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Deleted item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        Input in = new StubInput(
                new String[] {"0", item.getId(), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Удалите заявку ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator() +
                        "Успешное удаление" + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. === Удалите заявку ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator()
                ));
    }

    @Test
    public void whenFindAllAction() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Item[] add = {
                tracker.add(new Item("name1")),
                tracker.add(new Item("name2"))
        };
        UserAction[] actions = {
                new FindAllAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker,actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Отображение всех заявок ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator() +
                        "Список на 2 заявок(ки)" + System.lineSeparator() +
                        add[0] + System.lineSeparator() +
                        add[1] + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. === Отображение всех заявок ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByIdAction() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("new item"));
        String id = item.getId();
        Input in = new StubInput(
                new String[] {"0", id, "1"}
        );
        UserAction[] actions = {
                new FindByIdAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Поиск заявки по номеру ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator() +
                        tracker.findById(id) + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. === Поиск заявки по номеру ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByNameAction() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("new item"));
        Item[] findName = tracker.findByName("new item");
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        UserAction[] actions = {
                new FindByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. === Поиск списка заявок по совпавшим именам ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator() +
                        "Список на 1 заявок(ки)" + System.lineSeparator() +
                         findName[0] + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. === Поиск списка заявок по совпавшим именам ===" + System.lineSeparator() +
                        "1. === Конец программы ===" + System.lineSeparator()
        ));
    }
}
