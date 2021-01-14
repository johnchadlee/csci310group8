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

public class DomStepDefinitions {
	private final Map<String, String> idMap;
	
	public DomStepDefinitions() {
		idMap = IdMap.getGlobalIdMap();
	}
	
	@Then("The {string} has focus")
	public void the_name_has_focus(String name) {
		String id = idMap.get(name);
		WebElement element = StepDefinitions.driver.findElement(By.id(id));
		assertEquals(element, StepDefinitions.driver.switchTo().activeElement());
	}
	
	@Then("The {string} {string} is {string}")
	public void the_name_attribute_is_value(String name, String attribute, String value) {
		String id = idMap.get(name);
		WebElement element = StepDefinitions.driver.findElement(By.id(id));
		assertEquals(element.getAttribute(attribute), value);
	}
}
