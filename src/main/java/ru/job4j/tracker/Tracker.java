package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    /**
     * Метод добавления заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
    /**
     * Метод возвращает копию массива items без null эллеиентов.
     * @return itemsWithoutNull массив без null
     */

    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод проверяет в цикле все эллементы массива item, сравнивая name с аргументом key.
     * Элементы, у которых совпадает name, копирует в результативный массив и возвращает в его.
     * @param key получение списка по имени.
     * @return массив совпавших имён.
     */
    public Item[] findByName(String key) {
        Item[] itemsWithoutEquals = new Item[position];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                itemsWithoutEquals[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(itemsWithoutEquals, size);
    }

    /**
     * Метод проверяет в цикле все эллементы массива items, сравнивая id с аргументом String id
     * и возвращает найденный Item. Если не найдет, то null.
     * @param id получение заявки.
     * @return найденное совпадение или null.
     */
    public Item findById(String id) {
        // Находим индекс
        int index = indexOf(id);
        // Если индекс найден возвращаем item, иначе null
        return index != -1 ? items[index] : null;
    }
    /**
     * Метод возвращает index по id.
     * @param id запрос ячейки
     * @return индекса
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
    /**
     * Метод для замены заявки, т.е. удалить заявку,
     * которая уже есть всистеме и добавить в эту ячейку новую.
     * @param id номер заяви.
     * @param item отредактированная заявка.
     * @return возврат отредактированной заявки.
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        boolean result = false;
        if (index != -1) {
            result = true;
            item.setId(id);
            items[index] = item;
        }
        return result;
    }

    /**
     * Метод удаляет ячейку по id и сжимает массив.
     * @param id найденная ячейка.
     * @return массив с удалённой ячейкой.
     */
    public boolean delete(String id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, position - index);
            items[position - 1] = null;
            position--;
            rsl = true;
        }
        return rsl;
    }
}
