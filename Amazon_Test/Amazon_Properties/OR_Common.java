package Amazon_Properties;

import org.openqa.selenium.By;

public class OR_Common 
	{
	//Amazon Search Box
	public static By txtAmazonSearchBox = By.id("twotabsearchtextbox");
	
	public static By btnSearch = By.xpath("(.//*[@type='submit'])[1]");
	
	public static By lnkFirstProduct = By.xpath("(.//h2[@data-attribute])[1]");
	
	public static By weProductPageNavigationContainer = By.id("leftNavContainer");
	
	public static By btnBuyNow = By.id("buy-now-button");
	
	public static By weLoginSection = By.xpath(".//*[@name='signIn']");
	}
