package csci310;

import csci310.Hello;
import org.junit.Assert;
import org.junit.Test;

public class HelloTest {

	@Test
	public void testGreeting() {
		Hello h = new Hello();
		Assert.assertEquals(h.greet("test"), "Hello test");
	}

}
