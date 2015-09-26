package ru.blogspot.feomatr.formBean;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author polovinkin.igor
 * @since 10.09.2015
 */
public class AdminClassTest {
    @Test
    public void testDefaultConstructor() throws Exception {
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
}