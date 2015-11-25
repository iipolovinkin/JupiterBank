package ru.blogspot.feomatr.web;

import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.blogspot.feomatr.entity.Client;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

/**
 * Test clients\client at menu item "Accounts"
 * see <a href=https://github.com/codeborne/selenide/wiki/Snippets>Selenide snippets</a>
 *
 * @author iipolovinkin
 */
public class AccountSpecIT {

	private static String appHost = "http://127.0.0.1:8081";

	private String senderAccountNo = "40817810061010000010";
	private String receiverAccountNo = "40817810061010000009";
	private String amount = "10";
	private Client validClient = new Client("Bill", "Main street, 117", 23);

	@BeforeClass
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
		open("/accounts?lang=ru_RU");
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		close();
	}

	@Test
	public void shouldShowSuccessMessageWhenTransferFromSenderToReceiver() throws Exception {
		AccountPageObject page = open("/accounts?transfer", AccountPageObject.class);

		page.setSenderNo(senderAccountNo);
		page.setReceiverNo(receiverAccountNo);
		page.setAmount(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeSuccessfull();
	}

	@Test
	public void shouldShowDangerMessageWhenTransferFromSenderToReceiver() throws Exception {
		AccountPageObject page = open("/accounts?transfer", AccountPageObject.class);
		String senderAccountNo = "invalidAccountNo";
		String receiverAccountNo = "invalidAccountNo";

		page.setSenderNo(senderAccountNo);
		page.setReceiverNo(receiverAccountNo);
		page.setAmount(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeFailed();
	}

	@Test
	public void shouldShowSuccessMessageWhenTransferFromSender() throws Exception {
		AccountPageObject page = open("/accounts?transferFrom", AccountPageObject.class);

		page.setSenderNo(senderAccountNo);
		page.setAmount(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeSuccessfull();
	}

	@Test
	public void shouldShowDangerMessageWhenTransferFromSender() throws Exception {
		AccountPageObject page = open("/accounts?transferFrom", AccountPageObject.class);
		String senderAccountNo = "invalidAccountNo";

		$(By.id("senderAccountNo")).val(senderAccountNo);
		$(By.id("amount")).val(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeFailed();
	}

	@Test
	public void shouldShowSuccessMessageWhenTransferToReceiver() throws Exception {
		AccountPageObject page = open("/accounts?transferTo", AccountPageObject.class);

		page.setReceiverNo(receiverAccountNo);
		page.setAmount(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeSuccessfull();
	}

	@Test
	public void shouldShowDangerMessageWhenTransferToReceiver() throws Exception {
		AccountPageObject page = open("/accounts?transferTo", AccountPageObject.class);
		String receiverAccountNo = "invalidAccountNo";

		page.setReceiverNo(receiverAccountNo);
		page.setAmount(amount);
		$("#submit").pressEnter();

		page.messageShouldBeVisible();
		page.messageShouldBeFailed();
	}

	@Test
	public void shouldShowAccount() throws Exception {
		Long id = 1L;

		open("/accounts/" + id);

		$(By.id("id")).attr("readonly").equals("true");
		$(By.id("accountNo")).attr("readonly").equals("true");
		$(By.id("state")).attr("readonly").equals("true");
		$(By.id("balance")).attr("readonly").equals("true");
		$(By.id("save")).attr("disabled").equals("true");
	}

	public static class AccountPageObject {
		public void messageShouldBeSuccessfull() {
			$(By.id("message")).$("span").shouldBe(text("Денежный перевод прошел"));
		}

		public void messageShouldBeFailed() {
			$(By.id("message")).$("span").shouldBe(text("Денежный перевод не прошел"));
		}

		public void messageShouldBeVisible() {
			$(By.id("message")).shouldBe(visible);
		}

		public void setReceiverNo(String receiverNo) {
			$(By.id("receiverAccountNo")).val(receiverNo);
		}

		public void setSenderNo(String senderNo) {
			$(By.id("senderAccountNo")).val(senderNo);
		}

		public void setAmount(String amount) {
			$(By.id("amount")).val(amount);
		}
	}
}
