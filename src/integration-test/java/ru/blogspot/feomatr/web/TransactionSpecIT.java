package ru.blogspot.feomatr.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test clients\client at menu item "Transactions"
 * see <a href=https://github.com/codeborne/selenide/wiki/Snippets>Selenide snippets</a>
 *
 * @author iipolovinkin
 */
public class TransactionSpecIT {

	private static String appHost = "http://127.0.0.1:8081";
	private static TransactionPageObject page;

	private String senderAccountNo = "40817810061010000010";
	private String receiverAccountNo = "40817810061010000009";

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

		page = open("/transactions?lang=ru_RU", TransactionPageObject.class);
	}

	@AfterAll
	public static void tearDownClass() throws Exception {
		close();
	}

	@Test
	public void shouldFilterWhenFilterPressed() throws Exception {
		page.setReceiverNo(receiverAccountNo);
		page.setSenderNo(senderAccountNo);
		page.setToday();

		$("#filter").pressEnter();

		page.getStartTime().shouldNotBe(empty);
	}

	@Test
	public void shouldShowLessThan10Transaction() {
		int size = page.getTransactions().size();

		assertTrue(size < 11);
	}

	@Test
	public void shouldShowEmptyTransactionsWhenFilterNotExistenceData() throws Exception {
		String invalidAccNo = "invalidAccNo";
		page.setReceiverNo(invalidAccNo);
		page.setSenderNo(invalidAccNo);
		page.setToday();

		$("#filter").pressEnter();

		page.getTransactions().shouldHaveSize(0);
	}

	@Test
	public void shouldReturnEmptyTransactionWhenButtonClearPressed() throws Exception {
		page.setReceiverNo(receiverAccountNo);
		page.setSenderNo(senderAccountNo);
		page.setToday();

		$("#clear").pressEnter();

		page.senderNoShouldBeEmpty();
		page.receiverNoShouldBeEmpty();
		page.startTimeShouldBeEmpty();
		page.endTimeShouldBeEmpty();

	}

	@Test
	public void shouldClearFieldWhenButtonClearPressed() throws Exception {
		page.setReceiverNo(receiverAccountNo);
		page.setSenderNo(senderAccountNo);
		page.setToday();

		$("#clear").pressEnter();

		page.senderNoShouldBeEmpty();
		page.receiverNoShouldBeEmpty();
		page.startTimeShouldBeEmpty();
		page.endTimeShouldBeEmpty();

	}

	public static class TransactionPageObject {
		public void setReceiverNo(String receiverNo) {
			$(By.id("receiverAccountNo")).val(receiverNo);
		}

		public void setSenderNo(String senderNo) {
			$(By.id("senderAccountNo")).val(senderNo);
		}

		public void setStartTime(String time) {
			$(By.id("startTime")).val(time);
		}

		public SelenideElement getStartTime() {
			return $(By.id("startTime"));
		}

		public void setEndTime(String time) {
			$(By.id("endTime")).val(time);
		}

		public void receiverNoShouldBeEmpty() {
			$(By.id("receiverAccountNo")).shouldBe(empty);
		}

		public void senderNoShouldBeEmpty() {
			$(By.id("senderAccountNo")).shouldBe(empty);
		}

		public void startTimeShouldBeEmpty() {
			$(By.id("startTime")).shouldBe(empty);
		}

		public void endTimeShouldBeEmpty() {
			$(By.id("endTime")).shouldBe(empty);
		}

		public void setToday() {
			$(By.id("startTime")).click();
			$(By.className("today")).click();
			$(By.id("endTime")).click();
			$(By.className("today")).click();
		}

		public ElementsCollection getTransactions() {
			return $$("#tableTransactions tr");
		}
	}
}
