package cucumber;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UtilStepDefinitions {
	
	@When("I wait {int} milliseconds")
	public void i_wait_time_milliseconds(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
