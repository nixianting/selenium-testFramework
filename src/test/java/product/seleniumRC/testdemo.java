package product.seleniumRC;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Base;


public class testdemo extends Base{

	@Test
	public void testDemo() throws Exception {
		selenium.open("/login.jsp");
		Thread.sleep(1000);
	}
}
