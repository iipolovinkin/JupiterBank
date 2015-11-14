package ru.blogspot.feomatr.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.formBean.Paginator;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * see <a href=https://github.com/codeborne/selenide/wiki/Snippets>Selenide snippets</a>
 *
 * @author iipolovinkin
 */
@Ignore
public class WebIT {

	/*
	create own UI test for each page\class


##clients
create client +
try create invalid client +

add accounts to new client +

add ROWS_COUNT_PER_PAGE+2 accounts to new client + | pagination doesnot work || delete . client with a lot of account shoud show all of them. without pagination.
!!fix Accounts for client pagination!!

create ROWS_COUNT_PER_PAGE+2  clients, "check pagination" + || delete. we should check pagination.


## language
check rus\eng

## accounts
check

## transfer
transfer to
transfer from
transfer

invalid transfers

## transactions

	 */
	private static String appHost = "http://127.0.0.1:8081";
	private String client_name = "client_name";
	private String client_address = "client_address";
	private String client_age = "client_age";
	private String btnClass = "btn";

	private Long clientId;
	private Client validClient = new Client("Bill", "Main street, 117", 23);
	private List<String> accs;

	@BeforeClass
	public static void setUpClass() {
//		todo move parameters to configuration file
		Configuration.browser = "chrome";
		Configuration.baseUrl = appHost;
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

		open("/login");
		$(By.id("username")).setValue("admin");
		$(By.id("password")).setValue("pass");
		$(By.id("loginButton")).pressEnter();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		close();
	}

	@Test
	public void testShowHome() throws Exception {
		open("/");
	}

	@Test
	public void shouldCreateClient() throws Exception {
		open("/clients?new");
		$(By.id(client_name)).setValue("Bill");
		$(By.id(client_address)).setValue("Main street, 117");
		$(By.id(client_age)).setValue("23");

		$("#submit").pressEnter();

		ElementsCollection td = $$(By.tagName("td"));
		td.get(1).shouldHave(text("Bill"));
		td.get(2).shouldHave(text("Main street, 117"));
		td.get(3).shouldHave(text("23"));
//	    clientId = Long.valueOf(td.get(0).getText());
	}

	@Test
	public void shouldShowErrorsWhenCreateInvalidClient() {
		open("/clients?new");
		String title = title();
		assertFalse($(By.id("firstname.errors")).isDisplayed());
		assertFalse($(By.id("address.errors")).isDisplayed());
		assertFalse($(By.id("age.errors")).isDisplayed());
		$(By.id(client_name)).val("1");
		$(By.id(client_address)).val("1");
		$(By.id(client_age)).val("0");

		$("#submit").pressEnter();

		assertEquals(title, title());
		assertTrue($(By.id("firstname.errors")).isDisplayed());
		assertTrue($(By.id("address.errors")).isDisplayed());
		assertTrue($(By.id("age.errors")).isDisplayed());
	}

	@Test
	public void shouldCreateAccounts() throws Exception {
		Long id = createClient(validClient);
		System.out.println("id = " + id);
		open("/clients/" + id);
		int count = 5;
		for (int i = 0; i < count; i++) {
			$("#submit").pressEnter();
			sleep(100l);
		}

		int actualSize = $$("#tableAccounts tr").size();

		assertThat(actualSize, is(count + 1));
	}

	@Test
	public void shouldCreateALotOfAccountsWithoutPagination() throws Exception {
		Long id = createClient(validClient);
		open("/clients/" + id);
		int count = Paginator.ROWS_COUNT_PER_PAGE + 2;
		for (int i = 0; i < count; i++) {
			$("#submit").pressEnter();
			sleep(100l);
		}

		int actualSize = $$("#tableAccounts tr").size();

		assertThat(actualSize, is(count + 1));
	}

	@Test
	public void shouldCreateALotOfClientsWithPagination() throws Exception {
		int count = Paginator.ROWS_COUNT_PER_PAGE + 2;
		for (int i = 0; i < count; i++) {
			createClient(validClient);
		}

		open("/clients/");
		int actualSize = $$("#tableClients tr").size();

		assertThat(actualSize, is(Paginator.ROWS_COUNT_PER_PAGE + 1));
	}

	// todo Add Language Check

	@Ignore
	@Test
	public void showAndFilterTransactions() throws Exception {
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

	@Ignore
	@Test
	public void shouldDoTransfer() throws Exception {
		Long id = createClient("Bill", "Main street, 117", "23");
		int count = 5;
		open("/clients/" + id);
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
			String str = td.get(td.size() - i * 2).getText();
			accs.add(str);
		}
		return accs;
	}

	private Long createClient(Client client) {
		return createClient(client.getFirstname(), client.getAddress(), client.getAge().toString());
	}

	private Long createClient(String firstname, String address, String age) {
		open("/clients?new");
		$(By.id(client_name)).val(firstname);
		$(By.id(client_address)).val(address);
		$(By.id(client_age)).val(age);

		$("#submit").pressEnter();

		sleep(200l);
		ElementsCollection td = $$(By.tagName("td"));
		Long clientId = Long.valueOf(td.get(0).getText());

		return clientId;
	}

}
