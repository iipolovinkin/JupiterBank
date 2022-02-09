package ru.blogspot.feomatr.formBean;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author polovinkin.igor
 * @since 10.09.2015
 */
class AdminClassTest {
    @Test
    void testDefaultConstructor() throws Exception {
        AdminClass adminClass = new AdminClass();
        assertNotNull(adminClass.getAccountsCount());
        assertNotNull(adminClass.getClientsCount());
        assertNotNull(adminClass.getThreadsCount());
        assertNotNull(adminClass.getTransfersCount());
        assertNotNull(adminClass.getAttr05());
        assertNotNull(adminClass.getAttr06());
        assertNotNull(adminClass.getAttr07());
        assertNotNull(adminClass.getAttr08());
        assertNotNull(adminClass.getAttr09());
        assertNotNull(adminClass.getAttr10());

        assertThat(adminClass.toString(),
                is("AdminClass(clientsCount=0, accountsCount=0, transfersCount=0, " +
                        "threadsCount=0, attr05=, attr06=, attr07=, attr08=, attr09=, attr10=)"));
    }

    @Test
    void testCreateAdminFromCATT() throws Exception {
        int clientsCount = 1;
        int accountsCount = 2;
        int transfersCount = 3;
        int threadsCount = 4;

        AdminClass adminFromCATT = AdminClass.createAdminFromCATT(clientsCount, accountsCount, transfersCount, threadsCount);

        assertThat(adminFromCATT.getClientsCount(), is(clientsCount));
        assertThat(adminFromCATT.getAccountsCount(), is(accountsCount));
        assertThat(adminFromCATT.getTransfersCount(), is(transfersCount));
        assertThat(adminFromCATT.getThreadsCount(), is(threadsCount));
    }
}