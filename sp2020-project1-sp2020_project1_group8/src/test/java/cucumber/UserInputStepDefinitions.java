package cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserInputStepDefinitions {
	private final Map<String, String> idMap;
	
	public UserInputStepDefinitions() {
		idMap = IdMap.getGlobalIdMap();
	}
	
	@When("I input {string} with {string}")
	public void then_i_am_on_the_title_page(String input, String value) {
		String id = idMap.get(input);
		WebElement element = StepDefinitionsDropdown.driver.findElement(By.id(id));
		element.clear();
		element.sendKeys(value);
	}
	
	@When("I click {string}")
	public void i_click_element(String element) {
		String id = idMap.get(element);
		StepDefinitionsDropdown.driver.findElement(By.id(id)).click();
	}
	
	@Then("The element {string} exists")
	public void the_element_name_exists(String name) {
		String id = idMap.get(name);
		assertNotNull(StepDefinitionsDropdown.driver.findElement(By.id(id)));
	}
}
