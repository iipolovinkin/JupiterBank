/**
 *
 */
package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientDAO {
    /**
     * Возвращает все объекты
     *
     * @return
     */
    List<Client> getAll();

    /**
     * Возвращает объект по id
     *
     * @param id
     * @return
     */
    Client getById(Long id);

    /**
     * Создает объект
     *
     * @param client
     * @return
     */
    Client create(Client client);

    /**
     * Удаляет объект
     *
     * @param client
     * @return
     */
    boolean delete(Client client);

    /**
     * Обновляет объект
     *
     * @param client
     * @return
     */
    int update(Client client);

}
