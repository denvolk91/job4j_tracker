package ru.job4j.tracker;
import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new ConsoleOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
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
        Output out = new ConsoleOutput();
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
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new ConsoleOutput();
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
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindAllAction() {
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "new item1", "0", "new item2", "1", "2"}
        );
        UserAction[] actions = {
                new CreateAction(out),
                new FindAllAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker,actions);
        assertThat(tracker.findAll()[0].getName(), is("new item1"));
    }

    @Test
    public void whenFindByIdAction() {
        Output out = new ConsoleOutput();
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
        assertThat(tracker.findById(id).getId(), is(id));
    }

    @Test
    public void whenFindByNameAction() {
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("new item"));
        String name = item.getName();
        Input in = new StubInput(
                new String[] {"0", name, "1"}
        );
        UserAction[] actions = {
                new FindByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findByName(name)[0].getName(), is(name));
    }
}
