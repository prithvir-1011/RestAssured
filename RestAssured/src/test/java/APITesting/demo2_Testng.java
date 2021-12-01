package APITesting;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class demo2_Testng {
	
	@BeforeClass
	public void BC()
	{
		System.out.println("This is my second class");
	}
	
    @BeforeMethod
    public void BM()
    {
	    System.out.println("this is before method");
    }
    
    @Test
	public void testcase()
	{
    	Assert.assertEquals("prithvi", "prithvi");
		System.out.println("This is my second testcase");
	}
    
    @AfterTest
	public void AT()
	{
		System.out.println("This will execute after all the test");
	}
	
	
	@AfterSuite
	public void AS()
	{
		System.out.println("This will execute at the end ");
	}
	

}
