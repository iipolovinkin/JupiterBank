package ru.blogspot.feomatr.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

/**
 * @author iipolovinkin
 */
@Ignore
public class WebIT {

    private static String appHost = "http://127.0.0.1:8081";
    private String client_name = "client_name";
    private String client_address = "client_address";
    private String client_age = "client_age";
    private String btnClass = "btn";

    private Long clientId;
    private List<String> accs;

    @BeforeClass
    public static void setUp() {
//        todo move parameters to configuration file
        Configuration.browser = "chrome";
        Configuration.baseUrl = appHost;
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
    }

    @Test
    public void testShowHome() throws Exception {
        open("/");
    }

    @Test
    @Ignore
    public void showAndFilterTransactions() {
        int count = 5;

        open("/clients");
        clientId = Long.valueOf(getIdOfLastClient());
        open("/clients/" + clientId);
        accs = getTDStrings(count);

        open("/transactions");
        ElementsCollection fcs = $$(By.className("form-control"));
        SelenideElement btn = $(By.className("btn"));

        for (int i = 0; i < count; i++) {
            fcs.get(1).setValue(accs.get(i));
            btn.pressEnter();
        }
    }

    @Test
    public void createInvalidClient() {
        open("/clients?new");

        $(By.id(client_name)).setValue("1");
        $(By.id(client_address)).setValue("1");
        $(By.id(client_age)).setValue("0");

        String title = title();

        $(By.name("commit")).pressEnter();

        assertEquals(title, title());

        sleep(3000l);
    }

    // todo Add Language Check

    @Test
    public void createClientAndFiveAccounts() {
        open("/clients?new");


        $(By.id(client_name)).setValue("Bill");
        $(By.id(client_address)).setValue("Main street, 117");
        $(By.id(client_age)).setValue("23");

        $(By.name("commit")).pressEnter();

        ElementsCollection td = $$(By.tagName("td"));
        clientId = Long.valueOf(td.get(0).getText());

        td.get(1).shouldHave(text("Bill"));
        td.get(2).shouldHave(text("Main street, 117"));
        td.get(3).shouldHave(text("23"));
        td.get(3).shouldNotHave(text("24"));
        td.get(3).shouldNotHave(text("22"));

        int count = 5;
        for (int i = 0; i < count; i++) {
            $(By.name("submit")).pressEnter();
        }

        open("/clients/" + clientId);
        accs = getTDStrings(count);

        open("/accounts?transferTo");

        ElementsCollection fcs = $$(By.className("form-control"));
        SelenideElement btn = $(By.className(btnClass));

//      todo move transferTo
        for (int i = 0; i < count; i++) {
            fcs.get(0).setValue(accs.get(i));
            fcs.get(1).setValue("200.34");
            btn.pressEnter();
        }

        open("/accounts?transfer");
        btn = $(By.className(btnClass));
//      todo move transfer
        for (int i = 0; i < 4; i++) {
            fcs.get(0).setValue(accs.get(i));
            fcs.get(1).setValue(accs.get(i + 1));
            fcs.get(2).setValue("30.74");
            btn.pressEnter();
        }

        showAndFilterTransactions();

    }

    private List<String> getTRStrings() {
        ElementsCollection tr = $$(By.tagName("tr"));
        List<String> accs = new ArrayList<>();
        for (int i = 1; i < tr.size(); i++) {
            accs.add(tr.get(i).getText());
        }
        return accs;
    }

    private List<String> getTRStrings(int count) {
        ElementsCollection tr = $$(By.tagName("tr"));
        List<String> accs = new ArrayList<>();
        for (int i = 1; i < count && tr.size() > i; i++) {
            accs.add(tr.get(i).getText());
        }
        return accs;
    }

    private String getIdOfLastClient() {
        int col = 4;
        ElementsCollection td = $$(By.tagName("td"));
        int size = td.size();
        return td.get(size - col).getText();
    }

    private List<String> getTDStrings(int count) {
        ElementsCollection td = $$(By.tagName("td"));
        List<String> accs = new ArrayList<>();
        for (int i = 1; i < count + 1 && td.size() > (i - 2); i++) {
            accs.add(td.get(td.size() - i * 2).getText());
        }
        return accs;
    }

}
