package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAll() throws DAOException;

    List<Account> getAll(int pageNumber, int pageSize) throws DAOException;

    Account getById(Long id) throws DAOException;

    Account getByNo(String accountNo) throws DAOException;

    Account create(Account account) throws DAOException;

    void delete(Account account) throws DAOException;

    void update(Account account) throws DAOException;
}
