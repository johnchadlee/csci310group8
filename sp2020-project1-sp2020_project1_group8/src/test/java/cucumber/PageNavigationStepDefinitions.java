package cucumber;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class PageNavigationStepDefinitions {
	private final String baseUrl = "http://localhost:8080";
	private final Map<String, String> pageUrls;
	public PageNavigationStepDefinitions() {
		pageUrls = new HashMap<String, String>();
		pageUrls.put("Search", "/mainSearch.html");
		pageUrls.put("Vacation Planning", "/vacationPlanning.html");
		pageUrls.put("Activity Planning", "/ActivityPlanning.html");
		pageUrls.put("Analysis", "/analysis.html");
	}
	
	@Given("I access the main domain")
	public void i_access_the_main_domain() {
		StepDefinitions.driver.get(baseUrl);
	}
	
	@Given("I am on the {string} page")
	public void when_i_am_on_the_title_page(String title) {
		StepDefinitions.driver.get(baseUrl + pageUrls.get(title));
	}
	
	@Then("I link to the {string} page")
	public void then_i_am_on_the_title_page(String title) {
		assertEquals(StepDefinitions.driver.getCurrentUrl(), baseUrl + pageUrls.get(title));
	}
}
