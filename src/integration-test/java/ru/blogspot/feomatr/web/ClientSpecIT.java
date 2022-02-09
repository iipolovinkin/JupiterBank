package ru.blogspot.feomatr.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.blogspot.feomatr.entity.Client;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Test clients\client at menu item "Clients"
 * see <a href=https://github.com/codeborne/selenide/wiki/Snippets>Selenide snippets</a>
 *
 * @author iipolovinkin
 */
public class ClientSpecIT {

	private static String appHost = "http://127.0.0.1:8081";

	private String client_name = "client_name";
	private String client_address = "client_address";
	private String client_age = "client_age";
	private Client validClient = new Client("Bill", "Main street, 117", 23);

	@BeforeAll
	public static void setUpClass() {
//		todo move parameters to configuration file
		Configuration.browser = "chrome";
		Configuration.baseUrl = appHost;
		Configuration.fastSetValue = true;
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

		open("/login");
		$(By.id("username")).setValue("admin");
		$(By.id("password")).setValue("pass");
		$(By.id("loginButton")).pressEnter();
	}

	@AfterAll
	public static void tearDownClass() throws Exception {
		close();
	}

	@Test
	void shouldCreateClientWithoutAccounts() throws Exception {
		open("/clients?new");

		$(By.id(client_name)).setValue("Bill");
		$(By.id(client_address)).setValue("Main street, 117");
		$(By.id(client_age)).setValue("23");
		$("#submit").pressEnter();

		$(By.id("firstname.errors")).shouldNotBe(visible);
		$(By.id("address.errors")).shouldNotBe(visible);
		$(By.id("age.errors")).shouldNotBe(visible);
		$$("#tableAccounts tr").shouldBe(empty);
//	    clientId = Long.valueOf(td.get(0).getText());
	}

	@Test
	void shouldShowErrorsWhenCreateInvalidClient() {
		open("/clients?new");
		$(By.id("firstname.errors")).shouldNotBe(visible);
		$(By.id("address.errors")).shouldNotBe(visible);
		$(By.id("age.errors")).shouldNotBe(visible);

		$(By.id(client_name)).val("1");
		$(By.id(client_address)).val("1");
		$(By.id(client_age)).val("0");
		$("#submit").pressEnter();

		$(By.id("firstname.errors")).shouldBe(visible);
		$(By.id("address.errors")).shouldBe(visible);
		$(By.id("age.errors")).shouldBe(visible);
	}

	@Test
	void shouldShowAccountsHeadAndCreatedAccount() throws Exception {
		Long id = createClient(validClient);
		open("/clients/" + id);
		int count = $$("#tableAccounts tr").size();

		$("#submit").pressEnter();

		$$("#tableAccounts tr").shouldHaveSize(count + 1 + 1);
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

		sleep(100l);
		ElementsCollection td = $$(By.tagName("td"));

		Long clientId = Long.valueOf(td.get(0).getText());

		return clientId;
	}

}
