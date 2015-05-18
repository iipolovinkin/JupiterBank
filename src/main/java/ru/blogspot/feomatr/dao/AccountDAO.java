package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAll();

    Account getAccountById(Long id);

    Account create(Account account);

    boolean delete(Account account);

    void update(Account account);

}
