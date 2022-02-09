package ru.blogspot.feomatr.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author iipolovinkin
 * @since 22.09.2015
 */
public class XmlClientViewTest {

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    void testRenderMergedOutputModel() throws Exception {
        XmlClientView xmlClientView = new XmlClientView();
        Map<String, Object> map = new HashMap<>();
        Client client = new Client(1L, "Jim", "RnD", 19);
        Set<Account> accounts = new HashSet<>();
        accounts.add(new Account(client, 10L));
        accounts.add(new Account(client, 20L));
        client.setAccounts(accounts);
        map.put("data", client);
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        xmlClientView.renderMergedOutputModel(map, request, response);

        assertThat(response.getContentType(), is("binary/xml; charset=ISO-8859-1"));
    }
}