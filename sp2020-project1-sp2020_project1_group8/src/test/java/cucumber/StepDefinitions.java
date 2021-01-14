package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";

	public static final WebDriver driver = new ChromeDriver();

	@Given("I am on the Activity Planning page")
	public void i_am_on_the_ap_page() {
		PageNavigationStepDefinitions nav = new PageNavigationStepDefinitions();
		nav.when_i_am_on_the_title_page("Activity Planning");
	}

	@When("I type {string} in activity planning input box")
	public void i_click_the_link() {
		driver.findElement(By.id("act")).getText();
	}

	@Then("I should see dropdown option {string}")
	public void i_should_see_header(String string) {
		assertTrue(driver.findElement(By.cssSelector("h2")).getText().contains(string));
	}

	@After()
	public void after() {
		// driver.quit();
	}
}
