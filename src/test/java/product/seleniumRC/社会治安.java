package product.seleniumRC;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Base;


public class 社会治安 extends Base{

	@Test
	public void test社会治安() throws Exception {
		selenium.open("/login.jsp");
		Thread.sleep(1000);
	}
}
