package ru.blogspot.feomatr.formBean;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author polovinkin.igor
 * @since 10.09.2015
 */
public class AdminClassTest {
    @Test
    public void testName() throws Exception {
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
    }
}